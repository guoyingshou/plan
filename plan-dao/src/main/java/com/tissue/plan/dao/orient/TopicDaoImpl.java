package com.tissue.plan.dao.orient;

import com.tissue.core.util.OrientDataSource;
import com.tissue.core.util.OrientIdentityUtil;

import com.tissue.domain.profile.User;
import com.tissue.domain.plan.Plan;
import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Post;

import com.tissue.plan.dao.TopicDao;
import com.tissue.plan.dao.PostDao;
import com.tissue.core.converter.TopicConverter;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
//import java.nio.charset.Charset;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.orientechnologies.orient.core.db.graph.OGraphDatabase;
import com.orientechnologies.orient.core.db.record.OTrackedList;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.id.ORecordId;

@Component
public class TopicDaoImpl implements TopicDao {

    @Autowired
    private OrientDataSource dataSource;

    @Autowired
    private PostDao postDao;

    /**
     * Add a topic.
     */
    public Topic create(Topic topic) {
        OGraphDatabase db = dataSource.getDB();
        ODocument doc = new ODocument("Topic");
        topic.setCreateTime(new Date());
        try {
            doc.field("title", topic.getTitle());
            doc.field("content", topic.getContent());
            doc.field("createTime", topic.getCreateTime());
            doc.field("tags", topic.getTags());

            ORecordId user = new ORecordId(OrientIdentityUtil.decode(topic.getUser().getId()));
            doc.field("user", user);

            doc.save();

            topic.setId(OrientIdentityUtil.encode(doc.getIdentity().toString()));
        }
        catch(Exception exc) {
            //to do
        }
        finally {
            db.close();
        }

        return topic;
    }

    /**
     * Get a topic with all fields available.
     */
    public Topic getTopic(String topicId) {
        Topic topic = null;

        String sql = "select from " + OrientIdentityUtil.decode(topicId);
        OGraphDatabase db = dataSource.getDB();
        try {
            OSQLSynchQuery<ODocument> q = new OSQLSynchQuery(sql);
            List<ODocument> result = db.query(q.setFetchPlan("*:-1"));
            if(result.size() > 0) {
                ODocument doc = result.get(0);
                topic = TopicConverter.buildTopic(doc);
            }
        }
        catch(Exception exc) {
            exc.printStackTrace();
            //to do
        }
        finally {
            db.close();
        }
        return topic;
    }

    /**
     * Get all topics reverse ordered by createTime.
     */
    public List<Topic> getTopics() {
        List<Topic> topics = new ArrayList();

        String qstr = "select from topic order by createTime desc";

        OGraphDatabase db = dataSource.getDB();
        try {
            OSQLSynchQuery query = new OSQLSynchQuery(qstr);
            List<ODocument> docs = db.query(query);
            for(ODocument doc : docs) {
                Topic topic = new Topic();
                topic.setId(OrientIdentityUtil.encode(doc.getIdentity().toString()));
                topic.setTitle(doc.field("title").toString());
                topics.add(topic);
            }
        }
        catch(Exception exc) {
            //to do
            exc.printStackTrace();
        }
        finally {
            db.close();
        }
        return topics;
    }

    /**
     * Get all tags.
     */
    public List<String> getTopicTags() {
        OTrackedList<String> tags = null;

        String qstr = "select set(tags) from topic";

        OGraphDatabase db = dataSource.getDB();
        try {
            OSQLSynchQuery query = new OSQLSynchQuery(qstr);
            List<ODocument> docs = db.query(query);
            if(docs != null) {
                tags = docs.get(0).field("set");
            }
        }
        catch(Exception exc) {
            //to do
            exc.printStackTrace();
        }
        finally {
            db.close();
        }
        return tags;
    }

    public List<Topic> getTopicsByTag(String tag) {
        List<Topic> topics = new ArrayList();

        String qstr = "select from topic where tags in ? order by createTime desc";

        OGraphDatabase db = dataSource.getDB();
        try {
            OCommandSQL cmd = new OCommandSQL(qstr);
            List<ODocument> docs = db.command(cmd).execute(tag);
            for(ODocument doc : docs) {
                Topic topic = new Topic();
                topic.setId(OrientIdentityUtil.encode(doc.getIdentity().toString()));
                topic.setTitle(doc.field("title").toString());
                topics.add(topic);
            }
        }
        catch(Exception exc) {
            //to do
            exc.printStackTrace();
        }
        finally {
            db.close();
        }
        return topics;
    }














    /**
    public long getTopicsCount() {
        long result = 0L;
        OGraphDatabase db = dataSource.getDB();
        try {
            result = db.countClass("Topic");
        }
        finally {
            db.close();
        }
        return result;
    }

    public List<Topic> getTopics(Integer currentPage, Integer pageSize) {

        String qstr = "select from topic order by createTime desc skip " + (currentPage - 1) * pageSize + " limit " + pageSize;

        List<Topic> result = new ArrayList();

        OGraphDatabase db = dataSource.getDB();
        try {
            OSQLSynchQuery query = new OSQLSynchQuery(qstr);
            List<ODocument> docs = db.query(query);
            for(ODocument doc : docs) {
                Topic topic = new Topic();
                topic.setId(OrientIdentityUtil.encode(doc.getIdentity().toString()));
                topic.setTitle(doc.field("title").toString());
                result.add(topic);
            }
        }
        catch(Exception exc) {
            //to do
        }
        finally {
            db.close();
        }

        return result;
    }

    public Topic updateTopic(Topic topic) {
        return null;

    }
    */
}
