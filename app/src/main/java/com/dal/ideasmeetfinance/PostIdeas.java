package com.dal.ideasmeetfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dal.ideasmeetfinance.pojo.Posting;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostIdeas extends AppCompatActivity {
    private DatabaseReference databaseReference,databaseReference1;
    Posting idea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ideas);
        Button btn = findViewById(R.id.btnRegister);
        //SharedPreferences sp = this.getSharedPreferences("Login", MODE_PRIVATE);

//        final String userName = sp.getString("UserName", null);
//        Log.e("m","username is----------: "+userName);
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText title = findViewById(R.id.idea_title);
                EditText abs = findViewById(R.id.idea_abstract);
                EditText content = findViewById(R.id.idea_content);
                final String[] name = new String[1];

                final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                //final String key = database.getReference("ideas").push().getKey();
                databaseReference = FirebaseDatabase.getInstance().getReference("ideas").child(userId);
                //databaseReference = FirebaseDatabase.getInstance().getReference("ideas");
                idea = new Posting();

//                database.getReference().child("users").child(userId)
//                        .addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                final String userName = dataSnapshot.child("username").getValue(String.class);
//                                Log.e("m","username is: "+userName);
//                                try{
//
//                                    idea.setUsername(userName);
//                                }
//                                catch(Exception e)
//                                {
//                                    Log.e("m","error: "+e);
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//                                Log.e("m","cancel");
//                            }
//
//                        });

                String temp=userId;
                final String title_text = title.getText().toString();
                final String abs_text = abs.getText().toString();
                final String content_text = content.getText().toString();

                idea.setTitle(title_text);
                idea.setAbs(abs_text);
                idea.setContent(content_text);
                idea.setAuthor(temp);
                databaseReference.push().setValue(idea);
                Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PostIdeas.this,FinancerHomeActivity.class));




//                database.getReference().child("ideas").child(userId)
//                        .addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                String userName = dataSnapshot.child("users").child("username").getValue(String.class);
//                                Log.e("Username is","user is :"+userName);
//                                Posting idea = new Posting(title_text, abs_text, content_text, userName);
//
//                                //Map<String, Object> postValues = new HashMap<String,Object>();
//                                idea = new Posting();
//                                idea.setTitle(title_text);
//                                idea.setAbs(abs_text);
//                                idea.setContent(content_text);
//                                idea.setUsername(userName);
//                                databaseReference.push().setValue(idea);
//                                database.getReference().child("ideas").child(userId).child(key).setValue(idea, new DatabaseReference.CompletionListener() {
//                                    @Override
//                                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
//                                        if (databaseError != null) {
//                                            Toast.makeText(PostIdeas.this, "Error uploading idea!", Toast.LENGTH_SHORT).show();
//                                        } else {
//                                            Toast.makeText(PostIdeas.this, "Idea uploaded successfully!", Toast.LENGTH_SHORT).show();
//                                            //progressBar.setVisibility(View.INVISIBLE);
//                                            startActivity(new Intent(PostIdeas.this,FinancerHomeActivity.class));
//                                        }
//                                    }
//                                });
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//                            }
//                        });
            }
        });
    }
}
