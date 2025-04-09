/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Cart;
import Model.CartItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sontu
 */
public class DaoCart {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;

    public Cart getOrCreateCart(int customerID) {
        try {
            // Khởi tạo connection từ DBContext
            connection = new DBContext().connection;

            // Kiểm tra xem người dùng đã có giỏ hàng hay chưa
            String sql = "SELECT * FROM Cart WHERE CustomerID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, customerID);
            rs = ps.executeQuery();

            // Nếu giỏ hàng đã tồn tại, trả về giỏ hàng đó
            if (rs.next()) {
                return new Cart(rs.getInt(1), rs.getInt(2));
            } else {
                // Nếu chưa có giỏ hàng, tạo mới
                String sql2 = "INSERT INTO Cart (CustomerID) VALUES (?)";
                ps = connection.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, customerID);
                ps.executeUpdate();

                // Lấy cartID của giỏ hàng vừa tạo
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int cartID = rs.getInt(1);
                    return new Cart(cartID, customerID);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCart.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đảm bảo đóng ResultSet và PreparedStatement sau khi sử dụng
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close(); // Đảm bảo đóng connection
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean checkProductBeforeAdd(int productID, int sizeID) {
        String sql = "SELECT Product.Status \n"
                + "FROM Product\n"
                + "JOIN ProductDetails ON Product.ProductID = ProductDetails.ProductID\n"
                + "JOIN Size ON ProductDetails.SizeID = Size.SizeID\n"
                + "WHERE Product.ProductID = ? AND Size.SizeID = ? and Product.Status = 'Active';";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setInt(2, sizeID);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addToCart(int cartID, int productID, String title, int quantity, String thumbnail, double price, int sizeID) {
        try {
            // Khởi tạo connection từ DBContext
            connection = new DBContext().connection;

            // Kiểm tra xem người dùng đã có giỏ hàng hay chưa
            String checksql = "select *from CartItems\n"
                    + "where CartID = ? and ProductID = ? and SizeID = ?";
            ps = connection.prepareStatement(checksql);
            ps.setInt(1, cartID);
            ps.setInt(2, productID);
            ps.setInt(3, sizeID);
            rs = ps.executeQuery();
            if (rs.next()) {
                String updateSql = "UPDATE CartItems SET quantity = quantity + ? WHERE CartID = ? AND ProductID = ? AND SizeID = ?";
                ps = connection.prepareStatement(updateSql);
                ps.setInt(1, quantity);
                ps.setInt(2, cartID);
                ps.setInt(3, productID);
                ps.setInt(4, sizeID);
                ps.executeUpdate();
            } else {
                String insertSql = "insert into CartItems (CartID, ProductID, Title, Quantity, Thumbnail, Price, SizeID) \n"
                        + "values (?,?,?,?,?,?,?)";
                ps = connection.prepareStatement(insertSql);
                ps.setInt(1, cartID);
                ps.setInt(2, productID);
                ps.setString(3, title);
                ps.setInt(4, quantity);
                ps.setString(5, thumbnail);
                ps.setDouble(6, price);
                ps.setInt(7, sizeID);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCart.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đảm bảo đóng ResultSet và PreparedStatement sau khi sử dụng
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close(); // Đảm bảo đóng connection
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkProductAdd(int cartID, int productID, int sizeID) {
        try {
            connection = new DBContext().connection;
            String sql = "FROM ProductDetails\n"
                    + "JOIN Size s ON ProductDetails.sizeID = s.sizeID\n"
                    + "WHERE ProductDetails.productID = ?\n"
                    + "AND s.SizeID = ?;";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, cartID);
            ps.setInt(2, productID);
            ps.setInt(3, sizeID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCart.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đảm bảo đóng ResultSet và PreparedStatement sau khi sử dụng
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close(); // Đảm bảo đóng connection
                }
            } catch (Exception e) {
            }
            return false;
        }
    }

    public List<CartItem> getAllProductsInCart(int cartID) {
        String sql = "select *from CartItems where CartID = ?";
        List<CartItem> list = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, cartID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int cartItemID = rs.getInt(1);
                int cartid = rs.getInt(2);
                int productID = rs.getInt(3);
                String title = rs.getString(4);
                int quanlity = rs.getInt(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                int sizeID = rs.getInt(8);
                CartItem ci = new CartItem(cartItemID, cartid, productID, title, quanlity, thumbnail, price, sizeID);
                list.add(ci);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteItemCart(int cartItemID) {
        String sql = "Delete from CartItems where CartItemID=?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, cartItemID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllIteaminCart(int cartID) {
        String sql = "Delete from CartItems where CartID=?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, cartID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Cart getCartfromUserID(int customerID) {
        String sql = "SELECT * FROM Cart WHERE CustomerID = ?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, customerID);
            rs = ps.executeQuery();

            // Kiểm tra nếu có kết quả
            if (rs.next()) {
                return new Cart(rs.getInt("cartID"), rs.getInt("CustomerID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng các tài nguyên sau khi sử dụng
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void updateQuanlityinCart(int quantity, int cartItemID) {
        String sql = "UPDATE CartItems SET quantity = ? WHERE CartItemID = ?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, cartItemID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng kết nối sau khi sử dụng
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public CartItem getCartItemByID(int cartItemID) {
        String sql = "Select *from CartItems where CartItemID = ?";
        CartItem c = null;
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, cartItemID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int CartItemID = rs.getInt(1);
                int cartid = rs.getInt(2);
                int productID = rs.getInt(3);
                String title = rs.getString(4);
                int quanlity = rs.getInt(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                int sizeID = rs.getInt(8);
                c = new CartItem(CartItemID, cartid, productID, title, quanlity, thumbnail, price, sizeID);
            }
        } catch (Exception e) {
        }
        return c;
    }

    public void changeHoldInProduct(int productID, String sizeName, int hold) {
        String sql = "UPDATE pd\n"
                + "SET pd.Hold = pd.Hold + ?\n"
                + "FROM ProductDetails pd\n"
                + "INNER JOIN Size s ON pd.SizeID = s.SizeID\n"
                + "WHERE pd.ProductID = ? AND s.SizeName = ?;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, hold);
            ps.setInt(2, productID);
            ps.setString(3, sizeName);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSizeIDbySizeName(String sizeName) {
        String sql = "select Size.SizeID from Size where Size.SizeName = ?";
        int a = 0;
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setString(1, sizeName);
            rs = ps.executeQuery();
            while (rs.next()) {
                a = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return a;
    }

    public static void main(String[] args) {
        DaoCart dao = new DaoCart();
        dao.checkProductBeforeAdd(1, 1);
        System.out.println(dao.checkProductBeforeAdd(1, 1));
    }
}
