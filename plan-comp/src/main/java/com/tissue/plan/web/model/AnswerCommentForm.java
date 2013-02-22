package com.tissue.plan.web.model;

import com.tissue.core.command.Command;
import com.tissue.core.command.AnswerCommentCommand;
import com.tissue.core.plan.Answer;
import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class AnswerCommentForm extends Command implements AnswerCommentCommand {

    private Answer answer;

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }
}
