package com.example.project.Model;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailTK {
    private int orderDetailId;
    private int priceProduct;
    private int quantity;
    private int productId;

    public OrderDetailTK() {
    }

    public OrderDetailTK(int orderDetailId, int priceProduct, int quantity, int productId) {
        this.orderDetailId = orderDetailId;
        this.priceProduct = priceProduct;
        this.quantity = quantity;
        this.productId = productId;
    }
    public OrderDetailTK(int priceProduct, int quantity, int productId) {
        this.priceProduct = priceProduct;
        this.quantity = quantity;
        this.productId = productId;
    }

    public static int [] isExistProductID(List<OrderDetailTK> shoppingCart, int productID) {
        int [] result = new int[3];
        for (OrderDetailTK orderDetail : shoppingCart) {
            int pID = orderDetail.getProductId();
            System.out.println(pID+"..........");
            if(pID == productID){
                result[0] = 1;
                result[1] = orderDetail.getQuantity();
                result[2] = shoppingCart.indexOf(orderDetail);
                return result;
            }
        }
        int [] notExist = {0,0,0};
        return notExist;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public static void main(String[] args) {
        List <OrderDetailTK> list = new ArrayList<>();
        list.add(new OrderDetailTK(1,2,3,4));
        list.add(new OrderDetailTK(5,6,7,8));
        list.add(new OrderDetailTK(9,10,11,12));
        int [] isExist = OrderDetailTK.isExistProductID(list, 8);
        System.out.println(isExist[0]);
        System.out.println(isExist[1]);
        System.out.println(isExist[2]);
    }
}
