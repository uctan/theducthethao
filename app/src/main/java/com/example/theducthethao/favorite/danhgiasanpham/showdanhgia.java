package com.example.theducthethao.favorite.danhgiasanpham;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

public class showdanhgia extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterReviews adapter;
    private List<ReviewItem> reviewItems = new ArrayList<>();
    ImageButton closecmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdanhgia);

        // Khởi tạo RecyclerView và cấu hình LayoutManager
        recyclerView = findViewById(R.id.recyclerViewdanhgia);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reviewItems = getReviewItems();

        // Khởi tạo Adapter và gắn với RecyclerView
        adapter = new AdapterReviews(reviewItems);
        recyclerView.setAdapter(adapter);

        closecmt = findViewById(R.id.closecmt);
        closecmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(showdanhgia.this, trangchu.class);
                startActivity(intent);
            }
        });
    }

    // Phương thức mẫu để lấy dữ liệu đánh giá
    private List<ReviewItem> getReviewItems() {
        List<ReviewItem> items = new ArrayList<>();
        // Thêm các đánh giá vào danh sách items
        DatabaseReference reviewReference = FirebaseDatabase.getInstance().getReference().child("Message");
        reviewReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                items.clear(); // Xóa dữ liệu cũ trước khi thêm mới
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    float rating = snapshot.child("rating").getValue(Float.class);
                    String review = snapshot.child("review").getValue(String.class);
                    items.add(new ReviewItem(rating, review));
                }
                adapter.notifyDataSetChanged(); // Thông báo cho Adapter cập nhật hiển thị
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return items;
    }
}