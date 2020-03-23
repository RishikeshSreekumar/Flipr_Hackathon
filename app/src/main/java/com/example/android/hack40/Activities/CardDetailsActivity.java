package com.example.android.hack40.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.hack40.R;

public class CardDetailsActivity extends AppCompatActivity {

    TextView title,desc,board,list,due;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        due = findViewById(R.id.date);
        board = findViewById(R.id.list_name);
        list = findViewById(R.id.board_name);

        title.setText(getIntent().getStringExtra("title"));
        desc.setText(getIntent().getStringExtra("desc"));
        due.setText(getIntent().getStringExtra("date"));
        board.setText(getIntent().getStringExtra("board"));
        list.setText(getIntent().getStringExtra("list"));

    }
}
