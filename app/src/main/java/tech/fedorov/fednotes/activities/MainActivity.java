package tech.fedorov.fednotes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import tech.fedorov.fednotes.R;
import tech.fedorov.fednotes.activities.NoteActivity;

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
    }
}