package com.tissue.plan.service;

import com.tissue.domain.profile.User;
import com.tissue.domain.plan.Concept;
//import com.tissue.domain.plan.PostMessage;
import com.tissue.plan.dao.ConceptDao;
//import com.tissue.plan.dao.PostMessageDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Component
public class ConceptService {

    @Autowired
    private ConceptDao conceptDao;

    /**
    @Autowired
    private PostMessageDao postMessageDao;
    */

    /**
     * Save a concept.
     * 
     * @param concept
     * @return the newly added concept
     */
    public Concept addConcept(Concept concept) {
        concept = conceptDao.create(concept);
        return concept;
    }
}
