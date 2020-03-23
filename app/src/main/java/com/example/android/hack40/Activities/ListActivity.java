package com.example.android.hack40.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.hack40.Adpaters.ListAdapter;
import com.example.android.hack40.Database.BoardContract;
import com.example.android.hack40.Model.ListModel;
import com.example.android.hack40.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListAdapter adapter;
    RecyclerView recyclerView;
    String board_name;
    static ArrayList arrayList = new ArrayList<ListModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        board_name = getIntent().getStringExtra("board");
        recyclerView = findViewById(R.id.rv_board);
        adapter = new ListAdapter(arrayList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        displayDatabaseInfo();
    }

    public void addItems(View v) {
        Intent i = new Intent(this,AddListActivity.class);
        i.putExtra("board",board_name);
        startActivityForResult(i,202);
    }

    private void displayDatabaseInfo() {

        String[] projection = {BoardContract.ListEntry.COLUMN_BOARD_NAME,BoardContract.ListEntry.COLUMN_LIST_NAME};

        Cursor cursor = getContentResolver().query(BoardContract.ListEntry.CONTENT_URI,projection,
                BoardContract.ListEntry.COLUMN_BOARD_NAME + " = ? ",new String[]{board_name},null);

        try {
            int nameColumnIndex = cursor.getColumnIndex(BoardContract.ListEntry.COLUMN_LIST_NAME);
            int boardColumnIndex = cursor.getColumnIndex(BoardContract.ListEntry.COLUMN_BOARD_NAME);

            adapter.clear();
            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColumnIndex);
                String boardName = cursor.getString(boardColumnIndex);
                adapter.add(new ListModel(currentName,boardName));
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
