package com.tml.web;

import com.google.gson.Gson;
import com.tml.bean.Question;
import com.tml.dao.QuestionDAO;
import com.tml.dao.demo;
import com.tml.service.impl.HomeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name="homeLoadServlet")
public class HomeLoadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    /**
     * 调用不同的ajax所使用不同的函数
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");

        try {
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,request,response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void updateHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HomeServiceImpl homeService = new HomeServiceImpl();
        List<Question> questions = homeService.mainContent();
        Gson gson = new Gson();
        String json = gson.toJson(questions);
        response.getWriter().write(json);
    }



}
