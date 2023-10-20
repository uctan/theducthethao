package com.example.theducthethao.banhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theducthethao.R;
import com.example.theducthethao.trangchu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class trangbanhang extends AppCompatActivity {
    ImageButton carrt,vectortrangbanhang;
    RecyclerView recyclerView;
    List<Dataclass> dataList;
    List<Boolean> isFavoriteList; // Danh sách trạng thái yêu thích của các sản phẩm
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangbanhang);
        carrt = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(trangbanhang.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        vectortrangbanhang = findViewById(R.id.vectortrangbanhang);
        vectortrangbanhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(trangbanhang.this, trangchu.class);
                startActivity(intent);
            }
        });



        AlertDialog.Builder builder = new AlertDialog.Builder(trangbanhang.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();
        isFavoriteList = new ArrayList<>(); // Khởi tạo danh sách trạng thái yêu thích

        adapter = new MyAdapter(trangbanhang.this, dataList, isFavoriteList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
        dialog.show();

        eventListener =  databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                isFavoriteList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()){
                    Dataclass dataclass = itemSnapshot.getValue(Dataclass.class);
                    dataList.add(dataclass);
                    isFavoriteList.add(dataclass.isFavorite());
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);

                return true;
            }
        });

        carrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(trangbanhang.this, UploadActivity.class);
                startActivity(intent);
            }
        });

    }
    public  void searchList(String text){
        ArrayList<Dataclass> searchList = new ArrayList<>();
        for (Dataclass dataClass: dataList){
            if(dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}