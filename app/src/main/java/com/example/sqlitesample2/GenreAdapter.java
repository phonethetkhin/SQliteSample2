package com.example.sqlitesample2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private ArrayList<GenreModel> genreList;
    private MyDatabase myDatabase;

    public GenreAdapter(ArrayList<GenreModel> genreList, MyDatabase myDatabase) {
        this.genreList = genreList;
        this.myDatabase = myDatabase;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_genre, parent, false);
        return new GenreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        holder.tvGenreName.setText(genreList.get(position).getGenreName());

        holder.ivDelete.setOnClickListener(view -> {
            myDatabase.deleteGenre(genreList.get(position).getGenreId());
            genreList.remove(genreList.get(position));
            notifyDataSetChanged();
        });
        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(holder.itemView.getContext(), EditActivity.class);
            i.putExtra("genreId", genreList.get(position).getGenreId());
            i.putExtra("genreName", genreList.get(position).getGenreName());
            holder.itemView.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public class GenreViewHolder extends RecyclerView.ViewHolder {
        TextView tvGenreName;
        ImageView ivDelete;

        public GenreViewHolder(@NonNull View v) {
            super(v);
            tvGenreName = v.findViewById(R.id.tvGenreName);
            ivDelete = v.findViewById(R.id.ivDelete);
        }
    }
}
