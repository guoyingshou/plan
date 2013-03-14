package com.tissue.plan.web.model;

import com.tissue.core.command.MessageCommand;
import com.tissue.core.plan.Article;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class MessageForm extends CommentForm implements MessageCommand {

    private Article article;

    public void setArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

}
