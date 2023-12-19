package com.tml.dao;

import com.tml.bean.Comment;
import com.tml.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {


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



            comments.add(comment);
        }
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
                String sql = "INSERT INTO comment (qid, content, date, name, filename) VALUES (?, ?, ?, ?, ?)";

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

                // 执行 SQL 语句
                preparedStatement.executeUpdate();
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
            }
        }
    }

