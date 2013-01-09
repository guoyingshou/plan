package com.tissue.plan.service;

import com.tissue.core.profile.User;
import com.tissue.core.plan.Note;
import com.tissue.core.plan.dao.NoteDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Component
public class NoteService {

    @Autowired
    private NoteDao noteDao;

    /**
    @Autowired
    private PostMessageDao postMessageDao;
    */

    /**
     * Save a note.
     * 
     * @param note
     * @return the newly added note
     */
    public Note addNote(Note note) {
        note = noteDao.create(note);
        return note;
    }
}
