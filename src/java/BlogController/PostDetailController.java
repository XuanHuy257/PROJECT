/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package BlogController;

import Dao.BlogCategoryDAO;
import Dao.BlogDAO;
import Dao.UserDAO;
import Model.Employee;
import Model.Post;
import Model.PostCategory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author
 */
@WebServlet(name = "PostDetailController", urlPatterns = {"/post-detail"})
public class PostDetailController extends HttpServlet {

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
            //Khai báo các DAO
            BlogDAO bdao = new BlogDAO();
            BlogCategoryDAO bcdao = new BlogCategoryDAO();

            //Lấy giá trị id của post được truyền từ url
            String id = request.getParameter("id");

            //lấy thông tin của post theo id ở trên
            Post post = bdao.getBlogById(Integer.parseInt(id));
            //Lưu danh sách các post vào request để hiển thị lên giao diện
            request.setAttribute("post", post);
            
            //Lấy tất cả danh sách các loại bài post
            List<PostCategory> blogCategories = bcdao.getAllBlogCategory();
            //Lưu danh sách loại post vào request để hiển thị lên giao diện
            request.setAttribute("blogCategories", blogCategories);
            
            //Mở trang post detail
            request.getRequestDispatcher("mkt-post-detail.jsp").forward(request, response);
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
