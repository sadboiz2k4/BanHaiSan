package com.example.project.Dao;

import com.example.project.Model.*;

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

//    //Load product
//    public List<String> findImgProdutc(int id) {
//        List<String> listImg = new ArrayList<>();
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ImgProducts WHERE ProductId = ?");
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                listImg.add(rs.getString("ImgURL"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return listImg;
//    }
//Load product
public static List<String> findImgProdutc(int id) {
    List<String> listImg = new ArrayList<>();
    String sql = "SELECT * FROM ImgProducts WHERE ProductId = ?";

    try {Connection conn = Dao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
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

    public static List<ProductLarge> loadProductLargeFromDB(ResultSet rs) {
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

    public static void loadDicountPricePL(List<ProductLarge> listProduct) {
        for (ProductLarge product : listProduct) {
            int discountPrice = 0;
            int discountPencent = 0;
            int discountFixed = 0;
            String sql = "SELECT DiscountType, DiscountValue FROM Promotions WHERE ProductID = ?";

            try {Connection conn = Dao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
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

    public static List<String> getCategoriesFromListProduct(List<ProductLarge> listProduct) {
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
        String query = "SELECT p.ProductID, p.NameProduct, p.Description, p.Price, p.Type, p.DiscountPrice, " +
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
                product.setDiscountPrice(rs.getInt("DiscountPrice"));
                product.setImg(findImgProdutc(id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public List<Category> getAllCategory(){
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM categories");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Category(rs.getInt(1),rs.getString(2)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public String getProductCategoryByID(int id) {
        String name = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT categories.`Name` from products INNER JOIN categories\n" +
                    "ON products.CategoriesID = categories.CategoriesID\n" +
                    "WHERE products.ProductID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
    public List<Supplier> getAllSupplier(){
        List<Supplier> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM suppliers");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Supplier(rs.getInt(1),rs.getString(2)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public String getProductSupplierByID(int id) {
        String name = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT suppliers.`Name` from products INNER JOIN suppliers\n" +
                    "ON products.SupplierID = suppliers.SupplierID\n" +
                    "WHERE products.ProductID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    public Stock getStockById(int id) {
            Stock stock = null;
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * from inventorys WHERE ProductID = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    stock = new Stock();
                    stock.setProductID(rs.getInt("ProductID"));
                    stock.setStock_quantity(rs.getInt("Stock"));
                    stock.setStatus(rs.getString("Status"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return stock;
        }


    public void deleteProduct(String pid) {
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa sản phẩm!", e);
        }
    }
    public void deleteImgProduct(String pid) {
        String sql = "DELETE FROM ImgProducts WHERE ProductID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProduct(String name, String category, String supplier, String price, String type,  String description) {
        String sql = "INSERT INTO Products (NameProduct, CategoriesID, SupplierID, Price,DistcountPrice, Type, Description, CreateDate, LastUpdateDate) VALUES (?, ?, ?, ?,0, ?, ?,'2024-12-11','2024-12-11')";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setString(3, supplier);
            ps.setString(4, price);
            ps.setString(5, type);
            ps.setString(6, description);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertImgProduct(String productID, String imgURL) {
        String sql = "INSERT INTO imgproducts (ProductID,AltText,IsActive,Priority,LastUpdateDate,ImgURL) VALUES (?, 'Ảnh sản phẩm', 1, 6, '2024-12-16', ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, productID);
            ps.setString(2, imgURL);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertStockProduct(String productID, int stock_quantity, String status) {
        String sql = "INSERT INTO inventorys (OSID, ProductID, AddressID, Stock, Status) VALUES (1,?,1,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, productID);
            ps.setInt(2, stock_quantity);
            ps.setString(3, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // [Bước 2.2.4] ProductService -> DaoProduct
    public static List<ProductLarge> loadProductSearch(String valueSearch) {
        List<ProductLarge> listProduct = new ArrayList<>();

        // [Bước 2.2.5] DaoProduct -> Database
        // Chuẩn bị câu lệnh SQL với từ khóa tìm kiếm
        String sql = "SELECT products.*, categories.Name AS NameCategories, promotions.PromotionID " +
                "FROM products LEFT JOIN promotions ON products.ProductID = promotions.ProductID " +
                "INNER JOIN categories ON products.CategoriesID = categories.CategoriesID " +
                "WHERE products.NameProduct LIKE ? OR promotions.Name LIKE ? OR categories.Name LIKE ? " +
                "ORDER BY products.NameProduct LIKE ? DESC";

        try (Connection conn = Dao.getConnection();  // Lấy kết nối MỚI từ Connection Pool
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Truyền tham số tìm kiếm
            String searchParam = "%" + valueSearch + "%";
            ps.setString(1, searchParam);
            ps.setString(2, searchParam);
            ps.setString(3, searchParam);
            ps.setString(4, searchParam);

            // [Bước 2.2.6] Database -> DaoProduct
            // Thực thi câu lệnh SQL và nhận kết quả ResultSet
            try (ResultSet rs = ps.executeQuery()) {

                // [Bước 2.2.7] DaoProduct -> ProductService
                // Chuyển đổi ResultSet thành danh sách ProductLarge
                listProduct = loadProductLargeFromDB(rs);

                // Gọi thêm hàm để tính và gán giá giảm giá cho sản phẩm (nếu có khuyến mãi)
                loadDicountPricePL(listProduct);
            }

        } catch (SQLException e) {
            // [Bước 2.2.10.a] Hiển thị thông báo khi không tìm thấy sản phẩm
            throw new RuntimeException("Lỗi khi tìm kiếm sản phẩm", e);
        }
        // Trả về danh sách sản phẩm đã tìm được
        return listProduct;
    }

    public static void main(String[] args) {

        DaoProduct dao = new DaoProduct();
        System.out.println(dao.getProductSupplierByID(3));



    }

    public List<String> findPromoList(String valueFind) {
        List<String> listPromo = new ArrayList<>();
        List<String> listPromoNameProduct = new ArrayList<>();
        List<String> listPromoCategories = new ArrayList<>();
        List<String> listPromoPromotions = new ArrayList<>();

        String sql ="SELECT DISTINCT Name FROM Promotions WHERE Name LIKE ?";

        try {Connection conn = Dao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
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
}
