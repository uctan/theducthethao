package com.example.theducthethao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theducthethao.dangkydangnhap.HelperClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dangky extends AppCompatActivity {
    EditText signupName,signupEmail,signupUsername,signupPassword;

    ImageButton siggnupButton;

    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        siggnupButton = findViewById(R.id.signup_button);

        siggnupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();

                HelperClass helperClass = new HelperClass(name,email,username,password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(dangky.this,"Bạn đã đăng ký thành công",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(dangky.this, dangnhap.class);
                startActivity(intent);
            }
        });
    }
}