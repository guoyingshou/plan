package com.tissue.plan.dao.orient;

import com.tissue.core.util.OrientIdentityUtil;
import com.tissue.core.util.OrientDataSource;
import com.tissue.core.converter.PostConverter;

import com.tissue.domain.social.Event;
import com.tissue.domain.profile.User;
import com.tissue.domain.plan.Post;

import com.tissue.commons.dao.social.EventDao;
import com.tissue.plan.dao.PostDao;

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
            //post = PostConverter.buildPostWithoutChild(doc);
            post = PostConverter.buildPost(doc);
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

    public List<Post> getPagedPostsByTopicId(String topicId, int page, int size) {
        List<Post> posts = null;

        OGraphDatabase db = dataSource.getDB();
        try {
            posts = getPagedPostsByTopicId(db, topicId, page, size);
        }
        finally {
            db.close();
        }

        return posts;
    }

    private List<Post> getPagedPostsByTopicId(OGraphDatabase db, String topicId, int page, int size) {
        String sql = "select from post where plan.topic in " + 
                      OrientIdentityUtil.decode(topicId) + 
                      " order by createTime desc skip " + 
                      ((page - 1) * size) + 
                      " limit " + size;
        OSQLSynchQuery q = new OSQLSynchQuery(sql);

        List<ODocument> postsDoc = db.query(q);
        List<Post> posts = PostConverter.buildPosts(postsDoc);
        return posts;
    }


    public long getPostsCountByTopicId(String topicId) {
        long result = 0;

        String sql = "select count(*) from post where plan.topic in " + OrientIdentityUtil.decode(topicId);
        OGraphDatabase db = dataSource.getDB();
        try {
            OSQLSynchQuery q = new OSQLSynchQuery(sql);
            List<ODocument> postsCountDoc = db.query(q);
            if(postsCountDoc.size() > 0) {
                 ODocument doc = postsCountDoc.get(0);
                 result = doc.field("count", long.class);
            }
        }
        finally {
            db.close();
        }
        return result;
    }


    public long getPostsCountByTopicIdAndType(String topicId, String type) {
        long result = 0;

        String sql = "select count(*) from post where plan.topic in ? and type = ?";
        OGraphDatabase db = dataSource.getDB();
        try {
            OSQLSynchQuery q = new OSQLSynchQuery(sql);
            List<ODocument> postsCountDoc = db.command(q).execute(OrientIdentityUtil.decode(topicId), type);
            if(postsCountDoc.size() > 0) {
                 ODocument doc = postsCountDoc.get(0);
                 result = doc.field("count", long.class);
            }
        }
        finally {
            db.close();
        }
        return result;
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

    public List<Post> getPagedPostsByTopicIdAndType(String topicId, String type, int page, int size) {
        List<Post> posts = null;

        OGraphDatabase db = dataSource.getDB();
        try {
            posts = getPagedPostsByTopicIdAndType(db, topicId, type, page, size);
        }
        finally {
            db.close();
        }

        return posts;
    }

    public List<Post> getPagedPostsByTopicIdAndType(OGraphDatabase db, String topicId, String type, int page, int size) {
        String postQstr = "select from post where plan.topic in ? and type = ? order by createTime desc skip " + 
                           (page - 1) * size +
                           " limit " + size;
        OSQLSynchQuery q = new OSQLSynchQuery(postQstr);

        List<ODocument> postsDoc = db.command(q).execute(OrientIdentityUtil.decode(topicId), type);
        List<Post> posts = PostConverter.buildPosts(postsDoc);
        return posts;
    }

    public long getPostsCountByPlanId(String planId) {
        long result = 0;

        String sql = "select count(*) from post where plan in ?";
        OGraphDatabase db = dataSource.getDB();
        try {
            OSQLSynchQuery q = new OSQLSynchQuery(sql);
            List<ODocument> postsCountDoc = db.command(q).execute(OrientIdentityUtil.decode(planId));
            if(postsCountDoc.size() > 0) {
                 ODocument doc = postsCountDoc.get(0);
                 result = doc.field("count", long.class);
            }
        }
        finally {
            db.close();
        }
        return result;
    }

    public List<Post> getPagedPostsByPlanId(String planId, int page, int size) {
        List<Post> posts = null;
        OGraphDatabase db = dataSource.getDB();
        try {
            posts = getPagedPostsByPlanId(db, planId, page, size);
        }
        finally {
            db.close();
        }
        return posts;
    }

    public List<Post> getPagedPostsByPlanId(OGraphDatabase db, String planId, int page, int size) {
        String postQstr = "select from post where plan in ? order by createTime desc skip " +
                           (page - 1) * size + 
                           " limit " + size;
        OSQLSynchQuery q = new OSQLSynchQuery(postQstr);
        List<ODocument> postsDoc = db.command(q).execute(OrientIdentityUtil.decode(planId));
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
    
    public long getPostsCountByUserId(String userId) {
        long result = 0;

        String sql = "select count(*) from post where user in ?";
        OGraphDatabase db = dataSource.getDB();
        try {
            OSQLSynchQuery q = new OSQLSynchQuery(sql);
            List<ODocument> postsCountDoc = db.command(q).execute(OrientIdentityUtil.decode(userId));
            if(postsCountDoc.size() > 0) {
                 ODocument doc = postsCountDoc.get(0);
                 result = doc.field("count", long.class);
            }
        }
        finally {
            db.close();
        }
        return result;
    }


    public List<Post> getPagedPostsByUserId(String userId, int page, int size) {
        List<Post> posts = null;

        OGraphDatabase db = dataSource.getDB();
        try {
            posts = getPagedPostsByUserId(db, userId, page, size);
        }
        finally {
            db.close();
        }
        return posts;
    }

    public List<Post> getPagedPostsByUserId(OGraphDatabase db, String userId, int page, int size) {
        String postQstr = "select from post where user in ? order by createTime desc skip " +
                           (page - 1) * size +
                           " limit " + size;
        OSQLSynchQuery q = new OSQLSynchQuery(postQstr);
        List<ODocument> postsDoc = db.command(q).execute(OrientIdentityUtil.decode(userId));
        List<Post> posts = PostConverter.buildPosts(postsDoc);
        return posts;
    }

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
