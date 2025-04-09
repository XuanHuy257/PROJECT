/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.ProductFeedback;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Anh Tuan
 */
public class DAOFeedback extends DBContext {

    public List<ProductFeedback> ListtoFeedback(int customerid) {
        List<ProductFeedback> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    OrderDetails.ProductID,\n"
                + "    MAX(OrderDetails.OrderID) AS OrderID,  \n"
                + "    MIN(OrderDetails.Thumbnail) AS Thumbnail, \n"
                + "    MIN(OrderDetails.Title) AS Title, \n"
                + "    SUM(OrderDetails.Quantity) AS Quantity  \n"
                + "FROM \n"
                + "    OrderDetails \n"
                + "INNER JOIN \n"
                + "    Orders ON OrderDetails.OrderID = Orders.OrderID \n"
                + "WHERE \n"
                + "    Orders.CustomerID = ? \n"
                + "    AND Orders.StatusID = 9  \n"
                + "    AND NOT EXISTS (\n"
                + "        SELECT 1 \n"
                + "        FROM Feedback \n"
                + "        WHERE Feedback.ProductID = OrderDetails.ProductID \n"
                + "        AND Feedback.CustomerID = Orders.CustomerID\n"
                + "    )\n"
                + "GROUP BY \n"
                + "    OrderDetails.ProductID;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductFeedback c = new ProductFeedback(rs.getInt("ProductID"),
                        rs.getInt("OrderID"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getString("Title")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }
}
