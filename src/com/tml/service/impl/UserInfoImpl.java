package com.tml.service.impl;

import com.tml.bean.User;
import com.tml.dao.UserDAO;
import com.tml.service.UserInform;

public class UserInfoImpl implements UserInform {

    @Override
    public User getUser(String id) {
        UserDAO userDAO = new UserDAO();
        return userDAO.check_id(id);
    }
}
