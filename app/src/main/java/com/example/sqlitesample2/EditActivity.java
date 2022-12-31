package com.example.sqlitesample2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    EditText etGenre;
    Button btUpdateGenre;
    MyDatabase myDatabase;

    int genreId;
    String genreName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initViews();
        myDatabase = new MyDatabase(this);

        genreId = getIntent().getIntExtra("genreId", 0);
        genreName = getIntent().getStringExtra("genreName");
        etGenre.setText(genreName);

        handleClicks();

    }


    private void initViews() {
        etGenre = findViewById(R.id.etGenreName);
        btUpdateGenre = findViewById(R.id.btUpdate);
    }

    private void handleClicks() {
        btUpdateGenre.setOnClickListener(view -> {
            if (myDatabase.updateGenre(genreId, etGenre.getText().toString())) {
                Utils.showToast(this, "Genre Updated Successfully");
                finish();
            } else {
                Utils.showToast(this, "Something went wrong, please try again.");
            }
        });
    }
}
