package com.example.theducthethao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class gioithieu extends AppCompatActivity {
    ImageButton btndangnhap,btndangdangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioithieu);

        btndangnhap = findViewById(R.id.btndangnhap);
        btndangdangky = findViewById(R.id.btndangdangky);

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gioithieu.this,dangnhap.class);
                startActivity(intent);
            }
        });
        btndangdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gioithieu.this,dangky.class);
                startActivity(intent);
            }
        });
    }
}