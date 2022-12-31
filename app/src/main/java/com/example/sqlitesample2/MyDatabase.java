package com.example.sqlitesample2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "genre_db";
    private static final int DB_VERSION = 1;

    private final String TBL_GENRE = "tbl_genre";

    public MyDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TBL_GENRE + "(genre_id INTEGER PRIMARY KEY AUTOINCREMENT, genre_name TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    boolean addGenre(String genreName) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("genre_name", genreName);
        try {
            database.insert(TBL_GENRE, null, cv);
            database.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            database.close();
            return false;
        }
    }

    @SuppressLint("Range")
    ArrayList<GenreModel> getGenreModelList() {
        ArrayList<GenreModel> genreModelArrayList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TBL_GENRE, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int genreId = cursor.getInt(cursor.getColumnIndex("genre_id"));
                String genreName = cursor.getString(cursor.getColumnIndex("genre_name"));
                GenreModel model = new GenreModel(genreId, genreName);
                genreModelArrayList.add(model);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return genreModelArrayList;
    }

    boolean deleteGenre(int genreId) {
        SQLiteDatabase database = getWritableDatabase();
        try {
            database.delete(TBL_GENRE, "genre_id=" + genreId, null);
            database.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            database.close();
            return false;
        }
    }

    boolean updateGenre(int genreId, String updateValue) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("genre_name", updateValue);
        try {
            database.update(TBL_GENRE, cv, "genre_id=" + genreId, null);
            database.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            database.close();
            return false;
        }
    }
}
