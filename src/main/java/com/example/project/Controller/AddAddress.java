package com.example.project.Controller;

import com.example.project.Dao.AddressSuportShipDB;
import com.example.project.Dao.HandlerAddressDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AddAddress", value = "/add-address")
public class AddAddress extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("userID") == null){
            System.out.println("userID = 0");
            resp.sendRedirect("login");
            return;
        }
        String referer = req.getHeader("Referer");
        if(referer == null){
            resp.sendRedirect("add-address.jsp");
            return;
        }
        session.setAttribute("referer", referer);
        resp.sendRedirect("add-address.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }}
