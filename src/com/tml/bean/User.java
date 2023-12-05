package com.tml.bean;

public class User {
    /**
     * 用户类结构
     */
    private String username = null;
    private String password = null;

    public User(){}
    public User(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
