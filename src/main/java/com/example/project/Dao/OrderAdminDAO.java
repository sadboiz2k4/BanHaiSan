package com.example.project.Dao;

import com.example.project.Model.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderAdminDAO {
    private final Dao dao;

    public OrderAdminDAO() {
        this.dao = new Dao();
    }

    public List<OrderStatus> getAllOrders() throws SQLException {
        List<OrderStatus> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String sql = "SELECT o.OrderID, c.FirstName, c.LastName, " +
                    "p.NameProduct, od.Quantity, od.Price * od.Quantity as TotalAmount, " +
                    "o.Status, o.CreateDate " +
                    "FROM Orders o " +
                    "JOIN Customers c ON o.UserID = c.UserID " +
                    "JOIN OrderDetails od ON o.OrderID = od.OrderID " +
                    "JOIN Products p ON od.ProductID = p.ProductID " +
                    "ORDER BY o.CreateDate ASC";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(extractOrderFromResultSet(rs));
            }
            return orders;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public OrderStatus getOrderById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String sql = "SELECT o.OrderID, c.FirstName, c.LastName, " +
                    "p.NameProduct, od.Quantity, od.Price * od.Quantity as TotalAmount, " +
                    "o.Status, o.CreateDate " +
                    "FROM Orders o " +
                    "JOIN Customers c ON o.UserID = c.UserID " +
                    "JOIN OrderDetails od ON o.OrderID = od.OrderID " +
                    "JOIN Products p ON od.ProductID = p.ProductID " +
                    "WHERE o.OrderID = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return extractOrderFromResultSet(rs);
            }
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private OrderStatus extractOrderFromResultSet(ResultSet rs) throws SQLException {
        OrderStatus order = new OrderStatus();
        order.setOrderId(rs.getInt("OrderID"));
        order.setCustomerName(rs.getString("LastName") + " " + rs.getString("FirstName"));
        order.setProductName(rs.getString("NameProduct"));
        order.setQuantity(rs.getInt("Quantity"));
        order.setTotalAmount(rs.getInt("TotalAmount"));
        order.setStatus(rs.getString("Status"));
        order.setOrderDate(rs.getString("CreateDate"));
        return order;
    }

    public boolean deleteOrder(int orderId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            conn.setAutoCommit(false);

            // First delete from Payments
            String deletePayments = "DELETE FROM Payments WHERE OrderID = ?";
            ps = conn.prepareStatement(deletePayments);
            ps.setInt(1, orderId);
            ps.executeUpdate();
            ps.close();

            // Delete from Shippings
            String deleteShippings = "DELETE FROM Shippings WHERE OrderID = ?";
            ps = conn.prepareStatement(deleteShippings);
            ps.setInt(1, orderId);
            ps.executeUpdate();
            ps.close();

            // Delete from OrderDetails
            String deleteOrderDetails = "DELETE FROM OrderDetails WHERE OrderID = ?";
            ps = conn.prepareStatement(deleteOrderDetails);
            ps.setInt(1, orderId);
            ps.executeUpdate();
            ps.close();

            // Finally delete from Orders
            String deleteOrder = "DELETE FROM Orders WHERE OrderID = ?";
            ps = conn.prepareStatement(deleteOrder);
            ps.setInt(1, orderId);
            int result = ps.executeUpdate();

            conn.commit();
            return result > 0;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}