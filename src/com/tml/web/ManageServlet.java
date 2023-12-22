package com.tml.web;

import com.google.gson.Gson;
import com.tml.bean.Comment;
import com.tml.bean.Question;
import com.tml.bean.User;
import com.tml.service.UserInform;
import com.tml.service.impl.CommentServiceImpl;
import com.tml.service.impl.QuestionServiceImpl;
import com.tml.service.impl.UserInfoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="manageServlet")
public class ManageServlet extends HttpServlet {
    Gson gson = new Gson();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        System.out.println("action="+action);

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

    protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        UserInform userInform = new UserInfoImpl();
        userInform.deleteUser(username);
        response.getWriter().write("success");
    }

    protected void deleteQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String qid = request.getParameter("qid");
        QuestionServiceImpl questionService = new QuestionServiceImpl();
        questionService.deleteQuestion(qid);
        response.getWriter().write("success");

    }

    protected void deleteComment(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String cid = request.getParameter("cid");
        CommentServiceImpl commentService = new CommentServiceImpl();
        commentService.deleteComment(cid);
        response.getWriter().write("success");
    }

    protected void loadNormalUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserInfoImpl userInfo = new UserInfoImpl();
        List<User> userList = userInfo.getNormalUser();
        String json = gson.toJson(userList);
        response.getWriter().write(json);

    }

    protected void loadBlackList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserInfoImpl userInfo = new UserInfoImpl();
        List<User> userList = userInfo.getBlackList();
        String json = gson.toJson(userList);
        response.getWriter().write(json);
    }

    protected void loadLearning(HttpServletRequest request, HttpServletResponse response) throws IOException {
        QuestionServiceImpl questionService = new QuestionServiceImpl();
        List<Question> questionList = questionService.getLearning();
        String json = gson.toJson(questionList);
        response.getWriter().write(json);
    }

    protected void loadPsychogical(HttpServletRequest request, HttpServletResponse response) throws IOException {
        QuestionServiceImpl questionService = new QuestionServiceImpl();
        List<Question> questionList = questionService.getPsychogical();
        String json = gson.toJson(questionList);
        response.getWriter().write(json);
    }

    protected void loadHealth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        QuestionServiceImpl questionService = new QuestionServiceImpl();
        List<Question> questionList = questionService.getHealth();
        String json = gson.toJson(questionList);
        response.getWriter().write(json);
    }

    protected void loadLaw(HttpServletRequest request, HttpServletResponse response) throws IOException {
        QuestionServiceImpl questionService = new QuestionServiceImpl();
        List<Question> questionList = questionService.getLaw();
        String json = gson.toJson(questionList);
        response.getWriter().write(json);
    }

    protected void loadJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
        QuestionServiceImpl questionService = new QuestionServiceImpl();
        List<Question> questionList = questionService.getJob();
        String json = gson.toJson(questionList);
        response.getWriter().write(json);
    }

    protected void loadOther(HttpServletRequest request, HttpServletResponse response) throws IOException {
        QuestionServiceImpl questionService = new QuestionServiceImpl();
        List<Question> questionList = questionService.getOther();
        String json = gson.toJson(questionList);
        response.getWriter().write(json);
    }
    protected void loadComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CommentServiceImpl commentService = new CommentServiceImpl();
        List<Comment> commentList = commentService.getComment();
        String json = gson.toJson(commentList);
        response.getWriter().write(json);
    }

    protected void addBlack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        UserInform userInform = new UserInfoImpl();
        userInform.addBlack(username);
        response.getWriter().write("success");
    }

    protected void removeBlack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        UserInform userInform = new UserInfoImpl();
        userInform.removeBlack(username);
        response.getWriter().write("success");
    }

    protected void searchNormalUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String key = request.getParameter("key");
        UserInfoImpl userInfo = new UserInfoImpl();
        List<User> userList = userInfo.searchNormalUser(key);
        String json = gson.toJson(userList);
        response.getWriter().write(json);


    }
    protected void searchBlackList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserInfoImpl userInfo = new UserInfoImpl();
        String key = request.getParameter("key");
        List<User> userList = userInfo.searchBlackList(key);
        String json = gson.toJson(userList);
        response.getWriter().write(json);

    }
    protected void searchLearning(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String key = request.getParameter("key");

        QuestionServiceImpl questionService = new QuestionServiceImpl();
        List<Question> questionList = questionService.searchLearning(key);
        String json = gson.toJson(questionList);
        response.getWriter().write(json);

    }
    protected void searchPsychogical(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String key = request.getParameter("key");

        QuestionServiceImpl questionService = new QuestionServiceImpl();
        List<Question> questionList = questionService.searchPsychogical(key);
        String json = gson.toJson(questionList);
        response.getWriter().write(json);
    }
    protected void searchHealth(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String key = request.getParameter("key");

        QuestionServiceImpl questionService = new QuestionServiceImpl();
        List<Question> questionList = questionService.searchHealth(key);
        String json = gson.toJson(questionList);
        response.getWriter().write(json);
    }
    protected void searchLaw(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String key = request.getParameter("key");

        QuestionServiceImpl questionService = new QuestionServiceImpl();
        List<Question> questionList = questionService.searchLaw(key);
        String json = gson.toJson(questionList);
        response.getWriter().write(json);
    }
    protected void searchJob(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String key = request.getParameter("key");

        QuestionServiceImpl questionService = new QuestionServiceImpl();
        List<Question> questionList = questionService.searchJob(key);
        String json = gson.toJson(questionList);
        response.getWriter().write(json);
    }
    protected void searchOther(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String key = request.getParameter("key");

        QuestionServiceImpl questionService = new QuestionServiceImpl();
        List<Question> questionList = questionService.searchOther(key);
        String json = gson.toJson(questionList);
        response.getWriter().write(json);
    }
    protected void searchComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String key = request.getParameter("key");
        CommentServiceImpl commentService = new CommentServiceImpl();
        List<Comment> commentList = commentService.searchComment(key);
        String json = gson.toJson(commentList);
        response.getWriter().write(json);
    }



}
