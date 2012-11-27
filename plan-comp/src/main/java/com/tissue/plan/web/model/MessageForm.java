package com.tissue.plan.web.model;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class MessageForm implements Serializable {

    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
