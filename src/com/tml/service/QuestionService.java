package com.tml.service;

import com.tml.bean.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionService {
    public void saveQuestion(Question question);
    public void deleteQuestion(String qid);
    public List<Question> getLearning();
    public List<Question> getPsychogical();
    public List<Question> getHealth();
    public List<Question> getLaw();
    public List<Question> getJob();
    public List<Question> getOther();

    public List<Question> searchLearning(String key) throws SQLException;
    public List<Question> searchPsychogical(String key) throws SQLException;
    public List<Question> searchHealth(String key) throws SQLException;
    public List<Question> searchLaw(String key) throws SQLException;
    public List<Question> searchJob(String key) throws SQLException;
    public List<Question> searchOther(String key) throws SQLException;
}
