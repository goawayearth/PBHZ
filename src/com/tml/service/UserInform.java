package com.tml.service;


import com.tml.bean.Question;
import com.tml.bean.User;

import java.util.List;

public interface UserInform {
    public void changeIcon(String username, String path);

    public User getUser(String username);

    public List<Question> getQuestion(String username);

    public void deleteUser(String username);

    public List<User> getNormalUser();

    public List<User> getBlackList();
}
