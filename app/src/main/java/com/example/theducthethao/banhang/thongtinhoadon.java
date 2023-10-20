package com.example.theducthethao.banhang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theducthethao.R;
import com.example.theducthethao.favorite.danhgiasanpham.vietcomment;

public class thongtinhoadon extends AppCompatActivity {
    private TextView idten, idtsdt, idkhoahoc, idthanhtoan;
    ImageButton savett,vectorttshow;

    String nameUser, emailUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinhoadon);

        idten = findViewById(R.id.idten);
        idten.setText(nameUser);
        idtsdt = findViewById(R.id.idtsdt);
        idtsdt.setText(emailUser);
        idkhoahoc = findViewById(R.id.idkhoahoc);
        idthanhtoan = findViewById(R.id.idthanhtoan);
        savett = findViewById(R.id.savett);

        vectorttshow = findViewById(R.id.vectorttshow);
        vectorttshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thongtinhoadon.this, DetailActivity.class);
                startActivity(intent);
            }
        });



        savett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thongtinhoadon.this, vietcomment.class);
                startActivity(intent);
            }
        });

        // Nhận dữ liệu từ Intent của Activity "Cửa hàng sản phẩm" (trangbanhang)

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("Title");
            String giaca = intent.getStringExtra("Giaca");
            String nameUser = intent.getStringExtra("name");
            String emailUser = intent.getStringExtra("email");


            // Gán dữ liệu vào các TextView
            idkhoahoc.setText(title);
            idthanhtoan.setText(giaca);
            idten.setText(nameUser);
            idtsdt.setText(emailUser);
        } else {
        // Hiển thị thông báo lỗi
    }


    }
}