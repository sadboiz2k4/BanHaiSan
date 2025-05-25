package com.example.project.Dao;

import com.example.project.Model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private final Dao dao;

    public ReviewDAO() {
        this.dao = new Dao();
    }

    public List<Review> getReviewsByProductId(int productId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM Reviews WHERE ProductID = ? ORDER BY CreateDate DESC";

        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setReviewID(rs.getInt("ReviewID"));
                review.setProductID(rs.getInt("ProductID"));
                review.setRating(rs.getDouble("Rating"));
                review.setReviewContent(rs.getString("ReviewContent"));
                review.setCreateDate(rs.getDate("CreateDate"));
                review.setLastUpdateDate(rs.getDate("LastUpdateDate"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public void closeConnection() {
        dao.closeConnection(dao.getConn());
    }

    public static void main(String[] args) {
        ReviewDAO dao = new ReviewDAO();
        System.out.println(dao.getReviewsByProductId(1));
    }
}