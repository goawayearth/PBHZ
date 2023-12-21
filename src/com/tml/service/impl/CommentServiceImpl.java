package com.tml.service.impl;

import com.tml.bean.Comment;
import com.tml.dao.CommentDAO;
import com.tml.service.CommentService;

import java.sql.SQLException;

public class CommentServiceImpl implements CommentService {
    CommentDAO commentDao = new CommentDAO(); // 实例化 CommentDao，具体方式可能需要根据你的项目结构来调整
    @Override
    public void addComment(Comment comment) {
        // 在这里实现将评论添加到数据库的逻辑
        // 你可以调用 DAO 或者其他持久层操作

        commentDao.addComment(comment);
    }

    @Override
    public void deleteComment(String cid) throws SQLException {
        commentDao.deleteComment(cid);
    }
}
