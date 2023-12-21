package com.tml.service;

import com.tml.bean.Comment;

import java.sql.SQLException;

public interface CommentService {
    void addComment(Comment comment);
    void deleteComment(String cid) throws SQLException;
}

