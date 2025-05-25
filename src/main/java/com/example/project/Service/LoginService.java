package com.example.project.Service;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginService", value = "/LoginService")
public class LoginService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String googleAuthURL = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?client_id=" + OAuthConstants.GOOGLE_CLIENT_ID
                + "&redirect_uri=" + URLEncoder.encode(OAuthConstants.GOOGLE_REDIRECT_URI, "UTF-8")
                + "&response_type=code&scope=openid%20email%20profile";
        String fbAuthURL = "https://www.facebook.com/v17.0/dialog/oauth"
                + "?client_id=" + OAuthConstants.FB_CLIENT_ID
                + "&redirect_uri=" + URLEncoder.encode(OAuthConstants.FB_REDIRECT_URI, "UTF-8")
                + "&response_type=code&scope=email,public_profile";

        req.setAttribute("googleAuthURL", googleAuthURL);
        req.setAttribute("fbAuthURL", fbAuthURL);
        req.getRequestDispatcher("/WEB‑INF/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}