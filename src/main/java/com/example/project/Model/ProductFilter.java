package com.example.project.Model;

import java.util.List;

public class ProductFilter {
    private int id;
    private String name;
    private String description;
    private int price;
    private int discountPrice;
    private String type;
    private List<String> img;
    private String categories;
    private Time dateOfEntry;
    private boolean isPromotion;
    private int quantity;

    public ProductFilter(String type, int id, String name, String description, int price, int discountPrice, List<String> img, String categories, Time dateOfEntry, boolean isPromotion, int quantity) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discountPrice = discountPrice;
        this.img = img;
        this.categories = categories;
        this.dateOfEntry = dateOfEntry;
        this.isPromotion = isPromotion;
        this.quantity = quantity;
    }
    public static ProductFilter convertProductLargeToPL(ProductLarge productLarge, int quantity){
        return new ProductFilter(productLarge.getType(),productLarge.getId(),productLarge.getName(),productLarge.getDescription(),productLarge.getPrice(),productLarge.getDiscountPrice(),productLarge.getImg(),productLarge.getCategories(),productLarge.getDateOfEntry(),productLarge.isPromotion(),quantity);
    }
    public static ProductLarge convertProductFilterToPL(ProductFilter productFilter){
        return new ProductLarge(productFilter.getId(),productFilter.getName(),productFilter.getDescription(),productFilter.getPrice(),productFilter.getDiscountPrice(),productFilter.getType(),productFilter.getImg(),productFilter.getCategories(),productFilter.getDateOfEntry(),productFilter.isPromotion());
    }

    public void setPromotion(boolean promotion) {
        isPromotion = promotion;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductLarge{" +
                "img=" + img +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", type='" + type + '\'' +
                ", categories='" + categories + '\'' +
                ", dateOfEntry=" + dateOfEntry +
                ", isPromotion=" + isPromotion +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Time getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Time dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public boolean isPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(boolean promotion) {
        isPromotion = promotion;
    }


}
