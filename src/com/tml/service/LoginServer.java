package com.tml.service;

import com.tml.bean.User;
import com.tml.dao.UserDAO;

public class LoginServer {


    public String userLogin(String username,String password){
        UserDAO userDAO = new UserDAO();
        User user = null;
        user = userDAO.check_id(username);
        if(user == null){
            return "用户不存在";
        }
        if(!password.equals(user.getPassword())){
            return "账号或者密码不正确";
        }
        return "success";
    }

}
