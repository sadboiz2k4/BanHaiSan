package com.example.project.Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {

    //Make connect to database;
    private Connection conn;
    public static Connection getConnection (String url, String user, String password) {
        Connection connect;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connect;
    }
    public void closeConnection(Connection conn){
        try {
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Dao(Connection conn) {

        this.conn = conn;
    }

    public Dao(String url, String user, String password) {

        this.conn = getConnection(url, user, password);
    }

    public Dao() {

        this.conn = getConnection("jdbc:mysql://localhost:3306/osdb", "root", "");
    }

    public Connection getConn() {
        return conn;
    }
//    private static final String url = "jdbc:mysql://localhost:3306/osdb";
//    private static final String user = "root";

//    Connection conn = null;
//    PreparedStatement stmt = null;
//        try {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        conn = DriverManager.getConnection(url, user, "");
//        String sql = "SELECT * FROM product ORDER BY product_created_at DESC";
//        stmt = conn.prepareStatement(sql);
//
//        stmt.execute();
//
//        ResultSet rs = stmt.getResultSet();
//
//        ArrayList<Product> result = new ArrayList<>();
//
//        while (rs.next()) {
//            result.add(createProduct(rs));
//        }
//        for (Product p: result) {
//            System.out.println(p);
//        }
//
//
//
//
//    } catch (SQLException e) {
//        e.printStackTrace();
//    } catch (ClassNotFoundException e) {
//
//        e.printStackTrace();
//    } finally {
//        try {
//            if (stmt != null) stmt.close();
//            if (conn != null) conn.close();
//        } catch (SQLException se) {
//            se.printStackTrace();
//        }
//    }
}
