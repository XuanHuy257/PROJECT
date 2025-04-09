///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Dao;
//
//import Model.Brand;
//import Model.Category;
//import Model.Product;
//import Model.ProductDetail;
//import Model.Products;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author sontu
// */
//public class DaoProduct {
//
//    private PreparedStatement ps = null;
//    private ResultSet rs = null;
//    private Connection connection = null;
//
//    public List<Products> getAllProducts() {
//        List<Products> list = new ArrayList<>();
//        String query = "SELECT \n"
//                + "    p.ProductID,\n"
//                + "    p.Title,\n"
//                + "    p.BriefInfo,\n"
//                + "    b.BrandName,\n"
//                + "    p.Description,\n"
//                + "    p.Thumbnail,\n"
//                + "    p.OriginalPrice,\n"
//                + "    p.Status,\n"
//                + "    s.SizeName,\n"
//                + "    pd.StockQuantity\n"
//                + "    pd.UpdatedDate\n"
//                + "FROM \n"
//                + "    Product p\n"
//                + "JOIN \n"
//                + "    Brand b ON p.BrandID = b.BrandID\n"
//                + "JOIN \n"
//                + "    ProductDetails pd ON p.ProductID = pd.ProductID\n"
//                + "JOIN \n"
//                + "    Size s ON pd.SizeID = s.SizeID;";
//        try {
//            connection = new DBContext().connection;
//            ps = connection.prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                String id = rs.getString(1);
//                String title = rs.getString(2);
//                String briefInfor = rs.getString(3);
//                String brand = rs.getString(4);
//                String description = rs.getString(5);
//                String thumbnail = rs.getString(6);
//                double price = rs.getDouble(7);
//                String status = rs.getString(8);
//                String size = rs.getString(9);
//                int stock = rs.getInt(10);
//                String upDate = rs.getString(11);
//                Products p = new Products(id, title, briefInfor, brand, description, thumbnail, price, status, size, stock, upDate);
//                list.add(p);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public Product getLast() {
//        String sql = "SELECT TOP 1 [ProductID]\n"
//                + "      ,[Title]\n"
//                + "      ,[BriefInfo]\n"
//                + "      ,[Description]\n"
//                + "      ,[Thumbnail]\n"
//                + "      ,[OriginalPrice]\n"
//                + "      ,[Status]\n"
//                + "      ,[UpdatedDate]\n"
//                + "      ,[BrandID]\n"
//                + "  FROM [dbo].[Product]\n"
//                + "  ORDER BY [ProductID] DESC;";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                Product a = new Product(rs.getInt("ProductID"),
//                        rs.getString("Title"),
//                        rs.getString("BriefInfo"),
//                        rs.getString("Description"),
//                        rs.getString("Thumbnail"),
//                        rs.getDouble("OriginalPrice"),
//                        rs.getString("Status"),
//                        rs.getString("UpdatedDate"),
//                        rs.getInt("BrandID"));
//
//                return a;
//
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return null;
//    }
//
//    public List<Brand> getAllBrand() {
//        List<Brand> list = new ArrayList<>();
//        String query = "SELECT [BrandID]\n"
//                + "      ,[BrandName]\n"
//                + "  FROM.[dbo].[Brand]";
//        try {
//            connection = new DBContext().connection;
//            ps = connection.prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Brand br = new Brand();
//                br.setId(rs.getInt("BrandID"));
//                br.setName(rs.getString("BrandName"));
//                list.add(br);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public List<Category> getAllCategory() {
//        List<Category> list = new ArrayList<>();
//        String query = "SELECT [CategoryID]\n"
//                + "      ,[CategoryName]\n"
//                + "  FROM.[dbo].[Category]";
//        try {
//            connection = new DBContext().connection;
//            ps = connection.prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Category ct = new Category();
//                ct.setId(rs.getInt("CategoryID"));
//                ct.setName(rs.getString("CategoryName"));
//                list.add(ct);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public List<Products> getProductHightoLow() {
//        List<Products> list = new ArrayList<>();
//        String query = "SELECT \n"
//                + "    p.ProductID,\n"
//                + "    p.Title,\n"
//                + "    p.BriefInfo,\n"
//                + "    b.BrandName,\n"
//                + "    p.Description,\n"
//                + "    p.Thumbnail,\n"
//                + "    p.OriginalPrice,\n"
//                + "    p.Status,\n"
//                + "    s.SizeName,\n"
//                + "    pd.StockQuantity\n"
//                + "FROM \n"
//                + "    Product p\n"
//                + "JOIN \n"
//                + "    Brand b ON p.BrandID = b.BrandID\n"
//                + "JOIN \n"
//                + "    ProductDetails pd ON p.ProductID = pd.ProductID\n"
//                + "JOIN \n"
//                + "    Size s ON pd.SizeID = s.SizeID\n"
//                + "ORDER BY p.OriginalPrice DESC;";  // Sort products by OriginalPrice in descending order
//
//        try {
//            // Create connection to the database
//            connection = new DBContext().connection;
//            ps = connection.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            // Loop through the result set and create product objects
//            while (rs.next()) {
//                String id = rs.getString(1);
//                String title = rs.getString(2);
//                String briefInfo = rs.getString(3);
//                String brand = rs.getString(4);
//                String description = rs.getString(5);
//                String thumbnail = rs.getString(6);
//                double price = rs.getDouble(7);
//                String status = rs.getString(8);
//                String size = rs.getString(9);
//                int stock = rs.getInt(10);
//                String upDate = rs.getString(11);
//                // Create a Products object and add it to the list
//                Products p = new Products(id, title, briefInfo, brand, description, thumbnail, price, status, size, stock, upDate);
//                list.add(p);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public List<Products> getProductLowToHigh() {
//        List<Products> list = new ArrayList<>();
//        String query = "SELECT \n"
//                + "    p.ProductID,\n"
//                + "    p.Title,\n"
//                + "    p.BriefInfo,\n"
//                + "    b.BrandName,\n"
//                + "    p.Description,\n"
//                + "    p.Thumbnail,\n"
//                + "    p.OriginalPrice,\n"
//                + "    p.Status,\n"
//                + "    s.SizeName,\n"
//                + "    pd.StockQuantity\n"
//                + "FROM \n"
//                + "    Product p\n"
//                + "JOIN \n"
//                + "    Brand b ON p.BrandID = b.BrandID\n"
//                + "JOIN \n"
//                + "    ProductDetails pd ON p.ProductID = pd.ProductID\n"
//                + "JOIN \n"
//                + "    Size s ON pd.SizeID = s.SizeID\n"
//                + "ORDER BY p.OriginalPrice ASC;";  // Sort products by OriginalPrice in descending order
//
//        try {
//            // Create connection to the database
//            connection = new DBContext().connection;
//            ps = connection.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            // Loop through the result set and create product objects
//            while (rs.next()) {
//                String id = rs.getString(1);
//                String title = rs.getString(2);
//                String briefInfo = rs.getString(3);
//                String brand = rs.getString(4);
//                String description = rs.getString(5);
//                String thumbnail = rs.getString(6);
//                double price = rs.getDouble(7);
//                String status = rs.getString(8);
//                String size = rs.getString(9);
//                int stock = rs.getInt(10);
//                String upDate = rs.getString(11);
//                // Create a Products object and add it to the list
//                Products p = new Products(id, title, briefInfo, brand, description, thumbnail, price, status, size, stock, upDate);
//                list.add(p);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public List<Products> getProductSortedByNameAZ() {
//        List<Products> list = new ArrayList<>();
//        String query = "SELECT \n"
//                + "    p.ProductID,\n"
//                + "    p.Title,\n"
//                + "    p.BriefInfo,\n"
//                + "    b.BrandName,\n"
//                + "    p.Description,\n"
//                + "    p.Thumbnail,\n"
//                + "    p.OriginalPrice,\n"
//                + "    p.Status,\n"
//                + "    s.SizeName,\n"
//                + "    pd.StockQuantity\n"
//                + "FROM \n"
//                + "    Product p\n"
//                + "JOIN \n"
//                + "    Brand b ON p.BrandID = b.BrandID\n"
//                + "JOIN \n"
//                + "    ProductDetails pd ON p.ProductID = pd.ProductID\n"
//                + "JOIN \n"
//                + "    Size s ON pd.SizeID = s.SizeID\n"
//                + "ORDER BY p.Title ASC;";  // Sắp xếp theo tên sản phẩm từ A đến Z (tăng dần)
//
//        try {
//            // Tạo kết nối đến cơ sở dữ liệu
//            connection = new DBContext().connection;
//            ps = connection.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            // Duyệt qua các bản ghi trong kết quả và tạo đối tượng sản phẩm
//            while (rs.next()) {
//                String id = rs.getString(1);
//                String title = rs.getString(2);
//                String briefInfo = rs.getString(3);
//                String brand = rs.getString(4);
//                String description = rs.getString(5);
//                String thumbnail = rs.getString(6);
//                double price = rs.getDouble(7);
//                String status = rs.getString(8);
//                String size = rs.getString(9);
//                int stock = rs.getInt(10);
//                String upDate = rs.getString(11);
//                // Tạo đối tượng sản phẩm và thêm vào danh sách
//                Products p = new Products(id, title, briefInfo, brand, description, thumbnail, price, status, size, stock, upDate);
//                list.add(p);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public List<Products> searchProductsByTitle(String searchTitle) {
//        List<Products> list = new ArrayList<>();
//
//        // Câu lệnh SQL sử dụng LIKE để tìm kiếm sản phẩm theo tên
//        String query = "SELECT \n"
//                + "    p.ProductID,\n"
//                + "    p.Title,\n"
//                + "    p.BriefInfo,\n"
//                + "    b.BrandName,\n"
//                + "    p.Description,\n"
//                + "    p.Thumbnail,\n"
//                + "    p.OriginalPrice,\n"
//                + "    p.Status,\n"
//                + "    s.SizeName,\n"
//                + "    pd.StockQuantity\n"
//                + "FROM \n"
//                + "    Product p\n"
//                + "JOIN \n"
//                + "    Brand b ON p.BrandID = b.BrandID\n"
//                + "JOIN \n"
//                + "    ProductDetails pd ON p.ProductID = pd.ProductID\n"
//                + "JOIN \n"
//                + "    Size s ON pd.SizeID = s.SizeID\n"
//                + "WHERE p.Title LIKE ?";  // Tìm kiếm sản phẩm theo tên
//
//        try {
//            // Tạo kết nối tới database
//            connection = new DBContext().connection;
//            ps = connection.prepareStatement(query);
//
//            // Thiết lập tham số cho câu lệnh SQL
//            ps.setString(1, "%" + searchTitle + "%");  // Sử dụng ký tự '%' để tìm kiếm gần đúng
//
//            rs = ps.executeQuery();
//
//            // Lặp qua kết quả truy vấn và tạo các đối tượng sản phẩm
//            while (rs.next()) {
//                String id = rs.getString(1);
//                String title = rs.getString(2);
//                String briefInfo = rs.getString(3);
//                String brand = rs.getString(4);
//                String description = rs.getString(5);
//                String thumbnail = rs.getString(6);
//                double price = rs.getDouble(7);
//                String status = rs.getString(8);
//                String size = rs.getString(9);
//                int stock = rs.getInt(10);
//                String upDate = rs.getString(11);
//                // Tạo đối tượng Products và thêm vào danh sách
//                Products p = new Products(id, title, briefInfo, brand, description, thumbnail, price, status, size, stock, upDate);
//                list.add(p);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // Đóng tài nguyên
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (ps != null) {
//                    ps.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return list;  // Trả về danh sách sản phẩm tìm kiếm
//    }
//
//    public List<Products> getProductsByBrandId(int brandId) {
//        List<Products> list = new ArrayList<>();
//        String query = "SELECT \n"
//                + "    p.ProductID,\n"
//                + "    p.Title,\n"
//                + "    p.BriefInfo,\n"
//                + "    b.BrandName,\n"
//                + "    p.Description,\n"
//                + "    p.Thumbnail,\n"
//                + "    p.OriginalPrice,\n"
//                + "    p.Status,\n"
//                + "    s.SizeName,\n"
//                + "    pd.StockQuantity\n"
//                + "FROM \n"
//                + "    Product p\n"
//                + "JOIN \n"
//                + "    Brand b ON p.BrandID = b.BrandID\n"
//                + "JOIN \n"
//                + "    ProductDetails pd ON p.ProductID = pd.ProductID\n"
//                + "JOIN \n"
//                + "    Size s ON pd.SizeID = s.SizeID\n"
//                + "WHERE \n"
//                + "    p.BrandID = ?";  // Thêm điều kiện lọc theo BrandID
//
//        try {
//            connection = new DBContext().connection;
//            ps = connection.prepareStatement(query);
//            ps.setInt(1, brandId);  // Thiết lập giá trị cho tham số BrandID
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                String id = rs.getString(1);
//                String title = rs.getString(2);
//                String briefInfo = rs.getString(3);
//                String brand = rs.getString(4);
//                String description = rs.getString(5);
//                String thumbnail = rs.getString(6);
//                double price = rs.getDouble(7);
//                String status = rs.getString(8);
//                String size = rs.getString(9);
//                int stock = rs.getInt(10);
//                String upDate = rs.getString(11);
//                Products p = new Products(id, title, briefInfo, brand, description, thumbnail, price, status, size, stock, upDate);
//                list.add(p);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public List<Products> getProductsByCategoryId(int categoryId) {
//        List<Products> list = new ArrayList<>();
//        String query = "SELECT \n"
//                + "    p.ProductID,\n"
//                + "    p.Title,\n"
//                + "    p.BriefInfo,\n"
//                + "    c.CategoryName,\n"
//                + "    p.Description,\n"
//                + "    p.Thumbnail,\n"
//                + "    p.OriginalPrice,\n"
//                + "    p.Status,\n"
//                + "    p.UpdatedDate,\n"
//                + "    s.SizeName,\n"
//                + "    pd.StockQuantity\n"
//                + "FROM \n"
//                + "    [SWP_Group4_1].[dbo].[Product] p\n"
//                + "JOIN \n"
//                + "    [SWP_Group4_1].[dbo].[ProductCategory] pc ON p.ProductID = pc.ProductID\n"
//                + "JOIN \n"
//                + "    [SWP_Group4_1].[dbo].[Category] c ON pc.CategoryID = c.CategoryID\n"
//                + "JOIN \n"
//                + "    [SWP_Group4_1].[dbo].[ProductDetails] pd ON p.ProductID = pd.ProductID\n"
//                + "JOIN \n"
//                + "    [SWP_Group4_1].[dbo].[Size] s ON pd.SizeID = s.SizeID\n"
//                + "WHERE \n"
//                + "    c.CategoryID = ?;";
//
//        try (Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query)) {
//
//            // Thiết lập giá trị cho tham số CategoryID
//            ps.setInt(1, categoryId);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                // Xử lý kết quả truy vấn
//                while (rs.next()) {
//                    String id = rs.getString("ProductID");
//                    String title = rs.getString("Title");
//                    String briefInfo = rs.getString("BriefInfo");
//                    String category = rs.getString("CategoryName");
//                    String description = rs.getString("Description");
//                    String thumbnail = rs.getString("Thumbnail");
//                    double price = rs.getDouble("OriginalPrice");
//                    String status = rs.getString("Status");
//                    String size = rs.getString("SizeName");
//                    int stock = rs.getInt("StockQuantity");
//                    // Tạo đối tượng Products
//                    Products p = new Products(id, title, briefInfo, category, description, thumbnail, price, status, size, stock, null);
//                    list.add(p);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();  // In ra lỗi SQL
//            System.out.println("Error in getProductsByCategoryId: " + e.getMessage());
//        }
//
//        return list;
//    }
//
//    public static void main(String[] args) {
//    }
//}
