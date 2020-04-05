package com.dal.ideasmeetfinance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailedIdea extends AppCompatActivity {

    private TextView title, author, abstractTxt, content;
    private FloatingActionButton btnMessage;
    DatabaseReference databaseReference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_idea);

        Intent intent = getIntent();
        String titleText = intent.getExtras().getString("title");
        String abstractTxt = intent.getExtras().getString("abstractTxt");
        String contentText = intent.getExtras().getString("content");
        String actAuthor = intent.getExtras().getString("author");
        final String authorId = intent.getExtras().getString("authorId");
        Log.e("m","authorID: "+authorId);

        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        //abstractTxt=findViewById()
        content = findViewById(R.id.content);
        btnMessage = findViewById(R.id.chat_button);
        database = FirebaseDatabase.getInstance();

        title.setText(titleText);
        author.setText("Created by: " + actAuthor);
        content.setText(contentText);

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),MessageActivity.class);
                i.putExtra("authorId",authorId);
                startActivity(i);
            }
        });

    }
}
