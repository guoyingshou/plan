package com.tissue.plan.web.model;

import com.tissue.core.command.PostCommand;
import com.tissue.core.social.User;
import com.tissue.core.plan.Plan;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class PostForm extends AbstractCommand implements PostCommand {

    @NotNull
    @NotEmpty
    private String title;
    
    @NotNull
    private String type;

    private Plan plan;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
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
}
