package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.ProductLarge;
import com.example.project.Service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

// [Bước 2.2.2] Servlet xử lý yêu cầu tìm kiếm từ search.jsp
@WebServlet(name = "searchAll", value = "/search-all")
public class SearchAll extends HttpServlet {

    public void init() {
        // Hàm khởi tạo servlet, nếu cần thêm cấu hình thì thêm vào đây
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // [Bước 2.2.2] Nhận yêu cầu GET từ trình duyệt với tham số valueSearch
        HttpSession session = request.getSession(); // Lấy session hiện tại
        String valueSearch = request.getParameter("valueSearch"); // Lấy từ khóa tìm kiếm từ request

        // Kiểm tra nếu từ khóa tìm kiếm rỗng hoặc null
        if (valueSearch == null || valueSearch.trim().isEmpty()) {
//            [Bước 2.2.3.a.1] Hiển thị lỗi "Vui lòng nhập từ khóa"
            request.setAttribute("errorMessage", "Vui lòng nhập từ khóa tìm kiếm");
            try {
                request.getRequestDispatcher("/search.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
            return;  // dừng xử lý tiếp
        }

        int currentPage = 1; // Mặc định là trang đầu tiên
        int totalProductPerPage = 20; // Số lượng sản phẩm mỗi trang

        // [Bước 2.2.3] Gọi Service để xử lý logic tìm kiếm
        // [Bước 2.2.3] Gọi phương thức searchProducts() trong Service
        ProductService productService = new ProductService();

        // [Bước 2.2.7] Nhận kết quả trả về từ Service
        List<ProductLarge> listProduct = productService.searchProducts(valueSearch);

        int totalProduct  = listProduct.size(); // Tổng số sản phẩm tìm được

        // Tính toán danh sách sản phẩm cho trang hiện tại (paging)
        // [Bước 2.2.9] Chuẩn bị dữ liệu để hiển thị ở JSP
        List<ProductLarge> listProductForPage = listProduct.subList(
                (currentPage - 1) * totalProductPerPage,
                Math.min(currentPage * totalProductPerPage, totalProduct)
        );

        // [Bước 2.2.9] Đặt danh sách sản phẩm vào request attribute lên trang JSP
        if(!listProductForPage.isEmpty()){
            request.setAttribute("ListProductSearchAll", listProductForPage);
        }

        // Tính tổng số trang dựa vào tổng số sản phẩm và số sản phẩm mỗi trang
        int totalPage = (totalProduct % totalProductPerPage == 0)
                ? totalProduct / totalProductPerPage
                : totalProduct / totalProductPerPage + 1;

        // [Bước 2.2.9] Gửi thông tin danh mục, phân trang về JSP
        List<String> listCategories = productService.getCategoriesFromListProduct(listProduct);
        Collections.sort(listCategories); // Sắp xếp danh sách loại sản phẩm theo thứ tự bảng chữ cái

        // Gán các thông tin cần thiết để hiển thị ra trang JSP
        request.setAttribute("listCategories", listCategories);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("valueSearch", valueSearch);

        // [Bước 2.2.9] Lưu toàn bộ danh sách vào session để dùng cho phân trang/lọc
        session.setAttribute("listProduct", listProduct);
        session.setAttribute("listProductInit", listProduct); // Ban đầu chưa áp dụng filter

        try {
            // [Bước 2.2.9] Forward đến trang JSP để hiển thị kết quả tìm kiếm
            request.getRequestDispatcher("/search.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo flush buffer và đóng kết nối khi hoàn tất
            response.flushBuffer();
//            productService.close();  // Đóng kết nối hoặc tài nguyên từ service
        }
    }

    public void destroy() {
        // Được gọi khi servlet bị huỷ, có thể dùng để giải phóng tài nguyên nếu cần
    }
}
