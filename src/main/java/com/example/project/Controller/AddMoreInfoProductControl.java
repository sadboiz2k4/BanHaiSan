package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.Category;
import com.example.project.Model.ProductLarge;
import com.example.project.Model.Stock;
import com.example.project.Model.Supplier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddMoreInfoProductControl",urlPatterns = {"/add-more-info-product"})
public class AddMoreInfoProductControl extends HttpServlet {
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
        String category = daoProduct.getProductCategoryByID(Integer.parseInt(pid));

        req.setAttribute("category", category);

        String supplier = daoProduct.getProductSupplierByID(Integer.parseInt(pid));
        req.setAttribute("supplier", supplier);

        Stock stock = daoProduct.getStockById(Integer.parseInt(pid));
        req.setAttribute("stock", stock);


        req.getRequestDispatcher("/AddInfoProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imgUrl = req.getParameter("imageFile");
        String pid = req.getParameter("productID");
        int stock = Integer.parseInt(req.getParameter("stock"));
        DaoProduct dao = new DaoProduct();
        dao.insertImgProduct(pid, imgUrl);
        String status = req.getParameter("status");
        if (stock>0){
            dao.insertStockProduct(pid, stock, status);
        }else {
            dao.insertStockProduct(pid, stock, status);
        }

        resp.sendRedirect("product-admin");

    }
}
