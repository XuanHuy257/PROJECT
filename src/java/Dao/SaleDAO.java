/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Attribute;
import Model.Customer;
import Model.Order;
import Model.OrderDetail;
import Model.OrderDetailEmp;
import Model.OrderInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.OrderType;
import Model.ProductQty;
import Model.SaleAndOrder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anh Tuan
 */
public class SaleDAO extends DBContext {

    public Customer getCustomer(int cusid) {

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
                + "  WHERE [CustomerID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cusid);
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

    public OrderType getOrderForSale(int roleid, int empid) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n"
                + "    COUNT(CASE WHEN StatusID IN (1,7,8) THEN 1 END) AS ProcessOrder,\n"
                + "    COUNT(CASE WHEN StatusID = 9 THEN 1 END) AS CompletedOrder,\n"
                + "    COUNT(CASE WHEN StatusID = 3 THEN 1 END) AS RejectOrder\n"
                + "FROM [Orders]\n"
                + "WHERE 1= 1 ");
        if (roleid == 2) {
            sql.append(" AND [SaleID] = ? ");
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());
            if (roleid == 2) {
                st.setInt(1, empid);
            }

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderType c = new OrderType(rs.getInt("ProcessOrder"),
                        rs.getInt("CompletedOrder"),
                        rs.getInt("RejectOrder"));

                return c;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public List<Attribute> getAllStatus() {
        List<Attribute> list = new ArrayList<>();
        String sql = "SELECT [StatusID]\n"
                + "      ,[StatusName]\n"
                + "  FROM [dbo].[Status]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Attribute c = new Attribute(rs.getInt("StatusID"),
                        rs.getString("StatusName"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public List<OrderInfo> getOrderBySale(int roleid, int saleid, String datefrom, String dateto, String salename, String statusfilter, String status) {
        List<OrderInfo> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n"
                + "    Orders.OrderID, \n"
                + "    Orders.CustomerID,  \n"
                + "    Customer.FullName AS CustomerName,\n"
                + "    (SELECT Title \n"
                + "     FROM OrderDetails \n"
                + "     WHERE OrderID = Orders.OrderID \n"
                + "     AND OrderDetailID = (SELECT MIN(OrderDetailID) \n"
                + "                          FROM OrderDetails \n"
                + "                          WHERE OrderID = Orders.OrderID)) AS FirstTitle,\n"
                + "    COUNT(OrderDetails.ProductID) - 1 AS OtherProducts, \n"
                + "    SUM(OrderDetails.Quantity * OrderDetails.Price) AS TotalCost,\n"
                + "    Orders.StatusID, \n"
                + "    [Status].StatusName, \n"
                + "    Orders.PaymentMethod, \n"
                + "    Orders.CreatedOrder, \n"
                + "    Orders.SaleID, \n"
                + "    Employee.FullName AS SaleName,\n"
                + "    Orders.ReceiverMobile,\n"
                + "    Orders.ReceiverAddress,\n"
                + "    Orders.SaleNotes\n"
                + "FROM \n"
                + "    Orders \n"
                + "INNER JOIN \n"
                + "    Status ON Orders.StatusID = Status.StatusID \n"
                + "INNER JOIN \n"
                + "    Customer ON Orders.CustomerID = Customer.CustomerID \n"
                + "INNER JOIN \n"
                + "    Employee ON Orders.SaleID = Employee.EmployeeID \n"
                + "INNER JOIN \n"
                + "    OrderDetails ON Orders.OrderID = OrderDetails.OrderID\n"
                + "WHERE 1=1 ");
        if (roleid == 2) {
            sql.append("AND Orders.SaleID = ? ");
        }
        if (datefrom != null && !datefrom.isEmpty()) {
            sql.append("AND Orders.CreatedOrder >= ? ");
        }

        if (dateto != null && !dateto.isEmpty()) {
            sql.append("AND Orders.CreatedOrder <= ? ");
        }

        if (salename != null && !salename.isEmpty()) {
            sql.append("AND Employee.FullName LIKE ? ");
        }
        if (statusfilter != null && !statusfilter.isEmpty()) {
            sql.append("AND Orders.StatusID = ? ");
        }

        if (status != null && !status.isEmpty()) {
            if (status.equalsIgnoreCase("process")) {
                sql.append("AND Orders.StatusID IN (1,7,8) ");

            } else if (status.equalsIgnoreCase("completed")) {
                sql.append("AND Orders.StatusID = 9 ");

            } else {
                sql.append("AND Orders.StatusID = 3 ");

            }
        }
        sql.append("GROUP BY \n"
                + "    Orders.OrderID, \n"
                + "    Orders.CustomerID,  \n"
                + "    Customer.FullName,\n"
                + "    Orders.StatusID, \n"
                + "    [Status].StatusName, \n"
                + "    Orders.PaymentMethod, \n"
                + "    Orders.CreatedOrder, \n"
                + "    Orders.SaleID, \n"
                + "    Employee.FullName,\n"
                + "    Orders.ReceiverMobile,\n"
                + "    Orders.ReceiverAddress,\n"
                + "    Orders.SaleNotes");
        if (status != null && !status.isEmpty()) {
            sql.append(" ORDER BY Orders.StatusID ");
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());
            int count = 1;
            if (roleid == 2) {
                st.setInt(count++, saleid);
            }

            if (datefrom != null && !datefrom.isEmpty()) {
                st.setString(count++, datefrom);
            }

            if (dateto != null && !dateto.isEmpty()) {
                st.setString(count++, dateto);
            }

            if (salename != null && !salename.isEmpty()) {
                st.setString(count++, "%" + salename + "%");
            }
            if (statusfilter != null && !statusfilter.isEmpty()) {
                st.setString(count++, statusfilter);
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderInfo c = new OrderInfo(rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getString("FirstTitle"),
                        rs.getInt("OtherProducts"),
                        rs.getDouble("TotalCost"),
                        rs.getInt("StatusID"),
                        rs.getString("StatusName"),
                        rs.getString("PaymentMethod"),
                        rs.getDate("CreatedOrder"),
                        rs.getInt("SaleID"),
                        rs.getString("SaleName"),
                        rs.getString("ReceiverMobile"),
                        rs.getString("ReceiverAddress"),
                        rs.getString("SaleNotes"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<ProductQty> getAllProductQuantity() {
        List<ProductQty> list = new ArrayList<>();
        String sql = "SELECT [ProductID]\n"
                + "      ,[SizeID]\n"
                + "      ,[Quantity]\n"
                + "      ,[Hold]\n"
                + "  FROM [dbo].[ProductDetails]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductQty c = new ProductQty(
                        rs.getInt("ProductID"),
                        rs.getInt("sizeID"),
                        rs.getInt("Quantity"),
                        rs.getInt("Hold"));

                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public void updateHoldProduct(List<OrderDetail> orderdetail, List<ProductQty> products) {
        String sql = "UPDATE [dbo].[ProductDetails]\n"
                + "   SET [Hold] = [Hold] - ?\n"
                + " WHERE [ProductID] = ? AND [SizeID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            for (OrderDetail c : orderdetail) {
                for (ProductQty p : products) {
                    if (c.getProductID() == p.getProductID() && c.getSizeID() == p.getSizeID()) {
                        int orderQuantity = c.getQuantity();
                        st.setInt(1, orderQuantity);
                        st.setInt(2, c.getProductID());
                        st.setInt(3, c.getSizeID());
                        st.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateQuantityProduct(List<OrderDetail> orderdetail, List<ProductQty> products) {
        String sql = "UPDATE [dbo].[ProductDetails]\n"
                + "   SET [Quantity] = [Quantity] + ?\n"
                + " WHERE [ProductID] = ? AND [SizeID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            for (OrderDetail c : orderdetail) {
                for (ProductQty p : products) {
                    if (c.getProductID() == p.getProductID() && c.getSizeID() == p.getSizeID()) {
                        int orderQuantity = c.getQuantity();
                        st.setInt(1, orderQuantity);
                        st.setInt(2, c.getProductID());
                        st.setInt(3, c.getSizeID());
                        st.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<OrderDetail> getOrderDetailByID(String orderid) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT OrderDetails.OrderDetailID, OrderDetails.OrderID, OrderDetails.ProductID, OrderDetails.Title, OrderDetails.Quantity, OrderDetails.Thumbnail, OrderDetails.Price, OrderDetails.SizeID, Size.SizeName\n"
                + "FROM     OrderDetails INNER JOIN\n"
                + "                  Size ON OrderDetails.SizeID = Size.SizeID\n"
                + "				  WHERE OrderDetails.OrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetail c = new OrderDetail(rs.getInt("OrderDetailID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getString("Title"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getDouble("Price"),
                        rs.getInt("SizeID"),
                        rs.getString("SizeName"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public void updateOrderStatus(String orderid, String statusid) {
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [StatusID] = ?\n"
                + " WHERE [OrderID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, statusid);
            st.setString(2, orderid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<OrderDetailEmp> getOrderDetailForSale(String orderid) {
        List<OrderDetailEmp> list = new ArrayList<>();
        String sql = "SELECT OrderDetails.OrderDetailID, OrderDetails.OrderID, OrderDetails.ProductID, OrderDetails.Title, OrderDetails.Quantity, OrderDetails.Thumbnail, OrderDetails.Price, OrderDetails.SizeID, Size.SizeName, Category.CategoryName\n"
                + "FROM     Product INNER JOIN\n"
                + "                  OrderDetails ON Product.ProductID = OrderDetails.ProductID INNER JOIN\n"
                + "                  Size ON OrderDetails.SizeID = Size.SizeID INNER JOIN\n"
                + "                  Category ON Product.CategoryID = Category.CategoryID\n"
                + "				  WHERE OrderDetails.OrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetailEmp c = new OrderDetailEmp(rs.getInt("OrderDetailID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getString("Title"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getDouble("Price"),
                        rs.getInt("SizeID"),
                        rs.getString("SizeName"),
                        rs.getString("CategoryName"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public Order getOrderByID(String orderid) {
        String sql = "SELECT Orders.OrderID, Orders.CustomerID, Orders.ReceiverName, Orders.ReceiverGender, Orders.ReceiverEmail, Orders.ReceiverMobile, Orders.ReceiverAddress, Orders.ReceiverNotes, Orders.StatusID, Status.StatusName, \n"
                + "                  Orders.PaymentMethod, Orders.CreatedOrder, Orders.SaleID, Orders.SaleNotes\n"
                + "FROM     Orders INNER JOIN\n"
                + "                  Status ON Orders.StatusID = Status.StatusID\n"
                + "				  WHERE Orders.OrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderid);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Order c = new Order(rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getString("ReceiverName"),
                        rs.getString("ReceiverGender"),
                        rs.getString("ReceiverEmail"),
                        rs.getString("ReceiverMobile"),
                        rs.getString("ReceiverAddress"),
                        rs.getString("ReceiverNotes"),
                        rs.getInt("StatusID"),
                        rs.getString("StatusName"),
                        rs.getString("PaymentMethod"),
                        rs.getDate("CreatedOrder"),
                        rs.getInt("SaleID"),
                        rs.getString("SaleNotes")
                );

                return c;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<SaleAndOrder> getAllSale() {
        List<SaleAndOrder> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    Employee.EmployeeID, \n"
                + "    Employee.FullName, \n"
                + "    COALESCE(COUNT(Orders.OrderID), 0) AS TotalOrder\n"
                + "FROM \n"
                + "    Employee \n"
                + "LEFT JOIN \n"
                + "    Orders ON Orders.SaleID = Employee.EmployeeID AND Orders.StatusID IN (1, 7, 8)\n"
                + "WHERE \n"
                + "    Employee.RoleID = 2 AND Employee.Status = 'Active'\n"
                + "GROUP BY \n"
                + "    Employee.EmployeeID, \n"
                + "    Employee.FullName\n"
                + "ORDER BY \n"
                + "    Employee.EmployeeID;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SaleAndOrder c = new SaleAndOrder(rs.getInt("EmployeeID"),
                        rs.getString("FullName"),
                        rs.getInt("TotalOrder"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public Customer getCustomerCreateOrder(int customerid) {

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
                + "  WHERE [CustomerID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerid);
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

    public void updateSaleAndNotes(String saleid, String salenotes, String orderid) {
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [SaleID] = ?\n"
                + "      ,[SaleNotes] = ?\n"
                + " WHERE [OrderID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, saleid);
            st.setString(2, salenotes);
            st.setString(3, orderid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updatePaymentStatus(String orderID) {
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [PaymentStatus] = 'Paid'\n"
                + " WHERE [OrderID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String getPaymentStauts(String orderID) {
        String status = "";
        String sql = "SELECT [PaymentStatus]\n"
                + "  FROM [dbo].[Orders]\n"
                + "  WHERE [OrderID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                status = rs.getString("PaymentStatus");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return status;
    }
}
