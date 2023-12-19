package com.tml.service.impl;

import com.tml.bean.User;
import com.tml.dao.UserDAO;
import com.tml.service.RegisterService;


public class RegisterServiceImpl implements RegisterService {

    private UserDAO userDAO = new UserDAO();

    @Override
    public String createUser(String username, String password) {
        User user = userDAO.check_id(username);
        if(user!=null){
            //用户不是空
            return "用户名已经存在";
        }
        // 调用数据库的函数
        userDAO.create(username,password);

        return "success";
    }


    @Override
    public boolean userExist(String username) {
        User user = userDAO.check_id(username);
        if(user == null)
            return false;
        return true;
    }


}
