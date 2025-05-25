package com.example.project.Controller;

import com.example.project.Dao.ProductAdminDao;
import com.example.project.Model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "product-admin", urlPatterns = {"/product-admin", "/product-admin/*"})
public class ProductAdminController extends HttpServlet {
    private ProductAdminDao productAdminDao;

    @Override
    public void init() throws ServletException {
        super.init();
        productAdminDao = new ProductAdminDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all products
                List<Product> products = productAdminDao.getAllProducts();
                req.setAttribute("products", products);
                req.getRequestDispatcher("/ProductManagement.jsp").forward(req, resp);
            } else {
                // Handle specific product
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length >= 2) {
                    int productId = Integer.parseInt(pathParts[1]);
                    Product product = productAdminDao.getProductById(productId);

                    if (product != null) {
                        req.setAttribute("product", product);
                        req.getRequestDispatcher("/ProductManagement.jsp").forward(req, resp);
                    } else {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in ProductController servlet: " + e.getMessage());
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && pathInfo.startsWith("/delete")) {
            try {
                int productId = Integer.parseInt(req.getParameter("id"));
                boolean deleted = productAdminDao.deleteProduct(productId);

                if (deleted) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
}
