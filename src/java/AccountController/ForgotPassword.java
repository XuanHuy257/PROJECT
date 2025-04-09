/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.DaoAccount;
import Model.Customer;
import Model.Employee;
import Verify.SendForgot;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Anh Tuan
 */
public class ForgotPassword extends HttpServlet {

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
            out.println("<title>Servlet ResetPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPassword at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
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
        // lay email cua tai khoan khanh hang quen password
        String email = request.getParameter("email");
        DaoAccount d = new DaoAccount();
        Customer cus = d.getCustomer(email);
        Employee emp = d.getEmployee(email);
        request.setAttribute("EmailFail", email);
        if (cus == null && emp == null) {
            // neu khong co, thong bao
            request.setAttribute("mess", "This email address is not registered. Please sign up to create an account!");
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);

        } else if (cus == null && emp != null) { // neu la Employee
            // thong bao khong co quyen 
            request.setAttribute("mess", "You do not have permission to perform this action. Please contact the Admin to reset your password!");
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);

        } else if (cus != null && emp == null) { // neu la Customer
            SendForgot sf = new SendForgot();
            String ExpiredTimeRaw = getServletContext().getInitParameter("ExpiredTime");
            int ExpiredTime = Integer.parseInt(ExpiredTimeRaw);
            long ResetTime = System.currentTimeMillis() / 1000;

            sf.sendForgot(email, ExpiredTime, ResetTime);

            request.setAttribute("mess", "We have send an email to your address with a Link to reset your password. Please check your email inbox ~");
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
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
