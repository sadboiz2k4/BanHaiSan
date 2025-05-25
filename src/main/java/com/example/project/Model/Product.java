package com.example.project.Model;

import java.util.Date;
import java.util.List;

public class Product {
    private int productID;
    private int categoriesID;
    private int supplierID;
    private String nameProduct;
    private String description;
    private String descriptionDetails;;
    private int price;
    private int discountPrice;
    private String type;
    private Date createDate;
    private Date lastUpdateDate;
    private int stock;
    private String status;
    private String imgURL;
    private String categoryName; // Will store category names like "BÁN CHẠY NHẤT", "KHUYẾN MÃI", etc.
    private int countView;
    private String origin;
    private List<Review> reviews;
    private double averageRating;
    private int reviewCount;

    public Product(String descriptionDetails) {
        this.descriptionDetails = descriptionDetails;
    }

    public String getDescriptionDetails() {
        return descriptionDetails;
    }

    public void setDescriptionDetails(String descriptionDetails) {
        this.descriptionDetails = descriptionDetails;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Product(double averageRating, int reviewCount) {
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Product(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Product(int productID, int categoriesID, int supplierID, String nameProduct, String description, int price, int discountPrice, String type, Date createDate, Date lastUpdateDate, int stock, String status, String imgURL, String categoryName, int countView, String origin) {
        this.productID = productID;
        this.categoriesID = categoriesID;
        this.supplierID = supplierID;
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
        this.discountPrice = discountPrice;
        this.type = type;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.stock = stock;
        this.status = status;
        this.imgURL = imgURL;
        this.categoryName = categoryName;
        this.countView = countView;
        this.origin = origin;

    }

    public Product() {

    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCategoriesID() {
        return categoriesID;
    }

    public void setCategoriesID(int categoriesID) {
        this.categoriesID = categoriesID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCountView() {
        return countView;
    }

    public void setCountView(int countView) {
        this.countView = countView;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }



}