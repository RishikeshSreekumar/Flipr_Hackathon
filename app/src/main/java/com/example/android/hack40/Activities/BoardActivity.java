package com.example.android.hack40.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.hack40.Adpaters.BoardAdapter;
import com.example.android.hack40.Database.BoardContract;
import com.example.android.hack40.Database.BoardDbHelper;
import com.example.android.hack40.Model.BoardModel;
import com.example.android.hack40.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.Objects;

public class BoardActivity extends AppCompatActivity{

    BoardAdapter adapter;
    RecyclerView recyclerView;
    static ArrayList arrayList = new ArrayList<BoardModel>();
    private DatabaseReference mDatabase;
    BoardDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
//        Objects.requireNonNull(getActionBar()).setTitle("Boards");
        recyclerView = findViewById(R.id.rv_board);
        adapter = new BoardAdapter(arrayList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        dbHelper = new BoardDbHelper(this);

        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        String[] projection = {BoardContract.BoardEntry.COLUMN_BOARD_NAME,BoardContract.BoardEntry.COLUMN_BOARD_CATEGORY};

        Cursor cursor = getContentResolver().query(BoardContract.BoardEntry.CONTENT_URI,projection,null,null,null);

        try {
            int nameColumnIndex = cursor.getColumnIndex(BoardContract.BoardEntry.COLUMN_BOARD_NAME);
            int catColumnIndex = cursor.getColumnIndex(BoardContract.BoardEntry.COLUMN_BOARD_CATEGORY);

            adapter.clear();
            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColumnIndex);
                Log.i("Name",currentName);
                adapter.add(new BoardModel(currentName,cursor.getInt(catColumnIndex)));
                adapter.notifyDataSetChanged();
            }
        } finally {
            cursor.close();
        }
    }

    public void addItems(View v) {
        Intent i = new Intent(this,AddBoardActivity.class);
        startActivityForResult(i,202);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 202){
            displayDatabaseInfo();
        }
    }

    @Override
    public void setSupportActionBar(@Nullable androidx.appcompat.widget.Toolbar toolbar) {
        super.setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Board");
        actionBar.show();
    }
}
