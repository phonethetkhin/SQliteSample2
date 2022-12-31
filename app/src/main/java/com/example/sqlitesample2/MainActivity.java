package com.example.sqlitesample2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    EditText etGenre;
    Button btAddGenre;
    RecyclerView rvGenre;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = new MyDatabase(this);
        initViews();
        handleClicks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();
    }

    private void initViews() {
        etGenre = findViewById(R.id.etGenre);
        btAddGenre = findViewById(R.id.btAddGenre);
        rvGenre = findViewById(R.id.rvGenre);
    }

    private void setAdapter() {
        GenreAdapter genreAdapter = new GenreAdapter(myDatabase.getGenreModelList(), myDatabase);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        rvGenre.addItemDecoration(dividerItemDecoration);
        rvGenre.setAdapter(genreAdapter);
    }


    private void handleClicks() {
        btAddGenre.setOnClickListener(view -> {
            if (myDatabase.addGenre(etGenre.getText().toString())) {
                Utils.showToast(this, "Genre Added Successfully");
                setAdapter();
                etGenre.setText("");
            } else {
                Utils.showToast(this, "Something went wrong, please try again.");
            }
        });
    }

}