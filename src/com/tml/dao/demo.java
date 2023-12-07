package com.tml.dao;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.tml.bean.User;
import com.tml.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class demo {

    public String check_type(String type){
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String content = null;

        try{
            connection = JdbcUtil.getConnection();
            if(connection==null){
                System.out.println("connect is null");
            }
            String sql = "select * from question where type=?";
            pstmt = (PreparedStatement)connection.prepareStatement(sql);
            pstmt.setString(1,type);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                content = resultSet.getString("content");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return content;
    }
}
