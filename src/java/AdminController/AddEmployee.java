/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminController;

import Dao.AdminDAO;
import Model.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author FPT
 */
public class AddEmployee extends HttpServlet {

    private AdminDAO adminDAO;

    @Override
    public void init() throws ServletException {
        // Initialize the EmployeeDAO
        adminDAO = new AdminDAO();
    }

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
            out.println("<title>Servlet AddEmployee</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEmployee at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("admin-addEmployee.jsp").forward(request, response);
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

        int employeeID = Integer.parseInt(request.getParameter("employeeID")); // Retrieve employeeID
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String avatar = request.getParameter("avatar");
        int roleId = Integer.parseInt(request.getParameter("roleId")); // Assuming roleId is sent as a string
        String status = request.getParameter("status");
        java.util.Date createAt = new java.util.Date(); // Current date and time

        // Create an Employee object with employeeID
        Employee employee = new Employee(employeeID, fullName, email, BCrypt.hashpw(password, BCrypt.gensalt()), gender, phoneNumber, address, avatar, roleId, status, createAt);

        // Insert employee into the database
        try {
            adminDAO.insertEmployee(employee);
            response.sendRedirect("userlist"); // Redirect to a success page
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            response.sendRedirect("error.jsp"); // Redirect to an error page
        }
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
