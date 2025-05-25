package com.example.project.Dao;

import com.example.project.Model.User;

import java.sql.*;

public class UserDAO {
    Dao dao = new Dao();
    Connection conn;

    public UserDAO() {
        this.conn = dao.getConn();
    }

    public User findByProviderId(String provider, String providerId) throws SQLException {
        String sql = "SELECT * FROM users WHERE Provider=? AND ProviderId=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, provider);
            ps.setString(2, providerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("id"));
                u.setProvider(provider);
                u.setProviderId(providerId);
                u.setUsername(rs.getString("userName"));
                u.setEmail(rs.getString("email"));
                u.setAccessToken(rs.getString("accessToken"));
                return u;
            }
            return null;
        }
    }

    public void save(User user) throws SQLException {
        String sql = "INSERT INTO users(Provider,ProviderId,Username,Email) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getProvider());
            ps.setString(2, user.getProviderId());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getEmail());
            ps.setString(6, user.getAccessToken());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                user.setUserID(keys.getInt(1));
            }
        }
    }
}
