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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author
 */
@WebServlet(name = "PostController", urlPatterns = {"/posts"})
public class PostController extends HttpServlet {

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
                BlogDAO bdao = new BlogDAO();
                BlogCategoryDAO bcdao = new BlogCategoryDAO();
                UserDAO udao = new UserDAO();

                //Lấy tất cả danh sách các bài post của người đăng bài (người đang đăng nhập)
                List<Post> blogs = bdao.getAllBlogsByAuthor(marketer.getEmployeeID());
                //Lưu danh sách post vào request để hiển thị lên giao diện
                request.setAttribute("blogs", blogs);

                //Lấy tất cả danh sách các loại bài post
                List<PostCategory> blogCategories = bcdao.getAllBlogCategory();
                //Lưu danh sách loại post vào request để hiển thị lên giao diện
                request.setAttribute("blogCategories", blogCategories);

                //Lấy tất cả danh sách marketer (author)
                List<Employee> authors = udao.getAllMarketers();
                //Lưu danh sách marketer vào request để hiển thị lên giao diện
                request.setAttribute("authors", authors);

                //Mở trang post list
                request.getRequestDispatcher("mkt-post.jsp").forward(request, response);
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
