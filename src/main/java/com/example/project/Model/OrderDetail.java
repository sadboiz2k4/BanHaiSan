package com.example.project.Model;

import java.util.Date;

public class OrderDetail {
    private int oDetailID;
    private int productID;
    private int orderID;
    private int price;
    private int quantity;
    private Date dateAdd;
    
    // Constructors
    public OrderDetail() {}
    
    public OrderDetail(int oDetailID, int productID, int orderID, int price, int quantity, Date dateAdd) {
        this.oDetailID = oDetailID;
        this.productID = productID;
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.dateAdd = dateAdd;
    }
    
    // Getters and Setters
    public int getODetailID() { return oDetailID; }
    public void setODetailID(int oDetailID) { this.oDetailID = oDetailID; }
    
    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }
    
    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }
    
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public Date getDateAdd() { return dateAdd; }
    public void setDateAdd(Date dateAdd) { this.dateAdd = dateAdd; }
}