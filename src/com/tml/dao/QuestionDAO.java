package com.tml.dao;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.tml.bean.Question;
import com.tml.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    public List<Question> check_type(String type){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Question> questions = new ArrayList<>();

        try{
            connection = JdbcUtil.getConnection();
            if(connection == null){
                System.out.println("connect is null");
            }
            String sql = "select * from question where type=? order by date desc limit 2";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1,type);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Question question = new Question();
                question.setQid(resultSet.getString("qid"));
                question.setContent(resultSet.getString("content"));
                question.setDate(resultSet.getTimestamp("date"));
                question.setType(resultSet.getString("type"));
                question.setNum(resultSet.getInt("num"));
                questions.add(question);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return questions;
    }


}
