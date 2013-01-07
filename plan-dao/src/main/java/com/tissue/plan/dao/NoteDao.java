package com.tissue.plan.dao;

import com.tissue.domain.plan.Note;
import java.util.List;

public interface NoteDao {

    /**
     * Add a note.
     */
    Note create(Note note);


}
