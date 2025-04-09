/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.DaoAccount;
import Model.Employee;
import Model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Anh Tuan
 */
public class Register extends HttpServlet {

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
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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

        // lay du lieu 
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String rawpassword = request.getParameter("password");
        String gender = request.getParameter("gender");
        String phonenumber = request.getParameter("phonenumber");
        String address = request.getParameter("address");
        String avatar = "images/avatar1.png";
        String status = "Inactive";

        DaoAccount d = new DaoAccount();

        Customer existingUser = d.getCustomer(email); // kiem tra bang Customer
        Employee existingEmployee = d.getEmployee(email); // kiem tra bang Employee

        if (existingUser != null || existingEmployee != null) {

            // neu email da ton tai
            // luu thong tin r tra ve
            request.setAttribute("fullname", fullname);
            request.setAttribute("email", email);
            request.setAttribute("rawpassword", rawpassword);
            request.setAttribute("gender", gender);
            request.setAttribute("phonenumber", phonenumber);
            request.setAttribute("address", address);

            // thong bao
            request.setAttribute("ErrorEmail", "This email address already Exists!");
            request.getRequestDispatcher("register.jsp").forward(request, response);

        } else {
            // email chua ton tai 
            Customer rawCustomer = new Customer(); // tao doi tuong rawUser            

            // set cac thuoc tinh 
            rawCustomer.setFullName(fullname);
            rawCustomer.setEmail(email);

            String password = BCrypt.hashpw(rawpassword, BCrypt.gensalt()); // bam password
            rawCustomer.setPassword(password);

            rawCustomer.setGender(gender);
            rawCustomer.setPhoneNumber(phonenumber);
            rawCustomer.setAddress(address);
            rawCustomer.setAvatar(avatar);
            rawCustomer.setStatus(status);

            int customerID = d.registerCustomer(rawCustomer);
            
            HttpSession session = request.getSession();
            session.setAttribute("customerID", customerID);
            response.sendRedirect("activecustomer");
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
