package com.example.theducthethao.favorite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theducthethao.R;
import com.example.theducthethao.banhang.Dataclass;
import com.example.theducthethao.trangchu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class favorites extends AppCompatActivity {

    private RecyclerView favoriteRecyclerView;
    private List<Dataclass> favoriteList;
    private FavoriteAdapter favoriteAdapter;

    ImageButton closefvr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoriteRecyclerView = findViewById(R.id.recyclerViewFavourite);
        favoriteList = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(this, favoriteList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        favoriteRecyclerView.setLayoutManager(layoutManager);
        favoriteRecyclerView.setAdapter(favoriteAdapter);

        loadFavoriteListFromFirebase();

        closefvr = findViewById(R.id.closefvr);
        closefvr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(favorites.this, trangchu.class);
                startActivity(intent);
            }
        });
    }

    private void loadFavoriteListFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                favoriteList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Dataclass dataclass = itemSnapshot.getValue(Dataclass.class);
                    if (dataclass .isFavorite() && dataclass.isFavorite()) {
                        favoriteList.add(dataclass);
                    }
                }
                favoriteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled event
            }
        });
    }
}