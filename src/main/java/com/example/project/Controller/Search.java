package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.ProductLarge;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "search", value = "/search")
public class Search extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String valueSearch = request.getParameter("valueSearch");
        int currentPage = Integer.parseInt(request.getParameter("currentPage") != null ? request.getParameter("currentPage"):"1");
        DaoProduct daoProduct = new DaoProduct();
        int totalProductPerPage = 20;
        List<ProductLarge> listProduct = (List<ProductLarge>) session.getAttribute("listProduct");

        int totalProduct  = listProduct.size();
        List<ProductLarge> listProductForPage = listProduct.subList((currentPage-1)*totalProductPerPage,Math.min(currentPage*totalProductPerPage,totalProduct));
        System.out.println(totalProduct);
        System.out.println(listProductForPage.size());
        for (ProductLarge product : listProductForPage) {
            System.out.println("l"+product);
        }
        int totalPage = totalProduct % totalProductPerPage ==0 ? (int)totalProduct/totalProductPerPage:(int)totalProduct/totalProductPerPage+1;
        request.setAttribute("ListProductSearch", listProductForPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);

        daoProduct.close();

        try {
            request.getRequestDispatcher("/JspComForHome/search-product.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoProduct daoProduct = new DaoProduct();
        HttpSession session = req.getSession(false);
        List<ProductLarge> listProductInit = (List<ProductLarge>) session.getAttribute("listProductInit");
        session.setAttribute("listProductInit",listProductInit);
        List<ProductLarge> listProduct = listProductInit;
        int minPrice = Integer.parseInt(req.getParameter("minPrice"));
        int maxPrice = Integer.parseInt(req.getParameter("maxPrice"));
        boolean isPromotion = Boolean.parseBoolean(req.getParameter("isPromotion"));
        boolean isNewProduct = Boolean.parseBoolean(req.getParameter("isNewProduct"));
        List<String> listChoseCategories = (List<String>) session.getAttribute("listChoseCategories");
        String newChoseCategories = req.getParameter("newChoseCategories");
        boolean plusCategory = Boolean.parseBoolean(req.getParameter("plusCategory"));
        if(listChoseCategories == null){
            listChoseCategories = new ArrayList<>();
        }
        if(plusCategory && newChoseCategories !=""){
            listChoseCategories.add(newChoseCategories);
        }else{
            listChoseCategories.remove(newChoseCategories);
        }
        if(minPrice >0 && maxPrice >0){
            if(minPrice < maxPrice){
                listProduct = daoProduct.filterProductByPrice(listProductInit,minPrice,maxPrice);
            }
        }
        if(isPromotion ){
            listProduct = daoProduct.filterProductByPromotion(listProductInit);
        }
        if(isNewProduct){
            listProduct = daoProduct.filterProductByNewProduct(listProductInit);
        }
        if(!listChoseCategories.isEmpty()){
            listProduct = daoProduct.filterProductByCategories(listProductInit,listChoseCategories);
        }
        System.out.println(listProduct.size());
        session.setAttribute("listProduct",listProduct);
        req.setAttribute("ListProductSearch", listProduct.size() >= 20 ? listProduct.subList(0,20):listProduct);
        req.setAttribute("currentPage", 1);
        if(listProduct.size()<=20){
            req.setAttribute("totalPage", 1);
        }else{
            req.setAttribute("totalPage", listProduct.size() % 20 ==0 ? (int)listProduct.size()/20:(int)listProduct.size()/20+1);
        }
        System.out.println(listProduct.size());
        daoProduct.close();
        req.getRequestDispatcher("/JspComForHome/search-product.jsp").forward(req,resp);
    }
    public void destroy() {
    }

}