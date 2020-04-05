package com.dal.ideasmeetfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dal.ideasmeetfinance.pojo.Posting;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FinancerHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private RecyclerView recyclerView;
    private FactAdapter adapter;
    //private List<CardModel> allFactsList;
    private List<Posting> allFactsList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financer_home);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.main);
        mToggle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        SharedPreferences sp = this.getSharedPreferences("Login", MODE_PRIVATE);



        allFactsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);

        databaseReference = FirebaseDatabase.getInstance().getReference("ideas");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                allFactsList.clear();
                for (DataSnapshot unit : dataSnapshot.getChildren())
                {
                    for (DataSnapshot indi : unit.getChildren())
                    {
                        String title_txt = indi.child("title").getValue().toString();
                        String abstract_txt = indi.child("abs").getValue().toString();
                        String content_txt = indi.child("content").getValue().toString();
                        String author= indi.child("author").getValue().toString();
                        allFactsList.add(
                                new Posting(R.drawable.ideapng,author,title_txt,abstract_txt,content_txt));
                    }
                }
                adapter = new FactAdapter(FinancerHomeActivity.this, allFactsList);
                recyclerView.setAdapter(adapter);

                recyclerView.setLayoutManager(new LinearLayoutManager(FinancerHomeActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click.
                startActivity(new Intent(FinancerHomeActivity.this,PostIdeas.class));
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toast.makeText(getApplicationContext(),"Navigation",Toast.LENGTH_SHORT).show();
        switch(item.getItemId()){
            case R.id.logout:
                startActivity(new Intent(FinancerHomeActivity.this, LoginActivity.class));
                finish();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.logout:
                startActivity(new Intent(FinancerHomeActivity.this, LoginActivity.class));
                finish();
                break;

            case R.id.messages:
                startActivity(new Intent(FinancerHomeActivity.this,DisplayChats.class));
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.drawermenu,menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }
}
