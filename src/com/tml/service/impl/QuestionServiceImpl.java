package com.tml.service.impl;

import com.tml.bean.Question;
import com.tml.dao.QuestionDAO;
import com.tml.service.QuestionService;

import java.sql.SQLException;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public void saveQuestion(Question question) {
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.save(question);
    }

    @Override
    public void deleteQuestion(String qid) {

        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.deleteQuestion(qid);
    }

    @Override
    public List<Question> getLearning() {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.check_type_all("learning");
    }

    @Override
    public List<Question> getPsychogical() {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.check_type_all("psychogical");
    }

    @Override
    public List<Question> getHealth() {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.check_type_all("health");
    }

    @Override
    public List<Question> getLaw() {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.check_type_all("law");
    }

    @Override
    public List<Question> getJob() {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.check_type_all("job");
    }

    @Override
    public List<Question> getOther() {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.check_type_all("other");
    }


    @Override
    public List<Question> searchLearning(String key) throws SQLException {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.searchLearning(key);
    }

    @Override
    public List<Question> searchPsychogical(String key) throws SQLException {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.searchPsychogical(key);
    }

    @Override
    public List<Question> searchHealth(String key) throws SQLException {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.searchHealth(key);
    }

    @Override
    public List<Question> searchLaw(String key) throws SQLException {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.searchLaw(key);
    }

    @Override
    public List<Question> searchJob(String key) throws SQLException {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.searchJob(key);
    }

    @Override
    public List<Question> searchOther(String key) throws SQLException {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.searchOther(key);
    }


}
