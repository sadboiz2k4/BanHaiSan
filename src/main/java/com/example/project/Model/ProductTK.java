package com.example.project.Model;

import java.util.List;

public class ProductTK {
    private int id;
    private String name;
    private String description;
    private int price;
    private int discountPrice;
    private String type;
    private List<String> img;

    public ProductTK(int id, String name, String description, int price, int discountPrice, String type, List<String> img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discountPrice = discountPrice;
        this.type = type;
        this.img = img;
    }
    @Override
    public String toString() {
        return "ProductTK{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", type='" + type + '\'' +
                ", img=" + img +
                '}';
    }

    public ProductTK() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void setPrice(int price) {

        this.price = price;
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

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }
}
