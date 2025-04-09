/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Feedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class FeedbackDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Feedback> getAllFeedbackByProductId(int productId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Feedback> list = new ArrayList<>();
        String query = "select F.*,C.Avatar\n"
                + "from Feedback F\n"
                + "left join Customer C on F.CustomerID = C.CustomerID\n"
                + "where F.ProductID = ? and F.Status = 1 \n"
                + "order by F.FeedbackID desc";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(rs.getInt("FeedbackID"),
                        rs.getInt("ProductID"),
                        rs.getInt("CustomerID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getInt("Rating"),
                        rs.getString("FeedbackText"),
                        rs.getString("ImageURL"),
                        rs.getTimestamp("FeedbackDate"),
                        rs.getString("Gender")
                );
                f.setAvatar(rs.getString("Avatar"));
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void addFeedback(Feedback feedback) {
        String query = "INSERT INTO [Feedback]([ProductID]\n"
                + "      ,[CustomerID]\n"
                + "      ,[Rating]\n"
                + "      ,[FeedbackText]\n"
                + "      ,[ImageURL], Status, FullName, Email,PhoneNumber,Gender,FeedbackDate)\n"
                + "VALUES (?, ?, ?, ?, ?, 1,?,?,?,?,GETDATE())";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, feedback.getProductID());
            ps.setInt(2, feedback.getUserID());
            ps.setInt(3, feedback.getRating());
            ps.setString(4, feedback.getFeedbackText());
            ps.setString(5, feedback.getImageURL());
            ps.setString(6, feedback.getFullName());
            ps.setString(7, feedback.getEmail());
            ps.setString(8, feedback.getPhoneNumber());
            ps.setString(9, feedback.getGender());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getAvgRating(int productId) {
        double rating = 0;
        String query = "SELECT \n"
                + "    ProductID, \n"
                + "    ROUND(AVG(CAST(Rating AS FLOAT)), 1) AS AverageRating\n"
                + "FROM \n"
                + "    [dbo].[Feedback]\n"
                + "where ProductID = ? and Status = 1 \n"
                + "GROUP BY \n"
                + "    ProductID;";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);

            rs = ps.executeQuery();
            while (rs.next()) {
                rating = rs.getDouble("AverageRating");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rating;
    }

    public List<Integer> getNumberOfFBByRating(int productId) {
        List<Integer> list = new ArrayList<>();
        String query = "WITH Ratings AS (\n"
                + "    SELECT 1 AS Rating\n"
                + "    UNION ALL\n"
                + "    SELECT 2\n"
                + "    UNION ALL\n"
                + "    SELECT 3\n"
                + "    UNION ALL\n"
                + "    SELECT 4\n"
                + "    UNION ALL\n"
                + "    SELECT 5\n"
                + ")\n"
                + "SELECT \n"
                + "    r.Rating, \n"
                + "    COUNT(f.Rating) AS FeedbackCount\n"
                + "FROM \n"
                + "    Ratings r\n"
                + "LEFT JOIN \n"
                + "    Feedback f\n"
                + "ON \n"
                + "    r.Rating = f.Rating \n"
                + "    AND f.ProductID = ? \n"
                + "    AND f.Status = 1\n"
                + "GROUP BY \n"
                + "    r.Rating\n"
                + "ORDER BY \n"
                + "    r.Rating DESC;";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public boolean isBought(int userId, int productId) {
        String query = "  select * from OrderDetails OD\n"
                + "  left join Orders O on O.OrderID = OD.OrderID\n"
                + "  where O.CustomerID = ? and OD.ProductID = ? AND O.StatusID = 9";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productId);

            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Feedback> getAllFeedbacks(String status, String productId, String star) {
        List<Feedback> list = new ArrayList<>();
        String query = "select F.*\n"
                + " , P.Title\n"
                + "from Feedback F left join Product P on F.ProductID = P.ProductID\n"
                + "where (? = '' or F.Status = ?) and (? = '' or F.ProductId = ?) and (? = '' or F.Rating = ?) "
                + "order by F.FeedbackID desc";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, status);
            ps.setString(3, productId);
            ps.setString(4, productId);
            ps.setString(5, star);
            ps.setString(6, star);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(rs.getInt("FeedbackID"),
                        rs.getInt("ProductID"),
                        rs.getInt("CustomerID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getInt("Rating"),
                        rs.getString("FeedbackText"),
                        rs.getString("ImageURL"),
                        rs.getTimestamp("FeedbackDate"),
                        rs.getBoolean("Status"),
                        rs.getString("Title")
                );
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Feedback getFeedbackById(String id) {
        String query = "select F.*"
                + " , P.Title\n"
                + "from Feedback F left join Product P on F.ProductID = P.ProductID\n"
                + "where F.FeedbackID = ? "
                + "order by F.FeedbackID desc";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(rs.getInt("FeedbackID"),
                        rs.getInt("ProductID"),
                        rs.getInt("CustomerID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getInt("Rating"),
                        rs.getString("FeedbackText"),
                        rs.getString("ImageURL"),
                        rs.getTimestamp("FeedbackDate"),
                        rs.getBoolean("Status"),
                        rs.getString("Title")
                );
                return f;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void changeStatusFeedback(Feedback p) {
        String query = "Update [Feedback] set Status = ? where FeedbackID = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setBoolean(1, p.isStatus());
            ps.setInt(2, p.getFeedbackID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
