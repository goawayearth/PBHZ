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
import java.util.ArrayList;
import java.util.List;

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
                user.setState(resultSet.getInt("state"));
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

        String sql = "insert into user(username,password) values(?,?)";
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

    public void deleteUser(String username){
        CommentDAO commentDAO = new CommentDAO();
        QuestionDAO questionDAO = new QuestionDAO();
        //删掉所有问题和图片
        //删掉所有评论和图片
        //删掉用户和图片
        System.out.println("进入了deleteUser()");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = JdbcUtil.getConnection();
            String sql2 = "select * from comment where name=?";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String cid = resultSet.getString("cid");
                commentDAO.deleteComment(cid);
            }

            String sql3 = "select * from question where name=?";
            preparedStatement = connection.prepareStatement(sql3);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String qid = resultSet.getString("qid");
                questionDAO.deleteQuestion(qid);
            }

            String sql1 = "select * from user where username=?";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String icon = resultSet.getString("icon");
                if(icon!=null){
                    DeleteImg.deleteImg(icon);
                }
            }

            String sql4 = "delete from user where username = ?";
            preparedStatement = connection.prepareStatement(sql4);
            preparedStatement.setString(1,username);
            preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(connection);
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(resultSet);
        }




    }

    public List<User> getNormalUser(){
        List<User> userList = new ArrayList<>();
        String sql = "select * from user where state = 1";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setIconPath(resultSet.getString("icon"));

                userList.add(user);
            }

        }catch (Exception e){

        }finally {
            JdbcUtil.close(connection);
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(resultSet);
        }

        return userList;
    }

    public List<User> getBlackList(){
        List<User> userList = new ArrayList<>();
        String sql = "select * from user where state = 0";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setIconPath(resultSet.getString("icon"));

                userList.add(user);
            }

        }catch (Exception e){

        }finally {
            JdbcUtil.close(connection);
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(resultSet);
        }

        return userList;
    }

    public void addBlack(String username){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "update user set state=0 where username=?";
        try{
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(connection);
            JdbcUtil.close(preparedStatement);
        }


    }

    public void removeBlack(String username){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "update user set state=1 where username=?";

        try{
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(connection);
            JdbcUtil.close(preparedStatement);
        }

    }

    public List<User> searchNormalUser(String key) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE state = 1 AND username LIKE ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + key + "%"); // Use "%" for wildcard matching

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setIconPath(resultSet.getString("icon"));
                userList.add(user);
            }

        } catch (SQLException e) {
            // Handle the exception, log it, or throw a custom exception
            e.printStackTrace(); // Log the exception or handle it according to your logging strategy

        } finally {
            // Close resources in a finally block
            JdbcUtil.close(connection);
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(resultSet);
        }

        return userList;
    }


    public List<User> searchBlackList(String key){
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE state = 0 AND username LIKE ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + key + "%"); // Use "%" for wildcard matching

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setIconPath(resultSet.getString("icon"));
                userList.add(user);
            }

        } catch (SQLException e) {
            // Handle the exception, log it, or throw a custom exception
            e.printStackTrace(); // Log the exception or handle it according to your logging strategy

        } finally {
            // Close resources in a finally block
            JdbcUtil.close(connection);
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(resultSet);
        }

        return userList;
    }

}
