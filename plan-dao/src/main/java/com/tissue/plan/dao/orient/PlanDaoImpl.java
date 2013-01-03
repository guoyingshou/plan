package com.tissue.plan.dao.orient;

import com.tissue.core.util.OrientDataSource;
import com.tissue.core.util.OrientIdentityUtil;
import com.tissue.core.converter.PlanConverter;

import com.tissue.domain.social.Event;
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
            ODocument doc = PlanConverter.convertPlan(plan);
            doc.save();

            String ridPlan = doc.getIdentity().toString();
            String ridUser = OrientIdentityUtil.decode(plan.getUser().getId());

            String sql = "create edge Publish from " + ridUser + " to " + ridPlan + " set label = 'plan', createTime = sysdate()";
            OCommandSQL cmd = new OCommandSQL(sql);
            db.command(cmd).execute(ridUser, ridPlan);

            //plan = PlanConverter.buildPlan(doc);

            String planId = OrientIdentityUtil.encode(ridPlan);
            plan.setId(planId);

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
                Plan plan = PlanConverter.buildPlan(doc);
                plans.add(plan);
            }
        }
        catch(Exception exc) {
            exc.printStackTrace();
            //to do
        }
        finally {
            db.close();
        }
        return plans;
    }

    public void addMember(String planId, String userId) {

        String ridUser = OrientIdentityUtil.decode(userId);
        String ridPlan = OrientIdentityUtil.decode(planId);

        String sql = "create edge member from " + ridUser + " to " + ridPlan + " set label='member', createTime=sysdate()";
        String sqlUpdate = "update " + ridPlan + " increment count = 1";

        OGraphDatabase db = dataSource.getDB();
        try {
            OCommandSQL cmd = new OCommandSQL(sql);
            db.command(cmd).execute();

            //workaround for not being able to order by members.size()
            OCommandSQL cmdUpdate = new OCommandSQL(sqlUpdate);
            db.command(cmdUpdate).execute();
        }
        finally {
            db.close();
        }
    }

}
