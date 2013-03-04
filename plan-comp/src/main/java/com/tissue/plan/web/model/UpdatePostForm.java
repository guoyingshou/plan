package com.tissue.plan.web.model;

import com.tissue.core.command.Command;
import com.tissue.core.command.PostCommand;
import com.tissue.core.social.User;
import com.tissue.core.plan.Plan;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class UpdatePostForm extends Command implements PostCommand {

    @NotEmpty
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return null;
    }

    public Plan getPlan() {
        return null;
    }

}
