package com.tissue.plan.web.model;

import com.tissue.core.Account;
import com.tissue.plan.command.TopicCommand;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class TopicForm implements TopicCommand {

    private String id;
    private Account account;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    @NotEmpty
    private Set<String> tags;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<String> getTags() {
        return tags;
    }

}
