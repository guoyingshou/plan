package com.tissue.plan.dao.orient;

import com.tissue.core.util.OrientIdentityUtil;
import com.tissue.core.util.OrientDataSource;
import com.tissue.core.converter.AnswerCommentConverter;

import com.tissue.domain.profile.User;
import com.tissue.domain.plan.Answer;
import com.tissue.domain.plan.AnswerComment;

import com.tissue.plan.dao.AnswerCommentDao;

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
public class AnswerCommentDaoImpl implements AnswerCommentDao {

    @Autowired
    private OrientDataSource dataSource;

    public AnswerComment create(AnswerComment comment) {

        OGraphDatabase db = dataSource.getDB();
        try {
            ODocument commentDoc = AnswerCommentConverter.convertAnswerComment(comment);
            commentDoc.save();

            String ridComment = commentDoc.getIdentity().toString();
            String ridUser = OrientIdentityUtil.decode(comment.getUser().getId());

            String sql = "create edge EdgePublish from " + ridUser + " to " + ridComment + " set label = 'publish', target = 'answerComment', createTime = sysdate()";
            OCommandSQL cmd = new OCommandSQL(sql);
            db.command(cmd).execute(ridUser, ridComment);

            //update answer adding this comment
            String record = OrientIdentityUtil.decode(comment.getAnswer().getId());
            ORecordId answerRecordId = new ORecordId(record);
            ODocument answerDoc = db.load(answerRecordId);
            Set<ODocument> commentsDoc = answerDoc.field("comments");
            if(commentsDoc == null) {
                commentsDoc = new HashSet();
            }
            commentsDoc.add(commentDoc);
            answerDoc.field("comments", commentsDoc);
            answerDoc.save();

            comment.setId(OrientIdentityUtil.encode(ridComment));
        }
        finally {
            db.close();
        }

        return comment;
    }

    public void update(AnswerComment comment) {

        OGraphDatabase db = dataSource.getDB();
        try {
            ODocument commentDoc = db.load(new ORecordId(OrientIdentityUtil.decode(comment.getId())));
            commentDoc.field("content", comment.getContent());
            commentDoc.save();
        }
        finally {
            db.close();
        }
    }

}
