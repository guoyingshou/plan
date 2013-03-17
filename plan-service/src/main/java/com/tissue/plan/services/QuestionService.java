package com.tissue.plan.services;

import com.tissue.core.dao.CommonDao;
import com.tissue.core.command.QuestionCommand;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Question;
import com.tissue.core.plan.dao.TopicDao;
import com.tissue.core.plan.dao.QuestionDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Component
public class QuestionService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private QuestionDao questionDao;

    /**
     * @param postCommand 
     * @return id of the newly created post
     */
    public String addQuestion(QuestionCommand command) {
        return questionDao.create(command);
    }

    /**

    public void updateQuestion(QuestionCommand command) {
        questionDao.update(command);
    }
    */

    /**
     * @param postId postId
     * @return topic id of the containing topic of the deleted post
     */
    public void deleteQuestion(String questionId) {
        commonDao.delete(questionId);
    }

    /**
     * Get a specific post.
     */
    public Question getQuestion(String questionId) {
        return questionDao.getQuestion(questionId);
    }

    public Topic getTopic(String questionId) {
         return topicDao.getTopicByPost(questionId);
    }
}
