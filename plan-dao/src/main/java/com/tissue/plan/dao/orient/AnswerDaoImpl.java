package com.tissue.plan.dao.orient;

import com.tissue.core.util.OrientIdentityUtil;
import com.tissue.core.util.OrientDataSource;
import com.tissue.core.converter.AnswerConverter;

import com.tissue.domain.profile.User;
import com.tissue.domain.plan.Post;
import com.tissue.domain.plan.Answer;
import com.tissue.plan.dao.AnswerDao;

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
public class AnswerDaoImpl implements AnswerDao {

    @Autowired
    private OrientDataSource dataSource;

    /**
     * It seems that sql command cann't be mixed with java api call.
     */
    public Answer create(Answer answer) {

        OGraphDatabase db = dataSource.getDB();
        try {

            ODocument doc = AnswerConverter.convertAnswer(answer);
            doc.save();

            String ridAnswer = doc.getIdentity().toString();
            String ridUser = OrientIdentityUtil.decode(answer.getUser().getId());

            String sql = "create edge EdgePublish from " + ridUser + " to " + ridAnswer + " set label = 'answer', createTime = sysdate()";
            OCommandSQL cmd = new OCommandSQL(sql);
            db.command(cmd).execute(ridUser, ridAnswer);

            String record = OrientIdentityUtil.decode(answer.getQuestion().getId());
            ORecordId postRecordId = new ORecordId(record);

            /**
            doc.field("post", postRecordId);
            doc.save();
            */

            ODocument postDoc = db.load(postRecordId);
            Set<ODocument> answersDoc = postDoc.field("answers", Set.class);
            if(answersDoc == null) {
                answersDoc = new HashSet();
            }
            answersDoc.add(doc);
            postDoc.field("answers", answersDoc);
            postDoc.save();

            answer.setId(OrientIdentityUtil.encode(ridAnswer));
        }
        finally {
            db.close();
        }

        return answer;
    }

    public void update(Answer answer) {
        OGraphDatabase db = dataSource.getDB();
        try {
            ODocument doc = db.load(new ORecordId(OrientIdentityUtil.decode(answer.getId())));
            doc.field("content", answer.getContent());
            doc.save();
        }
        finally {
            db.close();
        }
    }

}
