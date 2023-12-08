package com.tml.web;

import com.google.gson.Gson;
import com.tml.bean.Question;
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
    private HomeServiceImpl homeService = new HomeServiceImpl();
    private Gson gson = new Gson();
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

        List<Question> questions = homeService.mainContent();

        String json = gson.toJson(questions);
        response.getWriter().write(json);
    }

    protected void updateHelp(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Question> help = homeService.helpContent();
        String json = gson.toJson(help);
        response.getWriter().write(json);

    }

    protected void updateLearn(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Question> learn = homeService.learnContent();
        String json = gson.toJson(learn);
        response.getWriter().write(json);
    }

    protected void updatePsychogical(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Question> psychogical = homeService.psychogicalContent();
        String json = gson.toJson(psychogical);
        response.getWriter().write(json);
    }

    protected void updateHealth(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Question> health = homeService.healthContent();
        String json = gson.toJson(health);
        response.getWriter().write(json);
    }

    protected void updateLaw(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Question> law = homeService.lawContent();
        String json = gson.toJson(law);
        response.getWriter().write(json);
    }

    protected void updateJob(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Question> job = homeService.jobContent();
        String json = gson.toJson(job);
        response.getWriter().write(json);
    }

    protected void updateOther(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Question> other = homeService.otherContent();
        String json = gson.toJson(other);
        response.getWriter().write(json);
    }

}
