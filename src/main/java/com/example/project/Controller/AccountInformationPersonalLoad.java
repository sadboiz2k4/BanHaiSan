package com.example.project.Controller;

import com.example.project.Dao.HandlerSupport;
import com.example.project.Dao.HandlerUserDB;
import com.example.project.Service.EmailService;
import com.example.project.Model.CustomerTK;
import com.example.project.Model.Time;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "AcountInformation", value = "/account-infor")
public class AccountInformationPersonalLoad extends HttpServlet {
    private AccountInformationPersonalLoad() {
    }

    public static com.example.project.Controller.AccountInformationPersonalLoad createAccountInformationPersonalLoad() {
        return new com.example.project.Controller.AccountInformationPersonalLoad();
    }

    public void init() {

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        int id = 0;
        if(session.getAttribute("userID") != null){
            id = (int) session.getAttribute("userID");
        }else{
            resp.sendRedirect("login.jsp");
        }
        HandlerUserDB handlerUserDB = new HandlerUserDB();
        CustomerTK customer = handlerUserDB.getInforUser(id);
//        System.out.println(customer);
        req.setAttribute("customer", customer);
        handlerUserDB.close();
        req.getRequestDispatcher("/JspComForPersonal/account-information.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int id = 0;
        if(session.getAttribute("userID") != null){
            id = (int) session.getAttribute("userID");
        }else{
            resp.sendRedirect("login.jsp");
        }
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
        String birthDay = req.getParameter("dob");
        String phone = req.getParameter("phone");
        HandlerUserDB handlerUserDB = new HandlerUserDB();
        handlerUserDB.updateInforUser(id,email,phone,firstName,lastName,gender,birthDay);

        req.setAttribute("emailI", email);
        req.setAttribute("phone", phone);
        req.setAttribute("firstName", firstName);
        req.setAttribute("lastName", lastName);
        req.setAttribute("gender",gender);
        req.setAttribute("dob", birthDay);

        req.getRequestDispatcher("personal.jsp").forward(req, resp);
    }

    public void destroy() {
    }
}