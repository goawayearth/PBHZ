package com.tml.web;

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.Session;
import com.tml.service.impl.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="loginServlet")
public class LoginServlet extends HttpServlet {

    private String password = null;
    private String username = null;
    private String valiCode = null;
    private LoginServiceImpl loginService = new LoginServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        username = request.getParameter("username");
        password = request.getParameter("password");
        valiCode = request.getParameter("validate");
        String validate1 = (String)session.getAttribute("validateCode");
        System.out.println("vali"+validate1);


        /* 从数据库中找出该id的对应的pwd，比较是否一样 */

        String result = loginService.userLogin(username,password);
        //整体而言，这段代码处理了用户的 GET 请求，根据用户登录的成功与否生成不同的 JSON 响应。
        // 如果登录成功，返回包含重定向路径的 JSON；如果登录失败，返回包含 "fin" 属性的 JSON。
        // 这种方式通常用于在客户端进行页面重定向或显示错误消息。
        if(result.equals("success")&&validate1.equals(valiCode)){
            //得到session
//            session = request.getSession();
            //设置最长访问间隔时间是一天
            session.setMaxInactiveInterval(60*60*24);
            //将用户名存入session
            session.setAttribute("username",username);


            //这两行的目的是将存储有关登录结果的信息的 resultMap 转换为 JSON 格式的字符串。
            // 这样做的原因是，你希望将这个 JSON 字符串发送回客户端作为 HTTP 响应的一部分。
            // 这样的操作通常用于通过网络传输结构化数据，例如在前端和后端之间进行通信。
            String  path = "http://localhost:8080/Pbhz/home.html";
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("path",path);
            //这一行创建了一个 Gson 对象，该对象是 Gson 库中主要的操作入口。
            // 它负责将 Java 对象转换为 JSON 字符串，或将 JSON 字符串转换为 Java 对象。
            Gson gson = new Gson();
            //这一行使用先前创建的 Gson 对象将 resultMap（一个 Java 的 Map 对象）转换为 JSON 格式的字符串。
            // toJson 方法将 Java 对象转换为相应的 JSON 表示形式。
            String json = gson.toJson(resultMap);
            response.getWriter().write(json);
        }
        else{
            String fin = null;
            if(valiCode.equals(validate1)){
                 fin = "ok";
            }
            else{
                 fin = "ok1";
            }

            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("fin",fin);
            Gson gson = new Gson();
            String json = gson.toJson(resultMap);
            response.getWriter().write(json);
        }

    }
}
