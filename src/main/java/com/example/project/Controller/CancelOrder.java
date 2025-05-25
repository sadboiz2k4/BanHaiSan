package com.example.project.Controller;

import com.example.project.Dao.HandlerOrderDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CancelOrder", value = "/cancel-order")
public class CancelOrder extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        HandlerOrderDB daoOrder = new HandlerOrderDB();
        daoOrder.cancelOrder(id);
        daoOrder.close();
        resp.sendRedirect("/order");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
