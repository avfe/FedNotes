package tech.fedorov.fednotes.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import tech.fedorov.fednotes.R;
import tech.fedorov.fednotes.database.NotesDB;
import tech.fedorov.fednotes.entities.Note;

public class NoteActivity extends AppCompatActivity {
    private ImageButton backButton;
    private ImageButton doneButton;
    private EditText title;
    private EditText inputField;
    private TextView dateTime;

    private Note alreadyAvailableNote;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        backButton = findViewById(R.id.back_button);
        doneButton = findViewById(R.id.done_button);
        title = findViewById(R.id.title);
        inputField = findViewById(R.id.input_field);
        dateTime = findViewById(R.id.date_time);

        dateTime.setText(getCurrentTime());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        if (getIntent().getBooleanExtra("isViewOrUpdate", false)) {
            alreadyAvailableNote = (Note) getIntent().getSerializableExtra("note");
            setViewOrUpdateNote();
        } else if (savedInstanceState != null && savedInstanceState.containsKey("alreadyAvailableNote")) {
            alreadyAvailableNote = (Note) savedInstanceState.getSerializable("alreadyAvailableNote");
            setViewOrUpdateNote();
        }

    }

    private void setViewOrUpdateNote() {
        title.setText(alreadyAvailableNote.getTitle());
        inputField.setText(alreadyAvailableNote.getNoteBody());
        dateTime.setText(alreadyAvailableNote.getDateTime());

    }

    private void saveNote() {
        if (title.getText().toString().trim().isEmpty()) {
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.title_cant_be_empty),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (inputField.getText().toString().trim().isEmpty()) {
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.note_cant_be_empty),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        final Note note = new Note();
        note.setDateTime(dateTime.getText().toString());
        note.setTitle(title.getText().toString());
        note.setNoteBody(inputField.getText().toString());

        if (alreadyAvailableNote != null) {
            note.setId(alreadyAvailableNote.getId());
        }

        @SuppressLint("StaticFieldLeak")
        class SaveNote extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                NotesDB.getDatabase(getApplicationContext()).noteDAO().insert(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }

        new SaveNote().execute();
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd.MM.yyyy HH:mm:ss", Locale.getDefault());
        String currentDateTime = sdf.format(new Date());
        return currentDateTime;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (alreadyAvailableNote != null) {
            outState.putSerializable("alreadyAvailableNote", alreadyAvailableNote);
        }
    }
}
