package com.tissue.plan.dao;

import com.tissue.domain.plan.Answer;
import com.tissue.domain.plan.AnswerComment;

public interface AnswerCommentDao {

    /**
     * Add a comment to the specific post message.
     */
    AnswerComment create(AnswerComment comment);

    void update(AnswerComment comment);
}
