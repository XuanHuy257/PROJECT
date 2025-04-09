/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.DaoAccount;
import Model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Base64;

/**
 *
 * @author Anh Tuan
 */
public class ResetPassword extends HttpServlet {

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
            out.println("<title>Servlet RecoveryPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RecoveryPassword at " + request.getContextPath() + "</h1>");
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
        String email = request.getParameter("email");
        String ResetTime = request.getParameter("time");

        String decodedResetTime = new String(Base64.getDecoder().decode(ResetTime), StandardCharsets.UTF_8);
        HttpSession session = request.getSession();
        session.setAttribute("EmailToReset", email);
        session.setAttribute("ResetTime", decodedResetTime);
        request.getRequestDispatcher("resetpassword.jsp").forward(request, response);

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

        HttpSession session = request.getSession();
        long EndTime = System.currentTimeMillis() / 1000; // lay time hien tai (tinh bang giay)

        String ResetTimeRaw = (String) session.getAttribute("ResetTime");
        if (ResetTimeRaw == null) {
            request.setAttribute("errorMessage", "The Reset Password Link Is Invalid");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
            return;
        }
        long ResetTime;

        try {
            ResetTime = Long.parseLong(ResetTimeRaw);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "The Reset Password Link Is Invalid");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
            return;
        }

        // lay session time out cau hinh qua the context-param trong web.xml
        String ExpiredTimeRaw = getServletContext().getInitParameter("ExpiredTime");
        long ExpiredTime = Long.parseLong(ExpiredTimeRaw);

        // kiem tra xem verify link da het han hay chua
        if (EndTime - ResetTime > ExpiredTime) {
            // da het han
            session.invalidate(); //xoa session
            // thong bao        
            request.setAttribute("mess", "Verification Link has expired. Please request a new Link!");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
        }

        // kiem tra xem link da duoc su dung chua
        if (session.getAttribute("EmailToReset") == null) {
            // da dung -> thong bao
            request.setAttribute("mess", "Verification Link has expired. Please request a new Link!");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);

        } else {

            // chua dung
            DaoAccount d = new DaoAccount();

            String email = (String) session.getAttribute("EmailToReset"); // lay email tu session
            String rawnewpassword = request.getParameter("newpassword"); // lay password tu form

            Customer customer = d.getCustomer(email);
            if (customer == null) {
                request.setAttribute("errorMessage", "The Reset Password Link Is Invalid");
                request.getRequestDispatcher("Error.jsp").forward(request, response);
                return;
            }

            boolean valuate = BCrypt.checkpw(rawnewpassword, customer.getPassword()); //lay pass cu va kiem tra

            if (valuate) {
                // neu trung pass cu -> thong bao
                request.setAttribute("newpass", rawnewpassword);
                request.setAttribute("mess", "The new password must be different from the old password.");
                request.getRequestDispatcher("resetpassword.jsp").forward(request, response);

            } else {
                // neu khac -> change
                String password = BCrypt.hashpw(rawnewpassword, BCrypt.gensalt()); // bam password

                d.ChangePassWord(email, password); // doi password
                session.invalidate(); // xoa session de link chi xai duoc 1 lan
                // thong bao
                request.setAttribute("newpass", rawnewpassword);
                request.setAttribute("status", "success");
                request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            }
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
