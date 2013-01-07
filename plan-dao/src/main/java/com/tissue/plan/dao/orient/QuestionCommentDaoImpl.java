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

            String ridComment = commentDoc.getIdentity().toString();
            String ridUser = OrientIdentityUtil.decode(comment.getUser().getId());

            String sql = "create edge EdgeQuestionComment from " + ridUser + " to " + ridComment + " set label = 'questionComment', createTime = sysdate()";
            OCommandSQL cmd = new OCommandSQL(sql);
            db.command(cmd).execute();

            String ridQuestion = OrientIdentityUtil.decode(comment.getQuestion().getId());
            String sql2 = "update " + ridQuestion + " add comments = " + ridComment;
            cmd = new OCommandSQL(sql2);
            db.command(cmd).execute();

            /**

            ORecordId questionRecordId = new ORecordId(record);
            ODocument questionDoc = db.load(questionRecordId);
            Set<ODocument> commentsDoc = questionDoc.field("comments");
            if(commentsDoc == null) {
                commentsDoc = new HashSet();
            }
            commentsDoc.add(commentDoc);
            questionDoc.field("comments", commentsDoc);
            questionDoc.save();
            */

            comment.setId(OrientIdentityUtil.encode(ridComment));
        }
        finally {
            db.close();
        }

        return comment;
    }

    public void update(QuestionComment comment) {
        OGraphDatabase db = dataSource.getDB();
        try {
            ODocument doc = db.load(new ORecordId(OrientIdentityUtil.decode(comment.getId())));
            doc.field("content", comment.getContent());
            doc.save();
        }
        finally {
            db.close();
        }
    }


}
