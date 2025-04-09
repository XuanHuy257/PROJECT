/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package BlogController;

import Constant.Constant;
import Dao.BlogDAO;
import Model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author
 */
@WebServlet(name = "PostEditController", urlPatterns = {"/edit-post"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class PostEditController extends HttpServlet {

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
            out.println("<title>Servlet PostEditController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostEditController at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        try {

            // Lấy thông tin từ các input fields cua form tạo post
            String postId = request.getParameter("postID");
            String title = request.getParameter("title");
            String postCategory = request.getParameter("category");
            String postBrief = request.getParameter("postBrief");
            String postContent = request.getParameter("postContent");
            String featured = request.getParameter("featured");
            String status = request.getParameter("status");
            String thumbnailOld = request.getParameter("thumbnailOld");

            // Xử lý file ảnh thumbnail
            Part filePart = request.getPart("thumbnail");
            String imageFilePath = null;
            String imageFile = null;
            //nếu marketer có tải ảnh lên thì lưu ảnh
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
            } else {
                //nếu marketer không tải ảnh lên, sẽ giữ nguyên ảnh cũ
                imageFile = thumbnailOld;
            }

            //Khai báo DAO
            BlogDAO bdao = new BlogDAO();

            //Lưu thông tin post được update vào database
            Post post = new Post(Integer.parseInt(postId), title, imageFile, postContent, postBrief, Boolean.parseBoolean(featured), Integer.parseInt(postCategory), status);
            bdao.updatePost(post);

            //Trở về trang post list
            response.sendRedirect("posts");

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
