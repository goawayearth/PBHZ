package com.tml.service;

public interface RegisterService {

    /**
     * 实现检测username不重复并创建账户
     * @param username
     * @param password
     * @return
     */
    abstract public String createUser(String username,String password);

    /**
     * 单纯判断账号是否存在
     */

    abstract public boolean userExist(String username);
}
