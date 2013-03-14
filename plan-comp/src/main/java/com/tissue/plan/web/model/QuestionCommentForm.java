package com.tissue.plan.web.model;

import com.tissue.core.command.QuestionCommentCommand;
import com.tissue.core.plan.Question;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class QuestionCommentForm extends CommentForm implements QuestionCommentCommand {

    private Question question;

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

}
