package com.tml.service.impl;

import com.tml.bean.Question;
import com.tml.dao.QuestionDAO;
import com.tml.service.HomeService;

import java.util.ArrayList;
import java.util.List;

public class HomeServiceImpl implements HomeService {
    @Override
    public List<Question> mainContent() {
        QuestionDAO questionDAO = new QuestionDAO();
        List<Question> home = new ArrayList<>();
        home.addAll(questionDAO.check_type("learning"));
        home.addAll(questionDAO.check_type("psychogical"));
        home.addAll(questionDAO.check_type("health"));
        home.addAll(questionDAO.check_type("law"));
        home.addAll(questionDAO.check_type("job"));
        return home;
    }
}
