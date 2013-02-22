package com.tissue.plan.web.model;

import com.tissue.core.command.Command;
import com.tissue.core.command.PostMessageCommand;
import com.tissue.core.plan.Post;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class PostMessageForm extends Command implements PostMessageCommand {

    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

}
