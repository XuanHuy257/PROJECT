/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Anh Tuan
 */
public class OrderDetailEmp extends OrderDetail{
    private String categoryName;

    public OrderDetailEmp() {
    }

    public OrderDetailEmp( int orderDetailID, int orderID, int productID, String title, int quantity, String thumbnail, double price, int sizeID, String sizeName,String categoryName) {
        super(orderDetailID, orderID, productID, title, quantity, thumbnail, price, sizeID, sizeName);
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
}
