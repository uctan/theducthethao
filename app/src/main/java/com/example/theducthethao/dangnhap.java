package com.example.theducthethao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.theducthethao.banhang.thongtinhoadon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class dangnhap extends AppCompatActivity {
    ImageButton btndangdangky,login_button;

    EditText loginUsername,loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);

        login_button =findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateUsername() | !validatePassword())
                {
                    checkUser();
                }
                else
                {
                    checkUser();
                }
            }
        });
        btndangdangky = findViewById(R.id.btndangdangky);
        btndangdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dangnhap.this,dangky.class);
                startActivity(intent);
            }
        });
    }
    public  Boolean validateUsername(){
        String val = loginUsername.getText().toString();
        if (val.isEmpty()){
            loginUsername.setError("Tên không được để trống");
            return  false;
        }else {
            loginUsername.setError(null);
            return  true;
        }
    }
    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()){
            loginPassword.setError("Mật khẩu không được để trống");
            return  false;
        }else {
            loginPassword.setError(null);
            return  true;
        }
    }
    public  void checkUser()
    {
        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    loginUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userPassword)){
                        loginUsername.setError(null);
                        //chuyendulieu qua  profile
                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);

                        Intent intenttt = new Intent(dangnhap.this, thongtinhoadon.class);
                        intenttt.putExtra("name",nameFromDB);
                        intenttt.putExtra("email",emailFromDB);

                        startActivity(intenttt);

                        Intent intent = new Intent(dangnhap.this, trangchu.class);
                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("password",passwordFromDB);

                        startActivity(intent);


                    }else {
                        loginPassword.setError("mật khẩu không đúng");
                        loginPassword.requestFocus();
                    }
                }else {
                    loginUsername.setError("tên đăng nhập không tồn tại");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}