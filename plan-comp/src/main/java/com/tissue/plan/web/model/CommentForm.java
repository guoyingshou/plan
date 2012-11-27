package com.tissue.plan.web.model;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class CommentForm implements Serializable {

    private String content;
    private long topicId;
    private long planId;
    private long postId;
    private String username;

    private long answerId;
    private long messageId;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getPostId() {
        return postId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getMessageId() {
        return messageId;
    }

}
