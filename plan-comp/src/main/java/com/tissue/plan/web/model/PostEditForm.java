package com.tissue.plan.web.model;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class PostEditForm implements Serializable {

    @NotNull
    @NotEmpty
    private String title;
    
    @NotNull
    @NotEmpty
    private String content;

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

}
