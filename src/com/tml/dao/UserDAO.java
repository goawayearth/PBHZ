package com.tml.dao;

import com.tml.bean.User;
import com.tml.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 有关账户的数据库操作
 */
public class UserDAO {

    /**
     * 根据username去数据库中搜索并将结果返回
     */
    public User check_id(String id){
        User user = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try{
            connection = JdbcUtil.getUserConnection();
            if(connection==null){
                System.out.println("connect is null");
            }
            String sql = "select * from user where username=?";
            pstmt = (PreparedStatement)connection.prepareStatement(sql);
            pstmt.setString(1,id);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                user=new User();
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet);
            JdbcUtil.close(connection);
            JdbcUtil.close(pstmt);
        }
        return user;
    }


    public void create(String username,String password) {

        String sql = "insert into user values(?,?)";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = JdbcUtil.getUserConnection();
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtil.close(pstmt);
            JdbcUtil.close(connection);        //必须关闭
        }

    }

}
