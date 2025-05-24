package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.ProductFilter;
import com.example.project.Model.ProductLarge;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "searchFilterBestSaller", value = "/search-filter-best-saller")
public class SearchFilterBestSaller extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String valueSearch = req.getParameter("valueSearch");
        int currentPage = Integer.parseInt(req.getParameter("currentPage") != null ? req.getParameter("currentPage"):"1");
        DaoProduct daoProduct = new DaoProduct();
        int totalProductPerPage = 20;
        List<ProductLarge> listProduct = (List<ProductLarge>) session.getAttribute("listProduct");
        List<ProductFilter> LPF = new ArrayList<>();
        for(ProductLarge product : listProduct){
            LPF.add(ProductFilter.convertProductLargeToPL(product,daoProduct.getQuantitySale(product.getId())));
        }
        LPF.sort(Comparator.comparing(ProductFilter::getQuantity).reversed());
        listProduct = new ArrayList<>();
        for(ProductFilter productFilter : LPF){
            listProduct.add(ProductFilter.convertProductFilterToPL(productFilter));
        }
        int totalProduct  = listProduct.size();
        List<ProductLarge> listProductForPage = listProduct.subList((currentPage-1)*totalProductPerPage,Math.min(currentPage*totalProductPerPage,totalProduct));
        System.out.println(totalProduct);
        System.out.println(listProductForPage.size());
        for (ProductLarge product : listProductForPage) {
            System.out.println("l"+product);
        }
        int totalPage = totalProduct % totalProductPerPage ==0 ? (int)totalProduct/totalProductPerPage:(int)totalProduct/totalProductPerPage+1;
        req.setAttribute("ListProductSearch", listProductForPage);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("totalPage", totalPage);

        daoProduct.close();

        try {
            req.getRequestDispatcher("/JspComForHome/search-product.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
