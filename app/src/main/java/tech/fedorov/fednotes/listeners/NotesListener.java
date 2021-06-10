package tech.fedorov.fednotes.listeners;

import tech.fedorov.fednotes.entities.Note;

public interface NotesListener {
    void onNoteClicked(Note note, int position);
}
