package com.example.project.Dao;

import com.example.project.Model.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {
    private Dao dao;
    private Connection connection;
    // Assume you have a DBConnection utility class
    public StoreDAO() {
        this.dao = new Dao();
        this.connection = dao.getConn();
    }
    public List<Store> getAllStores() {
        List<Store> stores = new ArrayList<>();
        String query = "SELECT * FROM stores";
        
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Store store = new Store(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone_number"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude")
                );
                stores.add(store);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stores;
    }
}