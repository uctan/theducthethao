package com.example.theducthethao.favorite.danhgiasanpham;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theducthethao.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class vietcomment extends AppCompatActivity {
    ImageButton cmt;
    RatingBar ratingStarts;
    EditText messagedata;
    float myRating = 0;

    private DatabaseReference messageReference; // Tham chiếu đến "Message"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vietcomment);

        // Đánh dấu số sao
        cmt = findViewById(R.id.cmt);
        ratingStarts = findViewById(R.id.ratingBar);

        // Viết feedback
        messagedata = findViewById(R.id.messagedata);

        // Khởi tạo Firebase Database


        // Khởi tạo tham chiếu đến "Message"
        messageReference = FirebaseDatabase.getInstance().getReference().child("Message");

        ratingStarts.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                myRating = v;
                Toast.makeText(vietcomment.this, "Selected rating: " + myRating, Toast.LENGTH_SHORT).show();
            }
        });

        cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messagedata.getText().toString();

                if (message.isEmpty()) {
                    messagedata.setError("Bạn nên điền vào");
                    cmt.setEnabled(false);
                } else {
                    messagedata.setError(null);
                    cmt.setEnabled(true);

                    // Thực hiện lưu tin nhắn vào Firebase
                    String key = messageReference.push().getKey(); // Tạo khóa duy nhất
                    ReviewItem reviewItem = new ReviewItem(myRating, message);
                    messageReference.child(key).setValue(reviewItem);

                    // Chuyển sang trang showdanhgia và gửi dữ liệu đánh giá
                    Intent intent = new Intent(vietcomment.this, showdanhgia.class);
                    intent.putExtra("rating", myRating);
                    intent.putExtra("review", message);
                    startActivity(intent);
                }
            }
        });
    }
}