package com.example.spor;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class FilmInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_info);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        filmsAdapter adapter = new filmsAdapter(getLayoutInflater(), MyHelper.New);
        recyclerView.setAdapter(adapter);
        Picasso.get().load("http://cinema.areas.su/up/images/magicians.png").placeholder(R.drawable.errimg).fit().into((ImageView)findViewById(R.id.imageView5));
    }
}