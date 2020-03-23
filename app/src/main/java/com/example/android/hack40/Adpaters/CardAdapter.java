package com.example.android.hack40.Adpaters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.hack40.Activities.CardDetailsActivity;
import com.example.android.hack40.Model.CardModel;
import com.example.android.hack40.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    static ArrayList<CardModel> cards = new ArrayList<>();
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titleView;
        public TextView listName;
        public TextView boardName;
        public TextView date;
        public CardView cardView;
        private Button button;
        public MyViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title_card);
            listName = itemView.findViewById(R.id.list_name);
            boardName = itemView.findViewById(R.id.board_name);
            date = itemView.findViewById(R.id.due_date);
            cardView = itemView.findViewById(R.id.list_model);
            button = itemView.findViewById(R.id.btn_archive);
        }
    }
    public CardAdapter(ArrayList<CardModel> memesClasses, Context context){
        super();
        cards =memesClasses;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new MyViewHolder(itemView);

    }


    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CardModel card = cards.get(position);
        holder.titleView.setText(card.getCard_name());
        holder.listName.setText(card.getList_name());
        holder.date.setText(card.getDue_date());
        holder.boardName.setText(card.getBoard_name());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(card.getArchived() == 0) {
                    card.setArchived(1);
                    holder.button.setText("Archived");
                }
                else {
                    card.setArchived(0);
                    holder.button.setText("Archive");
                }
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CardDetailsActivity.class);
                i.putExtra("title",card.getCard_name());
                i.putExtra("desc",card.getDesc());
                i.putExtra("date",card.getDue_date());
                i.putExtra("board",card.getBoard_name());
                i.putExtra("list",card.getList_name());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public  void add(CardModel model){
        cards.add(model);
    }

    public void clear(){
        cards.clear();
    }

}
