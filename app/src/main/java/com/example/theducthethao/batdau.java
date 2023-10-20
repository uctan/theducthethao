package com.example.theducthethao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class batdau extends AppCompatActivity {
    ImageButton btnbatdau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batdau);

        btnbatdau = findViewById(R.id.btnbatdau);
        btnbatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(batdau.this, gioithieu.class);
                startActivity(intent);
            }
        });
    }
}