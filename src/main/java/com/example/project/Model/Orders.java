package com.example.project.Model;

import java.util.Date;

public class Orders {
    private int orderID;
    private int userID;
    private String status;
    private String customerNote;
    private Date createDate;
    
    // Constructors
    public Orders() {}
    
    public Orders(int orderID, int userID, String status, String customerNote, Date createDate) {
        this.orderID = orderID;
        this.userID = userID;
        this.status = status;
        this.customerNote = customerNote;
        this.createDate = createDate;
    }
    
    // Getters and Setters
    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }
    
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getCustomerNote() { return customerNote; }
    public void setCustomerNote(String customerNote) { this.customerNote = customerNote; }
    
    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
}