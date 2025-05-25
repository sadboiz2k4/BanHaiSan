package com.example.project.Model;

import java.io.Serializable;
import java.util.List;

public class CartProduct implements Serializable {
    private int id;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private String type;
    private List<String> img;
    private int discountPrice;


    public CartProduct(int id, String name, String description, int price, int quantity, String type, List<String> img, int discountPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.img = img;
        this.discountPrice = discountPrice;
    }

    public CartProduct() {
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

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", type='" + type + '\'' +
                ", img=" + img +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
