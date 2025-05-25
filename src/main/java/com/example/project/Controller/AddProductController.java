package com.example.project.Controller;

import com.example.project.Dao.AddProductDAO;
import com.example.project.Dao.Dao;
import com.example.project.Dao.DaoProduct;
import com.example.project.Model.Category;
import com.example.project.Model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AddControl",  value = "/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,  // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class AddProductController extends HttpServlet {
    private AddProductDAO productDAO;
    private String uploadPath;

    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new AddProductDAO(new Dao());
        // Đường dẫn lưu ảnh - cần điều chỉnh theo cấu hình server
        uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        // Lấy danh sách nhà cung cấp từ database
        DaoProduct daoProduct = new DaoProduct();

        List<Category> listC = daoProduct.getAllCategory();
        request.setAttribute("listCategory", listC);
        request.getRequestDispatcher("AddProduct.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            // Tạo đối tượng ProductL
            Product product = new Product();

            // Set các thông tin cơ bản
            product.setProductID(Integer.parseInt(request.getParameter("productId")));
            product.setNameProduct(request.getParameter("productName"));
            product.setStock(Integer.parseInt(request.getParameter("stock")));
            product.setStatus(request.getParameter("status").equals("1") ? "Còn hàng" : "Hết hàng");
            product.setCategoriesID(Integer.parseInt(request.getParameter("categoryId")));
            product.setSupplierID(Integer.parseInt(request.getParameter("supplierId")));
            product.setPrice(Integer.parseInt(request.getParameter("price")));
            product.setDescription(request.getParameter("description"));

            // Xử lý file ảnh
            Part filePart = request.getPart("imageFile");
            String fileName = getSubmittedFileName(filePart);
            if (fileName != null && !fileName.isEmpty()) {
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                String filePath = uploadPath + File.separator + uniqueFileName;
                filePart.write(filePath);
                product.setImgURL("/uploads/" + uniqueFileName);
            }

            // Set các thông tin thời gian
            Date currentDate = new Date();
            product.setCreateDate(currentDate);
            product.setLastUpdateDate(currentDate);

            // Lưu sản phẩm vào database
            boolean success = productDAO.AddProductDAO(product);

            if (success) {
                // Redirect tới trang chi tiết sản phẩm
                response.sendRedirect(request.getContextPath() + "/productL-details?productID=" + product.getProductID());
            } else {
                request.setAttribute("error", "Không thể thêm sản phẩm. Vui lòng thử lại.");
                request.getRequestDispatcher("/AddProduct.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Dữ liệu số không hợp lệ: " + e.getMessage());
            request.getRequestDispatcher("/AddProduct.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            request.getRequestDispatcher("/AddProduct.jsp").forward(request, response);
        }
    }

    // Helper method để lấy tên file từ Part
    private String getSubmittedFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return null;
    }
}