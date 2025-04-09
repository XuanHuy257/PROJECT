/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Size;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sontu
 */
public class DaoSize {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;

    public List<Size> getAllSize() {
        String sql = "select *from Size";
        List<Size> l = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int sizeID = rs.getInt(1);
                String sizeName = rs.getString(2);
                Size s = new Size(sizeID, sizeName);
                l.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public void addNewSizeForProduct(int productID, int sizeID) {
        String sql = "INSERT INTO ProductDetails (\n"
                + "                                      ProductID,\n"
                + "                                      SizeID,\n"
                + "                                      Quantity,\n"
                + "                 			OriginalPrice,\n"
                + "                 			SellPrice\n"
                + "                                  ) \n"
                + "                                  VALUES (\n"
                + "                                      ?,    -- ProductID\n"
                + "                                      ?,    -- SizeID\n"
                + "                                      0,0,0     -- Quantity, giá trị này sẽ được truyền vào\n"
                + "                                  );";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setInt(2, sizeID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkAvalibleSizeProduct(int productID, int sizeID) {
        String check = "select ProductDetails.SizeID from ProductDetails\n"
                + "                 where ProductDetails.ProductID = ? and  ProductDetails.SizeID= ?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(check);
            ps.setInt(1, productID);
            ps.setInt(2, sizeID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getSizeIDbySizeName(String sizeName) {
        String sql = "select Size.SizeID from Size where Size.SizeName= ?";
        int sizeID = 0;
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setString(1, sizeName);
            rs = ps.executeQuery();
            while (rs.next()) {
                sizeID = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sizeID;
    }

    public static void main(String[] args) {
        DaoSize dao = new DaoSize();
        System.out.println(dao.checkAvalibleSizeProduct(1, 1));
    }
}
