package com.example.project.Service;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.ProductLarge;
import com.example.project.Model.ProductTK;

import java.util.List;

public class ProductService {
    // Tạo đối tượng Dao để thao tác dữ liệu sản phẩm
    static DaoProduct productDao = new DaoProduct();

    public List<ProductTK> getAllProduct() {
        return productDao.getAllProduct();
    }

    // ===============================
    // BẮT ĐẦU CHUỖI TÌM KIẾM SẢN PHẨM
    // ===============================

    // [Bước 2.2.3] SearchAll → ProductService
    // Servlet gọi sang phương thức này để xử lý logic tìm kiếm
    public List<ProductLarge> searchProducts(String valueSearch) {
        // [Bước 2.2.4] ProductService → DaoProduct
        // Gọi DAO để thực hiện truy vấn tìm sản phẩm phù hợp từ database
        return DaoProduct.loadProductSearch(valueSearch);
    }

    // [Bước 2.2.9] SearchAll dùng phương thức này để lấy danh mục sản phẩm phục vụ hiển thị lọc
    public List<String> getCategoriesFromListProduct(List<ProductLarge> productList) {
        return DaoProduct.getCategoriesFromListProduct(productList);
    }

    // KHÔNG LIÊN QUAN ĐẾN TÌM KIẾM
    public ProductTK getDetail(String in) {
        try {
            int id = Integer.parseInt(in);
            return productDao.getById(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

//    // [Bước 2.2.10] Khi kết thúc xử lý (forward hoặc thoát servlet), có thể gọi close
//    public void close() {
//        DaoProduct.close();
//    }
}
