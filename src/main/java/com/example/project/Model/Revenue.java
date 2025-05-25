

// Revenue.java
package com.example.project.Model;

public class Revenue {
    private int totalEmployees;
    private int totalProducts;
    private int totalOrders;
    private double totalIncome;
    private int newEmployees;
    private int outOfStockProducts;
    private int cancelledOrders;

    public Revenue(int cancelledOrders, int outOfStockProducts, int newEmployees, double totalIncome, int totalOrders, int totalProducts, int totalEmployees) {
        this.cancelledOrders = cancelledOrders;
        this.outOfStockProducts = outOfStockProducts;
        this.newEmployees = newEmployees;
        this.totalIncome = totalIncome;
        this.totalOrders = totalOrders;
        this.totalProducts = totalProducts;
        this.totalEmployees = totalEmployees;
    }

    public Revenue() {

    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
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

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public int getNewEmployees() {
        return newEmployees;
    }

    public void setNewEmployees(int newEmployees) {
        this.newEmployees = newEmployees;
    }

    public int getOutOfStockProducts() {
        return outOfStockProducts;
    }

    public void setOutOfStockProducts(int outOfStockProducts) {
        this.outOfStockProducts = outOfStockProducts;
    }

    public int getCancelledOrders() {
        return cancelledOrders;
    }

    public void setCancelledOrders(int cancelledOrders) {
        this.cancelledOrders = cancelledOrders;
    }
}