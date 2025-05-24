package com.example.project.Service;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.ProductTK;

import java.util.List;

public class ProductService {
    static DaoProduct productDao = new DaoProduct();

    public List<ProductTK> getAllProduct() {
        return productDao.getAllProduct();
    }

    public ProductTK getDetail(String in) {
        try {
            int id = Integer.parseInt(in);
            return productDao.getById(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
