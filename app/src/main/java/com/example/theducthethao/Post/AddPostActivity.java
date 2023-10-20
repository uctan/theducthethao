package com.example.theducthethao.Post;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theducthethao.R;
import com.example.theducthethao.trangchu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddPostActivity extends AppCompatActivity {
    ImageButton vectortrangpost,addpost;
    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    List<ModelPost> postList;
    AdapterPosts adapterPosts;

    SearchView searchpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        searchpost = findViewById(R.id.searchpost);
        searchpost.clearFocus();

        recyclerView = findViewById(R.id.postsRecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setLayoutManager(layoutManager);

        postList = new ArrayList<>();
        adapterPosts = new AdapterPosts(this, postList);
        recyclerView.setAdapter(adapterPosts);

        loadPosts();

        addpost = findViewById(R.id.addpost);
        addpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPostActivity.this, UploadPost.class);
                startActivity(intent);
            }
        });

        vectortrangpost = findViewById(R.id.vectortrangpost);
        vectortrangpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPostActivity.this, trangchu.class);
                startActivity(intent);
            }
        });

        searchpost.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchListpost(newText);
                return false;
            }
        });
    }

    private void loadPosts() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    ModelPost modelPost = ds.getValue(ModelPost.class);
                    postList.add(modelPost);
                }
                adapterPosts.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddPostActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchListpost(String text) {
        ArrayList<ModelPost> searchList = new ArrayList<>();
        for (ModelPost modelPost : postList){
            if (modelPost.getpTitle().toLowerCase().contains(text.toLowerCase())){
                searchList.add(modelPost);
            }
        }
        adapterPosts.searchDataList(searchList);
    }

}