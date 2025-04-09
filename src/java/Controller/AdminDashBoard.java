/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Dao.AdminDAO;
import Model.FeedbackStats;
import Model.OrderTrend;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPT
 */
public class AdminDashBoard extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet AdminDashBoard</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminDashBoard at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
          AdminDAO adminDAO = new AdminDAO(); // Ensure AdminDAO is properly implemented
        
        // Fetch order statistics
        Map<String, Integer> orderStats = adminDAO.getOrderStatistics();
        int newCustomers = adminDAO.getNewCustomersCount();
        int newlyBoughtCustomers = adminDAO.getNewlyBoughtCustomersCount();
      
        
        // Fetch order trends and feedback stats
        List<OrderTrend> orderTrends = adminDAO.getOrderTrends();
        List<FeedbackStats> feedbackStatsList = adminDAO.getFeedbackStatsByCategory();

        // Set attributes for the JSP
     
        request.setAttribute("orderStats", orderStats);
        request.setAttribute("newCustomers", newCustomers);
        request.setAttribute("newlyBoughtCustomers", newlyBoughtCustomers);
        request.setAttribute("orderTrends", orderTrends);
        request.setAttribute("feedbackStatsList", feedbackStatsList);
     
  double totalRevenue = adminDAO.getTotalRevenue();
    
    // Set the total revenue in the request scope
    request.setAttribute("totalRevenue", totalRevenue);

            request.getRequestDispatcher("/admin-home.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // You may set an error message and forward to an error page if needed
            request.setAttribute("errorMessage", "Unable to fetch order statistics.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }


    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
