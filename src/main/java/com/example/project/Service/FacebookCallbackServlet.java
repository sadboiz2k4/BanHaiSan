//package com.example.project.Service;
//
//import com.example.project.Dao.UserDAO;
//import com.example.project.Model.User;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.net.httpserver.Request;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//
//@WebServlet(name = "FacebookCallbackServlet", value = "/FacebookCallbackServlet")
//public class FacebookCallbackServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String code = req.getParameter("code");
//        // 1. Exchange code for access_token
//        String tokenUrl = "https://graph.facebook.com/v17.0/oauth/access_token"
//                + "?client_id=" + OAuthConstants.FB_CLIENT_ID
//                + "&redirect_uri=" + URLEncoder.encode(OAuthConstants.FB_REDIRECT_URI, "UTF-8")
//                + "&client_secret=" + OAuthConstants.FB_CLIENT_SECRET
//                + "&code=" + code;
//        String tokenResponse = Request.get(tokenUrl)
//                .execute().returnContent().asString(StandardCharsets.UTF_8);
//        JsonNode tokenJson = new ObjectMapper().readTree(tokenResponse);
//        String accessToken = tokenJson.get("access_token").asText();
//
//        // 2. Lấy profile
//        String profileUrl = "https://graph.facebook.com/me"
//                + "?fields=id,name,email"
//                + "&access_token=" + accessToken;
//        String profileJson = Request.get(profileUrl)
//                .execute().returnContent().asString(StandardCharsets.UTF_8);
//        JsonNode profile = new ObjectMapper().readTree(profileJson);
//
//        String providerId = profile.get("id").asText();
//        UserDAO dao = new UserDAO();
//        User user = dao.findByProviderId("facebook", providerId);
//        if (user == null) {
//            user = new User();
//            user.setProvider("facebook");
//            user.setProviderId(providerId);
//            user.setUsername(profile.get("userName").asText());
//            user.setEmail(profile.has("email") ? profile.get("email").asText() : "");
//            user.setAccessToken(accessToken);
//            dao.save(user);
//        }
//        req.getSession().setAttribute("user", user);
//        resp.sendRedirect(req.getContextPath() + "/profile");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    }
//}