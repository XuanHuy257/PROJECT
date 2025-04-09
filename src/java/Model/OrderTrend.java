/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author FPT
 */
public class OrderTrend {
    private String orderDate;
    private int totalOrders;
    private int successfulOrders;

    public OrderTrend(String orderDate, int totalOrders, int successfulOrders) {
        this.orderDate = orderDate;
        this.totalOrders = totalOrders;
        this.successfulOrders = successfulOrders;
    }

    // Getters
    public String getOrderDate() {
        return orderDate;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public int getSuccessfulOrders() {
        return successfulOrders;
    }
}

