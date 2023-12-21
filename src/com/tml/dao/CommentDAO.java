package com.tml.dao;

import com.alibaba.druid.util.jdbc.LocalResultSet;
import com.tml.bean.Comment;
import com.tml.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    UserDAO userDAO = new UserDAO();


    public List<Comment> check_qid(String qid) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Comment> comments = new ArrayList<>();
        String sql = "select * from comment where qid=?";
        connection = JdbcUtil.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,qid);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Comment comment = new Comment();
            comment.setQid(resultSet.getString("qid"));
            comment.setContent(resultSet.getString("content"));
            comment.setDate(resultSet.getTimestamp("date"));
            comment.setCid(resultSet.getString("cid"));
            comment.setName(resultSet.getString("name"));
            comment.setFileName(resultSet.getString("filename"));
            comment.setIcon(userDAO.check_id(resultSet.getString("name")).getIconPath());
            comments.add(comment);
        }
        JdbcUtil.close(preparedStatement);
        JdbcUtil.close(connection);
        JdbcUtil.close(resultSet);
        return comments;
    }

    // 添加评论到数据库
    public void addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 获取数据库连接（这里需要根据你的项目结构和数据库配置进行调整）
            connection = JdbcUtil.getConnection();

            // 编写 SQL 语句
            String sql = "INSERT INTO comment (qid, content, date, name, filename, cid) VALUES (?, ?, ?, ?, ?,?)";

            // 创建 PreparedStatement 对象
            preparedStatement = connection.prepareStatement(sql);

            // 设置参数
            preparedStatement.setString(1, comment.getQid());
            preparedStatement.setString(2, comment.getContent());

            // 将java.util.Date转为java.sql.Timestamp
            java.util.Date utilDate = comment.getDate();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());

            preparedStatement.setTimestamp(3, sqlDate);
            preparedStatement.setString(4, comment.getName());
            preparedStatement.setString(5, comment.getFileName());
            preparedStatement.setString(6, comment.getCid());

            // 执行 SQL 语句
            preparedStatement.executeUpdate();
            System.out.println("问题存储成功");
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        } finally {
            // 关闭数据库连接等资源
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // 处理异常
            }
            finally {
                JdbcUtil.close(preparedStatement);
                JdbcUtil.close(connection);
            }
        }
    }

    public void deleteComment(String cid) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        System.out.println("进入了deleteComment()");
        //删除评论和图片
        ResultSet resultSet = null;
        String sql1="select * from comment where cid=?";
        connection=JdbcUtil.getConnection();
        preparedStatement=connection.prepareStatement(sql1);
        preparedStatement.setString(1,cid);
        resultSet = preparedStatement.executeQuery();
        String iconpath = null;
        if(resultSet.next()){
            iconpath = resultSet.getString("filename");
        }
        if(iconpath!=null){
            DeleteImg.deleteImg(iconpath);
        }

        String sql2 = "delete from comment where cid = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setString(1,cid);
        preparedStatement.executeUpdate();
        JdbcUtil.close(preparedStatement);
        JdbcUtil.close(connection);
        JdbcUtil.close(resultSet);

    }

    public List<Comment> getComment(){
        List<Comment> comments = new ArrayList<>();
        String sql = "select * from comment";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setQid(resultSet.getString("qid"));
                comment.setContent(resultSet.getString("content"));
                comment.setDate(resultSet.getTimestamp("date"));
                comment.setCid(resultSet.getString("cid"));
                comment.setName(resultSet.getString("name"));
                comment.setFileName(resultSet.getString("filename"));
                comment.setIcon(userDAO.check_id(resultSet.getString("name")).getIconPath());
                comments.add(comment);
            }

        }catch (Exception e){

        }finally {
            JdbcUtil.close(connection);
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(resultSet);
        }
        return comments;
    }
}

