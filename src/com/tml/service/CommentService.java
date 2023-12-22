package com.tml.service;

import com.tml.bean.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    void addComment(Comment comment);
    void deleteComment(String cid) throws SQLException;
    List<Comment> getComment();
    List<Comment> searchComment(String key);

}

