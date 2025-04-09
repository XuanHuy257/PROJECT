/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author sontu
 */
public class ProductDetail {

    private int productID;
    private int productDetailID;
    private String title;
    private String briefInfo;
    private String description;
    private String thumbnail;
    private String img1;
    private String img2;
    private String img3;
    private double sellPrice;
    private int discount;
    private int featureID;
    private String sizeName;
    private int Quantity;
    private int hold;
    private String category;
    private String brand;

    public ProductDetail() {
    }

    public ProductDetail(int productID, int productDetailID, String title, String briefInfo, String description, String thumbnail, String img1, String img2, String img3, double sellPrice, int discount, int featureID, String sizeName, int Quantity, int hold, String category, String brand) {
        this.productID = productID;
        this.productDetailID = productDetailID;
        this.title = title;
        this.briefInfo = briefInfo;
        this.description = description;
        this.thumbnail = thumbnail;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.sellPrice = sellPrice;
        this.discount = discount;
        this.featureID = featureID;
        this.sizeName = sizeName;
        this.Quantity = Quantity;
        this.hold = hold;
        this.category = category;
        this.brand = brand;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductDetailID() {
        return productDetailID;
    }

    public void setProductDetailID(int productDetailID) {
        this.productDetailID = productDetailID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getFeatureID() {
        return featureID;
    }

    public void setFeatureID(int featureID) {
        this.featureID = featureID;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getHold() {
        return hold;
    }

    public void setHold(int hold) {
        this.hold = hold;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    
}
