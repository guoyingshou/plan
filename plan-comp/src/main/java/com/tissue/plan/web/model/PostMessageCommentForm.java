package com.tissue.plan.web.model;

import com.tissue.core.command.PostMessageCommentCommand;
import com.tissue.core.plan.PostMessage;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class PostMessageCommentForm extends AbstractCommand implements PostMessageCommentCommand, Serializable {

    private PostMessage postMessage;

    public void setPostMessage(PostMessage postMessage) {
        this.postMessage = postMessage;
    }

    public PostMessage getPostMessage() {
        return postMessage;
    }

}
