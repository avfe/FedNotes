package tech.fedorov.fednotes.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import tech.fedorov.fednotes.R;
import tech.fedorov.fednotes.database.NotesDB;
import tech.fedorov.fednotes.entities.Note;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private ImageView addButton;

    public static final int CODE_ADD_NOTE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.search_field);
        addButton = findViewById(R.id.add_button);

        searchField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    searchField.setHint(getString(R.string.empty));
                } else {
                    searchField.setHint(getString(R.string.search_notes));
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noteIntent = new Intent(getApplicationContext(), NoteActivity.class);
                startActivityForResult(noteIntent, CODE_ADD_NOTE);
            }
        });

        getNotes();
    }

    private void getNotes() {

        @SuppressLint("StaticFieldLeak")
        class GetNotes extends AsyncTask<Void, Void, List<Note>> {

            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NotesDB.getDatabase(getApplicationContext()).noteDAO().getNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
            }
        }

        new GetNotes().execute();
    }
}