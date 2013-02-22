package com.tissue.plan.web.model;

import com.tissue.core.command.Command;
import com.tissue.core.command.QuestionCommentCommand;
import com.tissue.core.plan.Post;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class QuestionCommentForm extends Command implements QuestionCommentCommand {

    private Post question;

    public void setQuestion(Post question) {
        this.question = question;
    }

    public Post getQuestion() {
        return question;
    }

}
