package com.example.project.Dao;

import com.example.project.Model.Time;

import java.sql.*;
import java.time.LocalDateTime;

public class DatabaseTimeManager {
    Time time;
    Dao dao = new Dao();
    Connection conn = dao.getConn();
    public Time loadTimePromotions(String promotionName) {
        LocalDateTime timeLC = LocalDateTime.now();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT EndDate FROM Promotions WHERE Name = ? AND EndDate >= NOW() AND StartDate<=NOW() ORDER BY EndDate ASC LIMIT 1");
            ps.setString(1, promotionName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               timeLC = rs.getTimestamp("EndDate").toLocalDateTime();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dao.closeConnection(conn);
        return new Time(timeLC.getSecond(), timeLC.getMinute(),timeLC.getHour(),
                timeLC.getDayOfMonth(), timeLC.getMonthValue(), timeLC.getYear());
    }

    public static void main(String[] args) throws SQLException {
        DatabaseTimeManager databaseTimeManager = new DatabaseTimeManager();
        Time time = databaseTimeManager.loadTimePromotions("Flash Sale");
        System.out.println(time.toString());
        int [] s = time.timeMinus(Time.convertLocalDatetimeToTime(LocalDateTime.now()));
        System.out.println(s[0]+" "+s[1]+" "+s[2]);
    }

    public void close() {
        dao.closeConnection(conn);
    }
}
