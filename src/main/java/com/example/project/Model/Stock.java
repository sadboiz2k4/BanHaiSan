package com.example.project.Model;

public class Stock {
    private int ProductID;
    private int stock_quantity;
    private String status;
    public Stock() {
    }

    public Stock(int productID, int stock_quantity, String status) {
        ProductID = productID;
        this.stock_quantity = stock_quantity;
        this.status = status;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ProductID=" + ProductID +
                ", stock_quantity=" + stock_quantity +
                ", status='" + status + '\'' +
                '}';
    }
}
