/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Product;
import Model.ProductDetail;
import Model.ProductDetailforMKT;
import Model.ProductforMKT;
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
public class ProductDao extends DBContext {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;

    public List<Product> get8ExcellentProductNewest() {
        String sql = "SELECT TOP 8 \n"
                + "                     p.ProductID,\n"
                + "                     p.Title,\n"
                + "                     p.BriefInfo,\n"
                + "                     p.Thumbnail,\n"
                + "					 p.FeatureID,\n"
                + "                     b.Discount,\n"
                + "					 (SELECT TOP 1 ProductDetails.SellPrice \n"
                + "                      FROM ProductDetails \n"
                + "                      WHERE ProductDetails.ProductID = p.ProductID \n"
                + "                      ORDER BY ProductDetails.SizeID ASC) AS FirstPrice,\n"
                + "					 (SELECT SUM(ProductDetails.Quantity) \n"
                + "                      FROM ProductDetails \n"
                + "                      WHERE ProductDetails.ProductID = p.ProductID) AS TotalQuantity\n"
                + "                 FROM \n"
                + "                     dbo.Product p\n"
                + "                 JOIN \n"
                + "                     dbo.Feature f ON p.FeatureID = f.FeatureID\n"
                + "                 JOIN \n"
                + "                     dbo.Category c ON p.CategoryID = c.CategoryID\n"
                + "                 JOIN \n"
                + "                     dbo.Brand b ON p.BrandID = b.BrandID\n"
                + "                 WHERE \n"
                + "                     p.Status = 'Active' \n"
                + "                     AND f.FeatureName = 'Excellent'\n"
                + "                 ORDER BY \n"
                + "                     p.ProductID desc;";
        List<Product> list = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt(1);
                String title = rs.getString(2);
                String briefInfo = rs.getString(3);
                String thumbnail = rs.getString(4);
                int featureID = rs.getInt(5);
                int discount = rs.getInt(6);
                double firstPrice = rs.getDouble(7);
                int totalQuantity = rs.getInt(8);
                Product p = new Product(productID, title, briefInfo, thumbnail, featureID, discount, firstPrice, totalQuantity);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> get4NotReleasedProductNewest() {
        String sql = "SELECT TOP 4 \n"
                + "                     p.ProductID,\n"
                + "                     p.Title,\n"
                + "                     p.BriefInfo,\n"
                + "                     p.Thumbnail,\n"
                + "                     p.FeatureID,\n"
                + "                     b.Discount,\n"
                + "                     (SELECT TOP 1 ProductDetails.SellPrice \n"
                + "                      FROM ProductDetails \n"
                + "                      WHERE ProductDetails.ProductID = p.ProductID \n"
                + "                      ORDER BY ProductDetails.SizeID ASC) AS FirstPrice,\n"
                + "					 (SELECT SUM(ProductDetails.Quantity) \n"
                + "                      FROM ProductDetails \n"
                + "                      WHERE ProductDetails.ProductID = p.ProductID) AS TotalQuantity\n"
                + "                 FROM \n"
                + "                     dbo.Product p\n"
                + "                 JOIN \n"
                + "                     dbo.Feature f ON p.FeatureID = f.FeatureID\n"
                + "                 JOIN \n"
                + "                     dbo.Brand b ON p.BrandID = b.BrandID\n"
                + "                 JOIN \n"
                + "                     dbo.Category c ON p.CategoryID = c.CategoryID\n"
                + "                 WHERE \n"
                + "                     p.Status = 'Active' \n"
                + "                     AND f.FeatureID = 1\n"
                + "                 ORDER BY \n"
                + "                     p.ProductID DESC;";
        List<Product> list = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt(1);
                String title = rs.getString(2);
                String briefInfo = rs.getString(3);
                String thumbnail = rs.getString(4);
                int featureID = rs.getInt(5);
                int discount = rs.getInt(6);
                double firstPrice = rs.getDouble(7);
                int totalQuantity = rs.getInt(8);
                Product p = new Product(productID, title, briefInfo, thumbnail, featureID, discount, firstPrice, totalQuantity);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ProductDetail getProductByID(int productid) {
        ProductDetail p = null; // Khởi tạo đối tượng sản phẩm là null
        String query = "WITH RankedImages AS (\n"
                + "                                      SELECT \n"
                + "                                          pi.Image,\n"
                + "                                          pi.ProductID,\n"
                + "                                          ROW_NUMBER() OVER (PARTITION BY pi.ProductID ORDER BY pi.ImageID) AS ImageRank\n"
                + "                                      FROM \n"
                + "                                          ProductImage pi\n"
                + "                                  )\n"
                + "                                  \n"
                + "                                  SELECT \n"
                + "                                      p.ProductID,\n"
                + "                                      pd.ProductDetailID,\n"
                + "                                      p.Title,\n"
                + "                                      p.BriefInfo,\n"
                + "                                      p.Description,\n"
                + "                                      p.Thumbnail,\n"
                + "                                      MAX(CASE WHEN ri.ImageRank = 1 THEN ri.Image END) AS Img1,\n"
                + "                                      MAX(CASE WHEN ri.ImageRank = 2 THEN ri.Image END) AS Img2,\n"
                + "                                      MAX(CASE WHEN ri.ImageRank = 3 THEN ri.Image END) AS Img3,\n"
                + "                                      pd.SellPrice,\n"
                + "                                      b.Discount,\n"
                + "                                      p.FeatureID,\n"
                + "                                      s.SizeName,\n"
                + "                                      pd.Quantity,\n"
                + "                                      pd.Hold,\n"
                + "                                      c.CategoryName AS Category,\n"
                + "                                      b.BrandName AS Brand\n"
                + "                                  FROM \n"
                + "                                      Product p\n"
                + "                                  INNER JOIN \n"
                + "                                      ProductDetails pd ON p.ProductID = pd.ProductID\n"
                + "                                  LEFT JOIN \n"
                + "                                      RankedImages ri ON p.ProductID = ri.ProductID\n"
                + "                                  INNER JOIN \n"
                + "                                      Size s ON pd.SizeID = s.SizeID\n"
                + "                                  INNER JOIN \n"
                + "                                      Category c ON p.CategoryID = c.CategoryID\n"
                + "                                  INNER JOIN \n"
                + "                                      Brand b ON p.BrandID = b.BrandID\n"
                + "                 				 JOIN \n"
                + "                 					Feature f ON p.FeatureID = f.FeatureID\n"
                + "                                  WHERE \n"
                + "                                      p.ProductID = ?\n"
                + "                                  GROUP BY \n"
                + "                                      p.ProductID,\n"
                + "                                      pd.ProductDetailID,\n"
                + "                                      p.Title,\n"
                + "                                      p.BriefInfo,\n"
                + "                                      p.Description,\n"
                + "                                      p.Thumbnail,\n"
                + "                                      pd.SellPrice,\n"
                + "                                      b.Discount,\n"
                + "                                      p.FeatureID,\n"
                + "                                      s.SizeName,\n"
                + "                                      pd.Quantity,\n"
                + "                                      pd.Hold,\n"
                + "                                      c.CategoryName,\n"
                + "                                      b.BrandName;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            ps.setInt(1, productid); // Truyền giá trị pid vào câu truy vấn
            rs = ps.executeQuery();
            if (rs.next()) {
                int productID = rs.getInt(1);
                int productDetailID = rs.getInt(2);
                String title = rs.getString(3);
                String briefInfo = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                String img1 = rs.getString(7);
                String img2 = rs.getString(8);
                String img3 = rs.getString(9);
                double Price = rs.getDouble(10);
                int discount = rs.getInt(11);
                int featureID = rs.getInt(12);
                String sizename = rs.getString(13);
                int Quantity = rs.getInt(14);
                int hold = rs.getInt(15);
                String category = rs.getString(16);
                String brand = rs.getString(17);
                // Tạo đối tượng ProductDetail
                p = new ProductDetail(productID, productDetailID, title, briefInfo, description, thumbnail, img1, img2, img3, Price, discount, featureID, sizename, Quantity, hold, category, brand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p; // Trả về đối tượng sản phẩm (null nếu không tìm thấy)
    }

    public ProductDetail getProductByIDandSize(int productid, String sizeName) {
        ProductDetail p = null; // Khởi tạo đối tượng sản phẩm là null
        String query = "WITH RankedImages AS (\n"
                + "                                      SELECT \n"
                + "                                          pi.Image,\n"
                + "                                          pi.ProductID,\n"
                + "                                          ROW_NUMBER() OVER (PARTITION BY pi.ProductID ORDER BY pi.ImageID) AS ImageRank\n"
                + "                                      FROM \n"
                + "                                          ProductImage pi\n"
                + "                                  )\n"
                + "                                  \n"
                + "                                  SELECT \n"
                + "                                      p.ProductID,\n"
                + "                                      pd.ProductDetailID,\n"
                + "                                      p.Title,\n"
                + "                                      p.BriefInfo,\n"
                + "                                      p.Description,\n"
                + "                                      p.Thumbnail,\n"
                + "                                      MAX(CASE WHEN ri.ImageRank = 1 THEN ri.Image END) AS Img1,\n"
                + "                                      MAX(CASE WHEN ri.ImageRank = 2 THEN ri.Image END) AS Img2,\n"
                + "                                      MAX(CASE WHEN ri.ImageRank = 3 THEN ri.Image END) AS Img3,\n"
                + "                                      pd.SellPrice,\n"
                + "                                      b.Discount,\n"
                + "                                      p.FeatureID,\n"
                + "                                      s.SizeName,\n"
                + "                                      pd.Quantity,\n"
                + "                                      pd.Hold,\n"
                + "                                      c.CategoryName AS Category,\n"
                + "                                      b.BrandName AS Brand\n"
                + "                                  FROM \n"
                + "                                      Product p\n"
                + "                                  INNER JOIN \n"
                + "                                      ProductDetails pd ON p.ProductID = pd.ProductID\n"
                + "                                  LEFT JOIN \n"
                + "                                      RankedImages ri ON p.ProductID = ri.ProductID\n"
                + "                                  INNER JOIN \n"
                + "                                      Size s ON pd.SizeID = s.SizeID\n"
                + "                                  INNER JOIN \n"
                + "                                      Category c ON p.CategoryID = c.CategoryID\n"
                + "                                  INNER JOIN \n"
                + "                                      Brand b ON p.BrandID = b.BrandID\n"
                + "                 				 JOIN \n"
                + "                 					 dbo.Feature f ON p.FeatureID = f.FeatureID\n"
                + "                                  WHERE \n"
                + "                                      p.ProductID = ? And\n"
                + "                                      s.SizeName = ?\n"
                + "                                  GROUP BY \n"
                + "                                      p.ProductID,\n"
                + "                                      pd.ProductDetailID,\n"
                + "                                      p.Title,\n"
                + "                                      p.BriefInfo,\n"
                + "                                      p.Description,\n"
                + "                                      p.Thumbnail,\n"
                + "                                      pd.SellPrice,\n"
                + "                                      b.Discount,\n"
                + "                                      p.FeatureID,\n"
                + "                                      s.SizeName,\n"
                + "                                      pd.Quantity,\n"
                + "                                      pd.Hold,\n"
                + "                                      c.CategoryName,\n"
                + "                                      b.BrandName;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            ps.setInt(1, productid); // Truyền giá trị pid vào câu truy vấn
            ps.setString(2, sizeName);
            rs = ps.executeQuery();
            if (rs.next()) {
                int productID = rs.getInt(1);
                int productDetailID = rs.getInt(2);
                String title = rs.getString(3);
                String briefInfo = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                String img1 = rs.getString(7);
                String img2 = rs.getString(8);
                String img3 = rs.getString(9);
                double originalPrice = rs.getDouble(10);
                int discount = rs.getInt(11);
                int featureID = rs.getInt(12);
                String sizename = rs.getString(13);
                int Quantity = rs.getInt(14);
                int hold = rs.getInt(15);
                String category = rs.getString(16);
                String brand = rs.getString(17);
                // Tạo đối tượng ProductDetail
                p = new ProductDetail(productID, productDetailID, title, briefInfo, description, thumbnail, img1, img2, img3, originalPrice, discount, featureID, sizename, Quantity, hold, category, brand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p; // Trả về đối tượng sản phẩm (null nếu không tìm thấy)
    }

    public List<Product> getAllProduct() {
        String sql = "SELECT p.*\n"
                + "FROM Product p\n";
        List<Product> list = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setTitle(rs.getString("Title"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getAvalibleSize(int productID) {
        String sql = "SELECT s.SizeName FROM ProductDetails pd JOIN Size s ON pd.SizeID = s.SizeID WHERE pd.ProductID = ?";
        List<String> l = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                l.add(rs.getString("SizeName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public List<Size> getAvalibleSizeByID(int productID) {
        String sql = "SELECT s.SizeID, s.SizeName FROM ProductDetails pd JOIN Size s ON pd.SizeID = s.SizeID WHERE pd.ProductID = ?";
        List<Size> l = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
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

    public List<ProductforMKT> getAllProductForMKT(String status, String category) {
        String sql = "SELECT \n"
                + "                     p.ProductID, \n"
                + "                     p.Thumbnail, \n"
                + "                     p.Title, \n"
                + "                     p.BriefInfo, \n"
                + "                     c.CategoryName AS Category, \n"
                + "                     b.Discount, \n"
                + "                     f.FeatureName, \n"
                + "                     p.Status\n"
                + "                 FROM \n"
                + "                     Product p\n"
                + "                 JOIN \n"
                + "                     Category c ON p.CategoryID = c.CategoryID\n"
                + "                 JOIN \n"
                + "                     Brand b ON p.BrandID = b.BrandID\n"
                + "				 JOIN \n"
                + "                     Feature f ON p.FeatureID = f.FeatureID\n"
                + "                 WHERE \n"
                + "                     (? = '' OR p.Status = ?) AND \n"
                + "                     (? = '' OR c.CategoryName = ?);";
        List<ProductforMKT> l = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, status);
            ps.setString(3, category);
            ps.setString(4, category);
            rs = ps.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt(1);
                String thumbnail = rs.getString(2);
                String title = rs.getString(3);
                String briefInfo = rs.getString(4);
                String Category = rs.getString(5);
                int discount = rs.getInt(6);
                String featureName = rs.getString(7);
                String Status = rs.getString(8);
                ProductforMKT p = new ProductforMKT(productID, thumbnail, title, briefInfo, Category, discount, featureName, Status);
                l.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public void activeProduct(int productID) {
        String sql = "update Product\n"
                + "set Status = 'Active'\n"
                + "where ProductID = ?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inActiveProduct(int productID) {
        String sql = "update Product\n"
                + "set Status = 'Inactive'\n"
                + "where ProductID = ?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProductDetailforMKT getProductDetailforMKT(int productID) {
        String query = "WITH RankedImages AS (\n"
                + "                                      SELECT \n"
                + "                                          pi.Image,\n"
                + "                                          pi.ProductID,\n"
                + "                                          ROW_NUMBER() OVER (PARTITION BY pi.ProductID ORDER BY pi.ImageID) AS ImageRank\n"
                + "                                      FROM \n"
                + "                                          ProductImage pi\n"
                + "                                  )\n"
                + "                                  \n"
                + "                                  SELECT \n"
                + "                                      p.ProductID,\n"
                + "                                      pd.ProductDetailID,\n"
                + "                                      p.Title,\n"
                + "                                      p.BriefInfo,\n"
                + "                                      p.Description,\n"
                + "                                      p.Thumbnail,\n"
                + "                                      MAX(CASE WHEN ri.ImageRank = 1 THEN ri.Image END) AS Img1,\n"
                + "                                      MAX(CASE WHEN ri.ImageRank = 2 THEN ri.Image END) AS Img2,\n"
                + "                                      MAX(CASE WHEN ri.ImageRank = 3 THEN ri.Image END) AS Img3,\n"
                + "                                      pd.OriginalPrice,\n"
                + "									  pd.SellPrice,\n"
                + "                                      b.Discount,\n"
                + "                                      f.FeatureName,\n"
                + "                                      s.SizeName,\n"
                + "                                      pd.Quantity,\n"
                + "                                      pd.Hold,\n"
                + "                                      c.CategoryName AS Category,\n"
                + "                                      b.BrandName AS Brand,\n"
                + "                                      p.Status AS Status\n"
                + "                                  FROM \n"
                + "                                      Product p\n"
                + "                                  INNER JOIN \n"
                + "                                      ProductDetails pd ON p.ProductID = pd.ProductID\n"
                + "                                  LEFT JOIN \n"
                + "                                      RankedImages ri ON p.ProductID = ri.ProductID\n"
                + "                                  INNER JOIN \n"
                + "                                      Size s ON pd.SizeID = s.SizeID\n"
                + "                                  INNER JOIN \n"
                + "                                      Category c ON p.CategoryID = c.CategoryID\n"
                + "                                  INNER JOIN \n"
                + "                                      Brand b ON p.BrandID = b.BrandID\n"
                + "                 				 JOIN \n"
                + "                                      Feature f ON p.FeatureID = f.FeatureID\n"
                + "                                  WHERE \n"
                + "                                      p.ProductID = ?\n"
                + "                                  GROUP BY \n"
                + "                                      p.ProductID,\n"
                + "                                      pd.ProductDetailID,\n"
                + "                                      p.Title,\n"
                + "                                      p.BriefInfo,\n"
                + "                                      p.Description,\n"
                + "                                      p.Thumbnail,\n"
                + "                                      pd.OriginalPrice,\n"
                + "					 pd.SellPrice,\n"
                + "                                      b.Discount,\n"
                + "                                      f.FeatureName,\n"
                + "                                      s.SizeName,\n"
                + "                                      pd.Quantity,\n"
                + "                                      pd.Hold,\n"
                + "                                      c.CategoryName,\n"
                + "                                      b.BrandName,\n"
                + "                                      p.Status;";
        ProductDetailforMKT p = new ProductDetailforMKT();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            ps.setInt(1, productID); // Truyền giá trị pid vào câu truy vấn
            rs = ps.executeQuery();
            if (rs.next()) {
                int productiD = rs.getInt(1);
                int productDetailID = rs.getInt(2);
                String title = rs.getString(3);
                String briefInfor = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                String img1 = rs.getString(7);
                String img2 = rs.getString(8);
                String img3 = rs.getString(9);
                double originalPrice = rs.getDouble(10);
                double sellPrice = rs.getDouble(11);
                int discount = rs.getInt(12);
                String feature = rs.getString(13);
                String sizename = rs.getString(14);
                int Quantity = rs.getInt(15);
                int hold = rs.getInt(16);
                String category = rs.getString(17);
                String brand = rs.getString(18);
                String status = rs.getString(19);
                // Tạo đối tượng ProductDetail
                p = new ProductDetailforMKT(productiD, productDetailID, title, briefInfor, description, thumbnail, img1, img2, img3, originalPrice, sellPrice, discount, feature, sizename, Quantity, hold, category, brand, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public ProductDetailforMKT getProductDetailforMKTbyIDandSize(int productID, String sizeName) {
        String query = "WITH RankedImages AS (\n"
                + "                                      SELECT \n"
                + "                                          pi.Image,\n"
                + "                                          pi.ProductID,\n"
                + "                                          ROW_NUMBER() OVER (PARTITION BY pi.ProductID ORDER BY pi.ImageID) AS ImageRank\n"
                + "                                      FROM \n"
                + "                                          ProductImage pi\n"
                + "                                  )\n"
                + "                                  \n"
                + "                                  SELECT \n"
                + "                                      p.ProductID,\n"
                + "                                      pd.ProductDetailID,\n"
                + "                                      p.Title,\n"
                + "                                      p.BriefInfo,\n"
                + "                                      p.Description,\n"
                + "                                      p.Thumbnail,\n"
                + "                                      MAX(CASE WHEN ri.ImageRank = 1 THEN ri.Image END) AS Img1,\n"
                + "                                      MAX(CASE WHEN ri.ImageRank = 2 THEN ri.Image END) AS Img2,\n"
                + "                                      MAX(CASE WHEN ri.ImageRank = 3 THEN ri.Image END) AS Img3,\n"
                + "                                      pd.OriginalPrice,\n"
                + "					 pd.SellPrice,\n"
                + "                                      b.Discount,\n"
                + "                                      f.FeatureName,\n"
                + "                                      s.SizeName,\n"
                + "                                      pd.Quantity,\n"
                + "                                      pd.Hold,\n"
                + "                                      c.CategoryName AS Category,\n"
                + "                                      b.BrandName AS Brand,\n"
                + "                                      p.Status AS Status\n"
                + "                                  FROM \n"
                + "                                      Product p\n"
                + "                                  INNER JOIN \n"
                + "                                      ProductDetails pd ON p.ProductID = pd.ProductID\n"
                + "                                  LEFT JOIN \n"
                + "                                      RankedImages ri ON p.ProductID = ri.ProductID\n"
                + "                                  INNER JOIN \n"
                + "                                      Size s ON pd.SizeID = s.SizeID\n"
                + "                                  INNER JOIN \n"
                + "                                      Category c ON p.CategoryID = c.CategoryID\n"
                + "                                  INNER JOIN \n"
                + "                                      Brand b ON p.BrandID = b.BrandID\n"
                + "                 				 JOIN \n"
                + "                                      Feature f ON p.FeatureID = f.FeatureID\n"
                + "                                  WHERE \n"
                + "                                      p.ProductID = ? AND\n"
                + "                                      s.SizeName = ?\n"
                + "                                  GROUP BY \n"
                + "                                      p.ProductID,\n"
                + "                                      pd.ProductDetailID,\n"
                + "                                      p.Title,\n"
                + "                                      p.BriefInfo,\n"
                + "                                      p.Description,\n"
                + "                                      p.Thumbnail,\n"
                + "                                      pd.OriginalPrice,\n"
                + "					 pd.SellPrice,\n"
                + "                                      b.Discount,\n"
                + "                                      f.FeatureName,\n"
                + "                                      s.SizeName,\n"
                + "                                      pd.Quantity,\n"
                + "                                      pd.Hold,\n"
                + "                                      c.CategoryName,\n"
                + "                                      b.BrandName,\n"
                + "                                      p.Status;";
        ProductDetailforMKT p = new ProductDetailforMKT();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            ps.setInt(1, productID); // Truyền giá trị pid vào câu truy vấn
            ps.setString(2, sizeName);
            rs = ps.executeQuery();
            if (rs.next()) {
                int productiD = rs.getInt(1);
                int productDetailID = rs.getInt(2);
                String title = rs.getString(3);
                String briefInfor = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                String img1 = rs.getString(7);
                String img2 = rs.getString(8);
                String img3 = rs.getString(9);
                double originalPrice = rs.getDouble(10);
                double sellPrice = rs.getDouble(11);
                int discount = rs.getInt(12);
                String feature = rs.getString(13);
                String sizename = rs.getString(14);
                int Quantity = rs.getInt(15);
                int hold = rs.getInt(16);
                String category = rs.getString(17);
                String brand = rs.getString(18);
                String status = rs.getString(19);
                // Tạo đối tượng ProductDetail
                p = new ProductDetailforMKT(productiD, productDetailID, title, briefInfor, description, thumbnail, img1, img2, img3, originalPrice, sellPrice, discount, feature, sizename, Quantity, hold, category, brand, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public void updateProducts(int productID, String title, String thumbnail, String img1, String img2, String img3, String briefInfor, String description, int featureID, String status) {
        String updateProduct = "UPDATE [dbo].[Product]\n"
                + "                 SET \n"
                + "                     [Title] = ?,\n"
                + "                     [Thumbnail] = ?,\n"
                + "                     [BriefInfo] = ?,\n"
                + "                     [Description] = ?,\n"
                + "                     [FeatureID] = ?,\n"
                + "                     [Status] = ?\n"
                + "                 WHERE \n"
                + "                     [ProductID] = ?;";
        String updateProductImage = "UPDATE [dbo].[ProductImage]\n"
                + "SET \n"
                + "    [Image] = CASE [ImageID]\n"
                + "                 WHEN (SELECT [ImageID] FROM [dbo].[ProductImage] WHERE [ProductID] = ? ORDER BY [ImageID] OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY) THEN ?\n"
                + "                 WHEN (SELECT [ImageID] FROM [dbo].[ProductImage] WHERE [ProductID] = ? ORDER BY [ImageID] OFFSET 1 ROWS FETCH NEXT 1 ROWS ONLY) THEN ?\n"
                + "                 WHEN (SELECT [ImageID] FROM [dbo].[ProductImage] WHERE [ProductID] = ? ORDER BY [ImageID] OFFSET 2 ROWS FETCH NEXT 1 ROWS ONLY) THEN ?\n"
                + "             END\n"
                + "WHERE \n"
                + "    [ProductID] = ?;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(updateProduct);
            ps.setString(1, title);
            ps.setString(2, thumbnail);
            ps.setString(3, briefInfor);
            ps.setString(4, description);
            ps.setInt(5, featureID);
            ps.setString(6, status);
            ps.setInt(7, productID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(updateProductImage);
            ps.setInt(1, productID);
            ps.setString(2, img1);
            ps.setInt(3, productID);
            ps.setString(4, img2);
            ps.setInt(5, productID);
            ps.setString(6, img3);
            ps.setInt(7, productID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void MktAddNewProduct(String title, String briefInfor, String description, String thumbnail, int featureID, String status, int categoryID, int brandID) {
        String updateProduct = "INSERT INTO Product (\n"
                + "                     Title,\n"
                + "                     BriefInfo,\n"
                + "                     Description,\n"
                + "                     Thumbnail,\n"
                + "                     FeatureID,\n"
                + "                     Status,\n"
                + "                     BrandID,\n"
                + "                     CategoryID\n"
                + "                 ) \n"
                + "                 VALUES (\n"
                + "                     ?,    -- Title của sản phẩm\n"
                + "                     ?,    -- BriefInfo\n"
                + "                     ?,    -- Description\n"
                + "                     ?,    -- Thumbnail\n"
                + "                     ?,    -- FeatureID\n"
                + "                     ?,    -- Status\n"
                + "                     ?,    -- BrandID\n"
                + "                     ?     -- CategoryID\n"
                + "                 );";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(updateProduct);
            ps.setString(1, title);
            ps.setString(2, briefInfor);
            ps.setString(3, description);
            ps.setString(4, thumbnail);
            ps.setInt(5, featureID);
            ps.setString(6, status);
            ps.setInt(7, brandID);
            ps.setInt(8, categoryID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSellPrice(int productDetailID, double sellPrice) {
        String updateSellPrice = "update ProductDetails\n"
                + "set SellPrice = ?\n"
                + "where ProductDetailID = ?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(updateSellPrice);
            ps.setDouble(1, sellPrice);
            ps.setInt(2, productDetailID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePriceInCart(int productID, int sizeID, double sellPrice) {
        String updateInCart = "update CartItems\n"
                + "set Price = ?\n"
                + "where ProductID = ? and SizeID = ?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(updateInCart);
            ps.setDouble(1, sellPrice);
            ps.setInt(2, productID);
            ps.setInt(3, sizeID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkBeforeAddProduct(String title, int categoryID, int brandID) {
        String check = "select *from Product\n"
                + "where Title=? and CategoryID=? and BrandID = ?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(check);
            ps.setString(1, title);
            ps.setInt(2, categoryID);
            ps.setInt(3, brandID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ProductDetail> getAllProductDetail() {
        List<ProductDetail> l = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "                                                       SELECT \n"
                + "                                                           pi.Image,\n"
                + "                                                           pi.ProductID,\n"
                + "                                                           ROW_NUMBER() OVER (PARTITION BY pi.ProductID ORDER BY pi.ImageID) AS ImageRank\n"
                + "                                                       FROM \n"
                + "                                                           ProductImage pi\n"
                + "                                                   )                                                  \n"
                + "                                                   SELECT \n"
                + "                                                       p.ProductID,\n"
                + "                                                       pd.ProductDetailID,\n"
                + "                                                       p.Title,\n"
                + "                                                       p.BriefInfo,\n"
                + "                                                       p.Description,\n"
                + "                                                       p.Thumbnail,\n"
                + "                                                       MAX(CASE WHEN ri.ImageRank = 1 THEN ri.Image END) AS Img1,\n"
                + "                                                       MAX(CASE WHEN ri.ImageRank = 2 THEN ri.Image END) AS Img2,\n"
                + "                                                       MAX(CASE WHEN ri.ImageRank = 3 THEN ri.Image END) AS Img3,\n"
                + "                                                       pd.SellPrice,\n"
                + "                                                       b.Discount,\n"
                + "                                                       p.FeatureID,\n"
                + "                                                       s.SizeName,\n"
                + "                                                       pd.Quantity,\n"
                + "                                                       pd.Hold,\n"
                + "                                                       c.CategoryName AS Category,\n"
                + "                                                       b.BrandName AS Brand\n"
                + "                                                   FROM \n"
                + "                                                       Product p\n"
                + "                                                   INNER JOIN \n"
                + "                                                       ProductDetails pd ON p.ProductID = pd.ProductID\n"
                + "                                                   LEFT JOIN \n"
                + "                                                       RankedImages ri ON p.ProductID = ri.ProductID\n"
                + "                                                   INNER JOIN \n"
                + "                                                       Size s ON pd.SizeID = s.SizeID\n"
                + "                                                   INNER JOIN \n"
                + "                                                       Category c ON p.CategoryID = c.CategoryID\n"
                + "                                                   INNER JOIN \n"
                + "                                                       Brand b ON p.BrandID = b.BrandID\n"
                + "                                  				 JOIN \n"
                + "                                  					Feature f ON p.FeatureID = f.FeatureID\n"
                + "                                                   GROUP BY \n"
                + "                                                       p.ProductID,\n"
                + "                                                       pd.ProductDetailID,\n"
                + "                                                       p.Title,\n"
                + "                                                       p.BriefInfo,\n"
                + "                                                       p.Description,\n"
                + "                                                       p.Thumbnail,\n"
                + "                                                       pd.SellPrice,\n"
                + "                                                       b.Discount,\n"
                + "                                                       p.FeatureID,\n"
                + "                                                       s.SizeName,\n"
                + "                                                       pd.Quantity,\n"
                + "                                                       pd.Hold,\n"
                + "                                                       c.CategoryName,\n"
                + "                                                       b.BrandName;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt(1);
                int productDetailID = rs.getInt(2);
                String title = rs.getString(3);
                String briefInfo = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                String img1 = rs.getString(7);
                String img2 = rs.getString(8);
                String img3 = rs.getString(9);
                double originalPrice = rs.getDouble(10);
                int discount = rs.getInt(11);
                int featureID = rs.getInt(12);
                String sizename = rs.getString(13);
                int Quantity = rs.getInt(14);
                int hold = rs.getInt(15);
                String category = rs.getString(16);
                String brand = rs.getString(17);
                // Tạo đối tượng ProductDetail
                ProductDetail p = new ProductDetail(productID, productDetailID, title, briefInfo, description, thumbnail, img1, img2, img3, originalPrice, discount, featureID, sizename, Quantity, hold, category, brand);
                l.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public static void main(String[] args) {
        ProductDao p = new ProductDao();
        List<ProductDetail> a = p.getAllProductDetail();
        System.out.println(a.isEmpty());

    }
}
