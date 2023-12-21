package com.tml.service.impl;

import com.tml.bean.Question;
import com.tml.dao.QuestionDAO;
import com.tml.service.QuestionService;

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
}
