/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Constant.Constant;
import Model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class BlogDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Post> getAllBlogs(String txtSearch, int category, Integer page) {
        List<Post> list = new ArrayList<>();
        String query = "SELECT P.*, \n"
                + "       PC.PostCategoryName,E.FullName\n"
                + "FROM Post P\n"
                + "LEFT JOIN PostCategory PC ON P.PostCategoryID = PC.PostCategoryID\n"
                + "LEFT JOIN Employee E ON E.EmployeeID = P.EmployeeID\n"
                + "WHERE (? = '' OR P.Title LIKE '%' + ? + '%')\n"
                + "  AND (? = 0 OR PC.PostCategoryID = ?) and P.PostFlag = 0 and P.Status = 'Active'\n"
                + "ORDER BY P.PostID desc\n";
        if (page != null) {
            query += "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY;";
        }
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, txtSearch);
            ps.setString(2, txtSearch);
            ps.setInt(3, category);
            ps.setInt(4, category);
            if (page != null) {
                ps.setInt(5, page * Constant.BLOGS_PER_PAGE - Constant.BLOGS_PER_PAGE);
                ps.setInt(6, Constant.BLOGS_PER_PAGE);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Post(rs.getInt("PostID"),
                        rs.getInt("EmployeeID"),
                        rs.getString("Title"),
                        rs.getString("PostImg"),
                        rs.getString("PostContent"),
                        rs.getDate("PostDate"),
                        rs.getString("PostBrief"),
                        rs.getBoolean("PostFlag"),
                        rs.getInt("PostCategoryID"),
                        rs.getString("FullName"),
                        rs.getString("PostCategoryName"),
                        rs.getString("Status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Post> getTop3LastestBlogs() {
        List<Post> list = new ArrayList<>();
        String query = "SELECT top 3 P.*, \n"
                + "       PC.PostCategoryName,E.FullName\n"
                + "FROM Post P\n"
                + "LEFT JOIN PostCategory PC ON P.PostCategoryID = PC.PostCategoryID\n"
                + "LEFT JOIN Employee E ON E.EmployeeID = P.EmployeeID\n"
                + "where P.PostFlag = 0 and P.Status = 'Active' "
                + "order by P.PostID desc\n";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Post(rs.getInt("PostID"),
                        rs.getInt("EmployeeID"),
                        rs.getString("Title"),
                        rs.getString("PostImg"),
                        rs.getString("PostContent"),
                        rs.getDate("PostDate"),
                        rs.getString("PostBrief"),
                        rs.getBoolean("PostFlag"),
                        rs.getInt("PostCategoryID"),
                        rs.getString("FullName"),
                        rs.getString("PostCategoryName"),
                        rs.getString("Status")
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Post getBlogById(int id) {
        String query = "SELECT P.*, \n"
                + "       PC.PostCategoryName,E.FullName\n"
                + "FROM Post P\n"
                + "LEFT JOIN PostCategory PC ON P.PostCategoryID = PC.PostCategoryID\n"
                + "LEFT JOIN Employee E ON E.EmployeeID = P.EmployeeID\n"
                + " where P.PostID = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Post(rs.getInt("PostID"),
                        rs.getInt("EmployeeID"),
                        rs.getString("Title"),
                        rs.getString("PostImg"),
                        rs.getString("PostContent"),
                        rs.getDate("PostDate"),
                        rs.getString("PostBrief"),
                        rs.getBoolean("PostFlag"),
                        rs.getInt("PostCategoryID"),
                        rs.getString("FullName"),
                        rs.getString("PostCategoryName"),
                        rs.getString("Status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> getAllBlogsByAuthor(int userId) {
        List<Post> list = new ArrayList<>();
        String query = "SELECT P.*, \n"
                + "       PC.PostCategoryName,E.FullName\n"
                + "FROM Post P\n"
                + "LEFT JOIN PostCategory PC ON P.PostCategoryID = PC.PostCategoryID\n"
                + "LEFT JOIN Employee E ON E.EmployeeID = P.EmployeeID\n"
                + "WHERE P.EmployeeID = ?\n"
                + "ORDER BY P.PostID desc\n";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Post(rs.getInt("PostID"),
                        rs.getInt("EmployeeID"),
                        rs.getString("Title"),
                        rs.getString("PostImg"),
                        rs.getString("PostContent"),
                        rs.getDate("PostDate"),
                        rs.getString("PostBrief"),
                        rs.getBoolean("PostFlag"),
                        rs.getInt("PostCategoryID"),
                        rs.getString("FullName"),
                        rs.getString("PostCategoryName"),
                        rs.getString("Status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void changeFeaturedPost(Post p) {
        String query = "Update [Post] set PostFlag = ? where PostID = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setBoolean(1, p.isPostFlag());
            ps.setInt(2, p.getPostID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPost(Post post) {
        String query = "INSERT INTO [Post]([EmployeeID]\n"
                + "      ,[Title]\n"
                + "      ,[PostImg]\n"
                + "      ,[PostContent]\n"
                + "      ,[PostDate], PostBrief, PostFlag, PostCategoryID, Status)\n"
                + "VALUES (?, ?, ?, ?, GETDATE(), ?, 0, ?, 'Active')";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, post.getUserID());
            ps.setString(2, post.getTitle());
            ps.setString(3, post.getPostImg());
            ps.setString(4, post.getPostContent());
            ps.setString(5, post.getPostBrief());
            ps.setInt(6, post.getPostCategoryID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePost(Post post) {
        String query = "Update [Post] set Title = ?\n"
                + "      ,[PostImg] = ?\n"
                + "      ,[PostContent] = ?\n"
                + "      , PostBrief = ?, PostFlag = ?, PostCategoryID = ?, Status = ?\n"
                + "Where PostID = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getPostImg());
            ps.setString(3, post.getPostContent());
            ps.setString(4, post.getPostBrief());
            ps.setBoolean(5, post.isPostFlag());
            ps.setInt(6, post.getPostCategoryID());
            ps.setString(7, post.getStatus());
            ps.setInt(8, post.getPostID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
