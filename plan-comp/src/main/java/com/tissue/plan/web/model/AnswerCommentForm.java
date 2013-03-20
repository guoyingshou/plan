package com.tissue.plan.web.model;

import com.tissue.plan.command.AnswerCommentCommand;
import com.tissue.plan.Answer;
import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class AnswerCommentForm extends ContentForm implements AnswerCommentCommand {

    private Answer answer;

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }
}
