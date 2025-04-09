package Dao;

import Model.Employee;
import Model.FeedbackStats;
import Model.OrderTrend;
import Model.Settings; // Ensure this is your correct import for the Settings model
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDAO extends DBContext {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private java.sql.Connection connection = null;

    public List<Settings> getAllSettings() {
        List<Settings> list = new ArrayList<>();
        String query = "SELECT * FROM Settings";

        try {
            // Assuming DBContext is already defined elsewhere for connection handling
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);              // ID
                String type = rs.getString(2);      // Type
                String value = rs.getString(3);     // Value
                int order = rs.getInt(4);           // Order
                int statusInt = rs.getInt(5);       // Status as integer

                // Convert statusInt to boolean
                boolean status = (statusInt == 1);

                // Create a Settings object and add it to the list
                Settings setting = new Settings(id, type, value, order, status);
                list.add(setting);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources to avoid memory leaks
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return list;
    }

    public Settings getSettingByID(String settingID) {
        Settings setting = null;  // Initialize the Settings object
        String query = "Select * From Settings where ID = ?";

        // Use try-with-resources to handle resource management
        try (java.sql.Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, settingID);  // Set the parameter for SettingID
            try (ResultSet rs = ps.executeQuery()) {
                // Check if a result is returned
                if (rs.next()) {
                    // Retrieving data from the result set
                    int id = rs.getInt("Id");
                    String type = rs.getString("Type");
                    String value = rs.getString("Value");
                    int order = rs.getInt("Order");
                    boolean status = rs.getBoolean("Status"); // Assuming Status is stored as a boolean

                    // Create a Settings object and assign it to the variable
                    setting = new Settings(id, type, value, order, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return setting;  // Return the Settings object
    }

    public boolean updateSetting(Settings setting) {
        String query = "UPDATE Settings SET Type = ?, Value = ?, [Order] = ?, Status = ? WHERE ID = ?";
        boolean rowUpdated = false;

        // Using try-with-resources for automatic resource management
        try (java.sql.Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query)) {

            // Setting parameters from the setting object
            ps.setString(1, setting.getType());
            ps.setString(2, setting.getValue());
            ps.setInt(3, setting.getOrder());
            ps.setBoolean(4, setting.isStatus()); // Assuming status is a boolean
            ps.setInt(5, setting.getId()); // Use getSettingID() method

            // Execute the update
            int rowsAffected = ps.executeUpdate();
            rowUpdated = (rowsAffected > 0); // Update successful if rows were affected

        } catch (Exception e) {
            e.printStackTrace(); // Log any exceptions that occur
        }

        return rowUpdated;
    }

    public boolean addSetting(Settings setting) {
        String query = "INSERT INTO Settings (Type, Value, [Order], Status) VALUES (?, ?, ?, ?)";
        boolean rowAdded = false;

        // Using try-with-resources for automatic resource management
        try (java.sql.Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query)) {

            // Setting parameters from the setting object
            ps.setString(1, setting.getType());
            ps.setString(2, setting.getValue());
            ps.setInt(3, setting.getOrder());
            ps.setBoolean(4, setting.isStatus()); // Assuming status is a boolean

            // Execute the insert
            int rowsAffected = ps.executeUpdate();
            rowAdded = (rowsAffected > 0); // Insert successful if rows were affected

        } catch (Exception e) {
            e.printStackTrace(); // Log any exceptions that occur
        }

        return rowAdded;
    }

    public boolean deleteSetting(int id) {
        String query = "DELETE FROM Settings WHERE ID = ?";
        boolean rowDeleted = false;

        try (java.sql.Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query)) {

            // Set the ID parameter
            ps.setInt(1, id);

            // Execute the delete
            int rowsAffected = ps.executeUpdate();
            rowDeleted = (rowsAffected > 0); // Delete successful if rows were affected

        } catch (Exception e) {
            e.printStackTrace(); // Log any exceptions that occur
        }

        return rowDeleted;
    }

// In your DAO
    public String getRoleNameById(int roleID) {
        String roleName = "";
        String query = "SELECT roleName FROM Role WHERE roleID = ?";

        try (java.sql.Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, roleID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    roleName = rs.getString("roleName");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleName;
    }

    public Map<String, Integer> getOrderStatistics() throws Exception {
        Map<String, Integer> orderStats = new HashMap<>();
        String query = "  SELECT \n"
                + "    SUM(CASE WHEN s.[StatusName] = 'Completed' THEN 1 ELSE 0 END) AS successOrders,\n"
                + "    SUM(CASE WHEN s.[StatusName] = 'Reject' THEN 1 ELSE 0 END) AS cancelledOrders,\n"
                + "    SUM(CASE WHEN s.[StatusName] = 'Pending' THEN 1 ELSE 0 END) AS submittedOrders\n"
                + "FROM \n"
                + "    [Orders] o\n"
                + "JOIN \n"
                + "    [Status] s\n"
                + "    ON o.[StatusID] = s.[StatusID];";

        try (java.sql.Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                orderStats.put("successOrders", rs.getInt("successOrders"));
                orderStats.put("cancelledOrders", rs.getInt("cancelledOrders"));
                orderStats.put("submittedOrders", rs.getInt("submittedOrders"));
            }
        } catch (Exception e) {
            // Handle exception, optionally log or rethrow the exception
            throw new Exception("Error fetching order statistics", e);
        }

        return orderStats;
    }

    public int getNewCustomersCount() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Customer WHERE createAt >= DATEADD(day, -30, GETDATE())"; // Assuming registration_date is a column in your Customers table

        try (java.sql.Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // Method to get the count of newly bought customers
    public int getNewlyBoughtCustomersCount() {
        int count = 0;
        String query = "SELECT COUNT(DISTINCT CustomerID) FROM Orders WHERE CreatedOrder >= DATEADD(day, -30, GETDATE())"; // Assuming order_date is in your Orders table

        try (java.sql.Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //feedback statistic
    public List<FeedbackStats> getFeedbackStatsByCategory() {
        List<FeedbackStats> feedbackStatsList = new ArrayList<>();
        String query = "SELECT c.CategoryID, c.CategoryName, COUNT(f.FeedbackID) AS TotalFeedback, AVG(f.Rating) AS AverageStar \n"
                + "                FROM Category c \n"
                + "                LEFT JOIN Feedback f ON c.CategoryID = f.ProductID \n"
                + "                GROUP BY c.CategoryID, c.CategoryName \n"
                + "                 ORDER BY c.CategoryID";

        try (java.sql.Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int categoryId = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                int totalFeedback = rs.getInt("TotalFeedback");
                double averageStar = rs.getDouble("AverageStar");
                feedbackStatsList.add(new FeedbackStats(categoryId, categoryName, averageStar, totalFeedback));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
        return feedbackStatsList;
    }

//    public List<OrderStatistics> getOrderCountLast7Days() {
//        List<OrderStatistics> statistics = new ArrayList<>();
//        String sql = "SELECT \n"
//                + "    CAST(CreatedOrder AS DATE) AS orderDate, \n"
//                + "    COUNT(*) AS orderCount \n"
//                + "FROM \n"
//                + "    [SWP26].[dbo].[Orders]\n"
//                + "WHERE \n"
//                + "    CreatedOrder >= DATEADD(DAY, -7, GETDATE()) \n"
//                + "GROUP BY \n"
//                + "    CAST(CreatedOrder AS DATE) \n"
//                + "ORDER BY \n"
//                + "    orderDate;";
//        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                Date orderDate = resultSet.getDate("orderDate");
//                int orderCount = resultSet.getInt("orderCount");
//                statistics.add(new OrderStatistics(orderDate, orderCount));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return statistics;
//    }
    public List<OrderTrend> getOrderTrends() {
        List<OrderTrend> orderTrends = new ArrayList<>();
        String query = "SELECT \n" +
"    CAST(CreatedOrder AS DATE) AS order_date, \n" +
"    COUNT(*) AS total_orders,\n" +
"    SUM(CASE WHEN StatusID = 9 THEN 1 ELSE 0 END) AS successful_orders\n" +
"FROM \n" +
"    [Orders]\n" +
"WHERE \n" +
"    CreatedOrder >= DATEADD(DAY, -7, GETDATE())\n" +
"GROUP BY \n" +
"    CAST(CreatedOrder AS DATE)\n" +
"ORDER BY \n" +
"    order_date ASC;";

        try (java.sql.Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String orderDate = rs.getString("order_date");
                int totalOrders = rs.getInt("total_orders");
                int successfulOrders = rs.getInt("successful_orders");
                orderTrends.add(new OrderTrend(orderDate, totalOrders, successfulOrders));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderTrends;
    }

    public double getTotalRevenue() {
        double totalRevenue = 0.0;
        String query = "SELECT \n" +
"    SUM(od.Quantity * od.Price) AS TotalRevenue\n" +
"FROM \n" +
"    OrderDetails AS od\n" +
"JOIN \n" +
"    Orders AS o ON od.OrderID = o.OrderID\n" +
"WHERE \n" +
"    o.statusID = 9;";

        try (java.sql.Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                totalRevenue = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
        }
        return totalRevenue;
    }
    
       public void insertEmployee(Employee employee) {
    String query = "INSERT INTO [Employee] " +
                 "([FullName], [Email], [Password], [Gender], [PhoneNumber], [Address], [Avatar], [RoleID], [Status], [CreateAt]) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";

    try (java.sql.Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query)) {
        
        ps.setString(1, employee.getFullName());
        ps.setString(2, employee.getEmail());
        ps.setString(3, employee.getPassword());
        ps.setString(4, employee.getGender());
        ps.setString(5, employee.getPhoneNumber());
        ps.setString(6, employee.getAddress());
        ps.setString(7, employee.getAvatar());
        ps.setInt(8, employee.getRoleId()); // Ensure the method in Employee class is getRoleID()
        ps.setString(9, employee.getStatus());
       

        ps.executeUpdate(); // This will execute the insert statement
    }catch (Exception e) {
        e.printStackTrace(); // Log the SQL exception
    }
        // Log any other exceptions
        
}

    public static void main(String[] args) {
        // Create an instance of the EmployeeDAO
        AdminDAO employeeDAO = new AdminDAO();

        // Create a new Employee object
        Employee employee = new Employee();
        employee.setFullName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setPassword("password123"); // Make sure to hash passwords in a real application
        employee.setGender("Male");
        employee.setPhoneNumber("1234567890");
        employee.setAddress("123 Main St, Anytown, USA");
        employee.setAvatar("http://example.com/avatar.jpg");
        employee.setRoleId(1); // Set an appropriate Role ID
        employee.setStatus("Active");
       

        // Call the insertEmployee method to add the employee to the database
        try {
            employeeDAO.insertEmployee(employee);
            System.out.println("Employee inserted successfully.");
        } catch (Exception e) {
            System.out.println("Error inserting employee: " + e.getMessage());
        }
    }


//    public double getTotalRevenue() throws Exception {
//        String query = "SELECT SUM(TotalCost) AS TotalRevenue FROM Orders";
//        try (PreparedStatement statement = connection.prepareStatement(query);
//             ResultSet resultSet = statement.executeQuery()) {
//            if (resultSet.next()) {
//                return resultSet.getDouble("TotalRevenue");
//            }
//        }
//        return 0;
//    }
}

//

