package com.example.android.hack40.Activities;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.hack40.Database.BoardContract;
import com.example.android.hack40.Model.BoardModel;
import com.example.android.hack40.Model.ListModel;
import com.example.android.hack40.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddListActivity extends AppCompatActivity {

    EditText title;
    String board;
    Button button;
    private DatabaseReference mDatabase;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        title = findViewById(R.id.board_name);
        button = findViewById(R.id.btn_add);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref",0);
        board = getIntent().getStringExtra("board");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
                finish();
            }
        });
    }

    private void insert() {
        ContentValues values = new ContentValues();

        values.put(BoardContract.ListEntry.COLUMN_LIST_NAME,title.getText().toString());
        values.put(BoardContract.ListEntry.COLUMN_BOARD_NAME,board);

        Uri insert = getContentResolver().insert(BoardContract.ListEntry.CONTENT_URI, values);
        if(insert == null)
            Toast.makeText(this, "Failed to Insert", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();

        writeList(title.getText().toString(),board);
    }

    private void writeList(String name,String Board) {
        ListModel user = new ListModel(name,Board);
        String userId = sharedPreferences.getString("userId","a");
        mDatabase.child("Users").child(userId).child("List").setValue(user);
    }
}
