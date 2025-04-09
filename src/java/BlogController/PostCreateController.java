/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package BlogController;

import Constant.Constant;
import Dao.BlogCategoryDAO;
import Dao.BlogDAO;
import Model.Employee;
import Model.Post;
import Model.PostCategory;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author
 */
@WebServlet(name = "PostCreateController", urlPatterns = {"/create-post"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class PostCreateController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            //Khai báo các DAO
            BlogCategoryDAO bcdao = new BlogCategoryDAO();

            //Lấy tất cả danh sách các loại bài post
            List<PostCategory> blogCategories = bcdao.getAllBlogCategory();
            //Lưu danh sách loại post vào request để hiển thị lên giao diện
            request.setAttribute("blogCategories", blogCategories);

            //Mở trang create post
            request.getRequestDispatcher("mkt-post-create.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        response.setContentType("text/html;charset=UTF-8");
        try {
            //lấy thông tin người đang đăng nhập
            HttpSession session = request.getSession();
            Employee marketer = (Employee) session.getAttribute("account");
            
            //nếu đã đăng nhập và là marketer thì tiếp tục xử lí tạo post
            if (marketer != null && marketer.getRoleId() == 3) {
                // Lấy thông tin từ các input fields cua form tạo post
                String title = request.getParameter("title");
                String postCategory = request.getParameter("category");
                String postBrief = request.getParameter("postBrief");
                String postContent = request.getParameter("postContent");

                // Xử lý file ảnh thumbnail
                Part filePart = request.getPart("thumbnail");
                String imageFilePath = null;
                String imageFile = null;
                //nếu marketer có tải ảnh lên
                if (filePart != null && filePart.getSize() > 0) {
                    //lất tên ảnh
                    String fileName = extractFileName(filePart);
                    
                    //tạo đường dẫn folder để upload ảnh
                    String applicationPath = getServletContext().getRealPath("");
                    String uploadFilePath = applicationPath + File.separator + Constant.UPLOAD_IMAGES_DIR;

                    // Tạo thư mục nếu chưa tồn tại
                    File uploadDir = new File(uploadFilePath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    // Lưu file hình ảnh
                    imageFilePath = uploadFilePath + File.separator + fileName;
                    try (InputStream fileContent = filePart.getInputStream()) {
                        // Nếu file chưa tồn tại thì lưu ảnh
                        Path destinationPath = Paths.get(imageFilePath);
                        if (!Files.exists(destinationPath)) {
                            Files.copy(fileContent, new File(imageFilePath).toPath());
                        }
                    }
                    //get link image
                    imageFile = Constant.UPLOAD_IMAGES_DIR + File.separator + fileName;
                }

                //Khai báo DAO
                BlogDAO bdao = new BlogDAO();

                //Lấy id của người đang đăng nhập hiện tại (chính là marketer tạo bài post) để lưu vào database
                int userId = marketer.getEmployeeID();
                
                //Lưu thông tin post được tạo vào database
                //Mặc định postFlag là false -> nghĩa là featured là show bài post
                //Mặc định status của post là Active
                //Mặc định ngày đăng là thời gian hiện tại
                Post post = new Post(userId, title, imageFile, postContent, postBrief, Integer.parseInt(postCategory));
                bdao.addPost(post);
                
                //Trở về trang post list
                response.sendRedirect("posts");
            } else {
                //nếu chưa đăng nhập thì bắt đăng nhập
                response.sendRedirect("login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return "";
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
