package com.example.paymentreminders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paymentreminders.Interface.LoggedUser;
import com.example.paymentreminders.Model.Users;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class LoginActivity extends MiddlewareClass {

    TextView login;
    TextInputEditText phoneNo, password;
    String s_phoneNo, s_password;
    Button but;
    androidx.appcompat.widget.AppCompatImageView imgView;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        but = findViewById(R.id.login_button);
        login = findViewById(R.id.register_in_login);
        phoneNo = findViewById(R.id.edit_login_phone);
        password = findViewById(R.id.edit_login_password);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        login.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        but.setOnClickListener(v -> {
            if (phoneNo.getText().toString().isEmpty())
                Toast.makeText(this, "Would you care to enter phone number", Toast.LENGTH_SHORT).show();
            else if (password.getText().toString().isEmpty())
                Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show();
            else {
                s_password = password.getText().toString();
                s_phoneNo = phoneNo.getText().toString();


                Query query = FirebaseDatabase.getInstance().getReference("users")
                        .orderByChild("phoneNo")
                        .equalTo(s_phoneNo);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Users users = dataSnapshot1.getValue(Users.class);
                                if (users.getPassword().isEmpty())
                                    Log.e("phone firrbase", "no phone");
                                else
                                    Log.e("firebase password", users.getPassword());
                                if (s_password.equals(users.getPassword())) {
                                    LoggedUser.currentItem = users;
                                    Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                                    startActivity(intent);
                                } else {
                                    Log.e("typed password", s_password);

                                    Toast.makeText(LoginActivity.this, "Password is wrong -_-", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else {
                            Toast.makeText(LoginActivity.this, "Sorry Username doesn't exist", Toast.LENGTH_SHORT).show();
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
