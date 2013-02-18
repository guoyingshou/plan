package com.tissue.plan.web.model;

import com.tissue.core.command.PostMessageCommand;
import com.tissue.core.plan.Post;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class PostMessageForm extends AbstractCommand implements PostMessageCommand, Serializable {

    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

}
