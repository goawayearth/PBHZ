package com.tml.service.impl;

import com.tml.bean.Question;
import com.tml.bean.User;
import com.tml.dao.QuestionDAO;
import com.tml.dao.UserDAO;
import com.tml.service.UserInform;

import java.util.List;

public class UserInfoImpl implements UserInform {

    @Override
    public void changeIcon(String username, String path) {
        UserDAO userDAO = new UserDAO();
        userDAO.updateUserIcon(username,path);
    }

    @Override
    public User getUser(String username) {
        UserDAO userDAO = new UserDAO();
        return userDAO.check_id(username);
    }

    @Override
    public List<Question> getQuestion(String username) {
        QuestionDAO questionDAO = new QuestionDAO();
        List<Question> questions = null;
        try{
            questions = questionDAO.check_uid(username);

        }catch (Exception e){}
        return questions;
    }


    @Override
    public void deleteUser(String username) {
        UserDAO userDAO = new UserDAO();
        userDAO.deleteUser(username);
    }
}
