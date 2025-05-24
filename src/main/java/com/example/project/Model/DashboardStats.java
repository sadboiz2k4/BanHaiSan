package com.example.project.Model;

import java.io.Serializable;

public class DashboardStats implements Serializable {
    private int totalCustomers;
    private int totalProducts;
    private int totalOrders;
    private int lowStockProducts;
    private double totalIncome;  // Thêm totalIncome
    private int pendingOrders;
    private double averageRating;

    // Default constructor
    public DashboardStats() {}

    // Getters and Setters
    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getLowStockProducts() {
        return lowStockProducts;
    }

    public void setLowStockProducts(int lowStockProducts) {
        this.lowStockProducts = lowStockProducts;
    }

    public double getTotalIncome() {  // Getter cho totalIncome
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {  // Setter cho totalIncome
        this.totalIncome = totalIncome;
    }

    public int getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(int pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}