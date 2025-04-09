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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Anh Tuan
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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

        // lay thong tin email va password cua Customer
        String cemail = request.getParameter("cemail");
        String cpassword = request.getParameter("cpassword");

        HttpSession session = request.getSession(); // Khoi tao session

        DaoAccount d = new DaoAccount(); // khoi tao DAO

        Customer customer = d.getCustomer(cemail); // lay thong tin nguoi dung

        // kiem tra Customer co ton tai khong
        if (customer != null) {
            // neu ton tai kiem tra password
            boolean valuate = BCrypt.checkpw(cpassword, customer.getPassword());

            // dung
            if (valuate) {

                if (customer.getStatus().equals("Active")) {

                    String cremember = request.getParameter("cremember"); // check nguoi dung co chon remember me k

                    //tao cookie
                    Cookie cem = new Cookie("cemail", cemail);
                    Cookie cpa = new Cookie("cpass", cpassword);
                    Cookie cre = new Cookie("crem", cremember);

                    // neu co
                    if (cremember != null) {
                        cem.setMaxAge(60 * 60 * 24 * 7);
                        cpa.setMaxAge(60 * 60 * 24 * 7);
                        cre.setMaxAge(60 * 60 * 24 * 7); // 7 ngay ton tai

                        //neu khong
                    } else {
                        // huy cookie
                        cem.setMaxAge(0);
                        cpa.setMaxAge(0);
                        cre.setMaxAge(0);
                    }
                    // them cookie de phan hoi
                    response.addCookie(cem);
                    response.addCookie(cpa);
                    response.addCookie(cre);

                    // luu thong tin nguoi dung
                    session.setAttribute("account", customer);
                    session.setAttribute("Cay",0);
                    response.sendRedirect("home");

                } else {
                    // no active
                    session.setAttribute("customerID", customer.getCustomerID());
                    request.setAttribute("Inactive", "Inactive");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {

                // password sai
                request.setAttribute("mess", "Incorrect Email or Password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {

            //email sai
            request.setAttribute("mess", "Incorrect Email or Password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
