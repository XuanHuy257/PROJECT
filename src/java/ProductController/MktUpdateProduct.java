/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ProductController;

import Dao.DaoFeature;
import Dao.DaoImage;
import Dao.ProductDao;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author sontu
 */
@MultipartConfig
public class MktUpdateProduct extends HttpServlet {

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
            // Lấy ServletContext của ứng dụng
            ServletContext context = request.getServletContext();

// Đường dẫn tuyệt đối đến thư mục uploads
            String savePath = context.getRealPath("/images");

// Kiểm tra và tạo thư mục nếu nó chưa tồn tại
            File uploadDir = new File(savePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
// Lấy file từ request (ví dụ thumbnail, img1, img2, img3)
            Part thumbnailPart = request.getPart("thumbnail");
            Part img1Part = request.getPart("img1");
            Part img2Part = request.getPart("img2");
            Part img3Part = request.getPart("img3");
            // Hàm để lưu file ảnh và trả về đường dẫn lưu trong DB
// Lưu từng ảnh và lấy đường dẫn để lưu vào database
            String thumbnailPath = saveImage(thumbnailPart, savePath);
            String img1Path = saveImage(img1Part, savePath);
            String img2Path = saveImage(img2Part, savePath);
            String img3Path = saveImage(img3Part, savePath);
            int productID = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String briefInfo = request.getParameter("briefInfo");
            String description = request.getParameter("description");
            String featureName = request.getParameter("feature");
            String status = request.getParameter("status");
            ProductDao daoProduct = new ProductDao();
            DaoFeature daoFeature = new DaoFeature();
            int featureID = daoFeature.getFeatureIDbyFeatureName(featureName);
            if (thumbnailPath == null || img1Path == null || img2Path == null || img3Path == null) {
                String mess = "Please choose image for thumbnail, img1, img2 and img3!";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("list_product_mkt").forward(request, response);
            } else {
                daoProduct.updateProducts(productID, title, thumbnailPath, img1Path, img2Path, img3Path, briefInfo, description, featureID, status);
                String mess = "Update Sucessful!";
                request.setAttribute("mess", mess);
                response.sendRedirect("list_product_mkt");
            }
        }
    }

    private String saveImage(Part filePart, String savePath) throws IOException {
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (fileName == null || fileName.isEmpty()) {
            return null; // Nếu người dùng không chọn ảnh, trả về null
        }
        filePart.write(savePath + File.separator + fileName); // Lưu file ảnh vào thư mục
        return "images/" + fileName; // Trả về đường dẫn sẽ lưu trong DB
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
