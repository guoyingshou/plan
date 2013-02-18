package com.tissue.plan.web.model;

import com.tissue.core.command.TopicCommand;
import com.tissue.core.social.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class AbstractCommand implements Serializable {

    private String id;

    @NotNull
    @NotEmpty
    private String content;

    private User user;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
