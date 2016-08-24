/**
 * Project: o2osearchservice
 * <p/>
 * File Created at 2015-4-11
 * $Id$
 */
package com.sk.controller;

import com.sk.model.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * TODO Comment of BookController
 *
 * @author shisong
 */
@Controller
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    private CloseableHttpClient httpClient;
    private static String userIDKey = "userID";

    @PostConstruct
    public void init() {
        httpClient = HttpClients.createDefault();
    }

    @RequestMapping(value = "/loginLeanCloud")
    @ResponseBody
    public String handleMainRequest(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password,
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpGet httpget = new HttpGet("https://leancloud.cn:443/1.1/login?username=" + email + "&password=" + password);
        CloseableHttpResponse remoteResp;
        try {
            httpget.setHeader("'X-LC-Id'", "lehRrcPFUSbhSBVqDxNSocVV-gzGzoHsz");
            httpget.setHeader("X-LC-Key", "tuEz3gPGmS54YsTWbRoqEg5c");
            remoteResp = httpClient.execute(httpget);
        } catch (IOException e) {
            response.setStatus(400);
            return "Bad Request";
        }
        HttpEntity entity = remoteResp.getEntity();
        String ret;
        try {
            ret = EntityUtils.toString(entity);
        } catch (IOException e) {
            response.setStatus(400);
            return "Bad Request";
        }
        return ret;
    }


    @RequestMapping(value = "/index")
    public String handleIndexRequest(HttpServletRequest request, @ModelAttribute("user") User user) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        user.setEmail(userId);
        return "index_tpl";
    }

    @RequestMapping(value = "/login.do")
    public void handleLoginAction(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getEmail());
        response.sendRedirect("/index");
    }

    @RequestMapping(value = "/login")
    public String handleLoginRequest(@ModelAttribute("user") User user) {
        return "login_tpl";
    }

    @RequestMapping(value = "/logout")
    public void handleLogOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("userId");
        response.sendRedirect("/login");
    }

    @RequestMapping(value = "/profile")
    public String handleTest(@ModelAttribute("user") User user) throws IOException {
        user.setEmail("11");
        return "profile_tpl";
    }


}
