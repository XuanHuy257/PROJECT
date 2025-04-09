/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sontu
 */
public class DaoImage extends DBContext {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;

    public List<Image> getImagebyProductID(int productID) {
        String sql = "select ProductImage.ImageID, ProductImage.Image\n"
                + "from ProductImage where ProductID = ?";
        List<Image> l = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int imgID = rs.getInt(1);
                String img = rs.getString(2);
                Image i = new Image(imgID, img);
                l.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public int countImage(int productID) {
        String sql = "select ProductImage.ImageID, ProductImage.Image\n"
                + "from ProductImage where ProductID = ?";
        int count = 0;
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public void createImageforProduct(int productID) {
        String sql = "select ProductImage.ImageID, ProductImage.Image\n"
                + "from ProductImage where ProductID = ?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return;
            } else {
                String createImage = "INSERT INTO ProductImage (ProductID, Image) VALUES (?, ?)";
                try {
                    connection = new DBContext().connection;
                    ps = connection.prepareStatement(createImage);
                    ps.setInt(1, productID);
                    ps.setString(2, "a");
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    connection = new DBContext().connection;
                    ps = connection.prepareStatement(createImage);
                    ps.setInt(1, productID);
                    ps.setString(2, "a");
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    connection = new DBContext().connection;
                    ps = connection.prepareStatement(createImage);
                    ps.setInt(1, productID);
                    ps.setString(2, "a");
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DaoImage dao = new DaoImage();
        dao.createImageforProduct(32);
    }
}
