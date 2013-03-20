package com.tissue.plan.web.model;

import com.tissue.core.Account;
import com.tissue.core.plan.command.PlanCommand;
import com.tissue.core.plan.Topic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

public class PlanForm implements PlanCommand, Serializable {

    private String id;

    @NotNull
    private int duration;

    private Topic topic;
    private Account account;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setTopic(Topic topic) {
        this.topic= topic;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
