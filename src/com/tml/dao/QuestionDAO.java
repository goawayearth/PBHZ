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

    /**
     * 搜索某类型的帖子前两条
     * @param type
     * @return
     */
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
        finally {
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(connection);
            JdbcUtil.close(resultSet);
        }
        return questions;
    }


    /**
     * 搜索某类型的帖子的全部，最多20条
     * @param type
     * @return
     */
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
        finally {
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(connection);
            JdbcUtil.close(resultSet);
        }
        return questions;
    }


    /**
     * 根据qid搜索帖子
     * @param qid
     * @return
     * @throws SQLException
     */
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
        finally {
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(connection);
            JdbcUtil.close(resultSet);
        }
        return question;

    }

    /**
     * 搜索包含key的内容
     * @param key
     * @return
     * @throws SQLException
     */
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
        finally {
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(connection);
            JdbcUtil.close(resultSet);
        }

        return questions;
    }

    /**
     * 将帖子写进数据库
     * @param question
     */
    public void save(Question question){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "insert into question values (?,?,?,?,?,?,?)";
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
        finally {
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(connection);
        }

    }

    /**
     * 根据uid搜索帖子
     * @param uid
     * @return
     * @throws SQLException
     */
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
                question.setIcon(userDAO.check_id(resultSet.getString("name")).getIconPath());
                questions.add(question);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(connection);
        }

        return questions;
    }

    /**
     * 根据qid删除帖子
     * @param qid
     */
    public void deleteQuestion(String qid){
        CommentDAO commentDAO = new CommentDAO();
        //删除所有评论和评论图片
        //删除问题和图片
        System.out.println("进入了deleteQuestion()");
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql1 = "select * from comment where qid=?";

        try{
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1,qid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String cid = resultSet.getString("cid");
                commentDAO.deleteComment(cid);
            }
            String sql2 = "select * from question where qid=?";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1,qid);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String iconpath = resultSet.getString("filepath");
                if(iconpath!=null){
                    DeleteImg.deleteImg(iconpath);
                }
            }

            String sql3 = "delete from question where qid=?";
            preparedStatement = connection.prepareStatement(sql3);
            preparedStatement.setString(1,qid);
            preparedStatement.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JdbcUtil.close(preparedStatement);
            JdbcUtil.close(connection);
        }


    }

    /**
     * 搜索相关包含key的内容
     * @param key
     * @return
     * @throws SQLException
     */
    public List<Question> searchLearning(String key) throws SQLException {
        // 通过关键词搜索
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM question WHERE type='learning' AND content LIKE ?  order by date desc";

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            if (connection == null) {
                System.out.println("Connection is null");
            }

            preparedStatement.setString(1, '%' + key + '%');
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
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
        } catch (SQLException e) {
            // Handle the exception, log it, or rethrow it
            e.printStackTrace(); // Log the exception or handle it according to your logging strategy
            throw new SQLException("Error in searchLearning", e);
        }

        return questions;
    }


    public List<Question> searchPsychogical(String key) throws SQLException {
        // 通过关键词搜索
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM question WHERE type='psychogical' AND content LIKE ?  order by date desc";

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            if (connection == null) {
                System.out.println("Connection is null");
            }

            preparedStatement.setString(1, '%' + key + '%');
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
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
        } catch (SQLException e) {
            // Handle the exception, log it, or rethrow it
            e.printStackTrace(); // Log the exception or handle it according to your logging strategy
            throw new SQLException("Error in searchLearning", e);
        }

        return questions;
    }

    public List<Question> searchHealth(String key) throws SQLException {
        // 通过关键词搜索
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM question WHERE type='health' AND content LIKE ?  order by date desc";

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            if (connection == null) {
                System.out.println("Connection is null");
            }

            preparedStatement.setString(1, '%' + key + '%');
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
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
        } catch (SQLException e) {
            // Handle the exception, log it, or rethrow it
            e.printStackTrace(); // Log the exception or handle it according to your logging strategy
            throw new SQLException("Error in searchLearning", e);
        }

        return questions;
    }

    public List<Question> searchLaw(String key) throws SQLException {
        // 通过关键词搜索
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM question WHERE type='law' AND content LIKE ?  order by date desc";

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            if (connection == null) {
                System.out.println("Connection is null");
            }

            preparedStatement.setString(1, '%' + key + '%');
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
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
        } catch (SQLException e) {
            // Handle the exception, log it, or rethrow it
            e.printStackTrace(); // Log the exception or handle it according to your logging strategy
            throw new SQLException("Error in searchLearning", e);
        }

        return questions;
    }

    public List<Question> searchJob(String key) throws SQLException {
        // 通过关键词搜索
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM question WHERE type='job' AND content LIKE ?  order by date desc";

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            if (connection == null) {
                System.out.println("Connection is null");
            }

            preparedStatement.setString(1, '%' + key + '%');
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
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
        } catch (SQLException e) {
            // Handle the exception, log it, or rethrow it
            e.printStackTrace(); // Log the exception or handle it according to your logging strategy
            throw new SQLException("Error in searchLearning", e);
        }

        return questions;
    }

    public List<Question> searchOther(String key) throws SQLException {
        // 通过关键词搜索
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM question WHERE type='other' AND content LIKE ?  order by date desc";

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            if (connection == null) {
                System.out.println("Connection is null");
            }

            preparedStatement.setString(1, '%' + key + '%');
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
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
        } catch (SQLException e) {
            // Handle the exception, log it, or rethrow it
            e.printStackTrace(); // Log the exception or handle it according to your logging strategy
            throw new SQLException("Error in searchLearning", e);
        }

        return questions;
    }

}
