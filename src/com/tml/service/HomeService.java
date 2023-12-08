package com.tml.service;

import com.tml.bean.Question;

import java.util.List;

public interface HomeService {
    public List<Question> mainContent();
    public List<Question> helpContent();
    public List<Question> learnContent();
    public List<Question> psychogicalContent();
    public List<Question> healthContent();
    public List<Question> lawContent();
    public List<Question> jobContent();
    public List<Question> otherContent();



}
