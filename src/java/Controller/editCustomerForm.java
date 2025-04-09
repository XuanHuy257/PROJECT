package Controller;

import Dao.UserDAO;
import Model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author FPT
 */
public class editCustomerForm extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // CustomerDAO instance to interact with the database
    private UserDAO customerDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the customerID from the request parameter
        String customerID = request.getParameter("customerID");

        if (customerID != null && !customerID.trim().isEmpty()) {
            try {
                // Fetch the customer details from the database using the DAO method
                Customer customer = customerDAO.getCustomerByID(Integer.parseInt(customerID));

                if (customer != null) {
                    // Pass the customer object to the JSP for display
                    request.setAttribute("customer", customer);
                    request.getRequestDispatcher("mkt-EditForm.jsp").forward(request, response);
                } else {
                    // If the customer is not found, redirect to an error page
                    response.sendRedirect("errorPage1.jsp");
                }
            } catch (Exception e) {
                // Log the exception and redirect to a generic error page
                e.printStackTrace(); // This should be replaced with proper logging
                response.sendRedirect("errorPage.jsp");
            }
        } else {
            // Handle the case where customerID is missing or invalid
            response.sendRedirect("errorPage1.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch parameters from the request
        String customerIDStr = request.getParameter("customerID");
        System.out.println("Received customerIDStr: '" + customerIDStr + "'");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String avatar = request.getParameter("avatar");
        String status = request.getParameter("status");
        String createAtStr = request.getParameter("createAt");  // Fetch createAt

        int customerID = 0;
        if (customerIDStr != null) {
            customerIDStr = customerIDStr.trim();
            if (!customerIDStr.isEmpty()) {
                try {
                    customerID = Integer.parseInt(customerIDStr);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid customerID format: " + customerIDStr);
                    response.sendRedirect("error1.jsp?errorMessage=Invalid customerID format: " + customerIDStr);
                    return;
                }
            } else {
                System.err.println("customerIDStr is empty after trimming.");
                response.sendRedirect("error2.jsp?errorMessage=CustomerID is empty after trimming.");
                return;
            }
        } else {
            System.err.println("customerIDStr is null.");
            response.sendRedirect("error2.jsp?errorMessage=CustomerID is null.");
            return;
        }

        // Parse and handle `createAt` date (optional)
        Date createAt = null;
        if (createAtStr != null && !createAtStr.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                createAt = sdf.parse(createAtStr);
            } catch (ParseException e) {
                System.err.println("Error parsing createAt: " + e.getMessage());
                response.sendRedirect("error3.jsp?errorMessage=Error parsing createAt date.");
                return;
            }
        } else {
            // If `createAt` is not provided, set it to the current date
            createAt = new Date();
        }

        // Ensure no fields are null by assigning default values where necessary
        fullName = (fullName != null) ? fullName : "";
        email = (email != null) ? email : "";
        password = (password != null) ? password : "";
        gender = (gender != null) ? gender : "";
        phoneNumber = (phoneNumber != null) ? phoneNumber : "";
        address = (address != null) ? address : "";
        avatar = (avatar != null) ? avatar : "";
        status = (status != null) ? status : "";

        // Create a Customer object with the updated details
        Customer customer = new Customer(customerID, fullName, email, BCrypt.hashpw(password, BCrypt.gensalt()), gender, phoneNumber, address, avatar, status, createAt);

        // Update customer details using the CustomerDAO method
        boolean isUpdated = customerDAO.editCustomer(customer);

        // Redirect based on update result
        if (isUpdated) {
            // Redirect to the customer list if the update was successful
            response.sendRedirect("customer");
        } else {
            // Redirect to an error page if the update fails
            response.sendRedirect("error.jsp?errorMessage=Update failed.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
