package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "LoadProductControl",value = {"/loadProduct"})
public class LoadProductControl extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        DaoProduct daoProduct = new DaoProduct();

        String pid = req.getParameter("pid");

        ProductLarge product = daoProduct.getProductByID(Integer.parseInt(pid));
        req.setAttribute("detail",product);
        List<Category> listC = daoProduct.getAllCategory();
        req.setAttribute("listCategory", listC);
        List<Supplier> listS = daoProduct.getAllSupplier();
        req.setAttribute("listSupplier", listS);
        Stock stock = daoProduct.getStockById(Integer.parseInt(pid));
        req.setAttribute("stock", stock);

        req.getRequestDispatcher("/EditProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("productName");
        String stock = req.getParameter("stock");
        String status = req.getParameter("status");
        String category = req.getParameter("categoryId");
        String supplier = req.getParameter("supplierId");
        String price = req.getParameter("price");
        String type = req.getParameter("productType");
        String image = req.getParameter("imageFile");
        String description = req.getParameter("description");

        DaoProduct dao = new DaoProduct();
        dao.insertProduct(name,  category, supplier, price, type, description);

        resp.sendRedirect("product-admin");

    }
}
