package com.tml.dao;

import com.tml.bean.User;
import com.tml.util.JdbcUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            connection = JdbcUtil.getConnection();
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
                user.setIconPath(resultSet.getString("icon"));
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
            connection = JdbcUtil.getConnection();
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

    public void updateUserIcon(String username,String path){
        // 先找到原来的头像的名称，删掉图片
        // 将新的头像的名称写道数据库中
        User user = check_id(username);
        String oldPath = user.getIconPath();
        if(oldPath != null){
            String filePath = "D:\\STUDY\\1Web\\web作业\\work3"+oldPath;

            // 使用Paths.get()方法创建Path对象
            Path path1 = Paths.get(filePath);

            try {
                // 使用Files.delete()方法删除文件
                Files.delete(path1);
                System.out.println("文件删除成功！");
            } catch (IOException e) {
                System.err.println("删除文件时出错：" + e.getMessage());
                e.printStackTrace();
            }
        }

        String sql2 = "UPDATE user SET icon=? WHERE username =? ;";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1,path);
            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();
            System.out.println("头像修改成功");
        }catch (Exception e){}
        finally {
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(connection);
        }



    }

}
