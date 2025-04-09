/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.DaoAccount;
import Model.Employee;
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
public class EmployeeLogin extends HttpServlet {

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
            out.println("<title>Servlet EmployeeLogin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeLogin at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("employeelogin.jsp").forward(request, response);
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
        // tuong tu loginL cua Customer
        String eemail = request.getParameter("eemail");
        String epassword = request.getParameter("epassword");

        DaoAccount d = new DaoAccount();
        HttpSession session = request.getSession();

        Employee employee = d.getEmployee(eemail);

        if (employee != null) {

            boolean valuate = BCrypt.checkpw(epassword, employee.getPassword());

            if (valuate) {

                if (employee.getStatus().equals("Active")) {

                    String eremember = request.getParameter("eremember");

                    Cookie eem = new Cookie("eemail", eemail);
                    Cookie epa = new Cookie("epass", epassword);
                    Cookie ere = new Cookie("erem", eremember);

                    if (eremember != null) {
                        eem.setMaxAge(60 * 60 * 24 * 7);
                        epa.setMaxAge(60 * 60 * 24 * 7);
                        ere.setMaxAge(60 * 60 * 24 * 7);
                    } else {
                        eem.setMaxAge(0);
                        epa.setMaxAge(0);
                        ere.setMaxAge(0);
                    }
                    response.addCookie(eem);
                    response.addCookie(epa);
                    response.addCookie(ere);

                    session.setAttribute("account", employee);
                    session.setAttribute("Cay",employee.getRoleId());

                    int roleId = employee.getRoleId(); // lay RoleID

                    switch (roleId) {
                        case 1:
                            // RoleID = 1 den trang danh cho admin
                            response.sendRedirect("AdminDashBoard");
                            break;
                        case 2:
                            // RoleID = 2 den trang danh cho sales
                            response.sendRedirect("saledashboard");
                            break;
                        case 3:
                            // RoleID = 3 den trang marketing
                            response.sendRedirect("MarketingDashBoard");
                            break;
                        case 4:                           
                            response.sendRedirect("saledashboard");
                            break;
                        case 5:
                            // RoleID = 5 den trang kho hang
                            response.sendRedirect("warehousedashboard");
                            break;
                        default:
                            // RoleID khac hoac bi loi
                            response.sendRedirect("home");
                            break;
                    }
                } else {
                    request.setAttribute("Inactive", "Your account is suspended!. Please contact Admin to reactivate your account!.");
                    request.getRequestDispatcher("employeelogin.jsp").forward(request, response);
                }
            } else {

                request.setAttribute("mess", "Incorrect Email or Password!");
                request.getRequestDispatcher("employeelogin.jsp").forward(request, response);
            }
        } else {

            request.setAttribute("mess", "Incorrect Email or Password!");
            request.getRequestDispatcher("employeelogin.jsp").forward(request, response);
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
