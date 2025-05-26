package com.example.project.Dao;

import com.example.project.Model.AddressTK;
import com.example.project.Model.AddressUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HandlerAddressDB {
    Dao dao = new Dao();
    Connection con = dao.getConn();
    public List<AddressUser> getAllAddressUser(int id){
        List<AddressUser> listAddressUsers = new ArrayList<>();
        try {
            PreparedStatement ps  = con.prepareStatement("SELECT * FROM addressusers LEFT JOIN address ON addressusers.AddressID = address.AddressID WHERE addressusers.UserID =?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                listAddressUsers.add(new AddressUser(rs.getInt("AUserID"), rs.getString("FullnameReceiver"),
                        rs.getString("NumberPhoneReceiver"), new AddressTK(rs.getInt("AddressID"), rs.getString("Street"),
                        rs.getString("WardOrcommune"), rs.getString("District"), rs.getString("ProvinceOrCity")),
                        rs.getString("AddressType"), rs.getBoolean("IsPrimary")));
            }
            return listAddressUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void close(){
        dao.closeConnection(con);
    }

    public void setPrimaryAddress(int id, int AUid) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE addressusers SET IsPrimary = 0 WHERE IsPrimary = 1;");
            ps.executeUpdate();
            ps = con.prepareStatement("UPDATE addressusers SET IsPrimary = 1 WHERE AUserID = ? AND UserID = ?;");
            ps.setInt(2, id);
            ps.setInt(1, AUid);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAddress(int id, int userID) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM addressusers WHERE AUserID = ? AND UserID = ?;");
            ps.setInt(1, id);
            ps.setInt(2, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public AddressUser getAddressUserForID(int uID,int id){
        try {
            PreparedStatement ps  = con.prepareStatement("SELECT * FROM addressusers LEFT JOIN address ON addressusers.AddressID = address.AddressID WHERE addressusers.UserID =? AND addressusers.AUserID = ? ;");
            ps.setInt(2, id);
            ps.setInt(1, uID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return (new AddressUser(rs.getInt("AUserID"), rs.getString("FullnameReceiver"),
                        rs.getString("NumberPhoneReceiver"), new AddressTK(rs.getInt("AddressID"), rs.getString("Street"),
                        rs.getString("WardOrcommune"), rs.getString("District"), rs.getString("ProvinceOrCity")),
                        rs.getString("AddressType"), rs.getBoolean("IsPrimary")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void addAddress(int idUser, String name, String phone, String address, String province, String district, String ward, String street, boolean isPrimary, String type) {
        try {
            // Bắt đầu giao dịch
            con.setAutoCommit(false);

            // Thêm địa chỉ vào bảng address
            PreparedStatement ps = con.prepareStatement("INSERT INTO address (Street, WardOrcommune, District, ProvinceOrCity) VALUES (?,?,?,?);");
            ps.setString(1, street);
            ps.setString(2, ward);
            ps.setString(3, district);
            ps.setString(4, province);
            ps.executeUpdate();

            // Lấy AddressID vừa tạo
            ps = con.prepareStatement("SELECT LAST_INSERT_ID();");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int addressID = rs.getInt(1);

            if(isPrimary){
                ps = con.prepareStatement("UPDATE addressusers SET IsPrimary = 0 WHERE UserID = ?;");
                ps.setInt(1, idUser);
                ps.executeUpdate();
            }
            // Thêm vào bảng addressusers
            ps = con.prepareStatement("INSERT INTO addressusers (UserID, AddressID, FullnameReceiver, NumberPhoneReceiver, AddressType, IsPrimary, Description, CreateDate, LastUpdateDate) VALUES (?,?,?,?,?,?,?,?,?);");
            ps.setInt(1, idUser);
            ps.setInt(2, addressID);
            ps.setString(3, name);
            ps.setString(4, phone);
            ps.setString(5, type);
            ps.setBoolean(6, isPrimary);
            ps.setString(7, address);
            ps.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(9, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();

            // Xác nhận giao dịch
            con.commit();
        } catch (SQLException e) {
            try {
                // Rollback nếu có lỗi
                con.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new RuntimeException("Lỗi khi thêm địa chỉ", e);
        } finally {
            try {
                // Khôi phục trạng thái tự động commit
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateAddressUser(int idUser, int id, String name, String phone, String address, String province, String district, String ward, String street, boolean b) {
    }

    public void updateAddress(int id, String name, String phone, String address, String province, String district, String ward, String street, boolean isPrimary, String type) {
        try {
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement("UPDATE address SET Street = ?, WardOrcommune = ?, District = ?, ProvinceOrCity = ? WHERE AddressID = (SELECT AddressID FROM addressusers WHERE AUserID = ?);");
            ps.setString(1, street);
            ps.setString(2, ward);
            ps.setString(3, district);
            ps.setString(4, province);
            ps.setInt(5, id);
            ps.executeUpdate();

            if(isPrimary){
                ps = con.prepareStatement("UPDATE addressusers SET IsPrimary = 0 WHERE UserID = ?;");
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            ps = con.prepareStatement("UPDATE addressusers SET FullnameReceiver = ?, NumberPhoneReceiver = ?, AddressType = ?, IsPrimary = ?, Description = ?, LastUpdateDate = ? WHERE AUserID = ?;");
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, type);
            ps.setBoolean(4, isPrimary);
            ps.setString(5, address);
            ps.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setInt(7, id);
            ps.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new RuntimeException("Lỗi khi cập nhật địa chỉ", e);
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public AddressUser getAddressPrimary(int userID) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM addressusers LEFT JOIN address ON addressusers.AddressID = address.AddressID WHERE addressusers.UserID = ? AND addressusers.IsPrimary = 1;");
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new AddressUser(rs.getInt("AUserID"), rs.getString("FullnameReceiver"),
                        rs.getString("NumberPhoneReceiver"), new AddressTK(rs.getInt("AddressID"), rs.getString("Street"),
                        rs.getString("WardOrcommune"), rs.getString("District"), rs.getString("ProvinceOrCity")),
                        rs.getString("AddressType"), rs.getBoolean("IsPrimary"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void main(String[] args) {
        HandlerAddressDB handlerAddressDB = new HandlerAddressDB();
        System.out.println(handlerAddressDB.getAddressPrimary(2));
    }

    public boolean checkPrimaryAddress(int idUser) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM addressusers WHERE UserID = ? AND IsPrimary = 1;");
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
