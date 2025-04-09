/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CartController;

import Dao.CartDAO;
import Model.Customer;
import Model.Order;
import Model.OrderDetail;
import Model.ProductQty;
import Verify.SendReject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Anh Tuan
 */
public class PaymentController extends HttpServlet {

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
            out.println("<title>Servlet PaymentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentController at " + request.getContextPath() + "</h1>");
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
        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        String vnp_Amount = request.getParameter("vnp_Amount");
        String vnp_BankCode = request.getParameter("vnp_BankCode");
        String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
        String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");
        CartDAO d = new CartDAO();
        HttpSession session = request.getSession();
        int orderid = (Integer) session.getAttribute("orderid");
        if (!vnp_TransactionStatus.equalsIgnoreCase("00")) {
            
            d.updateOrderStatus(orderid, 3);
            SendReject sen = new SendReject();
            Order or = d.getOrderByID(orderid);
            List<OrderDetail> listorder = d.getOrderDetailByID(orderid);
            List<ProductQty> listqty = d.getAllProductQuantity();
            Customer cus = d.getCustomer(or.getCustomerD());
            d.updateHoldProduct02(listorder, listqty);
            sen.send(cus.getEmail(), or.getOrderID(), cus.getFullName(), or.getCreatedOrder(), calculateTotal(listorder));
        } else {
            d.updatePaymentStatus(orderid);
        }
        request.setAttribute("vnp_TxnRef", vnp_TxnRef);
        request.setAttribute("vnp_Amount", vnp_Amount);
        request.setAttribute("vnp_BankCode", vnp_BankCode);
        request.setAttribute("vnp_OrderInfo", vnp_OrderInfo);
        request.setAttribute("vnp_TransactionStatus", vnp_TransactionStatus);
        request.getRequestDispatcher("PaymentProcess.jsp").forward(request, response);
    }

    private double calculateTotal(List<OrderDetail> listorder) {
        double total = 0;
        if (listorder != null) {
            for (OrderDetail or : listorder) {
                total += or.getPrice() * or.getQuantity();
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
