package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.Category;
import com.example.project.Model.Supplier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "AddControl",  value = "/add-product")
public class AddProductControl extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        DaoProduct daoProduct = new DaoProduct();
        List<Category> listC = daoProduct.getAllCategory();
        req.setAttribute("listCategory", listC);
        List<Supplier> listS = daoProduct.getAllSupplier();
        req.setAttribute("listSupplier", listS);
        req.getRequestDispatcher("AddProduct.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pname = req.getParameter("productName");
        String pcategory = req.getParameter("categoryId");
        String psupplier = req.getParameter("supplierId");
        String pprice = req.getParameter("price");
        String ptype = req.getParameter("productType");
        String pdescription = req.getParameter("description");

        DaoProduct dao = new DaoProduct();
        dao.insertProduct(pname,  pcategory, psupplier, pprice, ptype,  pdescription);

        resp.sendRedirect("product-admin");

    }
}
