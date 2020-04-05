//package com.dal.ideasmeetfinance;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.dal.ideasmeetfinance.pojo.CardModel;
//
//import java.util.List;
//
////This java class acts like an adapter and is used to inflate the card_view.xml layout
////with the individual cards.
//
//public class FactAdapter extends  RecyclerView.Adapter<FactAdapter.FactViewHolder>{
//    private Context ctx;
//    private List<CardModel> factsList;
//
//    public FactAdapter(Context ctx, List<CardModel> factsList) {
//        this.ctx = ctx;
//        this.factsList = factsList;
//    }
//
//    @NonNull
//    @Override
//    public FactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(ctx);
//        View view = inflater.inflate(R.layout.card_view, null);
//        FactViewHolder holder = new FactViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FactViewHolder holder, int position) {
//        final CardModel cards = factsList.get(position);
//        holder.title_view.setText(cards.getTitle_card());
//        holder.imgView.setImageDrawable(ctx.getResources().getDrawable(cards.getImage(),null));
//        holder.abs_view.setText(cards.getAbs_card());
//        holder.content_view.setText(cards.getContent_card());
//        holder.name.setText(cards.getUser_name());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ctx, DetailedIdea.class);
//                intent.putExtra("title", cards.getTitle_card());
//                intent.putExtra("abstractTxt", cards.getAbs_card());
//                intent.putExtra("content", cards.getContent_card());
//                intent.putExtra("author", cards.getUser_name());
//                ctx.startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return factsList.size();
//    }
//
//
//
//    class FactViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView imgView;
//        TextView title_view;
//        TextView abs_view;
//        TextView content_view;
//        TextView name;
//
//
//        public FactViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            imgView = itemView.findViewById(R.id.idea_image);
//            title_view = itemView.findViewById(R.id.title_card);
//            abs_view = itemView.findViewById(R.id.abs_card);
//            content_view = itemView.findViewById(R.id.content_card);
//            name = itemView.findViewById(R.id.name_author);
//        }
//    }
//}

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

import java.util.List;

//This java class acts like an adapter and is used to inflate the card_view.xml layout
//with the individual cards.

public class FactAdapter extends  RecyclerView.Adapter<FactAdapter.FactViewHolder>{
    private Context ctx;
    private List<Posting> factsList;

    private String actUserName;

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
        //holder.content_view.setText(cards.getContent());
        //databaseReference= FirebaseDatabase.getInstance().getReference("users").child(cards.getAuthor());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("users").child(cards.getAuthor())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                final String userName = dataSnapshot.child("username").getValue(String.class);
                                Log.e("m","username is: "+userName);
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

        //holder.name.setText("Created by: "+cards.getAuthor());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, DetailedIdea.class);
                intent.putExtra("title", cards.getTitle());
                intent.putExtra("abstractTxt", cards.getAbs());
                intent.putExtra("content", cards.getContent());
                intent.putExtra("authorId",cards.getAuthor());
                intent.putExtra("author",actUserName);
                //intent.putExtra("author", cards.getUsername());
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
        TextView content_view;
        TextView name;


        public FactViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.idea_image);
            title_view = itemView.findViewById(R.id.title_card);
            abs_view = itemView.findViewById(R.id.abs_card);
//            content_view = itemView.findViewById(R.id.content_card);
            name = itemView.findViewById(R.id.name_author);
        }
    }
}
