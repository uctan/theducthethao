package com.example.theducthethao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Editprofile extends AppCompatActivity {
    EditText editName,editEmail,editUsername,editPassword;
    ImageButton saveButton;
    String nameUser,emailUser,usernameUser,passwordUser;
    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        saveButton = findViewById(R.id.saveButton);
        showData();;

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged() || isEmailChanged() || isPasswordChanged()){
                    Toast.makeText(Editprofile.this,"Lưu thông tin",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Editprofile.this, profile.class);
                    intent.putExtra("name", editName.getText().toString());
                    intent.putExtra("email", editEmail.getText().toString());
                    intent.putExtra("username", editUsername.getText().toString());
                    intent.putExtra("password", editPassword.getText().toString());
                    startActivity(intent);
                }else {
                    Toast.makeText(Editprofile.this,"Không thay đổi thông tin",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public  boolean isNameChanged()
    {
        if (!nameUser.equals(editName.getText().toString())){
            reference.child(usernameUser).child("name").setValue(editName.getText().toString());
            nameUser = editName.getText().toString();
            return  true;
        }else{
            return  false;
        }
    }
    public boolean isEmailChanged() {
        if (!emailUser.equals(editEmail.getText().toString())) {
            reference.child(usernameUser).child("email").setValue(editEmail.getText().toString());
            emailUser = editEmail.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    public  boolean isPasswordChanged()
    {
        if (!passwordUser.equals(editPassword.getText().toString())){
            reference.child(passwordUser).child("password").setValue(editPassword.getText().toString());
            passwordUser = editPassword.getText().toString();
            return  true;
        }else{
            return  false;
        }
    }
    public  void showData()
    {
        Intent intent = getIntent();

        nameUser = intent.getStringExtra("name");
        emailUser = intent.getStringExtra("email");
        usernameUser = intent.getStringExtra("username");
        passwordUser = intent.getStringExtra("password");

        editName.setText(nameUser);
        editEmail.setText(emailUser);
        editUsername.setText(usernameUser);
        editPassword.setText(passwordUser);
    }
}