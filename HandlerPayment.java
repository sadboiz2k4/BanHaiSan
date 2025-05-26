package com.example.project.Dao;

import com.example.project.Model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HandlerPayment {
    Dao dao = new Dao();
    Connection connection = dao.getConn();
    public List<Payment> getAllPayment() {
        List<Payment> payments = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM paymentmethods");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                payments.add(new Payment(rs.getInt("PMID"), rs.getString("Name"),
                        rs.getString("ImgURL"), rs.getString("ImgAltText"),
                        rs.getString("Sponsor")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return payments;
    }
    public Payment getPrimaryPayment() {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM paymentmethods WHERE IsPrimary = 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Payment(rs.getInt("PMID"), rs.getString("Name"),
                        rs.getString("ImgURL"), rs.getString("ImgAltText"),
                        rs.getString("Sponsor"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void close() {
        dao.closeConnection(connection);
    }
}
