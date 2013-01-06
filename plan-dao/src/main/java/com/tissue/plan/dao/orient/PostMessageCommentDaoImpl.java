package com.tissue.plan.dao.orient;

import com.tissue.core.util.OrientIdentityUtil;
import com.tissue.core.util.OrientDataSource;
import com.tissue.core.converter.PostMessageCommentConverter;

import com.tissue.domain.profile.User;
import com.tissue.domain.plan.PostMessage;
import com.tissue.domain.plan.PostMessageComment;

import com.tissue.plan.dao.PostMessageCommentDao;

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
public class PostMessageCommentDaoImpl implements PostMessageCommentDao {

    @Autowired
    private OrientDataSource dataSource;

    public PostMessageComment create(PostMessageComment comment) {

        OGraphDatabase db = dataSource.getDB();
        try {
            ODocument commentDoc = PostMessageCommentConverter.convertPostMessageComment(comment);
            commentDoc.save();

            String ridComment = commentDoc.getIdentity().toString();
            String ridUser = OrientIdentityUtil.decode(comment.getUser().getId());

            String sql = "create edge EdgePublish from " + ridUser + " to " + ridComment + " set label = 'publish', target = 'postMessageComment', createTime = sysdate()";
            OCommandSQL cmd = new OCommandSQL(sql);
            db.command(cmd).execute(ridUser, ridComment);

            //update message adding this comment
            String record = OrientIdentityUtil.decode(comment.getPostMessage().getId());
            ORecordId messageRecordId = new ORecordId(record);
            ODocument messageDoc = db.load(messageRecordId);
            Set<ODocument> commentsDoc = messageDoc.field("comments");
            if(commentsDoc == null) {
                commentsDoc = new HashSet();
            }
            commentsDoc.add(commentDoc);
            messageDoc.field("comments", commentsDoc);
            messageDoc.save();

            comment.setId(OrientIdentityUtil.encode(ridComment));
            //comment = PostMessageCommentConverter.buildPostMessageCommentWithParent(commentDoc);
        }
        finally {
            db.close();
        }

        return comment;
    }

    public PostMessageComment update(PostMessageComment comment) {

        OGraphDatabase db = dataSource.getDB();
        try {
            ODocument commentDoc = db.load(new ORecordId(OrientIdentityUtil.decode(comment.getId())));
            commentDoc.field("content", comment.getContent());
            commentDoc.save();
        }
        finally {
            db.close();
        }

        return comment;
    }

}
