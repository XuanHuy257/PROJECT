/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.AttributeMore;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anh Tuan
 */
public class AttributeDAO extends DBContext {

    public List<AttributeMore> getAllStatus() {
        List<AttributeMore> list = new ArrayList<>();
        String sql = "SELECT [StatusID]\n"
                + "      ,[StatusName]\n"
                + "      ,[StatusDescription]\n"
                + "  FROM [dbo].[Status]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                AttributeMore c = new AttributeMore(rs.getInt("StatusID"),
                        rs.getString("StatusName"),
                        rs.getString("StatusDescription"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public void addStatus(String statusname, String statusdescription) {
        String sql = "INSERT INTO [dbo].[Status]\n"
                + "           ([StatusName]\n"
                + "           ,[StatusDescription])\n"
                + "     VALUES\n"
                + "           (?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, statusname);
            st.setString(2, statusdescription);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateStatus(String statusname, String statusdescription, String statusid) {
        String sql = "UPDATE [dbo].[Status]\n"
                + "   SET [StatusName] = ?\n"
                + "      ,[StatusDescription] = ?\n"
                + " WHERE [StatusID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, statusname);
            st.setString(2, statusdescription);
            st.setString(3, statusid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteStatus(String statusid) {
        String sql = "DELETE FROM [dbo].[Status]\n"
                + "      WHERE [StatusID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, statusid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
