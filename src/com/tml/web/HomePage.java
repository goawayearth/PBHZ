package com.tml.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="homePage")
public class HomePage extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的MIME类型和编码
        response.setContentType("text/html;charset=utf-8");
        //得到session，获取与当前请求关联的HttpSession对象，用于在会话中存储和检索信息
        HttpSession session = request.getSession();
        //取出用户名
        Object username = session.getAttribute("username");
        PrintWriter writer = response.getWriter();
        //判断用户名是否存在
        if (username != null){
            //在一天内登录过，无需再次登录
            if(username.equals("root")){
                response.sendRedirect("manager.html");
            }
            else
                response.sendRedirect("home.html?username="+username);
        }else {
            //没有登录，或者登录间隔大于1天。重定向到登陆界面
            //response.sendRedirect(request.getContextPath()+"/login.html");
            response.sendRedirect("login/login.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
