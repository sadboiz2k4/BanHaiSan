package com.example.project.Dao;

import com.example.project.Model.Product;
import com.example.project.Model.ProductTK;
import com.example.project.Model.ProductLarge;
import com.example.project.Model.Time;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class DaoProduct {
    Dao dao = new Dao();
    Connection conn;

    public DaoProduct() {
        this.conn = dao.getConn();
    }

    //Load product
    public List<String> findImgProdutc(int id) {
        List<String> listImg = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ImgProducts WHERE ProductId = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listImg.add(rs.getString("ImgURL"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listImg;
    }

    public List<ProductTK> loadProductFromDB(ResultSet rs) {
        List<ProductTK> listProduct = new ArrayList<>();
        try {
            while (rs.next()) {
                ProductTK product = new ProductTK();
                product.setId(rs.getInt("ProductId"));
                product.setName(rs.getString("NameProduct"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getInt("Price"));
                product.setType(rs.getString("Type"));
                product.setImg(findImgProdutc(rs.getInt("ProductId")));
                listProduct.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProduct;
    }

    public List<ProductLarge> loadProductLargeFromDB(ResultSet rs) {
        List<ProductLarge> listProduct = new ArrayList<>();
        try {
            while (rs.next()) {
                ProductLarge product = new ProductLarge();
                product.setId(rs.getInt("ProductId"));
                product.setName(rs.getString("NameProduct"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getInt("Price"));
                product.setType(rs.getString("Type"));
                product.setImg(findImgProdutc(rs.getInt("ProductId")));
                product.setCategories(rs.getString("NameCategories"));
                product.setDateOfEntry(Time.convertLocalDatetimeToTime(rs.getTimestamp("DateOfEntry").toLocalDateTime()));
                boolean isPromotion = false;
                int idPromotion = rs.getInt("PromotionID");
                if (idPromotion > 0) {
                    isPromotion = true;
                }
                product.setIsPromotion(isPromotion);

                listProduct.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProduct;
    }

    public List<ProductTK> loadProductPromotion(String promotionName) {
        List<ProductTK> listProductPromo = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products P  INNER JOIN Promotions Pr ON P.ProductID = Pr.ProductID WHERE Pr.Name = ?  AND Pr.EndDate >= NOW() AND Pr.StartDate <= NOW()");
            ps.setString(1, promotionName);
            ResultSet rs = ps.executeQuery();
            listProductPromo = loadProductFromDB(rs);
            loadDicountPrice(listProductPromo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProductPromo;
    }

    public List<ProductLarge> loadProductSearch(String valueSearch) {
        List<ProductLarge> listProduct = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT products.*, categories.Name AS NameCategories,promotions.PromotionID FROM products LEFT JOIN promotions ON products.ProductID = promotions.ProductID INNER JOIN categories ON products.CategoriesID = categories.CategoriesID WHERE products.NameProduct LIKE ? OR promotions.Name LIKE ? OR categories.Name LIKE ? ORDER BY products.NameProduct LIKE ? DESC ;");
            ps.setString(1, "%" + valueSearch + "%");
            ps.setString(2, "%" + valueSearch + "%");
            ps.setString(3, "%" + valueSearch + "%");
            ps.setString(4, "%" + valueSearch + "%");
            ResultSet rs = ps.executeQuery();
            listProduct = loadProductLargeFromDB(rs);
            loadDicountPricePL(listProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProduct;
    }

    public void loadDicountPrice(List<ProductTK> listProduct) {
        for (ProductTK product : listProduct) {
            int discountPrice = 0;
            int discountPencent = 0;
            int discountFixed = 0;
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT DiscountType, DiscountValue FROM Promotions WHERE ProductID = ?");
                ps.setInt(1, product.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("DiscountType").equals("Percent")) {
                        discountPencent += product.getPrice() * rs.getInt("DiscountValue") / 100;
                    } else {
                        discountFixed += rs.getInt("DiscountValue");
                    }
                }
                discountPrice = product.getPrice() - discountFixed;
                discountPrice -= discountPrice * discountPencent;
                product.setDiscountPrice(discountPrice);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadDicountPricePL(List<ProductLarge> listProduct) {
        for (ProductLarge product : listProduct) {
            int discountPrice = 0;
            int discountPencent = 0;
            int discountFixed = 0;
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT DiscountType, DiscountValue FROM Promotions WHERE ProductID = ?");
                ps.setInt(1, product.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("DiscountType").equals("Percent")) {
                        discountPencent += product.getPrice() * rs.getInt("DiscountValue") / 100;
                    } else {
                        discountFixed += rs.getInt("DiscountValue");
                    }
                }
                discountPrice = product.getPrice() - discountFixed;
                discountPrice -= discountPrice * discountPencent;
                product.setDiscountPrice(discountPrice);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<String> findPromoList(String valueFind) {
        List<String> listPromo = new ArrayList<>();
        List<String> listPromoNameProduct = new ArrayList<>();
        List<String> listPromoCategories = new ArrayList<>();
        List<String> listPromoPromotions = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT Name FROM Promotions WHERE Name LIKE ?");
            ps.setString(1, "%" + valueFind + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listPromoPromotions.add(rs.getString("Name"));
            }
            ps = conn.prepareStatement("SELECT DISTINCT NameProduct FROM Products WHERE NameProduct LIKE ?;");
            ps.setString(1, "%" + valueFind + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                listPromoNameProduct.add(rs.getString("NameProduct"));
            }
            ps = conn.prepareStatement("SELECT DISTINCT Name FROM categories WHERE Name LIKE ?");
            ps.setString(1, "%" + valueFind + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                listPromoCategories.add(rs.getString("Name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listPromo.addAll(listPromoPromotions);
        listPromo.addAll(listPromoNameProduct);
        listPromo.addAll(listPromoCategories);
        dao.closeConnection(conn);
        if (listPromo.size() > 10) {
            return listPromo.subList(0, 10);
        }
        return listPromo;
    }

    public List<ProductTK> loadProductBestSale(int limt) {
        List<ProductTK> listProductBestSale = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT products.*,SUM(orderdetails.Quantity) FROM products INNER JOIN orderdetails ON products.ProductID = orderdetails.ProductID GROUP BY products.ProductID ORDER BY SUM(orderdetails.Quantity) DESC LIMIT ?;");
            ps.setInt(1, limt);
            ResultSet rs = ps.executeQuery();
            listProductBestSale = loadProductFromDB(rs);
            loadDicountPrice(listProductBestSale);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProductBestSale;
    }

    public List<ProductTK> loadProductYourOrder(int userID, int monthTime) {
        List<ProductTK> listProductBestSale = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT products.* FROM products INNER JOIN orderdetails ON products.ProductID = orderdetails.ProductID INNER JOIN orders ON orderdetails.OrderID = orders.OrderID INNER JOIN users ON orders.UserID = users.UserID WHERE users.UserID = ?  AND orders.CreateDate >= DATE_SUB(NOW(), INTERVAL 1 MONTH);");
            ps.setInt(1, userID);
            ps.setInt(2, monthTime);
            ResultSet rs = ps.executeQuery();
            listProductBestSale = loadProductFromDB(rs);
            loadDicountPrice(listProductBestSale);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProductBestSale;
    }


    public List<ProductTK> loadProductOnSale(int limt) {
        List<ProductTK> listProductBestSale = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT products.* FROM products INNER JOIN promotions ON products.ProductID = promotions.ProductID WHERE promotions.StartDate <=NOW()  AND promotions.EndDate>=NOW() AND promotions.Name != 'Flash Sale'   ORDER BY promotions.EndDate ASC LIMIT ?;");
            ps.setInt(1, limt);
            ResultSet rs = ps.executeQuery();
            listProductBestSale = loadProductFromDB(rs);
            loadDicountPrice(listProductBestSale);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProductBestSale;
    }

    public List<ProductTK> loadProductCategories(int idCategories) {
        List<ProductTK> listProductPromo = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT products.* FROM products INNER JOIN categories ON products.CategoriesID = categories.CategoriesID WHERE categories.CategoriesID = ?;");
            ps.setInt(1, idCategories);
            ResultSet rs = ps.executeQuery();
            listProductPromo = loadProductFromDB(rs);
            loadDicountPrice(listProductPromo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProductPromo;
    }

    public List<String[]> loadBannerForHome() {
        List<String[]> listBanner = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT banners.LinkURL, banners.Position, imgbanners.ImgURL, imgbanners.AltText FROM banners INNER JOIN imgbanners ON banners.BannerID = imgbanners.BannerID WHERE banners.StartDate <= NOW() AND banners.EndDate >= NOW();");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] banner = new String[4];
                banner[0] = rs.getString("LinkURL");
                banner[1] = rs.getString("Position");
                banner[2] = rs.getString("ImgURL");
                banner[3] = rs.getString("AltText");
                listBanner.add(banner);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listBanner;
    }

    public List<String[]> findBannerWithPosition(List<String[]> listBanner, String position) {
        List<String[]> listBannerP = new ArrayList<>();
        for (String[] banner : listBanner) {
            if (banner[1].equals(position)) {
                listBannerP.add(banner);
            }
        }
        return listBannerP;
    }

    public List<ProductLarge> filterProductByPrice(List<ProductLarge> listProduct, int minPrice, int maxPrice) {
        List<ProductLarge> listProductFilter = new ArrayList<>();
        for (ProductLarge product : listProduct) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                listProductFilter.add(product);
            }
        }
        return listProductFilter;
    }

    public List<ProductLarge> filterProductByPromotion(List<ProductLarge> listProduct) {
        List<ProductLarge> listProductFilter = new ArrayList<>();
        for (ProductLarge product : listProduct) {
            if (product.isPromotion()) {
                listProductFilter.add(product);
            }
        }
        return listProductFilter;
    }

    public List<ProductLarge> filterProductByNewProduct(List<ProductLarge> listProduct) {
        List<ProductLarge> listProductFilter = new ArrayList<>();
        for (ProductLarge product : listProduct) {
            if (product.getDateOfEntry().getYear() == LocalDateTime.now().getYear()) {
                if (product.getDateOfEntry().getMonth() == LocalDateTime.now().getMonthValue()) {
                    listProductFilter.add(product);
                }
            }
        }
        return listProductFilter;
    }

    public List<ProductLarge> filterProductByCategories(List<ProductLarge> listProduct, List<String> listChoseCategories) {
        List<ProductLarge> listProductFilter = new ArrayList<>();
        for (ProductLarge product : listProduct) {
            if (listChoseCategories.contains(product.getCategories())) {
                listProductFilter.add(product);
            }
        }
        return listProductFilter;
    }

    public List<String> getCategoriesFromListProduct(List<ProductLarge> listProduct) {
        HashSet<String> listCategories = new HashSet<>();
        for (ProductLarge product : listProduct) {
            listCategories.add(product.getCategories());
        }
        return new ArrayList<>(listCategories);
    }

    public List<ProductTK> getAllFavoriteProduct(int userID) {
        List<ProductTK> listProduct = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT products.* FROM products INNER JOIN favoriteproducts ON products.ProductID = favoriteproducts.ProductID WHERE favoriteproducts.UserID = ?;");
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            listProduct = loadProductFromDB(rs);
            loadDicountPrice(listProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProduct;
    }




    public void close() {
        dao.closeConnection(conn);
    }

    public ProductLarge getProductByID(int productId) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT products.*, categories.Name AS NameCategories,promotions.PromotionID FROM products LEFT JOIN promotions ON products.ProductID = promotions.ProductID INNER JOIN categories ON products.CategoriesID = categories.CategoriesID WHERE products.ProductID = ?;");
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            List<ProductLarge> listProduct = loadProductLargeFromDB(rs);
            if (listProduct.isEmpty()) {
                return null; // Trả về null nếu không tìm thấy sản phẩm
            }
            loadDicountPricePL(listProduct);
            return listProduct.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getQuantitySale(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(Quantity) FROM orderdetails WHERE ProductID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNameCatergoryByID(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT Name FROM categories WHERE CategoriesID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Lấy tất cả sản phẩm( imgURL đầu tiên)
    public List<ProductTK> getAllProduct() {
        List<ProductTK> productList = new ArrayList<>();
        String query = "SELECT p.ProductID, p.NameProduct, p.Description, p.Price, p.Type, " +
                "(SELECT i.ImgURL FROM imgproducts i " +
                " WHERE i.ProductID = p.ProductID " +
                " ORDER BY i.IProductID ASC LIMIT 1) AS ImgURL " +
                "FROM products p";
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProductTK product = new ProductTK();
                product.setId(rs.getInt("ProductID"));
                product.setName(rs.getString("NameProduct"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getInt("Price"));
                product.setType(rs.getString("Type"));

                String imgUrl = rs.getString("ImgURL");
                product.setImg(imgUrl != null ? Collections.singletonList(imgUrl) : new ArrayList<>());
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách sản phẩm!", e);
        }
        return productList;
    }

    //Lấy sản phẩm theo id( trả về ProductTK)
    public ProductTK getById(int id) {
        ProductTK product = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products WHERE ProductID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new ProductTK();
                product.setId(rs.getInt("ProductID"));
                product.setName(rs.getString("NameProduct"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getInt("Price"));
                product.setType(rs.getString("Type"));
                product.setImg(findImgProdutc(id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
//    public List<Product> getAllCategory(){
//        ArrayList<Product> res = new ArrayList<>();
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT Name FROM categories");
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getString(1);
//            }
//            return null;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return res;
//    }
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String fls = new DaoProduct().getNameCatergoryByID(1);

            System.out.println(fls);

    }

}
