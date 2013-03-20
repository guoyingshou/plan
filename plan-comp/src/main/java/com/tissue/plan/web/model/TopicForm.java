package com.tissue.plan.web.model;

import com.tissue.plan.command.TopicCommand;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class TopicForm extends PostForm implements TopicCommand {

    @NotNull
    @NotEmpty
    private Set<String> tags;

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<String> getTags() {
        return tags;
    }

}
