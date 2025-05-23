/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ProductController;

import Dao.DaoBrand;
import Dao.DaoCategory;
import Dao.DaoFeature;
import Dao.DaoImage;
import Dao.ProductDao;
import Model.Brand;
import Model.Category;
import Model.Feature;
import Model.ProductDetailforMKT;
import Model.Size;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author sontu
 */
public class ProductDetailMKTConntroller extends HttpServlet {

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
            ProductDao daoProduct = new ProductDao();
            DaoCategory daoCategory = new DaoCategory();
            DaoBrand daoBrand = new DaoBrand();
            DaoFeature daoFeature = new DaoFeature();
            int productID = Integer.parseInt(request.getParameter("id"));
            ProductDetailforMKT productDetailforMKT = daoProduct.getProductDetailforMKT(productID);
            List<Category> allCategory = daoCategory.getAllCategory();
            List<Brand> allBrand = daoBrand.getAllBrand();
            List<Size> avalibleSizeNameByID = daoProduct.getAvalibleSizeByID(productID);
            List<Feature> allFeature = daoFeature.getAllFeature();
            request.setAttribute("allCategory", allCategory);
            request.setAttribute("allBrand", allBrand);
            request.setAttribute("allFeature", allFeature);
            request.setAttribute("productDetailforMKT", productDetailforMKT);
            request.setAttribute("avalibleSizeNameByID", avalibleSizeNameByID);
            DaoImage daoImage = new DaoImage();
            daoImage.createImageforProduct(productID);
            request.getRequestDispatcher("mkt-productDetail.jsp").forward(request, response);
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
