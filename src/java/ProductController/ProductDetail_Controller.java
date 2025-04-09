/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package ProductController;

import Dao.FeedbackDAO;
import Dao.ProductDao;
import Model.Customer;
import Model.Feedback;
import Model.ProductDetail;
import Model.Size;
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
 * @author sontu
 */
public class ProductDetail_Controller extends HttpServlet {
   
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
            ProductDao daoProduct = new ProductDao();
            String productId = request.getParameter("id");
            
            FeedbackDAO fdao = new FeedbackDAO();
            List<Feedback> feedbacks = fdao.getAllFeedbackByProductId(Integer.parseInt(productId));
            request.setAttribute("feedbacks", feedbacks);
            request.setAttribute("numberOfFeedback", feedbacks.size());
            
            double avgRating = fdao.getAvgRating(Integer.parseInt(productId));
            request.setAttribute("avgRating", avgRating);
            List<Integer> numberFBByRating = fdao.getNumberOfFBByRating(Integer.parseInt(productId));
            request.setAttribute("numberFBByRating", numberFBByRating);
            ProductDetail productDetail = daoProduct.getProductByID(Integer.parseInt(productId));
            List<Size> avalibleSizeNameByID = daoProduct.getAvalibleSizeByID(Integer.parseInt(productId));
            request.setAttribute("productDetail", productDetail);
            request.setAttribute("avalibleSizeNameByID", avalibleSizeNameByID);
            HttpSession session = request.getSession();
            Customer customer = (Customer)session.getAttribute("account");
            Integer userId = customer == null ? null : customer.getCustomerID();
            boolean canFeedback = false;
            if(userId != null){
                canFeedback = fdao.isBought(userId, Integer.parseInt(productId));
                request.setAttribute("canFeedback", canFeedback);
            }
            request.getRequestDispatcher("ProductDetail.jsp").forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
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
