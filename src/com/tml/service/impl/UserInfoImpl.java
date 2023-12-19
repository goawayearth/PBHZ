package com.tml.service.impl;

import com.tml.bean.User;
import com.tml.dao.UserDAO;
import com.tml.service.UserInform;

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
}
