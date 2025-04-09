/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Post;
import Model.PostCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class BlogCategoryDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<PostCategory> getAllBlogCategory() {
        List<PostCategory> list = new ArrayList<>();
        String query = "SELECT * FROM PostCategory";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PostCategory(
                        rs.getInt("PostCategoryID"),
                        rs.getString("PostCategoryName")
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
