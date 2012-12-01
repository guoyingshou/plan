package com.tissue.plan.dao.orient;

import com.tissue.core.util.OrientIdentityUtil;
import com.tissue.core.util.OrientDataSource;
import com.tissue.core.converter.QuestionCommentConverter;

import com.tissue.domain.profile.User;
import com.tissue.domain.plan.Post;
import com.tissue.domain.plan.QuestionComment;

import com.tissue.plan.dao.QuestionCommentDao;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.orientechnologies.orient.core.db.graph.OGraphDatabase;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.sql.OCommandSQL;

@Component
public class QuestionCommentDaoImpl implements QuestionCommentDao {

    @Autowired
    private OrientDataSource dataSource;

    public QuestionComment create(QuestionComment comment) {

        OGraphDatabase db = dataSource.getDB();
        try {
            ODocument commentDoc = QuestionCommentConverter.convertQuestionComment(comment);
            commentDoc.save();

            //update question adding this comment
            String record = OrientIdentityUtil.decode(comment.getQuestion().getId());
            ORecordId questionRecordId = new ORecordId(record);
            ODocument questionDoc = db.load(questionRecordId);
            Set<ODocument> commentsDoc = questionDoc.field("comments");
            if(commentsDoc == null) {
                commentsDoc = new HashSet();
            }
            commentsDoc.add(commentDoc);
            questionDoc.field("comments", commentsDoc);
            questionDoc.save();

            comment = QuestionCommentConverter.buildQuestionCommentWithParent(commentDoc);

        }
        finally {
            db.close();
        }

        return comment;
    }

}
