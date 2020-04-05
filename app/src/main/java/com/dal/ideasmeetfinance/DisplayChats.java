package com.dal.ideasmeetfinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.dal.ideasmeetfinance.pojo.ChatModel;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayChats extends AppCompatActivity {
    private ActionBarDrawerToggle mToggle;
    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private List<ChatModel> allChatList;
    private ArrayList<String> userNames;
    private FirebaseUser fuser;
    private DatabaseReference databaseReference;

    private String name="hello";
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_chats);


        allChatList = new ArrayList<>();
        userNames = new ArrayList<String>();

        recyclerView = findViewById(R.id.recycler_view_chat);

        fuser= FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences sp = this.getSharedPreferences("Login", MODE_PRIVATE);
        final String myid = sp.getString("UserId", null);


        databaseReference = FirebaseDatabase.getInstance().getReference("chat");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int flag = 0;

                for (DataSnapshot unit : dataSnapshot.getChildren()){


                    if(unit.child("sender").getValue().toString().equals(myid) ) {


                        String cname = unit.child("receiver").getValue().toString();
                        for (int i = 0; i < userNames.size(); i++) {
                            if (userNames.get(i).equals(cname)) {
                                flag = 1;
                            }
                        }
                        if(flag == 0) {
                            userNames.add(cname);
                            String new_name = getScreenName(cname);
                            allChatList.add(
                                    new ChatModel("Karen Gellar", R.drawable.ideapng, cname));
                        }
                    }

                    flag = 0;

                    if(unit.child("receiver").getValue().toString().equals(myid)){
                        String cname = unit.child("sender").getValue().toString();

                        for (int i = 0; i < userNames.size(); i++) {
                            if (userNames.get(i).equals(cname)) {
                                flag = 1;
                            }
                        }
                        if(flag == 0){
                            userNames.add(cname);
                            String new_name = getScreenName(cname);

                            allChatList.add(
                                    new ChatModel("John Doe",R.drawable.ideapng,cname));
                        }
                        flag = 0;
                    }
                }
                adapter = new ChatAdapter(DisplayChats.this, allChatList);
                recyclerView.setAdapter(adapter);

                recyclerView.setLayoutManager(new LinearLayoutManager(DisplayChats.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public String getScreenName(String id){
        DatabaseReference db;
        db = FirebaseDatabase.getInstance().getReference("users");
        TaskCompletionSource<String> task = new TaskCompletionSource<>();

        final String[] new_id = {id};
        final String[] screen_name = new String[1];
        db.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot unit : dataSnapshot.getChildren()) {

                    if(unit.getKey().equals(new_id[0])){
                      screen_name[0] = unit.child("name").getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

        return screen_name[0];
    }
}
