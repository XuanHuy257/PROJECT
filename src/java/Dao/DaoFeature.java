/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Feature;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sontu
 */
public class DaoFeature {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;

    public List<Feature> getAllFeature() {
        String sql = "select *from Feature";
        List<Feature> l = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int featureID = rs.getInt(1);
                String featureName = rs.getString(2);
                Feature f = new Feature(featureID, featureName);
                l.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public int getFeatureIDbyFeatureName(String featureName) {
        String sql = "select Feature.FeatureID from Feature\n"
                + "where FeatureName = ?;";
        int featureID =0;
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setString(1, featureName);
            rs=ps.executeQuery();
            while(rs.next()){
                featureID = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return featureID;
    }
    public static void main(String[] args) {
        DaoFeature d = new DaoFeature();
        System.out.println(d.getFeatureIDbyFeatureName("Normal"));
    }
}
