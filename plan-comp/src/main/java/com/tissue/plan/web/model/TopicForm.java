package com.tissue.plan.web.model;

import com.tissue.core.command.TopicCommand;
import com.tissue.core.social.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class TopicForm extends Command implements TopicCommand {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private Set<String> tags;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<String> getTags() {
        return tags;
    }

}
