package tech.fedorov.fednotes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tech.fedorov.fednotes.R;
import tech.fedorov.fednotes.entities.Note;

public class ListNotesAdapter extends RecyclerView.Adapter<ListNotesAdapter.NoteViewHolder> {

    private List<Note> listNotes;

    public ListNotesAdapter(List<Note> listNotes) {
        this.listNotes = listNotes;
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
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView inputField;
        TextView dateTime;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            inputField = itemView.findViewById(R.id.input_field);
            dateTime = itemView.findViewById(R.id.date_time);
        }

        void setNote(Note note) {
            title.setText(note.getTitle());
            inputField.setText(note.getNoteBody());
            dateTime.setText(note.getDateTime());
        }
    }
}
