package com.example.android.hack40.Adpaters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.hack40.Model.ListModel;
import com.example.android.hack40.Activities.CardsActivity;
import com.example.android.hack40.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    static ArrayList<ListModel> cards = new ArrayList<>();
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titleView;
        public TextView boardView;
        public CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.list_title);
            boardView = itemView.findViewById(R.id.board_name);
            cardView = itemView.findViewById(R.id.list_model);
        }
    }
    public ListAdapter(ArrayList<ListModel> memesClasses, Context context){
        super();
        cards =memesClasses;
        this.context = context;
    }
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ListAdapter.MyViewHolder(itemView);

    }


    public void onBindViewHolder(final ListAdapter.MyViewHolder holder, int position) {
        final ListModel card = cards.get(position);
        holder.titleView.setText(card.getName());
        holder.boardView.setText(card.getBoard_name());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CardsActivity.class);
                i.putExtra("board",card.getBoard_name());
                i.putExtra("list",card.getName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void clear(){
        cards.clear();
    }

    public void add(ListModel model){
        cards.add(model);
    }
}
