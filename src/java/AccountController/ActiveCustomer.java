/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.DaoAccount;
import Verify.RandomCode;
import Verify.SendEmail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Anh Tuan
 */
public class ActiveCustomer extends HttpServlet {

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
            out.println("<title>Servlet ActiveCustomer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActiveCustomer at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        int customerID = (Integer) session.getAttribute("customerID");

        RandomCode rc = new RandomCode();
        String ActiveCode = rc.activateCode();
        SendEmail se = new SendEmail();

        String ExpiredTimeRaw = getServletContext().getInitParameter("ExpiredTime");
        int ExpiredTime = Integer.parseInt(ExpiredTimeRaw);
        DaoAccount d = new DaoAccount();
        se.send(d.getEmail(customerID), ActiveCode, ExpiredTime);

        long ActiveTime = System.currentTimeMillis() / 1000;
        session.setAttribute("ActiveTime", ActiveTime);
        session.setAttribute("ActiveCode", ActiveCode);
        request.getRequestDispatcher("Active.jsp").forward(request, response);
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

        Long ActiveTime = (Long) session.getAttribute("ActiveTime");
        String ExpiredTimeRaw = getServletContext().getInitParameter("ExpiredTime");
        long ExpiredTime = Long.parseLong(ExpiredTimeRaw);
        long EndTime = System.currentTimeMillis() / 1000;

        if (ActiveTime == null || EndTime - ActiveTime > ExpiredTime || session.getAttribute("ActiveCode") == null) {
            session.invalidate();
            request.setAttribute("status", "error");
            request.setAttribute("message", "Active code has expired. Please request a new code!");
            request.getRequestDispatcher("Active.jsp").forward(request, response);
        } else {
            String ActiveCode = (String) session.getAttribute("ActiveCode");

            String rawCode = "";
            for (int i = 1; i < 7; i++) {
                rawCode += request.getParameter("number" + i);
            }
            if (rawCode.equals(ActiveCode)) {
                DaoAccount d = new DaoAccount();
                int customerID = (Integer) session.getAttribute("customerID");
                d.activeCustomer(customerID);
                session.invalidate();

                request.setAttribute("status", "success");
                request.setAttribute("message", "Account has been activated successfully! You will be redirected to the Login page in 5 seconds ~");
                request.getRequestDispatcher("Active.jsp").forward(request, response);
            } else {
                request.setAttribute("status", "error");
                request.setAttribute("message", "Verification code is not correct. Please try again!");
                request.getRequestDispatcher("Active.jsp").forward(request, response);

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
