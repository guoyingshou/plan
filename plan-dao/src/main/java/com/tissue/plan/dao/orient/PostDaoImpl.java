package com.tissue.plan.dao.orient;

import com.tissue.core.util.OrientIdentityUtil;
import com.tissue.core.util.OrientDataSource;

import com.tissue.domain.social.Event;
import com.tissue.domain.profile.User;
import com.tissue.domain.plan.Post;

import com.tissue.commons.dao.social.EventDao;
import com.tissue.plan.dao.PostDao;
import com.tissue.core.converter.PostConverter;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.nio.charset.Charset;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.orientechnologies.orient.core.db.graph.OGraphDatabase;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.tx.OTransaction;

@Component
public class PostDaoImpl implements PostDao {

    @Autowired
    private OrientDataSource dataSource;

    @Autowired
    private EventDao eventDao;

    public Post create(Post post) {
        OGraphDatabase db = dataSource.getDB();
        try {
            ODocument doc = PostConverter.convert(post);
            doc.save();
            //post.setId(OrientIdentityUtil.encode(doc.getIdentity().toString()));
            post = PostConverter.buildMiniumPost(doc);
        }
        catch(Exception exc) {
            exc.printStackTrace();
        }
        finally {
            db.close();
        }
        return post;
    }

    public Post getPost(String id) {
        Post post = null;
        OGraphDatabase db = dataSource.getDB();
        try {
            ORecordId recordId = new ORecordId(OrientIdentityUtil.decode(id));
            ODocument postDoc = db.load(recordId);
            post = PostConverter.buildPost(postDoc);
        }
        catch(Exception exc) {
            exc.printStackTrace();
        }
        finally {
            db.close();
        }
        return post;
    }

    public List<Post> getPostsByTopicId(String topicId) {
        List<Post> posts = null;

        OGraphDatabase db = dataSource.getDB();
        try {
            posts = getPostsByTopicId(db, topicId);
        }
        finally {
            db.close();
        }

        return posts;
    }

    private List<Post> getPostsByTopicId(OGraphDatabase db, String topicId) {
        String postQstr = "select from post where plan.topic in " + OrientIdentityUtil.decode(topicId);
        OSQLSynchQuery q = new OSQLSynchQuery(postQstr);

        List<ODocument> postsDoc = db.query(q);
        List<Post> posts = PostConverter.buildPosts(postsDoc);
        return posts;
    }

    public List<Post> getPostsByTopicIdAndType(String topicId, String type) {
        List<Post> posts = null;

        OGraphDatabase db = dataSource.getDB();
        try {
            posts = getPostsByTopicIdAndType(db, topicId, type);
        }
        finally {
            db.close();
        }

        return posts;
    }

    public List<Post> getPostsByTopicIdAndType(OGraphDatabase db, String topicId, String type) {
        String postQstr = "select from post where plan.topic in ? and type = ?";
        OSQLSynchQuery q = new OSQLSynchQuery(postQstr);

        List<ODocument> postsDoc = db.command(q).execute(OrientIdentityUtil.decode(topicId), type);
        List<Post> posts = PostConverter.buildPosts(postsDoc);
        return posts;
    }

    public List<Post> getPostsByPlanId(String planId) {
        List<Post> posts = null;

        OGraphDatabase db = dataSource.getDB();
        try {
            posts = getPostsByPlanId(db, planId);
        }
        finally {
            db.close();
        }
        return posts;
    }

    public List<Post> getPostsByPlanId(OGraphDatabase db, String planId) {
        String postQstr = "select from post where plan in ?";
        OSQLSynchQuery q = new OSQLSynchQuery(postQstr);
        List<ODocument> postsDoc = db.command(q).execute(OrientIdentityUtil.decode(planId));
        List<Post> posts = PostConverter.buildPosts(postsDoc);
        return posts;
    }

    //-- by user

    public List<Post> getPostsByUserId(String userId) {
        List<Post> posts = null;

        OGraphDatabase db = dataSource.getDB();
        try {
            posts = getPostsByUserId(db, userId);
        }
        finally {
            db.close();
        }
        return posts;
    }

    public List<Post> getPostsByUserId(OGraphDatabase db, String userId) {
        String postQstr = "select from post where user in ?";
        OSQLSynchQuery q = new OSQLSynchQuery(postQstr);
        List<ODocument> postsDoc = db.command(q).execute(OrientIdentityUtil.decode(userId));
        List<Post> posts = PostConverter.buildPosts(postsDoc);
        return posts;
    }

}
