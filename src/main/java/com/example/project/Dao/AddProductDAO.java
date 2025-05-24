package com.example.project.Dao;

import com.example.project.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddProductDAO {
    private final Dao dao;
    Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public AddProductDAO(Dao dao) {
        this.dao = dao;
    }

    public boolean AddProductDAO(Product product) {
        boolean success = false;
        String query = "INSERT INTO Products (product_id, product_name, stock, status, " +
                "category_id, supplier_id, price, image_path, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = dao.getConn();
            ps = conn.prepareStatement(query);

            ps.setInt(1, product.getProductID());
            ps.setString(2, product.getNameProduct());
            ps.setInt(3, product.getStock());
            ps.setString(4, product.getStatus());
            ps.setInt(5, product.getCategoriesID());
            ps.setInt(6, product.getSupplierID());
            ps.setDouble(7, product.getPrice());
            ps.setString(8, product.getImgURL());
            ps.setString(9, product.getDescription());

            int rowsAffected = ps.executeUpdate();
            success = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return success;
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
