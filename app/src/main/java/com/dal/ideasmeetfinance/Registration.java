package com.dal.ideasmeetfinance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dal.ideasmeetfinance.pojo.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    private EditText name, email, password, dalId;
    RadioButton student, professor;
    Button register;
    ImageView imgView;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    boolean studnt, prof;
    static int PreqCode = 1;
    //  static int REQCODE = 1;
    static int camCode = 0;
    static int galCode = 1;
    String photo;
    Uri selectedImage;
    private static final String LOG_TAG = "RegisterAct";
    String userID;
    Boolean ui, setImage = false;
    RadioGroup radioGroup;
    int radiogrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        dalId = findViewById(R.id.dalId);
        student = findViewById(R.id.student);
        professor = findViewById(R.id.professor);
        register = findViewById(R.id.btnregister);
        radioGroup = findViewById(R.id.radioBtnGrp);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        student.setChecked(true);


        //OnClick listener method for register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ui = false;
                radiogrp = radioGroup.getCheckedRadioButtonId();

                // Edittext validation
                if (name.getText().toString().isEmpty()) {
                    name.setError("Please enter your name");
                    ui = true;

                }
                if (email.getText().toString().isEmpty()) {
                    email.setError("Please enter your E-Mail ID");
                    ui = true;

                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("Please enter the password");
                    ui = true;
                }
                if (dalId.getText().toString().isEmpty()) {
                    dalId.setError("Please enter your dalhousie ID");
                    ui = true;
                }

                if (!ui) {
                    //Registering as student
                    if (student.isChecked()) {
                        System.out.println("student checked");
                        studnt = student.isChecked();
                        prof = professor.isChecked();
                        System.out.println("student button" + studnt);
                        System.out.println("prof button" + prof);
                        //    System.out.println("stud photo string is " + photo);
                        //   System.out.println("imageview" + imgView.getDrawable());
                        firebaseAuthInsertion(email.getText().toString(), password.getText().toString(), dalId.getText().toString(),
                                name.getText().toString(), studnt, prof);
                    }
                    //Registering as professor
                    if (professor.isChecked()) {
                        System.out.println("student checked");
                        studnt = student.isChecked();
                        prof = professor.isChecked();
                        System.out.println("student button" + studnt);
                        System.out.println("prof button" + prof);
                        firebaseAuthInsertion(email.getText().toString(), password.getText().toString(), dalId.getText().toString(),
                                name.getText().toString(), studnt, prof);
                    }

                }
            }
        });
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }
    protected void firebaseAuthInsertion(final String mail, String password, final String username,
                                         final String name, final boolean financer, final boolean entrepreneur) {
        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {



                            // Sign in success, update UI with the signed-in user's information
                            System.out.println("success");
                            //  Log.d(LOG_TAG,"TAG",+ task.isSuccessful());
                            FirebaseUser user = mAuth.getCurrentUser();
                            userID = mAuth.getUid();
                            SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                            SharedPreferences.Editor ed = sp.edit();
                            ed.putString("UserName", username);
                            ed.putString("UserId",userID);
                            ed.putString("ScreeName",name);
                            ed.apply();
                            System.out.println("uid is" + userID);
                            userProfileCreation(name, mail, username, financer, entrepreneur, userID);
                            Toast.makeText(Registration.this, "Registration Success",
                                    Toast.LENGTH_LONG).show();
                            System.out.println("Registration  is successful");


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Registration.this, "There is an error" + task.getException(), Toast.LENGTH_LONG).show();
                            System.out.println("FIREBASE EXCEPTION " + task.getException());
                            System.out.println("Else condition");
                        }

                        // ...
                    }
                });
    }

    //Setting values based on user type
    protected void userProfileCreation(String name, String email, String username,
                                       final boolean financer, final boolean entrepreneur, String userID) {
        Users user = new Users();
        user.setEmail(email);
        user.setName(name);
        user.setFinancer(financer);
        user.setEntrepreneur(entrepreneur);
        user.setUsername(username);
        user.setUserId(userID);
        mDatabase.child("users").child(userID).setValue(user);
        startActivity(new Intent(Registration.this, LoginActivity.class));
    }
}