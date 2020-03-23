package com.example.android.hack40.Adpaters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.hack40.Model.BoardModel;
import com.example.android.hack40.Activities.ListActivity;
import com.example.android.hack40.R;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.MyViewHolder> {
    static ArrayList<BoardModel> cards;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titleView;
        public CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title_card);
            cardView = itemView.findViewById(R.id.cv_board);
        }
    }
    public BoardAdapter(ArrayList<BoardModel> memesClasses, Context context){
        super();
        cards =memesClasses;
        this.context = context;
    }
    @Override
    public BoardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.board_item, parent, false);

        return new BoardAdapter.MyViewHolder(itemView);

    }


    public void onBindViewHolder(final BoardAdapter.MyViewHolder holder, int position) {
        final BoardModel card = cards.get(position);
        holder.titleView.setText(card.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ListActivity.class);
                i.putExtra("board",card.getName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void add(BoardModel model){
        cards.add(model);
    }

    public void clear(){
        cards.clear();
    }
}
