package com.example.project.Dao;

import com.example.project.Model.Order;
import com.example.project.Model.OrderDetailTK;
import com.example.project.Model.Time;
import com.example.project.Model.Voucher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HandlerOrderDB {
    Dao dao = new Dao();
    Connection connection = dao.getConn();
    
    public List<Order> getAllOrder(int userID){
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE UserID = ?;");
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            List<Order> listOrder = new ArrayList<>();
            while (rs.next()){
                listOrder.add(new Order(rs.getInt("OrderID"), rs.getString("Status"),
                        rs.getString("CustomerNote"),
                        Time.convertLocalDatetimeToTime(rs.getTimestamp("CreateDate").toLocalDateTime()),
                        getAllOrderDetailFromOrderID(rs.getInt("OrderID"))));
            }
            return listOrder;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<OrderDetailTK> getAllOrderDetailFromOrderID(int orderID){
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM orderdetails WHERE OrderID = ?;");
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            List<OrderDetailTK> listOrderDetail = new ArrayList<>();
            while (rs.next()){
                listOrderDetail.add(new OrderDetailTK(rs.getInt("ODetailID"), rs.getInt("Price"),
                        rs.getInt("Quantity"), rs.getInt("ProductID")));
            }
            return listOrderDetail;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void close(){
        dao.closeConnection(connection);
    }

    public void cancelOrder(int id) {
        try {
            if(id <= 0){
                return;
            }
            if(!checkOrderExist(id)){
                return;
            }
            PreparedStatement ps = connection.prepareStatement("UPDATE orders SET Status = 'Hủy' WHERE OrderID = ?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkOrderExist(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE OrderID = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> filterStatusOrder(int id,String status){
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE Status = ? AND UserID=?;");
            ps.setString(1, status);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            List<Order> listOrder = new ArrayList<>();
            while (rs.next()){
                listOrder.add(new Order(rs.getInt("OrderID"), rs.getString("Status"),
                        rs.getString("CustomerNote"),
                        Time.convertLocalDatetimeToTime(rs.getTimestamp("CreateDate").toLocalDateTime()),
                        getAllOrderDetailFromOrderID(rs.getInt("OrderID"))));
            }
            return listOrder;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkVoucher(int uID, String voucherCode) {
        try {
            PreparedStatement ps = connection.prepareStatement(" SELECT * FROM vouchersusers LEFT JOIN vouchers ON vouchersusers.VoucherID = vouchers.VoucherID WHERE vouchersusers.UserID = ? AND vouchers.Code = ? AND vouchers.StartDate <= NOW() AND vouchers.EndDate >= NOW() ;");
            ps.setInt(1, uID);
            ps.setString(2, voucherCode);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        HandlerOrderDB handlerOrderDB = new HandlerOrderDB();
        List<OrderDetailTK> listOrderDetail = handlerOrderDB.getAllOrderDetailFromOrderID(1);
        handlerOrderDB.proceedToOrder(2, 1, 1, 1, "massage", "null", listOrderDetail);
    }

    public Voucher getVoucher(String voucherCode) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vouchers WHERE Code = ?;");
            ps.setString(1, voucherCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Voucher(rs.getInt("VoucherID"), rs.getString("NameVoucher"), rs.getString("Description"),
                        rs.getString("Code"), rs.getString("DiscountType"), rs.getInt("DiscountValue"),
                        rs.getInt("MaximumDiscountAmount"), rs.getInt("MinimumOrderAmount"), Time.convertLocalDatetimeToTime(rs.getTimestamp("StartDate").toLocalDateTime()),
                        Time.convertLocalDatetimeToTime(rs.getTimestamp("EndDate").toLocalDateTime()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void proceedToOrder(int userID, int idAddress, int idShip, int idPayment, String massage, String voucher, List<OrderDetailTK> listOrderDetail) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (UserID, CustomerNote, Status, CreateDate) VALUES (?,?,'Đang xử lý',NOW());");
            ps.setInt(1, userID);
            ps.setString(2, massage);
            ps.executeUpdate();

            ps = connection.prepareStatement("SELECT MAX(OrderID) AS OrderIDMax FROM orders WHERE UserID = ?;");
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            int orderID = 0;
            if (rs.next()) {
                orderID = rs.getInt("OrderIDMax");
            }

            ps = connection.prepareStatement("INSERT INTO Shippings (OrderID, SMID, TrackingNumber, DeliveryDate,EstimatedDeliveryDate,AUserID) VALUES (?,?,?,NOW(),NOW(),?);");
            ps.setInt(1, orderID);
            ps.setInt(2, idShip);
            ps.setString(3, HandlerSupport.generateRandomCode(12));
            ps.setInt(4, idAddress);
            ps.executeUpdate();

            for (OrderDetailTK orderDetail : listOrderDetail) {
                ps = connection.prepareStatement("INSERT INTO orderdetails (OrderID, Price, Quantity, ProductID, DateAdd) VALUES (?,?,?,?,NOW());");
                ps.setInt(1, orderID);
                ps.setInt(2, orderDetail.getPriceProduct());
                ps.setInt(3, orderDetail.getQuantity());
                ps.setInt(4, orderDetail.getProductId());
                ps.executeUpdate();
            }

            ps = connection.prepareStatement("INSERT INTO Payments (OrderID, DiscountAmount, TotalAmount, FinalAmount, PaymentStatus, PaymentDate, TransactionHash, LastUpdateDate, CreateDate, PMID) VALUES(?,?,?,?,'Paid',NOW(),'Đang xử lý',NOW(),NOW(),?);");
            ps.setInt(1, orderID);
            Voucher voucher1 = getVoucher(voucher);
            if (voucher1 != null) {
                ps.setInt(2, voucher1.getDiscountValue());
            } else {
                ps.setInt(2, 0);
            }
            int totalAmount = 0;
            for (OrderDetailTK orderDetail : listOrderDetail) {
                totalAmount += orderDetail.getPriceProduct() * orderDetail.getQuantity();
            }
            ps.setInt(3, totalAmount);
            int finalAmount = 0;
            if (voucher1 != null) {
                if (voucher1.getDiscountType().equals("Percent")) {
                    finalAmount = totalAmount - totalAmount * (voucher1.getDiscountValue() / 100);
                } else {
                    finalAmount = totalAmount - voucher1.getDiscountValue();
                }
            } else {
                ps.setInt(4, totalAmount);
            }
            ps.setInt(4,finalAmount);
            ps.setInt(5, idPayment);
            ps.executeUpdate();


            if (!voucher.equals("null")) {
                ps = connection.prepareStatement("SELECT VoucherID FROM vouchers WHERE Code = ?;");
                ps.setString(1, voucher);
                rs = ps.executeQuery();
                int voucherID = 0;
                if (rs.next()) {
                    voucherID = rs.getInt("VoucherID");
                }
                ps = connection.prepareStatement("UPDATE vouchersusers SET Used = '1' WHERE UserID = ? AND VoucherID = ?;");
                ps.setInt(1, userID);
                ps.setInt(2, voucherID);
                ps.executeUpdate();
            }
            ps = connection.prepareStatement("SELECT Point FROM users WHERE UserID = ?;");
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            int point = 0;
            if (rs.next()) {
                point = rs.getInt("Point");
            }
            int rank = 0;
            if (point >= 1000) {
                rank = 1;
            }
            if (point >= 5000) {
                rank = 2;
            }
            if (point >= 10000) {
                rank = 3;
            }
            ps = connection.prepareStatement("UPDATE users SET Point = ?, Rank = ? WHERE UserID = ?;");
            ps.setInt(1, point + Math.abs(finalAmount / 1000));
            ps.setInt(2, rank);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Integer> getRevernue() {
        try {
            List<Integer> list = new ArrayList<>();
            for(int i= 1; i<=12 ;i++){
                PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS Total FROM Orders WHERE MONTH(CreateDate) = ?;");
                ResultSet rs = ps.executeQuery();
                ps.setInt(1, i);
                if (rs.next()) {
                    list.add(rs.getInt("Total"));
                }else{
                    list.add(0);
                }
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
