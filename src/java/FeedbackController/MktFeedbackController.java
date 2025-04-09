/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package FeedbackController;

import Dao.FeedbackDAO;
import Dao.ProductDao;
import Model.Employee;
import Model.Feedback;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "MktFeedbackController", urlPatterns = {"/mkt-feedbacks"})
public class MktFeedbackController extends HttpServlet {

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
        try {
            //lấy thông tin người đang đăng nhập
            HttpSession session = request.getSession();
            Employee marketer = (Employee) session.getAttribute("account");

            //nếu đã đăng nhập và là marketer thì tiếp tục xử lí tạo post
            if (marketer != null && marketer.getRoleId() == 3) {
                //Khai báo các DAO
                FeedbackDAO fdao = new FeedbackDAO();
                ProductDao pdao = new ProductDao();

                //get param
                String status = request.getParameter("status") == null ? "" : request.getParameter("status");
                String product = request.getParameter("product") == null ? "" : request.getParameter("product");
                String star = request.getParameter("star") == null ? "" : request.getParameter("star");

                //Lấy tất cả danh sách các feedback
                List<Feedback> feedbacks = fdao.getAllFeedbacks(status, product, star);
                //Lưu danh sách feedback vào request để hiển thị lên giao diện
                request.setAttribute("feedbacks", feedbacks);

                //lay tất cả product để hiển thị lên dropdown filter
                List<Product> products = pdao.getAllProduct();
                request.setAttribute("products", products);
                
                request.setAttribute("status", status);
                request.setAttribute("product", product.isEmpty() ? "" : Integer.parseInt(product));
                request.setAttribute("star", star);

                //Mở trang feedback list
                request.getRequestDispatcher("mkt-feedback.jsp").forward(request, response);
            } else {
                //nếu chưa đăng nhập thì bắt đăng nhập
                response.sendRedirect("login");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        processRequest(request, response);
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
