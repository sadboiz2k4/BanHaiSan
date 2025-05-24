package com.example.project.Dao;

import com.example.project.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductAdminDao {
    private final Dao dao;
    private static final String IMAGE_BASE_PATH = "Img/ImgProduct/";
    public ProductAdminDao() {
        this.dao = new Dao();
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String sql = "SELECT p.*, c.Name as Name, i.Stock, i.Status, " +
                    "img.ImgURL FROM Products p " +
                    "LEFT JOIN Categories c ON p.CategoriesID = c.CategoriesID " +
                    "LEFT JOIN Inventorys i ON p.ProductID = i.ProductID " +
                    "LEFT JOIN ImgProducts img ON p.ProductID = img.ProductID " +
                    "WHERE img.IsActive = 1 OR img.IsActive IS NULL " +
                    "ORDER BY p.ProductID ASC ";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
            return products;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public Product getProductById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String sql = "SELECT p.*, c.Name as Name, i.Stock, i.Status, " +
                    "img.ImgURL FROM Products p " +
                    "LEFT JOIN Categories c ON p.CategoriesID = c.CategoriesID " +
                    "LEFT JOIN Inventorys i ON p.ProductID = i.ProductID " +
                    "LEFT JOIN ImgProducts img ON p.ProductID = img.ProductID " +
                    "WHERE p.ProductID = ? AND (img.IsActive = 1 OR img.IsActive IS NULL)";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return extractProductFromResultSet(rs);
            }
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setProductID(rs.getInt("ProductID"));
        product.setCategoriesID(rs.getInt("CategoriesID"));
        product.setSupplierID(rs.getInt("SupplierID"));
        product.setNameProduct(rs.getString("NameProduct"));
        product.setDescription(rs.getString("Description"));
        product.setPrice(rs.getInt("Price"));
        product.setType(rs.getString("Type"));
        product.setCreateDate(rs.getDate("CreateDate"));
        product.setLastUpdateDate(rs.getDate("LastUpdateDate"));
        product.setStock(rs.getInt("Stock"));
        product.setStatus(rs.getString("Status"));
        product.setImgURL(rs.getString("ImgURL"));
        product.setCategoryName(rs.getString("Name"));

        return product;
    }

    public boolean deleteProduct(int productId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            conn.setAutoCommit(false);

            // First delete from ImgProducts
            String deleteImages = "DELETE FROM ImgProducts WHERE ProductID = ?";
            ps = conn.prepareStatement(deleteImages);
            ps.setInt(1, productId);
            ps.executeUpdate();
            ps.close();

            // Delete from Inventory
            String deleteInventory = "DELETE FROM Inventorys WHERE ProductID = ?";
            ps = conn.prepareStatement(deleteInventory);
            ps.setInt(1, productId);
            ps.executeUpdate();
            ps.close();

            // Finally delete from Products
            String deleteProduct = "DELETE FROM Products WHERE ProductID = ?";
            ps = conn.prepareStatement(deleteProduct);
            ps.setInt(1, productId);
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
    private String convertToWebPath(String filePath) {
        if (filePath == null) return null;
        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        return IMAGE_BASE_PATH + fileName;
    }

}
