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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dal.ideasmeetfinance.pojo.ChatModel;



import java.util.List;

//This java class acts like an adapter and is used to inflate the chats_card.xml layout
//with the individual cards.


public class ChatAdapter extends  RecyclerView.Adapter<ChatAdapter.FactViewHolder>{
    private Context ctx;
    private List<ChatModel> factsList;


    private String actUserName;

    public ChatAdapter(Context ctx, List<ChatModel> factsList) {
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
        final ChatModel cards = factsList.get(position);
        holder.name_chat.setText(cards.getNameCard());
        holder.imgView.setImageDrawable(ctx.getResources().getDrawable(cards.getImage(),null));
//        holder.hidden_chat.setText(cards.getNameHidden());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx,MessageActivity.class);
                String authorId = cards.getNameHidden();
                i.putExtra("authorId",authorId);
                ctx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return factsList.size();
    }



    class FactViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        TextView name_chat;
        TextView hidden_chat;



        public FactViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.idea_image);
            name_chat = itemView.findViewById(R.id.title_card);
            hidden_chat = itemView.findViewById(R.id.abs_card);

        }
    }
}
