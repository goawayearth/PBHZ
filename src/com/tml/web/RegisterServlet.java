package com.tml.web;

import com.tml.service.impl.RegisterServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "registerServlet")
public class RegisterServlet extends HttpServlet {
    private RegisterServiceImpl registerService = new RegisterServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String result = registerService.createUser(username,password);
        if(!result.equals("success")){
            response.sendRedirect("login/register.html");
        }
        response.sendRedirect("home.html");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
