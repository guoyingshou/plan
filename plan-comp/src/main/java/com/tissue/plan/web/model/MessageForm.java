package com.tissue.plan.web.model;

import com.tissue.plan.command.MessageCommand;
import com.tissue.plan.Article;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public class MessageForm extends ContentForm implements MessageCommand {

    private Article article;

    public void setArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

}
