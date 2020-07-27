package com.example.paymentreminders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paymentreminders.Interface.LoggedUser;
import com.example.paymentreminders.Model.Users;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class RegisterActivity extends MiddlewareClass {

    ImageView uploadPic, goBack;
    TextView login;
    Button but;
    TextInputEditText phone, email, username, password;
    String s_phone, s_email, s_username, s_password;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        but = findViewById(R.id.reg_button);
        uploadPic = findViewById(R.id.reg_logo_img);
        goBack = findViewById(R.id.back_arrow);
        login = findViewById(R.id.login_in_register);
        phone = findViewById(R.id.edit_phone);
        email = findViewById(R.id.edit_email);
        username = findViewById(R.id.edit_username);
        password = findViewById(R.id.edit_password);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        goBack.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        if (picasso == null)
            Toast.makeText(this, "bhai null ku hbej raha", Toast.LENGTH_SHORT).show();
        else {
            picasso.load(R.drawable.back_arrow).into(goBack);
            picasso.load(R.drawable.icon_upload_picture).into(uploadPic);
        }


        but.setOnClickListener(v -> {
            if (username.getText().toString().isEmpty()) {
                Toast.makeText(this, "Would you care to write your username first?!?", Toast.LENGTH_SHORT).show();
                username.setError("Enter Username");
                username.requestFocus();

            } else if (email.getText().toString().isEmpty())
                Toast.makeText(this, "Would you care to write your email first?!?", Toast.LENGTH_SHORT).show();
            else if (phone.getText().toString().isEmpty())
                Toast.makeText(this, "Would you care to write your phone first?!?", Toast.LENGTH_SHORT).show();
            else if (password.getText().toString().isEmpty())
                Toast.makeText(this, "Would you care to write your password first?!?", Toast.LENGTH_SHORT).show();
            else if (password.getText().toString().length() < 8) {
                Toast.makeText(this, "Does it hurt to write more than 8 characters? eh?!?", Toast.LENGTH_SHORT).show();
            } else if (phone.getText().toString().length() != 11) {
                Toast.makeText(this, "Does it hurt to write correct phone number? eh?!?", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                email.setError("Enter valid email");
                email.requestFocus();
            } else {


//                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString());

                Query query = FirebaseDatabase.getInstance().getReference("users")
                        .orderByChild("phoneNo")
                        .equalTo(phone.getText().toString());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            Toast.makeText(RegisterActivity.this, "This phone number already exists.", Toast.LENGTH_SHORT).show();

                        } else {
                            Users users = new Users(username.getText().toString(), phone.getText().toString(), password.getText().toString(), email.getText().toString());
                            databaseReference.child("users").push().setValue(users);
                            LoggedUser.currentItem = users;
                            Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });


            }


        });


    }


}
