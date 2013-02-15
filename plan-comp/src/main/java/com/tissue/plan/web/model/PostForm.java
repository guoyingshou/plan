package com.tissue.plan.web.model;

import com.tissue.core.social.User;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.command.PostCommand;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class PostForm implements PostCommand, Serializable {

    String id;

    @NotNull
    @NotEmpty
    private String title;
    
    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    private String type;

    private Plan plan;
    private User user;

    /**
    private String planId;
    private String topicId;
    */

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

    public void setPlan(Plan plan) {
        this.plan= plan;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
