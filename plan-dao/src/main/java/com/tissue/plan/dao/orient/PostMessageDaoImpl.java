package com.tissue.plan.dao.orient;

import com.tissue.core.util.OrientIdentityUtil;
import com.tissue.core.util.OrientDataSource;
import com.tissue.core.converter.PostMessageConverter;

import com.tissue.domain.profile.User;
import com.tissue.domain.plan.Post;
import com.tissue.domain.plan.PostMessage;

import com.tissue.plan.dao.PostMessageDao;

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
public class PostMessageDaoImpl implements PostMessageDao {

    @Autowired
    private OrientDataSource dataSource;

    /**
     * It seems that sql command cann't be mixed with java api call.
     */
    public PostMessage create(PostMessage message) {
        OGraphDatabase db = dataSource.getDB();
        try {
            ODocument doc = PostMessageConverter.convertPostMessage(message);
            doc.save();

            String ridMessage = doc.getIdentity().toString();
            String ridUser = OrientIdentityUtil.decode(message.getUser().getId());

            String sql = "create edge EdgePostMessage from " + ridUser + " to " + ridMessage + " set label = 'postMessage', createTime = sysdate()";
            OCommandSQL cmd = new OCommandSQL(sql);
            db.command(cmd).execute();

            String ridPost = OrientIdentityUtil.decode(message.getPost().getId());
            String sql2 = "update " + ridPost + " add messages = " + ridMessage;
            cmd = new OCommandSQL(sql2);
            db.command(cmd).execute();

            message.setId(OrientIdentityUtil.encode(ridMessage));
        }
        finally {
            db.close();
        }
        return message;
    }

    public void update(PostMessage message) {
        OGraphDatabase db = dataSource.getDB();
        try {
            ODocument doc = db.load(new ORecordId(OrientIdentityUtil.decode(message.getId())));
            doc.field("content", message.getContent());
            doc.save();
        }
        finally {
            db.close();
        }
    }

}
