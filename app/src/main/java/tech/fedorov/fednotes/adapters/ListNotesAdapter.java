package tech.fedorov.fednotes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tech.fedorov.fednotes.R;
import tech.fedorov.fednotes.entities.Note;
import tech.fedorov.fednotes.listeners.NotesListener;

public class ListNotesAdapter extends RecyclerView.Adapter<ListNotesAdapter.NoteViewHolder> {

    private List<Note> listNotes;
    private NotesListener notesListener;

    public ListNotesAdapter(List<Note> listNotes, NotesListener notesListener) {
        this.listNotes = listNotes;
        this.notesListener = notesListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(
                        parent.getContext()).inflate(
                                R.layout.item_note,
                                parent,
                                false
                        )
                );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.setNote(listNotes.get(position));
        holder.layoutNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesListener.onNoteClicked(listNotes.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView inputField;
        TextView dateTime;
        ConstraintLayout layoutNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            inputField = itemView.findViewById(R.id.input_field);
            dateTime = itemView.findViewById(R.id.date_time);
            layoutNote = itemView.findViewById(R.id.layout_note);
        }

        void setNote(Note note) {
            title.setText(note.getTitle());
            inputField.setText(note.getNoteBody());
            dateTime.setText(note.getDateTime());
        }
    }
}
