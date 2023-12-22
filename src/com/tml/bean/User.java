package com.tml.bean;

public class User {
    /**
     * 用户类结构
     */

    private String username = null;
    private String password = null;
    private String iconPath = null;
    private int state = 1;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

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
