package Controller;

import Dao.AdminDAO;
import Dao.UserDAO;
import Model.Employee;
import java.io.IOException;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author FPT
 */
public class editUserForm extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       String employeeIDStr = request.getParameter("employeeID");

    // Check if employeeID parameter is valid
    if (employeeIDStr != null && !employeeIDStr.isEmpty()) {
        try {
            Employee employee = userDAO.getEmployeeByID(employeeIDStr);

            if (employee != null) {
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("admin-EditForm.jsp").forward(request, response);
            } else {
                System.err.println("Employee not found with ID: " + employeeIDStr);
                response.sendRedirect("errorPage1.jsp");
            }
        } catch (Exception e) {
            System.err.println("Error retrieving employee: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("errorPage1.jsp");
        }
    } else {
        System.err.println("Invalid or missing employeeID parameter.");
        response.sendRedirect("errorPage23.jsp");
    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Retrieve parameters from the request
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String avatar = request.getParameter("avatar");  
        String status = request.getParameter("status");
        String roleIDStr = request.getParameter("roleID");
        String employeeIDStr = request.getParameter("employeeID");

        // Parse employeeID safely
        int employeeID = 0;
        if (employeeIDStr != null && !employeeIDStr.isEmpty()) {
            try {
                employeeID = Integer.parseInt(employeeIDStr);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing employeeID: " + e.getMessage());
                response.sendRedirect("error.jsp");
                return;
            }
        } else {
            System.err.println("EmployeeID is null or empty.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Parse roleID safely
        int roleID = 0;
        if (roleIDStr != null && !roleIDStr.trim().isEmpty()) {
            try {
                roleID = Integer.parseInt(roleIDStr);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing roleID: " + e.getMessage());
                response.sendRedirect("error.jsp");
                return;
            }
        } else {
            System.err.println("RoleID is null or empty.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Ensure no fields are null by assigning default values
        fullName = (fullName != null) ? fullName : "";
        email = (email != null) ? email : "";
        password = (password != null) ? password : "";
        gender = (gender != null) ? gender : "";
        phoneNumber = (phoneNumber != null) ? phoneNumber : "";
        address = (address != null) ? address : "";
        avatar = (avatar != null) ? avatar : "";
        status = (status != null) ? status : "";

        // Handle the createAt field (assume it's the current date for now)
        Date createAt = new Date();

        // Create an Employee object with updated details
        Employee employee = new Employee(employeeID, fullName, email, password, gender, phoneNumber, address, avatar, roleID, status, createAt);

        // Update employee details using the UserDAO method
        boolean isUpdated = userDAO.updateUser(employee);

        // Redirect based on update result
        if (isUpdated) {
            response.sendRedirect("userlist");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
