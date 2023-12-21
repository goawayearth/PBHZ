package com.tml.web;

import cn.dsna.util.images.ValidateCode;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "/validateCode")
public class ValidateCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进来了session");
        // 1 创建验证码
        ValidateCode validateCode = new ValidateCode(100, 30, 4, 30);
        // 2 将验证码存储到Session
        HttpSession session = request.getSession();
        session.setAttribute("validateCode", validateCode.getCode());
        // 3 将验证码以图片的形式发给浏览器
        validateCode.write(response.getOutputStream());
    }
}
