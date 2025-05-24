package com.example.project.Controller;

import com.example.project.Dao.HandlerUserDB;
import com.example.project.Model.CustomerTK;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoadPersonal", value = "/LoadPersonal")
public class LoadPersonal extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(session.getAttribute("userID")==null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        int id = (int) session.getAttribute("userID");
            HandlerUserDB handlerUserDB = new HandlerUserDB();
            CustomerTK customer = handlerUserDB.getInforUser(id);
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("personal.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
