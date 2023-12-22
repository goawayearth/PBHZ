package com.tml.service.impl;

import com.tml.bean.User;
import com.tml.dao.UserDAO;
import com.tml.service.LoginService;

public class LoginServiceImpl implements LoginService {
    @Override
    public String userLogin(String username, String password) {
        UserDAO userDAO = new UserDAO();
        User user = null;
        user = userDAO.check_id(username);
        if(user == null){
            return "用户不存在";
        }
        if(user.getState()==0){
            return "black";
        }
        if(!password.equals(user.getPassword())){
            return "账号或者密码不正确";
        }

        return "success";
    }


}

