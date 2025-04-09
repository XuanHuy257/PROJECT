/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author FPT
 */
public class FeedbackStats {
    private int categoryId;
    private String categoryName;
    private double averageStar;
    private int totalFeedback;

    public FeedbackStats(int categoryId, String categoryName, double averageStar, int totalFeedback) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.averageStar = averageStar;
        this.totalFeedback = totalFeedback;
    }

    // Getters
    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getAverageStar() {
        return averageStar;
    }

    public int getTotalFeedback() {
        return totalFeedback;
    }
}

