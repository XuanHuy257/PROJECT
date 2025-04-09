/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Slider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sontu
 */
public class DaoSlider {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;

    public List<Slider> getAllSlider() {
        List<Slider> list = new ArrayList<>();
        String sql = "Select *from Slider";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int sliderID = rs.getInt(1);
                String title = rs.getString(2);
                String img = rs.getString(3);
                String backLink = rs.getString(4);
                int status = rs.getInt(5);
                int productID = rs.getInt(6);
                Slider s = new Slider(sliderID, title, img, backLink, status, productID);
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Slider> getActiveSlider() {
        List<Slider> list = new ArrayList<>();
        String sql = "Select *from Slider where status = 1";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int sliderID = rs.getInt(1);
                String title = rs.getString(2);
                String img = rs.getString(3);
                String backLink = rs.getString(4);
                int status = rs.getInt(5);
                int productID = rs.getInt(6);
                Slider s = new Slider(sliderID, title, img, backLink, status, productID);
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void changeStatusSlider(int sliderID, int status) {
        String sql = "UPDATE Slider\n"
                + "SET status = ?\n"
                + "WHERE id =?;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, sliderID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public Slider getSliderByID(int sliderID){
        String sql = "Select *from Slider where id = ?";
        Slider s = null;
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, sliderID);
            rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String img = rs.getString(3);
                String backlink = rs.getString(4);
                int status = rs.getInt(5);
                int productID = rs.getInt(6);
                s = new Slider(sliderID, title, img, backlink, status, productID);
            }
        } catch (Exception e) {
        }
        return s;
    }

    public static void main(String[] args) {
        DaoSlider dao = new DaoSlider();
        Slider s = dao.getSliderByID(1);
        System.out.println(s.getStatus());
    }
}
