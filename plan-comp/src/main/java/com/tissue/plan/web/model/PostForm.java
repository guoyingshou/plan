package com.tissue.plan.web.model;

import com.tissue.core.Account;
import com.tissue.core.command.PostCommand;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class PostForm implements PostCommand {

    private String id;

    @NotNull
    @NotEmpty
    private String title;
    
    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    private String type;

    private Account account;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}
