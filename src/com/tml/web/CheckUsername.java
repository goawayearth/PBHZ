package com.tml.web;

import com.google.gson.Gson;
import com.tml.service.impl.RegisterServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "checkUsername")
public class CheckUsername extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决post请求中文乱码问题
        // 一定要在获取请求参数之前调用才有效
        request.setCharacterEncoding("UTF-8");
        // 解决响应的中文乱码
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        try{
            //这就是action等于什么，就会调用相同名字的函数
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,request,response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的参数username
        String username = request.getParameter("username");
        //查看username对应的有没有
        RegisterServiceImpl registerService = new RegisterServiceImpl();
        // 调用userService.existsUsername();
        boolean existsUsername = registerService.userExist(username);
        // 把返回的结果封装成为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        //true 是存在， false是可用
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        response.getWriter().write(json);
    }
}
