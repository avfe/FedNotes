package tech.fedorov.fednotes.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import tech.fedorov.fednotes.R;

public class NoteActivity extends AppCompatActivity {
    private ImageButton backButton;
    private ImageButton doneButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        backButton = findViewById(R.id.back_button);
        doneButton = findViewById(R.id.done_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                finish();
            }
        });

    }

    private void save() {

    }
}
