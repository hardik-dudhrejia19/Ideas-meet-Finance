//Class to implement the posting of ideas or requirements functionality

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
//        Handling the click for post ideas button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText title = findViewById(R.id.idea_title);
                EditText abs = findViewById(R.id.idea_abstract);
                EditText content = findViewById(R.id.idea_content);

                final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                databaseReference = FirebaseDatabase.getInstance().getReference("ideas").child(userId);
                idea = new Posting();

                String temp=userId;
                final String title_text = title.getText().toString();
                final String abs_text = abs.getText().toString();
                final String content_text = content.getText().toString();

//                Setting data in the layout file
                idea.setTitle(title_text);
                idea.setAbs(abs_text);
                idea.setContent(content_text);
                idea.setAuthor(temp);
//                Pushing data to database
                databaseReference.push().setValue(idea);
                Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PostIdeas.this,FinancerHomeActivity.class));

            }
        });
    }
}
