//This java class acts like an adapter and is used to inflate the chats_card.xml layout with the individual cards.

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

public class ChatAdapter extends  RecyclerView.Adapter<ChatAdapter.FactViewHolder>{

    private Context ctx;
    private List<ChatModel> factsList; //to store the chats retrieved

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
