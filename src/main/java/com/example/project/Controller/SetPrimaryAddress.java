package com.example.project.Controller;

import com.example.project.Dao.HandlerAddressDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "SetPrimaryAddress", value = "/set-primary-address")
public class SetPrimaryAddress extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int AUid = Integer.parseInt(req.getParameter("addressUserID"));
        HttpSession session = req.getSession();
        int id = (int) session.getAttribute("userID");

        HandlerAddressDB handlerAddressDB = new HandlerAddressDB();
        handlerAddressDB.setPrimaryAddress(id,AUid);
        handlerAddressDB.close();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
