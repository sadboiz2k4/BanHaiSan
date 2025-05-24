package com.example.project.Model;

public class Inventory {
    private int inventoryId;
    private int osId;
    private int productId;
    private int addressId;
    private int stock;
    private String status;

    // Constructor
    public Inventory(int inventoryId, int osId, int productId, int addressId, int stock, String status) {
        this.inventoryId = inventoryId;
        this.osId = osId;
        this.productId = productId;
        this.addressId = addressId;
        this.stock = stock;
        this.status = status;
    }
public Inventory() {    }
    // Getters and Setters
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getOsId() {
        return osId;
    }

    public void setOsId(int osId) {
        this.osId = osId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", osId=" + osId +
                ", productId=" + productId +
                ", addressId=" + addressId +
                ", stock=" + stock +
                ", status='" + status + '\'' +
                '}';
    }
}