/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package AdminController;

import Dao.AdminDAO;
import Model.Settings;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author FPT
 */
public class editSettingForm extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private AdminDAO adminDAO;
   
    @Override
    public void init() throws ServletException {
        super.init();
        adminDAO = new AdminDAO(); // Initialize your DAO here
    }
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editSettingForm</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editSettingForm at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
      String sidStr = request.getParameter("sid");

        // Check if the sid parameter is provided
        if (sidStr != null && !sidStr.isEmpty()) {
            try {
                // Parse the sid string to an integer
                int sid = Integer.parseInt(sidStr);

                // Retrieve the Settings object using the parsed ID
                Settings setting = adminDAO.getSettingByID(sidStr);
                if (setting != null) {
                    // Setting found, forward to the edit form
                    request.setAttribute("setting", setting);
                    request.getRequestDispatcher("admin-editSettingForm.jsp").forward(request, response);
                } else {
                    // Setting not found, redirect to error page
                    response.sendRedirect("errorPage1.jsp");
                }
            } catch (NumberFormatException e) {
                // Handle number format exception if sid is not a valid integer
                System.err.println("Error parsing sid: " + e.getMessage());
                response.sendRedirect("errorPage2.jsp");
            }
        } else {
            // sid parameter is missing, redirect to error page
            response.sendRedirect("errorPage2.jsp");
        }
        
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
          // Retrieve parameters from the form
        String settingIDStr = request.getParameter("settingID");
        String type = request.getParameter("type");
        String value = request.getParameter("value");
        String orderStr = request.getParameter("order");
        String statusStr = request.getParameter("status");

        int settingID = 0;
        boolean status = false;

        // Parse settingID
        if (settingIDStr != null && !settingIDStr.isEmpty()) {
            try {
                settingID = Integer.parseInt(settingIDStr);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing settingID: " + e.getMessage());
                response.sendRedirect("error.jsp");
                return;
            }
        } else {
            System.err.println("SettingID is null or empty.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse order
        int order = 0;
        if (orderStr != null && !orderStr.isEmpty()) {
            try {
                order = Integer.parseInt(orderStr);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing order: " + e.getMessage());
                response.sendRedirect("error.jsp");
                return;
            }
        }

        // Parse status
        if (statusStr != null) {
            status = Boolean.parseBoolean(statusStr);
        }

        // Create Settings object with updated details
        Settings setting = new Settings(settingID, type, value, order, status);

        // Update setting using the AdminDAO method
        boolean isUpdated = adminDAO.updateSetting(setting);

        // Redirect based on update result
        if (isUpdated) {
            response.sendRedirect("settinglist"); // Redirect to a list of settings
        } else {
            response.sendRedirect("error.jsp");
        }
    
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
