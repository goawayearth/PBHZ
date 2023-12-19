//这是一个基于Java Servlet的后端代码，处理前端页面发起的不同请求，并返回相应的数据。

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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="homeLoadServlet")
public class HomeLoadServlet extends HttpServlet {
    private HomeServiceImpl homeService = new HomeServiceImpl();

    //创建一个 Gson 对象，用于将 Java 对象转换为 JSON 格式的字符串，以便在响应中返回 JSON 数据
    private Gson gson = new Gson();

    //@Override：这个注解表明该方法是对父类（HttpServlet）的方法进行重写。
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

            //通过反射机制获取与 "action" 参数对应的方法
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            //调用获取到的方法，即根据 "action" 参数调用不同的处理方法
            method.invoke(this,request,response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 接下来的一系列方法是处理不同请求的具体业务逻辑
     * 例如 updateHome 处理主页更新请求，updateHelp 处理帮助类别的更新请求，依此类推。
     * 这些方法调用相应的Service来获取数据，然后将数据转换为 JSON 格式并写入响应中。
     * @param request
     * @param response
     * @throws IOException
     */
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

    //从请求中获取名为 "key" 的参数，即搜索关键字。
    protected void searchKey(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
        String key = request.getParameter("key");
        //调用 HomeServiceImpl 的 searchQuestion 方法，根据搜索关键字获取问题列表
        List<Question> questions = homeService.searchQuestion(key);

        String json = gson.toJson(questions);
        response.getWriter().write(json);
    }

}
