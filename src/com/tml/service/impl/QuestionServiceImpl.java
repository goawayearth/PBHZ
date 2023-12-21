package com.tml.service.impl;

import com.tml.bean.Question;
import com.tml.dao.QuestionDAO;
import com.tml.service.QuestionService;

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
        return questionDAO.check_type("learning");
    }

    @Override
    public List<Question> getPsychogical() {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.check_type("psychogical");
    }

    @Override
    public List<Question> getHealth() {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.check_type("health");
    }

    @Override
    public List<Question> getLaw() {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.check_type("law");
    }

    @Override
    public List<Question> getJob() {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.check_type("job");
    }

    @Override
    public List<Question> getOther() {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.check_type("other");
    }
}
