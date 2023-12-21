package com.tml.web;

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

@WebServlet(name="manageServlet")
public class ManageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

}
