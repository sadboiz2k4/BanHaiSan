package com.example.project.Dao;

import com.example.project.Model.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AddOrderDAO {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private final Dao dao;

    public AddOrderDAO(Dao dao) {
        this.dao = dao;
    }

    public boolean AddOrderDAO(OrderStatus order) {
        boolean success = false;
        String query = "INSERT INTO Orders (order_id, customer_name, product_name, quantity, total_price, status) " +
                "VALUES (?, ?, ?, ?, ?, ?,)";

        try {
            conn = dao.getConn();
            ps = conn.prepareStatement(query);

            ps.setInt(1, order.getOrderId());
            ps.setString(2, order.getCustomerName());
            ps.setString(5, order.getProductName());
            ps.setInt(6, order.getQuantity());
            ps.setDouble(7, order.getTotalAmount());
            ps.setString(8, order.getStatus());


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
