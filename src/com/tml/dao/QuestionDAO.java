package com.tml.dao;

import com.tml.bean.Question;
import com.tml.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    UserDAO userDAO = new UserDAO();

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
                question.setName(resultSet.getString("name"));
                questions.add(question);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return questions;
    }


    public List<Question> check_type_all(String type){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Question> questions = new ArrayList<>();


        try{
            connection = JdbcUtil.getConnection();
            if(connection == null){
                System.out.println("connect is null");
            }
            String sql = "select * from question where type=? order by date desc limit 20";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1,type);
            resultSet = preparedStatement.executeQuery();
            UserDAO userDAO = new UserDAO();
            while(resultSet.next()){
                Question question = new Question();
                question.setQid(resultSet.getString("qid"));
                question.setContent(resultSet.getString("content"));
                question.setDate(resultSet.getTimestamp("date"));
                question.setType(resultSet.getString("type"));
                question.setNum(resultSet.getInt("num"));
                question.setName(resultSet.getString("name"));


                question.setIcon(userDAO.check_id(resultSet.getString("name")).getIconPath());


                questions.add(question);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return questions;
    }


    public Question check_id(String qid) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Question question = new Question();

        try{
            connection = JdbcUtil.getConnection();
            if(connection == null){
                System.out.println("connect is null");
            }
            String sql = "select * from question where qid=? ";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1,qid);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                question.setQid(resultSet.getString("qid"));
                question.setContent(resultSet.getString("content"));
                question.setDate(resultSet.getTimestamp("date"));
                question.setType(resultSet.getString("type"));
                question.setNum(resultSet.getInt("num"));
                question.setName(resultSet.getString("name"));
                question.setIcon(userDAO.check_id(resultSet.getString("name")).getIconPath());
                question.setFilepath(resultSet.getString("filepath"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return question;

    }

    public List<Question> check_key(String key) throws SQLException {
        // 通过关键词搜索

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Question> questions = new ArrayList<>();

        String sql = "SELECT * FROM question WHERE content LIKE ?";
        try{
            connection = JdbcUtil.getConnection();
            if(connection == null){
                System.out.println("connect is null");
            }
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1,'%'+key+'%');
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Question question = new Question();
                question.setQid(resultSet.getString("qid"));
                question.setContent(resultSet.getString("content"));
                question.setDate(resultSet.getTimestamp("date"));
                question.setType(resultSet.getString("type"));
                question.setNum(resultSet.getInt("num"));
                question.setName(resultSet.getString("name"));
                question.setFilepath(resultSet.getString("filepath"));
                question.setIcon(userDAO.check_id(resultSet.getString("name")).getIconPath());
                questions.add(question);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return questions;
    }

    public void save(Question question){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "insert into question values (?,?,?,?,?,?,?,?)";
        try{
            connection = JdbcUtil.getConnection();
            if(connection == null){
                System.out.println("connect is null");
            }
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1,question.getQid());
            preparedStatement.setString(2,question.getContent());
            java.util.Date utilDate = question.getDate();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
            preparedStatement.setTimestamp(3,sqlDate);
            preparedStatement.setString(4,question.getType());
            preparedStatement.setInt(5,question.getNum());
            preparedStatement.setString(6,question.getName());
            preparedStatement.setString(7,question.getFilepath());

            preparedStatement.executeUpdate();

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public List<Question> check_uid(String uid) throws SQLException {
        // 通过关键词搜索

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Question> questions = new ArrayList<>();

        String sql = "SELECT * FROM question WHERE name=?";
        try{
            connection = JdbcUtil.getConnection();
            if(connection == null){
                System.out.println("connect is null");
            }
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1,uid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Question question = new Question();
                question.setQid(resultSet.getString("qid"));
                question.setContent(resultSet.getString("content"));
                question.setDate(resultSet.getTimestamp("date"));
                question.setType(resultSet.getString("type"));
                question.setNum(resultSet.getInt("num"));
                question.setName(resultSet.getString("name"));
                question.setFilepath(resultSet.getString("filepath"));
                questions.add(question);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return questions;
    }


}
