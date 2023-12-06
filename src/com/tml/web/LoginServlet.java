package com.tml.web;

import com.tml.service.impl.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="loginServlet")
public class LoginServlet extends HttpServlet {

    private String password = null;
    private String username = null;
    private LoginServiceImpl loginService = new LoginServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("username");
        password = request.getParameter("password");

        /* 从数据库中找出该id的对应的pwd，比较是否一样 */

        String result = loginService.userLogin(username,password);
        if(result.equals("success")){
            //得到session
            HttpSession session = request.getSession();
            //设置最长访问间隔时间是一天
            session.setMaxInactiveInterval(60*60*24);
            //将用户名存入session
            session.setAttribute("username",username);

            response.sendRedirect("home.html");
        }
        else{
            response.sendRedirect("login/login.html");
        }

    }
}
