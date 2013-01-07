package com.tissue.plan.dao;

import com.tissue.domain.plan.Question;
import java.util.List;

public interface QuestionDao {

    /**
     * Add a question.
     */
    Question create(Question question);


}
