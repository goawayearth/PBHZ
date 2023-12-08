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

    @Override
    public List<Question> mainContent() {

        List<Question> home = new ArrayList<>();
        home.addAll(questionDAO.check_type("learning"));
        home.addAll(questionDAO.check_type("psychogical"));
        home.addAll(questionDAO.check_type("health"));
        home.addAll(questionDAO.check_type("law"));
        home.addAll(questionDAO.check_type("job"));
        return home;
    }

    @Override
    public List<Question> helpContent() {
        return questionDAO.check_type("help");
    }

    @Override
    public List<Question> learnContent() {
        return questionDAO.check_type("learning");
    }

    @Override
    public List<Question> psychogicalContent() {
        return questionDAO.check_type("psychogical");
    }

    @Override
    public List<Question> healthContent() {
        return questionDAO.check_type("health");
    }

    @Override
    public List<Question> lawContent() {
        return questionDAO.check_type("law");
    }

    @Override
    public List<Question> jobContent() {
        return questionDAO.check_type("job");
    }

    @Override
    public List<Question> otherContent() {
        return questionDAO.check_type("other");
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
