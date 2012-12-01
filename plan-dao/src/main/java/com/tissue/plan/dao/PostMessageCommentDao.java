package com.tissue.plan.dao;

import com.tissue.domain.plan.PostMessage;
import com.tissue.domain.plan.PostMessageComment;

public interface PostMessageCommentDao {

    /**
     * Add a comment to the specific post message.
     */
    PostMessageComment create(PostMessageComment comment);
}
