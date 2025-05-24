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

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        String pass = req.getParameter("password");

        HttpSession session = req.getSession();
        HandlerUserDB handlerUserDB = new HandlerUserDB();
        CustomerTK user = handlerUserDB.checkLogin(uname,pass);

        boolean isLogin = false;
        System.out.println(user);
        if(user != null){
            int userID = user.getUserId();
            isLogin = true;
            session.setAttribute("user", user);
            session.setAttribute("isLogin", isLogin);
            session.setAttribute("userID", userID);
            if(handlerUserDB.checkRoleAdmin(user.getUserId())){
                resp.sendRedirect("dashboard");
            }else{
                resp.sendRedirect("index.jsp");
            }
        }else{
            req.setAttribute("uname", uname);
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu sai");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
