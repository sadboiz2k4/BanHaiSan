package com.example.project.Dao;

import com.example.project.Model.CustomerTK;
import com.example.project.Model.Date;
import com.example.project.Model.Time;
import com.example.project.Model.Voucher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HandlerUserDB {
    Dao dao = new Dao();
    Connection conn = dao.getConn();

    public HandlerUserDB() {
    }
    public CustomerTK getInforUser(int id){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT *  FROM users INNER JOIN customers ON users.UserID = customers.UserID WHERE users.UserID = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LocalDateTime birthDay = rs.getTimestamp("DateOfBirth").toLocalDateTime();


                return new CustomerTK(rs.getInt("UserID"), rs.getString("FirstName"),
                        rs.getString("LastName"), rs.getString("Email"),
                        rs.getString("PhoneNumber"), rs.getBoolean("Gender"),new Date(birthDay.getDayOfMonth(), birthDay.getMonthValue(), birthDay.getYear()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void updateInforUser(int id, String email, String phone, String firstName, String lastName, boolean gender, String birthDay) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE users SET Email = ?, PhoneNumber = ? WHERE UserID = ?;");
            ps.setString(1, email);
            ps.setString(2, phone);
            ps.setInt(3, id);
            ps.executeUpdate();
            ps = conn.prepareStatement("UPDATE customers SET FirstName = ?, LastName = ?, Gender = ?, DateOfBirth = ? WHERE UserID = ?;");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setBoolean(3,gender);
            ps.setString(4, birthDay.toString());
            ps.setInt(5, id);
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Update success");
    }
    public int getPointUser(int id){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT Point FROM users WHERE UserID = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Point");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
    public int getRankUser(int id){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT Rank FROM users WHERE UserID = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Rank");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public List<Voucher> getAllVoucher(int id){
        try {
            List<Voucher> listVoucher = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM vouchersusers LEFT JOIN vouchers ON vouchersusers.VoucherID = vouchers.VoucherID WHERE vouchersusers.UserID = ? AND vouchersusers.Used = '0';");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listVoucher.add(new Voucher(rs.getInt("VoucherID"), rs.getString("NameVoucher"), rs.getString("Description"),
                        rs.getString("Code"), rs.getString("DiscountType"), rs.getInt("DiscountValue"),
                        rs.getInt("MaximumDiscountAmount"), rs.getInt("MinimumOrderAmount"), Time.convertLocalDatetimeToTime(rs.getTimestamp("StartDate").toLocalDateTime()),
                        Time.convertLocalDatetimeToTime(rs.getTimestamp("EndDate").toLocalDateTime())));
            }
            return listVoucher;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int countOrder (int id){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM orders WHERE UserID = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }



    public void close() {
        dao.closeConnection(conn);
    }

    public CustomerTK checkLogin(String uname, String pass)  {
        String encrypt = HandlerSupport.encrypt(pass);
        int nEncrypt = Integer.parseInt(String.valueOf(encrypt.charAt(0)));
        System.out.println(encrypt);
        System.out.println(nEncrypt);
        boolean isExist = false;
        int userId = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE (Username = ? OR PhoneNumber = ? OR Email = ?) AND IsBlock = 0");
            ps.setString(1, uname);
            ps.setString(2, uname);
            ps.setString(3, uname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isExist = true;
                userId = rs.getInt("UserID");
                System.out.println(userId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isExist){
            String passwordQuery = nEncrypt > 5
                    ? "SELECT * FROM users LEFT JOIN customers ON users.UserID = customers.UserID WHERE users.UserID = ? AND users.EPassword = SHA2(MD5(?), 256);"
                    : "SELECT * FROM users LEFT JOIN customers ON users.UserID = customers.UserID WHERE users.UserID = ? AND users.EPassword = MD5(SHA2(?, 256));";

            try (PreparedStatement pss = conn.prepareStatement(passwordQuery)) {
                pss.setInt(1, userId);
                pss.setString(2, encrypt);

                try (ResultSet rss = pss.executeQuery()) {
                    if (rss.next()) {
                        System.out.println("Tồn tại");
                        LocalDateTime birthDay = rss.getTimestamp("DateOfBirth").toLocalDateTime();
                        CustomerTK cus = new CustomerTK(
                                rss.getInt("UserID"),
                                rss.getString("FirstName"),
                                rss.getString("LastName"),
                                rss.getString("Email"),
                                rss.getString("PhoneNumber"),
                                rss.getBoolean("Gender"),
                                Date.valueOf(birthDay.toLocalDate())
                        );
                        System.out.println(cus);
                        return cus;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Lỗi xác thực mật khẩu", e);
            }
        }
        System.out.println("Không tồn tại");
        return null;
    }

    public boolean checkRoleAdmin(int userId) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE UserID = ?;");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("IsAdmin");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean checkEmailExist(String email) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE Email = ?;");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePassword(String email, String newPass) {
        String encrypt = HandlerSupport.encrypt(newPass);
        int nEncrypt = Integer.parseInt(String.valueOf(encrypt.charAt(0)));
        PreparedStatement ps = null;
        try {
            if (nEncrypt > 5) {
                ps = conn.prepareStatement("UPDATE users SET EPassword = SHA2(MD5(?), 256) WHERE Email = ?;");
                ps.setString(1, encrypt);
                ps.setString(2, email);
            }else{
                ps = conn.prepareStatement("UPDATE users SET EPassword = MD5(SHA2(?, 256)) WHERE Email = ?;");
                ps.setString(1, encrypt);
                ps.setString(2, email);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerNewAccount(String uname, String email, String pass) {
        String encrypt = HandlerSupport.encrypt(pass);
        int nEncrypt = Integer.parseInt(String.valueOf(encrypt.charAt(0)));
        PreparedStatement ps = null;
        try {
            if (nEncrypt > 5) {
                ps = conn.prepareStatement("INSERT INTO users (Email, PhoneNumber, Username, EPassword, IsAdmin, IsBlock, Status, NumberOfFailedLogin, Rank, Point, CreateDate, DeleteDate, LastUpdateDate, LastLoginDate) VALUES (?, '1234567890', ?,SHA2(MD5(?), 256), 0, 0, 'Active', 0, 0, 0, NOW(), NULL, NOW(), NOW());");
                ps.setString(2, uname);
                ps.setString(1, email);
                ps.setString(3, encrypt);
            }else{
                ps = conn.prepareStatement("INSERT INTO users (Email, PhoneNumber, Username, EPassword, IsAdmin, IsBlock, Status, NumberOfFailedLogin, Rank, Point, CreateDate, DeleteDate, LastUpdateDate, LastLoginDate) VALUES (?, '1234567890', ?,MD5(SHA2(?, 256)), 0, 0, 'Active', 0, 0, 0, NOW(), NULL, NOW(), NOW());");
                ps.setString(2, uname);
                ps.setString(1, email);
                ps.setString(3, encrypt);
            }
            ps.executeUpdate();

            ps = conn.prepareStatement("SELECT UserID FROM users WHERE Email = ? AND Username = ?;");
            ps.setString(1, email);
            ps.setString(2, uname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("UserID");
                ps = conn.prepareStatement("INSERT INTO customers (UserID, FirstName, LastName, DateOfBirth, Gender) VALUES (?, 'NEW USER', 'NEW USER', NOW(), 1);");
                ps.setInt(1, userId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkUsernameExist(String uname) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE Username = ?;");
            ps.setString(1, uname);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkPassword(int userID, String password) {
        String encrypt = HandlerSupport.encrypt(password);
        int nEncrypt = Integer.parseInt(String.valueOf(encrypt.charAt(0)));
        PreparedStatement ps = null;
        try {
            if (nEncrypt > 5) {
                ps = conn.prepareStatement("SELECT * FROM users WHERE UserID = ? AND EPassword = SHA2(MD5(?), 256);");
                ps.setInt(1, userID);
                ps.setString(2, encrypt);
            }else{
                ps = conn.prepareStatement("SELECT * FROM users WHERE UserID = ? AND EPassword = MD5(SHA2(?, 256));");
                ps.setInt(1, userID);
                ps.setString(2, encrypt);
            }
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws SQLException {
        HandlerUserDB handlerUserDB = new HandlerUserDB();
        handlerUserDB.checkLogin("user02","k");
    }
}