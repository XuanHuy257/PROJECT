package Controller;

import Dao.UserDAO;
import Model.Customer;
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
public class UpdateUserInfo extends HttpServlet {
    private UserDAO userDAO; // Giả sử bạn có lớp UserDAO để tương tác với CSDL
   

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO(); // Khởi tạo DAO
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateUserInfo</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateUserInfo at " + request.getContextPath() + "</h1>");
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
        String customerID = request.getParameter("id");
        Customer customer = userDAO.getCustomerByID(Integer.parseInt(customerID)); // Lấy thông tin khách hàng từ CSDL
        request.setAttribute("user", customer); // Đặt thông tin khách hàng vào thuộc tính yêu cầu
        request.getRequestDispatcher("home-edituserinfo.jsp").forward(request, response); // Chuyển tiếp đến JSP

        String successMessage = (String) request.getSession().getAttribute("successMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
            // Clear the message after retrieving
            request.getSession().removeAttribute("successMessage");
        }

        // Forward to the appropriate JSP (e.g., profile.jsp)
        request.getRequestDispatcher("UserDetailServlet?id=" + customerID).forward(request, response);
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
        String customerIDString = request.getParameter("customerID"); // Change variable name here
        int customerID = 0;

        if (customerIDString != null && !customerIDString.isEmpty()) {
            try {
                customerID = Integer.parseInt(customerIDString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the invalid customer ID case
                request.setAttribute("errorMessage", "Invalid customer ID format.");
                request.getRequestDispatcher("/errorPage1.jsp").forward(request, response);
                return; // Exit the method to prevent further execution
            }
        } else {
            // Handle the case where customerId is null or empty
            request.setAttribute("errorMessage", "Customer ID is required.");
            request.getRequestDispatcher("/errorPage2.jsp").forward(request, response);
            return; // Exit the method to prevent further execution
        }

        // Retrieve updated data from the request
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");

        // Create a Customer object to hold the updated data
        Customer customer = new Customer();
        customer.setCustomerID(customerID); 
        customer.setFullName(fullName);
        customer.setPhoneNumber(phoneNumber);
        customer.setGender(gender);
        customer.setAddress(address);

        // Update the customer information in the database
        boolean isUpdated = userDAO.updateCustomerInfo(customer);

        if (isUpdated) {
            // Set a success message in the session
            request.getSession().setAttribute("successMessage", "User information updated successfully.");
            
            // Redirect to the UserDetailServlet with the customerId
            response.sendRedirect("UserDetailServlet?id=" + customerID);
        } else {
            // Handle the failure case (e.g., show an error message)
            request.setAttribute("errorMessage", "Failed to update user information.");
            request.getRequestDispatcher("/errorPage3.jsp").forward(request, response); 
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
