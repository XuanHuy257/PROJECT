/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Brand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sontu
 */
public class DaoBrand {
     private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;
    public List<Brand> getAllBrand(){
        String sql ="Select *from Brand";
        List<Brand> list = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int brandID = rs.getInt(1);
                String brandName = rs.getString(2);
                Brand b = new  Brand(brandID, brandName);
                 list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public int getBrandIDbyName(String brandName){
        String sql = "select Brand.BrandID from Brand where Brand.BrandName= ?;";
        int brandID =0;
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setString(1, brandName);
            rs = ps.executeQuery();
            while (rs.next()) {                
                brandID = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brandID;
    }
}
