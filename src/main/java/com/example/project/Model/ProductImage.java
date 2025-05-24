package com.example.project.Model;

import java.sql.Timestamp;

public class ProductImage {
    private Integer imgID;
    private Integer productID;
    private String altText;
    private Boolean isActive;
    private Integer priority;
    private Timestamp lastUpdateDate;
    private String imgURL;

    // Constructors
    public ProductImage() {
    }

    public ProductImage(Integer imgID, Integer productID, String altText, Boolean isActive,
                        Integer priority, Timestamp lastUpdateDate, String imgURL) {
        this.imgID = imgID;
        this.productID = productID;
        this.altText = altText;
        this.isActive = isActive;
        this.priority = priority;
        this.lastUpdateDate = lastUpdateDate;
        this.imgURL = imgURL;
    }

    // Getters and Setters
    public Integer getImgID() {
        return imgID;
    }

    public void setImgID(Integer imgID) {
        this.imgID = imgID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "imgID=" + imgID +
                ", productID=" + productID +
                ", altText='" + altText + '\'' +
                ", isActive=" + isActive +
                ", priority=" + priority +
                ", lastUpdateDate=" + lastUpdateDate +
                ", imgURL='" + imgURL + '\'' +
                '}';
    }
}