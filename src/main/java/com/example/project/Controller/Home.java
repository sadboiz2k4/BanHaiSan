package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.Category;
import com.example.project.Model.CustomerTK;
import com.example.project.Model.ProductTK;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "Home", value = "/home")
public class Home extends HttpServlet {

    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DaoProduct daoProduct = new DaoProduct();
        List<String[]> ListBanner = daoProduct.loadBannerForHome();
        boolean isLogin=false;
        if(session != null && session.getAttribute("isLogin") != null){
            isLogin= (boolean) session.getAttribute("isLogin");
            if (isLogin) {
                req.setAttribute("isLogin", isLogin);
                CustomerTK user = (CustomerTK) session.getAttribute("user");
                req.setAttribute("user", user);
            }
        }
        if(!daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Main banner-1").isEmpty()){
            req.setAttribute("BannerMain1", daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Main banner-1"));
        }

        if(!daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Main banner-2").isEmpty()){
            req.setAttribute("BannerMain2", daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Main banner-2"));
        }

        if(!daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Main banner-3").isEmpty()){
            req.setAttribute("BannerMain3", daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Main banner-3"));
        }

        if(!daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Main banner-4").isEmpty()){
            req.setAttribute("BannerMain4", daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Main banner-4"));
        }

        if(!daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Main banner-5").isEmpty()){
            req.setAttribute("BannerMain5", daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Main banner-5"));
        }
        if(!daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Banner small").isEmpty()){
            req.setAttribute("BannerSmall", daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Banner small"));
        }

        if(!daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Banner mid").isEmpty()){
            req.setAttribute("BannerMid", daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Banner mid"));
        }


        req.setAttribute("listProductBS", daoProduct.loadProductBestSale(10));

        req.setAttribute("listProductOS", daoProduct.loadProductOnSale(10));

        List<ProductTK> listProductFronzen = daoProduct.loadProductCategories(3);
        req.setAttribute("listProductFronzen", listProductFronzen.subList(0, Math.min(10, listProductFronzen.size())));
        Set<ProductTK> setProductForYou = new HashSet<>();

        if (session != null && session.getAttribute("userID") != null) {
//            int userID = (int)session.getAttribute("userID");
//            setProductForYou.addAll(daoProduct.loadProductYourOrder(userID, 1));
//            List<ProductTK> listProductView = (List<ProductTK>) session.getAttribute("yourViews");
//            if (listProductView != null) {
//                setProductForYou.addAll(listProductView);
//            }
            setProductForYou.addAll(daoProduct.loadProductBestSale(5));
            setProductForYou.addAll(daoProduct.loadProductOnSale(5));
        } else {
//            if (session != null) {
//                List<ProductTK> listProductView = (List<ProductTK>) session.getAttribute("yourViews");
//                if (listProductView != null) {
//                    setProductForYou.addAll(listProductView);
//                }
//            }
            setProductForYou.addAll(daoProduct.loadProductBestSale(5));
            setProductForYou.addAll(daoProduct.loadProductOnSale(5));
        }

        List<ProductTK> listProductForYou = new ArrayList<>(setProductForYou);
        req.setAttribute("listProductForYou", listProductForYou);


        List<ProductTK> listProductCrabShrimp = new ArrayList<>();
        listProductCrabShrimp.addAll(daoProduct.loadProductCategories(8));
        listProductCrabShrimp.addAll(daoProduct.loadProductCategories(10));
        req.setAttribute("listProductCrabShrimp", listProductCrabShrimp.subList(0, Math.min(10, listProductCrabShrimp.size())));

        List<ProductTK> listProductClamsMusselsSnails = daoProduct.loadProductCategories(8);
        req.setAttribute("listProductClamsMusselsSnails", listProductClamsMusselsSnails.subList(0, Math.min(10, listProductClamsMusselsSnails.size())));

        List<ProductTK> listProductSpice = daoProduct.loadProductCategories(12);
        req.setAttribute("listProductSpice", listProductSpice.subList(0, Math.min(10, listProductSpice.size())));

//        daoProduct.close();
        List<Category> listC = daoProduct.getAllCategory();
        req.setAttribute("listCategory",listC);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);



    }

    public void destroy() {
    }
}
