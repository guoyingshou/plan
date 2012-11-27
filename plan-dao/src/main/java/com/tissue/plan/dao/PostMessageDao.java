package com.tissue.plan.dao;

import com.tissue.domain.plan.PostMessage;
import java.util.List;

public interface PostMessageDao {

    /**
     * Add a message to the specific post.
     */
    void create(PostMessage message);

}
