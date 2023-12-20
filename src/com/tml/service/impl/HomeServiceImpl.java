package com.tml.service.impl;

import com.tml.bean.Comment;
import com.tml.bean.Question;
import com.tml.dao.CommentDAO;
import com.tml.dao.QuestionDAO;
import com.tml.service.HomeService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeServiceImpl implements HomeService {

    private QuestionDAO questionDAO = new QuestionDAO();
    private CommentDAO commentDAO = new CommentDAO();

    /**
     * addAll方法是Collection接口的一部分，而 List接口继承自 Collection接口，
     * addAll 方法，用于将另一个集合中的所有元素添加到当前列表中。
     * @return
     */
    @Override
    public List<Question> mainContent() {
        List<Question> home = new ArrayList<>();
        //check_type函数返回一个列表
        home.addAll(questionDAO.check_type("learning"));
        home.addAll(questionDAO.check_type("psychogical"));
        home.addAll(questionDAO.check_type("health"));
        home.addAll(questionDAO.check_type("law"));
        home.addAll(questionDAO.check_type("job"));
        return home;
    }

    @Override
    public List<Question> helpContent() {
        return questionDAO.check_type_all("help");
    }

    @Override
    public List<Question> learnContent() {
        return questionDAO.check_type_all("learning");
    }

    @Override
    public List<Question> psychogicalContent() {
        return questionDAO.check_type_all("psychogical");
    }

    @Override
    public List<Question> healthContent() {
        return questionDAO.check_type_all("health");
    }

    @Override
    public List<Question> lawContent() {
        return questionDAO.check_type_all("law");
    }

    @Override
    public List<Question> jobContent() {
        return questionDAO.check_type_all("job");
    }

    @Override
    public List<Question> otherContent() {
        return questionDAO.check_type_all("other");
    }

    @Override
    public Question getQuestionByQid(String qid) throws SQLException {
        return questionDAO.check_id(qid);
    }

    @Override
    public List<Comment> getCommentsByQid(String qid) throws SQLException {
        return commentDAO.check_qid(qid);

    }

    @Override
    public List<Question> searchQuestion(String key) throws SQLException {
        return questionDAO.check_key(key);
    }


}
