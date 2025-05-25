package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoadFavoriteProductP", value = "/favorite-products")
public class LoadFavoriteProductP extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("userID") == null) {
            resp.sendRedirect("/login");
        }

        int id = (int) session.getAttribute("userID");

        DaoProduct daoProduct = new DaoProduct();
        req.setAttribute("listProductFavorite",daoProduct.getAllFavoriteProduct(id));
        daoProduct.close();
        req.getRequestDispatcher("/JspComForPersonal/favorite-products.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
