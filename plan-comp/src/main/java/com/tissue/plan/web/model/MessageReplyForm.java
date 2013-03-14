package com.tissue.plan.web.model;

import com.tissue.core.command.MessageReplyCommand;
import com.tissue.core.plan.Message;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class MessageReplyForm extends CommentForm implements MessageReplyCommand {

    private Message message;

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

}
