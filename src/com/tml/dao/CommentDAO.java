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

            comments.add(comment);
        }
        return comments;
    }
}
