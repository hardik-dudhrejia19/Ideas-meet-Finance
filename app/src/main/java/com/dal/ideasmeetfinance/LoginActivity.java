package com.dal.ideasmeetfinance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login, register;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {

                    email.setError("Please fill in your email ID");
                    password.setError("Please fill in your password");
                } else {

                    login(email.getText().toString(), password.getText().toString());
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Registration.class);
                startActivity(i);
            }
        });


    }

    protected void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Toast.makeText(LoginActivity.this, "Authentication Success.",
//                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userid = user.getUid();

                            mDatabase.orderByChild("userId").equalTo(user.getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot childData : dataSnapshot.getChildren()) {
                                        //entrepreneur login
                                        if ((childData.child("entrepreneur").getValue().toString() == "true") || (childData.child("financer").getValue().toString() == "false")) {
                                            Intent i = new Intent(LoginActivity.this, UserHomeActivity.class);
                                            startActivity(i);
                                            SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.putString("UserName", childData.child("username").getValue().toString());
                                            ed.putString("UserId",childData.child("userId").getValue().toString());
                                            ed.putString("ScreeName",childData.child("name").getValue().toString());
                                            ed.putString("Financer",childData.child("financer").getValue().toString());
                                            ed.putString("Decider",childData.child("descision").getValue().toString());

                                            ed.apply();
                                            finish();
                                        }
                                        //financer login
                                        else if ((childData.child("entrepreneur").getValue().toString() == "false") || (childData.child("financer").getValue().toString() == "true")) {
                                            Intent i = new Intent(LoginActivity.this, FinancerHomeActivity.class);
                                            //Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                            startActivity(i);

                                            SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                                            SharedPreferences.Editor ed = sp.edit();
                                            ed.putString("UserName", childData.child("username").getValue().toString());
                                            ed.putString("UserId",childData.child("userId").getValue().toString());
                                            ed.putString("ScreeName",childData.child("name").getValue().toString());
                                            ed.putString("Financer",childData.child("financer").getValue().toString());
                                            ed.putString("Decider",childData.child("descision").getValue().toString());

                                            ed.apply();
                                            finish();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


}
