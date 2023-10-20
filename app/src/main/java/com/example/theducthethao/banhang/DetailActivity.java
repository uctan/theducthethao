package com.example.theducthethao.banhang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.theducthethao.R;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc,detailTitle,detailLang;
    ImageView detailImage;
    ImageButton savedt,close;
    String nameUser, emailUser;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailLang = findViewById(R.id.detailLang);
        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);
        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, trangbanhang.class);
                startActivity(intent);
            }
        });


        savedt = findViewById(R.id.savedt);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            detailLang.setText(bundle.getString("Giaca"));
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
        savedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this,thongtinhoadon.class);
                String title = detailTitle.getText().toString();
                String giaca = detailLang.getText().toString();
                intent.putExtra("name", nameUser);
                intent.putExtra("email", emailUser);
                // Put the data as extras in the Intent
                intent.putExtra("Title", title);
                intent.putExtra("Giaca", giaca);
                startActivity(intent);
            }
        });
    }
}