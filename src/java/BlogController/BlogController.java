/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package BlogController;

import Constant.Constant;
import Dao.BlogCategoryDAO;
import Dao.BlogDAO;
import Model.Post;
import Model.PostCategory;
import java.io.IOException;
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
@WebServlet(name = "BlogController", urlPatterns = {"/blogs"})
public class BlogController extends HttpServlet {

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
            BlogDAO bdao = new BlogDAO();
            BlogCategoryDAO bcdao = new BlogCategoryDAO();

            String search = request.getParameter("search");
            search = search == null ? "" : search.trim();
            String category = request.getParameter("category");
            category = category == null || category == "" ? "0" : category;
            String page = request.getParameter("page");
            page = page == null ? "1" : page;

            List<Post> blogs = bdao.getAllBlogs(search, Integer.parseInt(category), Integer.parseInt(page));
            int totalPage = getPageSize(Constant.BLOGS_PER_PAGE, bdao.getAllBlogs(search, Integer.parseInt(category), null).size());
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);
            request.setAttribute("search", search);
            request.setAttribute("category", category);

            List<Post> top3LastestBlog = bdao.getTop3LastestBlogs();
            request.setAttribute("top3LastestBlog", top3LastestBlog);

            List<PostCategory> blogCategories = bcdao.getAllBlogCategory();
            request.setAttribute("blogs", blogs);
            request.setAttribute("blogCategories", blogCategories);
            request.getRequestDispatcher("blog.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPageSize(int numberProduct, int allProduct) {
        int pageSize = allProduct / numberProduct;
        if (allProduct % numberProduct != 0) {
            pageSize += 1;
        }
        return pageSize;

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
