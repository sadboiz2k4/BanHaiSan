package com.example.project.Controller;

import com.example.project.Dao.HandlerUserDB;
import com.example.project.Model.CustomerTK;
import com.example.project.Model.Voucher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PointAndPromotionsLoad", value = "/point-and-promotions")
public class PointAndPromotionsLoad extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int id = (int) session.getAttribute("userID");
        if (id == 0) {
            resp.sendRedirect("/login");
        }
        HandlerUserDB handlerUserDB = new HandlerUserDB();
        List<Voucher> listVoucher = handlerUserDB.getAllVoucher(id);
        if(!listVoucher.isEmpty()){
            req.setAttribute("listVoucher", listVoucher);
        }

        req.setAttribute("point", handlerUserDB.getPointUser(id));
        int rank =  handlerUserDB.getRankUser(id);
        String rankName = "";
        if (rank == 1) {
            rankName = "Bronze";
        } else if (rank == 2) {
            rankName = "Silver";
        } else if (rank == 3) {
            rankName = "Gold";
        } else if (rank == 4) {
            rankName = "Platinum";
        }
        req.setAttribute("rank",rankName);
        req.setAttribute("quantityOrder", handlerUserDB.countOrder(id));
        CustomerTK customer = handlerUserDB.getInforUser(id);
        req.setAttribute("customer", customer);
        handlerUserDB.close();
        req.getRequestDispatcher("/JspComForPersonal/points-and-promotions.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void destroy() {

    }
}
