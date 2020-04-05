package com.dal.ideasmeetfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailIdeaEntrepreneur extends AppCompatActivity {

    private TextView title, author, abstractTxt, content;
    private FloatingActionButton btnMessage;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_idea_entrepreneur);


        Intent intent = getIntent();
        String titleText = intent.getExtras().getString("title");
        String abstractTxt = intent.getExtras().getString("abstractTxt");
        String contentText = intent.getExtras().getString("content");
        String actAuthor = intent.getExtras().getString("author");
        final String authorId = intent.getExtras().getString("authorId");
        Log.e("m","authorID: "+authorId);

        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        content = findViewById(R.id.content);
        btnMessage = findViewById(R.id.chat_button);
        database = FirebaseDatabase.getInstance();

        title.setText(titleText);
        author.setText("Created by: " + actAuthor);
        content.setText(contentText);


    }
}
