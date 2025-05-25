package com.example.project.Model;

import java.util.List;

public class Order {
    private int orderId;
    private String status;
    private String customerNote;
    private Time dateCreate;
    private List<OrderDetailTK> listOrderDetail;

    public Order() {
    }

    public Order(int orderId, String status, String customerNote, Time dateCreate, List<OrderDetailTK> listOrderDetail) {
        this.orderId = orderId;
        this.status = status;
        this.customerNote = customerNote;
        this.dateCreate = dateCreate;
        this.listOrderDetail = listOrderDetail;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public Time getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Time dateCreate) {
        this.dateCreate = dateCreate;
    }

    public List<OrderDetailTK> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<OrderDetailTK> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }
    public int quantityProduct(){
        int quantity = 0;
        for (OrderDetailTK orderDetail : listOrderDetail){
            quantity += orderDetail.getQuantity();
        }
        return quantity;
    }
    public int totalMoney(){
        int total = 0;
        for (OrderDetailTK orderDetail : listOrderDetail){
            total += orderDetail.getPriceProduct() * orderDetail.getQuantity();
        }
        return total;
    }
}
