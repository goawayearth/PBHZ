package com.tml.bean;

import java.util.Date;

public class Question {
    private String qid = null;
    private String content = null;
    private Date date = null;
    private int num = 0;
    private String type = null;
    private String name = null;
    private String filepath = null;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getNum() {
        return num;
    }

    public String getContent() {
        return content;
    }

    public String getQid() {
        return qid;
    }

    public String getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public void setType(String type) {
        this.type = type;
    }

}
