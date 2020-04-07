//This java class acts like an adapter and is used to inflate the card_view.xml layout with the individual cards.

package com.dal.ideasmeetfinance;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dal.ideasmeetfinance.pojo.Posting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.SharedPreferences;


import java.lang.reflect.Constructor;
import java.util.List;

public class FactAdapter extends  RecyclerView.Adapter<FactAdapter.FactViewHolder>{
    private static final Object MODE_PRIVATE = 1;
    private Context ctx;
    private List<Posting> factsList;
    private String actUserName;

//    Constructor

    public FactAdapter(Context ctx, List<Posting> factsList) {
        this.ctx = ctx;
        this.factsList = factsList;
    }

    @NonNull
    @Override
    public FactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.card_view, null);
        FactViewHolder holder = new FactViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FactViewHolder holder, int position) {
        final Posting cards = factsList.get(position);
        holder.title_view.setText(cards.getTitle());
        holder.imgView.setImageDrawable(ctx.getResources().getDrawable(cards.getImage(),null));
        holder.abs_view.setText(cards.getAbs());

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("users").child(cards.getAuthor())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                final String userName = dataSnapshot.child("username").getValue(String.class);
                                try{
                                    actUserName=userName;
                                    holder.name.setText("Created by :"+userName);
                                }
                                catch(Exception e)
                                {
                                    Log.e("m","error: "+e);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.e("m","cancel");
                            }

                        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ctx, DetailedIdea.class);
                intent.putExtra("title", cards.getTitle());
                intent.putExtra("abstractTxt", cards.getAbs());
                intent.putExtra("content", cards.getContent());
                intent.putExtra("authorId",cards.getAuthor());
                intent.putExtra("author",actUserName);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return factsList.size();
    }

    class FactViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        TextView title_view;
        TextView abs_view;
        TextView name;

        public FactViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.idea_image);
            title_view = itemView.findViewById(R.id.title_card);
            abs_view = itemView.findViewById(R.id.abs_card);
            name = itemView.findViewById(R.id.name_author);
        }
    }
}
