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

    public void addBlack(String username);

    public void removeBlack(String username);

    public List<User> searchNormalUser(String key);
    public List<User> searchBlackList(String key);



}
