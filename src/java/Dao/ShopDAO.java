/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Product;
import Model.Attribute;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anh Tuan
 */
public class ShopDAO extends DBContext {

    public List<Product> getAllProduct(Integer page, String category, String brand, String search, String sort) {
        List<Product> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n"
                + "    Product.ProductID, \n"
                + "    Product.Title, \n"
                + "    Product.BriefInfo, \n"
                + "    Product.Thumbnail, \n"
                + "    Product.FeatureID, \n"
                + "    Brand.Discount, \n"
                + "    (SELECT TOP 1 ProductDetails.SellPrice \n"
                + "     FROM ProductDetails \n"
                + "     WHERE ProductDetails.ProductID = Product.ProductID \n"
                + "     ORDER BY ProductDetails.SizeID ASC) AS FirstPrice,\n"
                + "    (SELECT SUM(ProductDetails.Quantity) \n"
                + "     FROM ProductDetails \n"
                + "     WHERE ProductDetails.ProductID = Product.ProductID) AS TotalQuantity\n"
                + "FROM \n"
                + "    Product\n"
                + "INNER JOIN \n"
                + "    Brand ON Product.BrandID = Brand.BrandID\n"
                + "WHERE Product.[Status] = 'Active' ");
        if (category != null && !category.isEmpty()) {
            sql.append(" AND Product.CategoryID = ? ");
        }

        if (brand != null && !brand.isEmpty()) {
            sql.append(" AND Product.BrandID = ? ");
        }

        if (search != null && !search.isEmpty()) {
            sql.append(" AND Product.Title LIKE ? ");
        }

        if (sort != null) {
            if (sort.equalsIgnoreCase("ASC")) {
                sql.append(" ORDER BY \n"
                        + "    ((SELECT TOP 1 ProductDetails.SellPrice \n"
                        + "      FROM ProductDetails \n"
                        + "      WHERE ProductDetails.ProductID = Product.ProductID \n"
                        + "      ORDER BY ProductDetails.SizeID ASC) * \n"
                        + "      (1 - Brand.Discount / 100)) ASC  ");
            } else if (sort.equalsIgnoreCase("DESC")) {
                sql.append(" ORDER BY \n"
                        + "    ((SELECT TOP 1 ProductDetails.SellPrice \n"
                        + "      FROM ProductDetails \n"
                        + "      WHERE ProductDetails.ProductID = Product.ProductID \n"
                        + "      ORDER BY ProductDetails.SizeID ASC) * \n"
                        + "      (1 - Brand.Discount / 100)) DESC  ");

            } else {
                sql.append(" ORDER BY Product.ReleaseDate ASC ");
            }
        } else {
            sql.append(" ORDER BY Product.ReleaseDate ASC ");
        }

        if (page
                != null) {
            sql.append(" OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY;");
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());
            int count = 1;

            if (category != null && !category.isEmpty()) {
                st.setString(count++, category);
            }

            if (brand != null && !brand.isEmpty()) {
                st.setString(count++, brand);
            }

            if (search != null && !search.isEmpty()) {
                st.setString(count++, "%" + search + "%");
            }

            if (page != null) {
                st.setInt(count++, page * com.vnpay.common.Config.PER_PAGE - com.vnpay.common.Config.PER_PAGE);
                st.setInt(count++, com.vnpay.common.Config.PER_PAGE);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product c = new Product(rs.getInt("ProductID"),
                        rs.getString("Title"),
                        rs.getString("BriefInfo"),
                        rs.getString("Thumbnail"),
                        rs.getInt("FeatureID"),
                        rs.getDouble("Discount"),
                        rs.getDouble("FirstPrice"),
                        rs.getInt("TotalQuantity"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public List<Attribute> ProductCategory() {
        List<Attribute> list = new ArrayList<>();
        String sql = "SELECT [CategoryID]\n"
                + "      ,[CategoryName]\n"
                + "  FROM [dbo].[Category]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Attribute c = new Attribute(rs.getInt("CategoryID"),
                        rs.getString("CategoryName"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public List<Attribute> ProductBrand() {
        List<Attribute> list = new ArrayList<>();
        String sql = "SELECT [BrandID]\n"
                + "      ,[BrandName]\n"
                + "  FROM [dbo].[Brand]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Attribute c = new Attribute(rs.getInt("BrandID"),
                        rs.getString("BrandName"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public Product getLastProduct() {
        String sql = "SELECT TOP 1\n"
                + "    Product.ProductID, \n"
                + "    Product.Title, \n"
                + "    Product.BriefInfo, \n"
                + "    Product.Thumbnail, \n"
                + "    Product.FeatureID, \n"
                + "    Brand.Discount, \n"
                + "    (SELECT TOP 1 ProductDetails.SellPrice \n"
                + "     FROM ProductDetails \n"
                + "     WHERE ProductDetails.ProductID = Product.ProductID \n"
                + "     ORDER BY ProductDetails.SizeID ASC) AS FirstPrice,\n"
                + "    (SELECT SUM(ProductDetails.Quantity) \n"
                + "     FROM ProductDetails \n"
                + "     WHERE ProductDetails.ProductID = Product.ProductID) AS TotalQuantity\n"
                + "FROM \n"
                + "    Product\n"
                + "INNER JOIN \n"
                + "    Brand ON Product.BrandID = Brand.BrandID\n"
                + "WHERE Product.[Status] = 'Active' AND \n"
                + "     (SELECT SUM(ProductDetails.Quantity) \n"
                + "     FROM ProductDetails \n"
                + "     WHERE ProductDetails.ProductID = Product.ProductID) > 0 AND Product.FeatureID IN (1,2) \n"
                + "ORDER BY Product.ReleaseDate DESC;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product c = new Product(rs.getInt("ProductID"),
                        rs.getString("Title"),
                        rs.getString("BriefInfo"),
                        rs.getString("Thumbnail"),
                        rs.getInt("FeatureID"),
                        rs.getDouble("Discount"),
                        rs.getDouble("FirstPrice"),
                        rs.getInt("TotalQuantity"));
                return c;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }
}
