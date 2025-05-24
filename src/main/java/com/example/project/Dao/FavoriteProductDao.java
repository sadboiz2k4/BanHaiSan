package com.example.project.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteProductDao {
    private Connection connection;

    public FavoriteProductDao(Connection connection) {
        this.connection = connection;
    }

    // Add favorite product
    public boolean addFavoriteProduct(int userId, int productId) {
        String sql = "INSERT INTO favoriteproducts (UserID, ProductID) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Remove favorite product
    public boolean removeFavoriteProduct(int userId, int productId) {
        String sql = "DELETE FROM favoriteproducts WHERE UserID = ? AND ProductID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if a product is favorite
    public boolean isProductFavorite(int userId, int productId) {
        String sql = "SELECT FVID FROM favoriteproducts WHERE UserID = ? AND ProductID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all favorite products by user
    public List<Integer> getFavoriteProductsByUser(int userId) {
        List<Integer> productIds = new ArrayList<>();
        String sql = "SELECT ProductID FROM favoriteproducts WHERE UserID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productIds.add(resultSet.getInt("ProductID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productIds;
    }
}
