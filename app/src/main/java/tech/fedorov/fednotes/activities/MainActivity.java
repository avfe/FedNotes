package tech.fedorov.fednotes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import tech.fedorov.fednotes.R;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.search_field);

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

    }
}