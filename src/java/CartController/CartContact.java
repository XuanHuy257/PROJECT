/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CartController;

import Dao.CartDAO;
import Model.CartItem;
import Model.CartItemMore;
import Model.Customer;
import Model.ProductQty;
import Model.Receiver;
import Verify.SendOrder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vnpay.common.Config;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author Anh Tuan
 */
public class CartContact extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartContact</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartContact at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("account");
        if (cus == null) {
            response.sendRedirect("login");
        } else {
            int cusid = cus.getCustomerID();
            CartDAO d = new CartDAO();
            Receiver currentreceiver = d.getCurrentReceiver(cusid);
            int cartid = d.getCartId(cusid);
            int TotalItems = d.getTotalItem(cartid);
            if (TotalItems == 0) {
                response.sendRedirect("cart");
                return;
            }

            List<CartItemMore> listitems = d.getCartItems(cartid);

            double totalraw = calculateTotal(listitems);
            int totalprice = (int) totalraw;

            request.setAttribute("totalprice", totalprice);
            request.setAttribute("listitems", listitems);
            request.setAttribute("totalitems", TotalItems);
            request.setAttribute("now", currentreceiver);
            request.getRequestDispatcher("CartContact.jsp").forward(request, response);
        }
    }

    private double calculateTotal(List<CartItemMore> cartItems) {
        double total = 0;
        if (cartItems != null) {
            for (CartItem item : cartItems) {
                total += item.getPrice() * item.getQuantity();
            }
        }
        return total;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("account");
        int cusid = cus.getCustomerID();
        CartDAO d = new CartDAO();

        int cartid = d.getCartId(cusid);
        List<CartItemMore> listitems = d.getCartItems(cartid);

        boolean isStockAvailable = true;
        boolean notchangeprice = true;

        for (CartItem c : listitems) {
            int stock = d.getStock(c.getProductID(), c.getSizeID());
            int hold = d.getHold(c.getProductID(), c.getSizeID());
            if (c.getQuantity() + hold > stock) {
                isStockAvailable = false;
                break;
            }
        }

        double epsilon = 0.01;
        for (CartItem c : listitems) {
            double cartprice = d.getFinalPrice(c.getProductID(), c.getSizeID());
            if (Math.abs(cartprice - c.getPrice()) > epsilon) {
                notchangeprice = false;
                break;
            }
        }

        if (isStockAvailable && notchangeprice) {
            String bankCode = request.getParameter("bankCode");
            if (bankCode.equalsIgnoreCase("COD")) {
                CODOrder(request, response);
            } else {
                ONLOrder(request, response);
            }
        } else {
            com.google.gson.JsonObject job = new JsonObject();
            job.addProperty("code", "01");
            job.addProperty("message", "success");
            job.addProperty("data", "http://localhost:9999/SWP_Group4/carterror");
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(job));
        }

    }

    private void CODOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        Customer cus = (Customer) session.getAttribute("account");
        CartDAO d = new CartDAO();

        String bankCode = request.getParameter("bankCode");

        int cartid = d.getCartId(cus.getCustomerID());
        List<CartItemMore> listitems = d.getCartItems(cartid);

        List<ProductQty> products = d.getAllProductQuantity();
        Receiver rec = d.getCurrentReceiver(cus.getCustomerID());
        String notes = request.getParameter("notes");

        d.updateHoldProduct(listitems, products);

        int orderid = d.insertOrder(cus.getCustomerID(), rec.getReceiverName(), rec.getGender(), rec.getEmail(), rec.getMobile(), rec.getAddress(), notes, bankCode, d.getSaleID());
        session.setAttribute("orderid", orderid);
        d.insertOrderDetail(listitems, orderid);

        SendOrder send = new SendOrder();
        LocalDate currentDate = LocalDate.now();
        send.send(cus.getEmail(), orderid, cus.getFullName(), currentDate, calculateTotal(listitems), "http://localhost:9999/SWP_Group4/home", bankCode);

        com.google.gson.JsonObject job = new JsonObject();
        job.addProperty("code", "00");
        job.addProperty("message", "success");
        job.addProperty("data", "http://localhost:9999/SWP_Group4/cartcompletion");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(job));
    }

    private void ONLOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        Customer cus = (Customer) session.getAttribute("account");
        CartDAO d = new CartDAO();

        int cartid = d.getCartId(cus.getCustomerID());
        List<CartItemMore> listitems = d.getCartItems(cartid);

        List<ProductQty> products = d.getAllProductQuantity();
        Receiver rec = d.getCurrentReceiver(cus.getCustomerID());
        String notes = request.getParameter("notes");

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = Integer.parseInt(request.getParameter("amount")) * 24820;
        String bankCode = request.getParameter("bankCode");

        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = Config.getIpAddress(request);

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Payment for Aroma Shop Order, Transaction Code: " + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        Date curret = cld.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        d.updateHoldProduct(listitems, products);

        int orderid = d.insertOrder(cus.getCustomerID(), rec.getReceiverName(), rec.getGender(), rec.getEmail(), rec.getMobile(), rec.getAddress(), notes, "VNPAY", d.getSaleID());
        session.setAttribute("orderid", orderid);
        d.insertOrderDetail(listitems, orderid);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        com.google.gson.JsonObject job = new JsonObject();
        job.addProperty("code", "00");
        job.addProperty("message", "success");
        job.addProperty("data", paymentUrl);
        SendOrder send = new SendOrder();
        LocalDate currentDate = LocalDate.now();
        send.send(cus.getEmail(), orderid, cus.getFullName(), currentDate, calculateTotal(listitems), paymentUrl, bankCode);
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(job));
    }
}
