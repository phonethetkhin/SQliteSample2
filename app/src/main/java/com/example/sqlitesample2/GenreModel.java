package com.example.sqlitesample2;

public class GenreModel {
    private int genreId;
    private String genreName;

    public GenreModel(int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getGenreName() {
        return genreName;
    }
}
