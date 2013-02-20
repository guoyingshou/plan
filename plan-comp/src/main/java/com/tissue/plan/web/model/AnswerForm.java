package com.tissue.plan.web.model;

import com.tissue.core.command.AnswerCommand;
import com.tissue.core.plan.Question;
import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class AnswerForm extends Command implements AnswerCommand {

    private Question question;

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
}
