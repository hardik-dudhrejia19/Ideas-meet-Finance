//Class to display a detailed view of an idea

package com.dal.ideasmeetfinance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailedIdea extends AppCompatActivity {

    private TextView title, author, content;
    private FloatingActionButton btnMessage;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = this.getSharedPreferences("Login", MODE_PRIVATE);
        String isFinancer = sp.getString("Decider", null);

        setContentView(R.layout.activity_detailed_idea);

//        Getting details of the idea to be displayed from previous activity
        Intent intent = getIntent();
        String titleText = intent.getExtras().getString("title");
        String abstractTxt = intent.getExtras().getString("abstractTxt");
        String contentText = intent.getExtras().getString("content");
        String actAuthor = intent.getExtras().getString("author");
        final String authorId = intent.getExtras().getString("authorId");

        if(isFinancer.equals("FFalse")){

            Intent i = new Intent(DetailedIdea.this,DetailIdeaEntrepreneur.class);
            i.putExtra("title", titleText);
            i.putExtra("abstractTxt", abstractTxt);
            i.putExtra("content", contentText);
            i.putExtra("authorId",authorId);
            i.putExtra("author",actAuthor);
            startActivity(i);
            finish();
        }

        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        content = findViewById(R.id.content);
        btnMessage = findViewById(R.id.chat_button);
        database = FirebaseDatabase.getInstance();

        title.setText(titleText);
        author.setText("Created by: " + actAuthor);
        content.setText(contentText);

//        Redirecting to messaging screen
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
