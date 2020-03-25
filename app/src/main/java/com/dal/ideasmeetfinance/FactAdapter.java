package com.dal.ideasmeetfinance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dal.ideasmeetfinance.pojo.CardModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//This java class acts like an adapter and is used to inflate the card_view.xml layout
//with the individual cards.

public class FactAdapter extends  RecyclerView.Adapter<FactAdapter.FactViewHolder>{
    private Context ctx;
    private List<CardModel> factsList;

    public FactAdapter(Context ctx, List<CardModel> factsList) {
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
    public void onBindViewHolder(@NonNull FactViewHolder holder, int position) {
        CardModel cards = factsList.get(position);
        holder.title_view.setText(cards.getTitle_card());
        holder.imgView.setImageDrawable(ctx.getResources().getDrawable(cards.getImage(),null));
        holder.abs_view.setText(cards.getAbs_card());
        holder.content_view.setText(cards.getContent_card());
        holder.name.setText(cards.getUser_name());
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
            content_view = itemView.findViewById(R.id.content_card);
            name = itemView.findViewById(R.id.name_user);
        }
    }
}
