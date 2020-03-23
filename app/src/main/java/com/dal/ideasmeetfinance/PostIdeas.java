package com.dal.ideasmeetfinance;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class PostIdeas extends AppCompatActivity {
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ideas);
        Button btn = findViewById(R.id.btnRegister);
        SharedPreferences sp = this.getSharedPreferences("Login", MODE_PRIVATE);

        final String user_name = sp.getString("UserId", null);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText title = findViewById(R.id.idea_title);
                EditText abs = findViewById(R.id.idea_abstract);
                EditText content = findViewById(R.id.idea_content);

                String title_text = title.getText().toString();
                String abs_text = abs.getText().toString();
                String content_text = abs.getText().toString();
                databaseReference = FirebaseDatabase.getInstance().getReference("ideas");


                Toast.makeText(getApplicationContext(),user_name,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
