/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Employee;
import Model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Anh Tuan
 */
public class DaoAccount extends DBContext {

    public String getEmail(int customerID) {
        String email = "";
        String sql = "SELECT [Email]\n"
                + "  FROM [dbo].[Customer]\n"
                + "  WHERE [CustomerID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                email = rs.getString("Email");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return email;
    }

    // lay nguoi dung Customer
    public Customer getCustomer(String email) {

        String sql = "SELECT [CustomerID]\n"
                + "      ,[FullName]\n"
                + "      ,[Email]\n"
                + "      ,[Password]\n"
                + "      ,[Gender]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[Address]\n"
                + "      ,[Avatar]\n"
                + "      ,[Status]\n"
                + "      ,[CreateAt]\n"
                + "  FROM [dbo].[Customer]\n"
                + "  WHERE [Email] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer a = new Customer(rs.getInt("CustomerID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Gender"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        rs.getString("Status"),
                        rs.getDate("CreateAt"));

                return a;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    // lay nguoi dung Employee
    public Employee getEmployee(String email) {

        String sql = "SELECT [EmployeeID]\n"
                + "      ,[FullName]\n"
                + "      ,[Email]\n"
                + "      ,[Password]\n"
                + "      ,[Gender]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[Address]\n"
                + "      ,[Avatar]\n"
                + "      ,[RoleID]\n"
                + "      ,[Status]\n"
                + "      ,[CreateAt]\n"
                + "  FROM [dbo].[Employee]\n"
                + "  WHERE [Email] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Employee a = new Employee(rs.getInt("EmployeeID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Gender"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        rs.getInt("RoleID"),
                        rs.getString("Status"),
                        rs.getDate("CreateAt"));

                return a;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    // doi password
    public void ChangePassWord(String email, String password) {
        String sql = "UPDATE [dbo].[Customer]\n"
                + "   SET [Password] = ?\n"
                + " WHERE [Email]= ? ";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, email);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    // tao customer voi uid lay duoc
    public int registerCustomer(Customer customer) {
        int customerID = -1;
        String sql = "INSERT INTO [dbo].[Customer]\n"
                + "           ([FullName]\n"
                + "           ,[Email]\n"
                + "           ,[Password]\n"
                + "           ,[Gender]\n"
                + "           ,[PhoneNumber]\n"
                + "           ,[Address]\n"
                + "           ,[Avatar]\n"
                + "           ,[Status]\n"
                + "           ,[CreateAt])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,GETDATE())";

        try {
            PreparedStatement st = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, customer.getFullName());
            st.setString(2, customer.getEmail());
            st.setString(3, customer.getPassword());
            st.setString(4, customer.getGender());
            st.setString(5, customer.getPhoneNumber());
            st.setString(6, customer.getAddress());
            st.setString(7, customer.getAvatar());
            st.setString(8, customer.getStatus());

            int row = st.executeUpdate();

            if (row > 0) {
                try (ResultSet key = st.getGeneratedKeys()) {
                    if (key.next()) {
                        customerID = key.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return customerID;
    }

    public void activeCustomer(int customerID) {
        String sql = "UPDATE [dbo].[Customer]\n"
                + "   SET [Status] = 'Active'\n"
                + " WHERE [CustomerID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerID);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
