package com.tml.bean;

import java.util.Date;

public class Comment {
    private String cid = null;
    private String qid = null;
    private String content = null;
    private Date date = null;
    private String name = null;
    private String filename=null;
    private byte[] filecontent=null;
    private String filetype=null;
    private String icon = null;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFileName() {
        return filename;
    }

    public void setFileName(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return filetype;
    }

    public void setFileType(String filetype) {
        this.filetype = filetype;
    }

    public byte[] getFileContent() {
        return filecontent;
    }

    public void setFileContent(byte[] filecontent) {
        this.filecontent = filecontent;
    }
}
