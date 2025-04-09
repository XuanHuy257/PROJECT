///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package OderController;
//
//import Dao.AttributeDAO;
//import Model.AttributeMore;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// *
// * @author Anh Tuan
// */
//public class StatusController extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet StatusController</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet StatusController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        AttributeDAO d = new AttributeDAO();
//
//        List<AttributeMore> list = d.getAllStatus();
//
//        request.setAttribute("liststatus", list);
//        request.getRequestDispatcher("StatusManager.jsp").forward(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String statusid = request.getParameter("statusid");
//        String action = request.getParameter("action");
//        String statusname = request.getParameter("statusname");
//        String statusdescription = request.getParameter("statusdescription");
//        AttributeDAO d = new AttributeDAO();
//
//        if (statusname != null && !statusname.isEmpty() && statusdescription != null && !statusdescription.isEmpty() && statusid == null && action == null) {
//            d.addStatus(statusname, statusdescription);
//        }
//
//        if (action != null && action.equalsIgnoreCase("delete") && statusid != null && !statusid.isEmpty()) {
//            d.deleteStatus(statusid);
//        }
//
//        if (action != null && action.equalsIgnoreCase("edit") && statusid != null && !statusid.isEmpty() && statusname != null && !statusname.isEmpty() && statusdescription != null && !statusdescription.isEmpty()) {
//            d.updateStatus(statusname, statusdescription, statusid);
//        }
//
//        List<AttributeMore> list = d.getAllStatus();
//
//        response.sendRedirect("managerstatus");
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
