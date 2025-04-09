/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package WarehouseController;

import Dao.WarehouseDAO;
import Model.ProductType;
import Model.WarehouseProduct;
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
public class ProductForWarehouse extends HttpServlet {

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
            out.println("<title>Servlet ProductForWarehouse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductForWarehouse at " + request.getContextPath() + "</h1>");
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
        String datefrom = request.getParameter("datefrom");
        String dateto = request.getParameter("dateto");
        String titleraw = request.getParameter("title");
        String title = "";
        if (titleraw != null && !titleraw.isEmpty()) {
            title = titleraw.trim();
        }
        String status = request.getParameter("status");
        WarehouseDAO d = new WarehouseDAO();
        ProductType pro = d.getProductForWarehouse();
        
        request.setAttribute("producttype",pro);
        List<WarehouseProduct> listproduct = d.getListProductForWarehouse(datefrom,dateto,title,status);
        request.setAttribute("datefrom", datefrom);
        request.setAttribute("dateto", dateto);
        request.setAttribute("title", title);
        request.setAttribute("status", status);
        request.setAttribute("listproduct", listproduct);
        request.getRequestDispatcher("WarehouseInventory.jsp").forward(request, response);
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
