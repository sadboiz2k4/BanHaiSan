package com.example.project.Dao;

import com.example.project.Model.Ship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HandlerShip {
    Dao dao = new Dao();
    Connection connection = dao.getConn();
    public List<Ship> getAllShip() throws SQLException {
        List<Ship> ships = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM ships");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ships.add(new Ship(rs.getInt("SMID"), rs.getString("NameShip"),
                        rs.getInt("Cost"), rs.getString("Description"),
                        rs.getString("Carrier")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ships;
    }

    public Ship getPrimaryShip() {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ships WHERE IsPrimary = 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Ship(rs.getInt("SMID"), rs.getString("NameShip"),
                        rs.getInt("Cost"), rs.getString("Description"),
                        rs.getString("Carrier"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void main(String[] args) {
        HandlerShip handlerShip = new HandlerShip();
        Ship ship = handlerShip.getPrimaryShip();
        System.out.println(ship.getShipName());
    }

    public void close() {
        dao.closeConnection(connection);
    }
}
