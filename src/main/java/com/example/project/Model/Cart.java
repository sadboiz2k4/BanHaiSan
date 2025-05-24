package com.example.project.Model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Cart {
    public Map<Integer, CartProduct> data = new HashMap<>();

    public boolean add(ProductTK product) {
        if (data.containsKey(product.getId())) {
            update(product.getId(), data.get(product.getId()).getQuantity() + 1);
            return true;
        }
        data.put(product.getId(), convert(product));
        return true;
    }
    //add cart có cập nhật số lượng( từ trang detailProduct)
    public boolean add(ProductTK product, int quantity) {
        if (data.containsKey(product.getId())) {
            int current = data.get(product.getId()).getQuantity();
            update(product.getId(), current + quantity);
            return true;
        }
        CartProduct item = convert(product);
        item.setQuantity(quantity);
        data.put(product.getId(), item);
        return true;
    }


    public boolean update(int id, int quantity) {
        if (!data.containsKey(id)) return false;
        CartProduct cartProduct = data.get(id);
        cartProduct.setQuantity(quantity);
        data.put(id, cartProduct);
        return true;
    }

    public boolean remove(int id) {
        if (!data.containsKey(id)) return false;
        data.remove(id);
        return true;
    }

    public List<CartProduct> getList() {
        return new ArrayList<>(data.values());
    }

    public int getTotalQuantity() {
        AtomicInteger total = new AtomicInteger(0);
        data.values().stream().forEach(cartProduct -> total.addAndGet(cartProduct.getQuantity()));
        return total.get();
    }

//    public Double getTotal() {
//        AtomicReference<Double> total = new AtomicReference<>(0.0);
//        data.values().stream().forEach(cartProduct -> total.updateAndGet(v -> v + (cartProduct.getQuantity() * cartProduct.getPrice())));
//        return total.get();
//    }
public Double getTotal() {
    AtomicReference<Double> total = new AtomicReference<>(0.0);
    data.values().forEach(cartProduct -> {
        double effectivePrice = cartProduct.getPrice() - cartProduct.getDiscountPrice();
        total.updateAndGet(v -> v + (cartProduct.getQuantity() * effectivePrice));
    });
    return total.get();
}


    private CartProduct convert(ProductTK p) {
        CartProduct re = new CartProduct();
        re.setId(p.getId());
        re.setName(p.getName());
        re.setPrice(p.getPrice());
        re.setImg(p.getImg());
        re.setType(p.getType());
        re.setQuantity(1);
        re.setDiscountPrice(p.getDiscountPrice());
        return re;
    }
}
