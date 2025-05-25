package com.example.project.Controller;

import com.example.project.Dao.HandlerOrderDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "OrderPersonal", value = "/order-personal")
public class OrderPersonal extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandlerOrderDB handlerOrderDB = new HandlerOrderDB();
        HttpSession session = req.getSession();
        int userID = (int)session.getAttribute("userID");

        req.setAttribute("listOrder", handlerOrderDB.getAllOrder(userID));
        handlerOrderDB.close();
        req.getRequestDispatcher("/JspComForPersonal/order.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
    }
