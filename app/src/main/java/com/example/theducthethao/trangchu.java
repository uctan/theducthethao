package com.example.theducthethao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.theducthethao.Post.AddPostActivity;
import com.example.theducthethao.banhang.trangbanhang;
import com.example.theducthethao.favorite.danhgiasanpham.showdanhgia;
import com.example.theducthethao.favorite.favorites;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class trangchu extends AppCompatActivity {
    ImageButton canhan,yeuthich,theloai1,theloai4,lichsu;
    String nameUser, emailUser, usernameUser, passwordUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);

        theloai4 = findViewById(R.id.theloai4);
        theloai4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(trangchu.this, AddPostActivity.class);
                startActivity(intent);
            }
        });
        lichsu = findViewById(R.id.lichsu);
        lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(trangchu.this, showdanhgia.class);
                startActivity(intent);
            }
        });

        theloai1 = findViewById(R.id.theloai1);
        MaterialToolbar toolbar = findViewById(R.id.topAppbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        yeuthich = findViewById(R.id.yeuthich);
        yeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(trangchu.this, favorites.class);
                startActivity(intent);
            }
        });
      theloai1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(trangchu.this, trangbanhang.class);
              startActivity(intent);
          }
      });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
                View headerView = navigationView.getHeaderView(0);
                TextView usernameTextView = headerView.findViewById(R.id.username);
                TextView usernameIdTextView = headerView.findViewById(R.id.usernameid);

                // Đặt giá trị tên người dùng và tên người dùng ID vào các TextView
                usernameTextView.setText(nameUser);
                usernameIdTextView.setText(usernameUser);
            }
        });

        // Các tác vụ khác trong onCreate (nếu có)

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);


                if (id == R.id.nav_cn) {
                    Toast.makeText(trangchu.this, "Bạn đã vào trang cá nhân", Toast.LENGTH_SHORT).show();
                    // Chuyển hướng tới trang cá nhân ở đây nếu cần thiết
                    Intent intent = new Intent(trangchu.this, profile.class);
                    intent.putExtra("name", nameUser);
                    intent.putExtra("email", emailUser);
                    intent.putExtra("username", usernameUser);
                    intent.putExtra("password", passwordUser);
                    startActivity(intent);
                } else if (id == R.id.nav_home) {
                    Toast.makeText(trangchu.this, "Bạn đã vào trang chủ", Toast.LENGTH_SHORT).show();
                    // Không nên chuyển hướng đến trang chủ của chính activity trangchu nếu đã ở đó rồi
                    // Để lại màn hình hiện tại không thay đổi
                }else if (id == R.id.nav_cn) {
                    Toast.makeText(trangchu.this, "Bạn đã đăng xuất", Toast.LENGTH_SHORT).show();
                    // Chuyển hướng tới trang cá nhân ở đây nếu cần thiết
                    Intent intent = new Intent(trangchu.this, dangnhap.class);
                    startActivity(intent);
                }else if (id == R.id.nav_yt) {
                    Toast.makeText(trangchu.this, "Bạn đã vào trang yếu thích", Toast.LENGTH_SHORT).show();
                    // Chuyển hướng tới trang cá nhân ở đây nếu cần thiết
                    Intent intent = new Intent(trangchu.this, favorites.class);
                    startActivity(intent);
                }
                   else  if (id == R.id.nav_lichsu){
                    Toast.makeText(trangchu.this, "Bạn đã vào trang yếu thích", Toast.LENGTH_SHORT).show();
                    // Chuyển hướng tới trang cá nhân ở đây nếu cần thiết
                    Intent intent = new Intent(trangchu.this, showdanhgia.class);
                    startActivity(intent);
                }

                return true;
            }
        });

        canhan = findViewById(R.id.canhan);

        canhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(trangchu.this, profile.class);
                intent.putExtra("name", nameUser);
                intent.putExtra("email", emailUser);
                intent.putExtra("username", usernameUser);
                intent.putExtra("password", passwordUser);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            nameUser = intent.getStringExtra("name");
            emailUser = intent.getStringExtra("email");
            usernameUser = intent.getStringExtra("username");
            passwordUser = intent.getStringExtra("password");
        }
    }
}