package com.example.project.Controller;

import com.example.project.Service.EmailService;
import com.example.project.Dao.HandlerSupport;
import com.example.project.Dao.HandlerUserDB;
import com.example.project.Model.Time;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "ForgetPassword", value = "/forget-password")
public class ForgetPassword extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        HandlerUserDB handlerUserDB = new HandlerUserDB();
        HttpSession session = req.getSession();
        // Kiểm tra email có tồn tại trong DB không
        boolean isExist = handlerUserDB.checkEmailExist(email);
        // OTP
        if(isExist){
            String otp = HandlerSupport.generateRandomCode(6);
            try {
                EmailService.sendEmail(email, "Your OTP Code", "Your OTP is: " + otp);
                session.setAttribute("email", email);
                session.setAttribute("otp", otp);
                session.setAttribute("OTPForgetPassCreateAt", Time.convertLocalDatetimeToTime(LocalDateTime.now()));
                req.setAttribute("typeCheckOTP", "forget");
                req.getRequestDispatcher("check-otp.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Nếu không thì thông báo email không tồn tại
        else{
            req.setAttribute("error", "Email không tồn tại");
            req.getRequestDispatcher("forget-password.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
