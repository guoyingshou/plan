package com.tissue.plan.web.model;

import com.tissue.core.Account;
import com.tissue.core.command.ContentCommand;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class ContentForm implements ContentCommand {

    private String id;

    @NotNull
    @NotEmpty
    private String content;

    private Account account;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content.trim();
    }

    public String getContent() {
        return content;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}
