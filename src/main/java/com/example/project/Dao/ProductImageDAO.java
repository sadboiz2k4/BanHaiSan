package com.example.project.Dao;

import com.example.project.Model.ProductImage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImageDAO {
    private final Dao dao;

    public ProductImageDAO() {
        this.dao = new Dao();
    }

    public ProductImageDAO(String url, String user, String password) {
        this.dao = new Dao(url, user, password);
    }

    public List<ProductImage> getProductImages(int productId) {
        String sql = "SELECT * FROM ImgProduct WHERE ProductID = ? ORDER BY Priority";
        List<ProductImage> images = new ArrayList<>();

        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProductImage image = new ProductImage();
                image.setImgID(rs.getInt("ImageID"));
                image.setImgURL(rs.getString("ImgURL"));
                image.setAltText(rs.getString("AltText"));
                image.setPriority(rs.getInt("Priority"));
                images.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    public void closeConnection() {
        if (dao != null && dao.getConn() != null) {
            dao.closeConnection(dao.getConn());
        }
    }
}