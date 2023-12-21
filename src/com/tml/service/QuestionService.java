package com.tml.service;

import com.tml.bean.Question;

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
}
