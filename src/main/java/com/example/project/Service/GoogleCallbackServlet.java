//package com.example.project.Service;
//
//import com.example.project.Dao.UserDAO;
//import com.example.project.Model.User;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.apache.http.message.BasicNameValuePair;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//
//@WebServlet(name = "GoogleCallbackServlet", value = "/GoogleCallbackServlet")
//public class GoogleCallbackServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String code = req.getParameter("code");
//
//        // 1. Exchange code for access_token
//        String tokenResponse = getAccessToken(code);
//        JsonNode tokenJson = new ObjectMapper().readTree(tokenResponse);
//        String accessToken = tokenJson.get("access_token").asText();
//
//        // 2. Get user profile
//        String profileJson = getUserProfile(accessToken);
//        JsonNode profile = new ObjectMapper().readTree(profileJson);
//
//        String providerId = profile.get("id").asText();
//        UserDAO dao = new UserDAO();
//        User user = dao.findByProviderId("google", providerId);
//        if (user == null) {
//            user = new User();
//            user.setProvider("google");
//            user.setProviderId(providerId);
//            user.setUsername(profile.get("name").asText()); // Changed from userName to name
//            user.setEmail(profile.get("email").asText());
//            user.setAccessToken(accessToken);
//            dao.save(user);
//        }
//
//        req.getSession().setAttribute("user", user);
//        resp.sendRedirect(req.getContextPath() + "/profile");
//    }
//
//    private String getAccessToken(String code) throws IOException {
//        try (CloseableHttpClient client = HttpClients.createDefault()) {
//            HttpPost post = new HttpPost("https://oauth2.googleapis.com/token");
//
//            List<NameValuePair> params = new ArrayList<>();
//            params.add(new BasicNameValuePair("code", code));
//            params.add(new BasicNameValuePair("client_id", OAuthConstants.GOOGLE_CLIENT_ID));
//            params.add(new BasicNameValuePair("client_secret", OAuthConstants.GOOGLE_CLIENT_SECRET));
//            params.add(new BasicNameValuePair("redirect_uri", OAuthConstants.GOOGLE_REDIRECT_URI));
//            params.add(new BasicNameValuePair("grant_type", "authorization_code"));
//
//            post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
//
//            HttpResponse response = client.execute(post);
//            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
//        }
//    }
//
//    private String getUserProfile(String accessToken) throws IOException {
//        try (CloseableHttpClient client = HttpClients.createDefault()) {
//            HttpGet get = new HttpGet("https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + accessToken);
//            HttpResponse response = client.execute(get);
//            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Not used
//    }
//}
