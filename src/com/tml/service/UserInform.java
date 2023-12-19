package com.tml.service;


import com.tml.bean.User;

public interface UserInform {
    public void changeIcon(String username, String path);

    public User getUser(String username);
}
