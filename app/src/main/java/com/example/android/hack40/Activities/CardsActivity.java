package com.example.android.hack40.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android.hack40.Adpaters.CardAdapter;
import com.example.android.hack40.Database.BoardContract;
import com.example.android.hack40.Model.CardModel;
import com.example.android.hack40.R;

import java.util.ArrayList;

public class CardsActivity extends AppCompatActivity {

    CardAdapter adapter;
    RecyclerView recyclerView;
    static ArrayList arrayList = new ArrayList<CardModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.list);
        adapter = new CardAdapter(arrayList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        displayDatabaseInfo();
    }

    public void addItems(View v) {
        Intent i = new Intent(this,AddCardActivity.class);
        i.putExtra("board",getIntent().getStringExtra("board"));
        i.putExtra("list",getIntent().getStringExtra("list"));
        startActivityForResult(i,202);
        adapter.notifyDataSetChanged();
    }

    private void displayDatabaseInfo() {

        String[] projection = {BoardContract.CardEntry.COLUMN_CARD_NAME,BoardContract.CardEntry.COLUMN_LIST_NAME,BoardContract.CardEntry.COLUMN_BOARD_NAME,BoardContract.CardEntry.COLUMN_DUE_DATE,BoardContract.CardEntry.COLUMN_DESC};
        String selection = BoardContract.CardEntry.COLUMN_BOARD_NAME + " = ? and " + BoardContract.CardEntry.COLUMN_LIST_NAME + "= ?";
        String[] selectionArgs = {getIntent().getStringExtra("board"),getIntent().getStringExtra("list")};
        Cursor cursor = getContentResolver().query(BoardContract.CardEntry.CONTENT_URI,projection,selection,selectionArgs,null);

        try {
            int cardColumnIndex = cursor.getColumnIndex(BoardContract.CardEntry.COLUMN_CARD_NAME);
            int listColumnIndex = cursor.getColumnIndex(BoardContract.CardEntry.COLUMN_LIST_NAME);
            int boardColumnIndex = cursor.getColumnIndex(BoardContract.CardEntry.COLUMN_BOARD_NAME);
            int duedtaeColumnIndex = cursor.getColumnIndex(BoardContract.CardEntry.COLUMN_DUE_DATE);
            int descColumnIndex = cursor.getColumnIndex(BoardContract.CardEntry.COLUMN_DESC);

            adapter.clear();
            while (cursor.moveToNext()) {
                String currentName = cursor.getString(cardColumnIndex);
                Log.i("Name",currentName);
                adapter.add(new CardModel(currentName,cursor.getString(listColumnIndex),cursor.getString(boardColumnIndex),cursor.getString(duedtaeColumnIndex),cursor.getString(descColumnIndex)));
                adapter.notifyDataSetChanged();
            }
        } finally {
            cursor.close();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 202){
            displayDatabaseInfo();
        }
    }
}
