package com.tissue.plan.dao;

import com.tissue.domain.plan.Answer;
import com.tissue.domain.plan.AnswerComment;

public interface AnswerCommentDao {

    /**
     * Add a comment to the specific post message.
     */
    void create(AnswerComment comment);
}
