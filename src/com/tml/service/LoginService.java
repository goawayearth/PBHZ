package com.tml.service;

public interface LoginService {
    /**
     * 实现检测账号并登录功能
     * @param username
     * @param password
     * @return
     */

    abstract public String userLogin(String username,String password);
}
