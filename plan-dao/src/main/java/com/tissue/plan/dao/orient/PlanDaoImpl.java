package com.tissue.plan.dao.orient;

import com.tissue.core.util.OrientDataSource;
import com.tissue.core.util.OrientIdentityUtil;
import com.tissue.core.converter.PlanConverter;

import com.tissue.domain.social.Event;
import com.tissue.domain.social.ActivityObject;
import com.tissue.domain.profile.User;
import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Plan;

import com.tissue.commons.dao.social.EventDao;
import com.tissue.plan.dao.PlanDao;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.orientechnologies.orient.core.db.graph.OGraphDatabase;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.id.ORecordId;

@Component
public class PlanDaoImpl implements PlanDao {
    

    @Autowired
    private OrientDataSource dataSource;

    @Autowired
    private EventDao eventDao;

    public Plan create(Plan plan) {

        OGraphDatabase db = dataSource.getDB();
        try {
            ODocument doc = new ODocument("Plan");
            doc.field("duration", plan.getDuration());
            doc.field("createTime", plan.getCreateTime());

            ORecordId topicRecord = new ORecordId(OrientIdentityUtil.decode(plan.getTopic().getId()));
            doc.field("topic", topicRecord);

            ORecordId userRecord = new ORecordId(OrientIdentityUtil.decode(plan.getUser().getId()));
            doc.field("user", userRecord);
            doc.save();
            
            plan = PlanConverter.buildPlan(doc);

            /**
            //add this plan to related topic
            ODocument topicDoc = db.load(topicRecord);
            Set<ODocument> plans = topicDoc.field("plans", Set.class);
            if(plans == null) {
                plans = new HashSet();
            }
            plans.add(doc);
            topicDoc.field("plans", plans);
            topicDoc.save();
            */

            /**
            //add event to activity stream
            ODocument topicUserDoc = topicDoc.field("user");
            String topicUserRid = topicUserDoc.getIdentity().toString();

            Event event = new Event();
            event.setType("plan");
            event.setPublished(plan.getCreateTime());
            event.setActor(plan.getUser());

            Map<String, String> object = new HashMap();
            object.put("id", plan.getId());
            event.setObject(object);

            List<String> notifies = new ArrayList();
            notifies.add(topicUserRid);
            event.setNotifies(notifies);

            eventDao.addEvent(event);
            */
            
        }
        catch(Exception exc) {
            //to do
            exc.printStackTrace();
        }
        finally {
            db.close();
        }
        return plan;
    }

    public Plan getPlan(String planId) {
        Plan plan = null;

        OGraphDatabase db = dataSource.getDB();
        try {
            plan = getPlan(planId, db);
        }
        catch(Exception exc) {
             //to do
             exc.printStackTrace();
        }
        finally {
            db.close();
        }
        return plan;
    }

    public Plan getPlan(String planId, OGraphDatabase db) {

        ORecordId rid = new ORecordId(OrientIdentityUtil.decode(planId));
        ODocument planDoc = db.load(rid);

        Plan plan = PlanConverter.buildPlan(planDoc);
        return plan;

    }

    public List<Plan> getPlans(String topicId) {
        StringBuilder plansByTopicId = new StringBuilder("select from plan where topic = ");

        List<Plan> plans = new ArrayList();

        OGraphDatabase db = dataSource.getDB();
        try {
            plansByTopicId.append(OrientIdentityUtil.decode(topicId));
            System.out.println(plansByTopicId);

            OSQLSynchQuery query = new OSQLSynchQuery(plansByTopicId.toString());
            List<ODocument> result = db.query(query);

            for(ODocument doc : result) {
                Plan plan = new Plan();
                plan.setId(OrientIdentityUtil.encode(doc.getIdentity().toString()));

                Integer duration = doc.field("duration", Integer.class);
                Date createTime = doc.field("createTime", Date.class);

                ODocument userDoc = doc.field("user");
                String username = userDoc.field("username", String.class);
                User user = new User();
                user.setUsername(username);

                Topic topic = new Topic();
                topic.setId(topicId);

                plan.setDuration(duration);
                plan.setCreateTime(createTime);

                plan.setUser(user);
                plan.setTopic(topic);

                plans.add(plan);
            }
        }
        catch(Exception exc) {
            //to do
        }
        finally {
            db.close();
        }
        return plans;
    }

    public void addMember(String planId, String userId) {

        String sql = "update " + OrientIdentityUtil.decode(planId) + " add members = " + OrientIdentityUtil.decode(userId);
        /**
        StringBuilder qstr = new StringBuilder("update ");
        qstr.append(OrientIdentityUtil.decode(planId));
        qstr.append(" add members = ");
        qstr.append(OrientIdentityUtil.decode(userId));
        OCommandSQL cmd = new OCommandSQL(qstr.toString());
        */

        OGraphDatabase db = dataSource.getDB();
        try {
            OCommandSQL cmd = new OCommandSQL(sql);
            db.command(cmd).execute();
        }
        finally {
            db.close();
        }
    }










    public Plan getPlanMinium(String planId) {
        Plan result = null;

        /**
        DBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(planId));

        DBObject keys = new BasicDBObject();
        keys.put("createTime", 1);
        keys.put("username", 1);
        keys.put("duration", 1);
        keys.put("topic", 1);

        DBObject found = getCollection().findOne(query, keys);

        if(found != null) {
            result = mapper.convertValue(found, Plan.class); 
            result.setId(planId);
        }
        */

        return result;
    }

    /**
    public User updateUser(String userid, User user) {

        DBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(userid));

        DBObject obj = new BasicDBObject();

        DBObject updater = mapper.convertValue(user, BasicDBObject.class);
        updater.put("updated", new Date());

        obj.put("$set", updater);

        getCollection().update(query, obj, true, false);

        DBObject result = getCollection().findOne(query);

        return mapper.convertValue(result, UserImpl.class);
    }
    */

}
