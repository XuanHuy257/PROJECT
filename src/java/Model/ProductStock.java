/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Anh Tuan
 */
public class ProductStock {
    private int sizeID;
    private String sizeName;
    private double OriginalPrice;
    private int quantity;

    public ProductStock() {
    }

    public ProductStock(int sizeID, String sizeName, double OriginalPrice, int quantity) {
        this.sizeID = sizeID;
        this.sizeName = sizeName;
        this.OriginalPrice = OriginalPrice;
        this.quantity = quantity;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public double getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(double OriginalPrice) {
        this.OriginalPrice = OriginalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
}
