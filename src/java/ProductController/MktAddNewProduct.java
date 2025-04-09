/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ProductController;

import Dao.DaoBrand;
import Dao.DaoCategory;
import Dao.DaoFeature;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author sontu
 */
@MultipartConfig
public class MktAddNewProduct extends HttpServlet {

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
            ServletContext context = request.getServletContext();
            ProductDao daoProduct = new ProductDao();
            // Đường dẫn tuyệt đối đến thư mục uploads
            String savePath = context.getRealPath("/images");

            // Kiểm tra và tạo thư mục nếu nó chưa tồn tại
            File uploadDir = new File(savePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Lấy file từ request (thumbnail)
            Part thumbnailPart = request.getPart("thumbnail");

            // Lấy các tham số từ request
            String title = request.getParameter("title");
            String briefInfor = request.getParameter("briefInfo");
            String description = request.getParameter("description");
            String categoryName = request.getParameter("category");
            String brandName = request.getParameter("brand");
            String feature = request.getParameter("feature");
            String status = request.getParameter("status");

            // Lưu file ảnh và lấy đường dẫn
            String thumbnailPath = saveImage(thumbnailPart, savePath);

            DaoBrand daoBrand = new DaoBrand();
            DaoCategory daoCategory = new DaoCategory();
            DaoFeature daoFeature = new DaoFeature();
            int featureID = daoFeature.getFeatureIDbyFeatureName(feature);
            int brandID = daoBrand.getBrandIDbyName(brandName);
            int categoryID = daoCategory.getCategoryIDbyName(categoryName);
            if (thumbnailPath == null) {
                String mess = "Please choose image for thumbnail!";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("list_product_mkt").forward(request, response);
            } else if (daoProduct.checkBeforeAddProduct(title, categoryID, brandID)) {
                String mess = "This Product is Already Exist!";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("list_product_mkt").forward(request, response);
            } else {
                daoProduct.MktAddNewProduct(title, briefInfor, description, thumbnailPath, featureID, status, categoryID, brandID);
                String mess = "Add Sucessful! Please add Size to Active Product";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("list_product_mkt").forward(request, response);
            }
        }
    }

    private String saveImage(Part filePart, String savePath) throws IOException {
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        Path filePath = Paths.get(savePath, fileName); // Tạo đường dẫn file đầy đủ
        if (fileName == null || fileName.isEmpty()) {
            return null; // Nếu người dùng không chọn ảnh, trả về null
        }
        // Sao chép file vào thư mục lưu trữ
        try (InputStream inputStream = filePart.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING); // Thay thế nếu đã tồn tại
        }

        return "images/" + fileName; // Đường dẫn sẽ lưu trong DB
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
