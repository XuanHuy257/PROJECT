/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package WarehouseController;

import Dao.WarehouseDAO;
import Model.ProductStock;
import Model.ProductWarehouse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Anh Tuan
 */
public class ProductDetailForWarehouse extends HttpServlet {

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
            out.println("<title>Servlet ProductDetailForWarehouse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductDetailForWarehouse at " + request.getContextPath() + "</h1>");
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
        String code = request.getParameter("code");
        String productid = request.getParameter("productid");
        WarehouseDAO d = new WarehouseDAO();
        ProductWarehouse pro = d.getProduct(productid);
        List<ProductStock> listsizeqty = d.getProductStock(productid);

        if (code != null && code.equalsIgnoreCase("00")) {
            request.setAttribute("mess", "Stock has been successfully added!");
        } else if (code != null && code.equalsIgnoreCase("01")) {
            request.setAttribute("mess", "Stock has been failed added!");
        }
        request.setAttribute("listsizeqty", listsizeqty);
        request.setAttribute("product", pro);
        request.getRequestDispatcher("ProductDetailForWarehouse.jsp").forward(request, response);
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
        String productid = request.getParameter("productid");
        String sizeid = request.getParameter("sizeid");
        String quantity = request.getParameter("quantity");
        String oriprice = request.getParameter("oriprice");
        WarehouseDAO d = new WarehouseDAO();
        d.updateProductWarehouse(quantity, oriprice, productid, sizeid);
        
//        int FeatureID = d.getFeatureID(productid);
//        if(FeatureID == 3){
//            d.updateFeature(productid);
//        }
        response.sendRedirect("productdetailforwarehouse?productid="+productid+"&code=00");
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
