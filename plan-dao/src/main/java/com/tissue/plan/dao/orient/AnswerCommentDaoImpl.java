package com.tissue.plan.dao.orient;

import com.tissue.core.util.OrientIdentityUtil;
import com.tissue.core.util.OrientDataSource;

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

    public void create(AnswerComment comment) {

        OGraphDatabase db = dataSource.getDB();
        try {

            //save the comment
            ODocument commentDoc = new ODocument("AnswerComment");
            commentDoc.field("content", comment.getContent());
            commentDoc.field("createTime", comment.getCreateTime());
            commentDoc.field("user", new ORecordId(OrientIdentityUtil.decode(comment.getUser().getId())));
            commentDoc.save();

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

        }
        finally {
            db.close();
        }
    }

}
