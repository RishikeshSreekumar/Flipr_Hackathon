package com.example.android.hack40.Activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
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
import com.example.android.hack40.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBoardActivity extends AppCompatActivity {

    EditText title;
    private DatabaseReference mDatabase;
    Button button;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_board);
        title = findViewById(R.id.board_name);
        button = findViewById(R.id.btn_add);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref",0);
        editor = sharedPreferences.edit();
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

        values.put(BoardContract.BoardEntry.COLUMN_BOARD_NAME,title.getText().toString());
        values.put(BoardContract.BoardEntry.COLUMN_BOARD_CATEGORY,0);

        Uri insert = getContentResolver().insert(BoardContract.BoardEntry.CONTENT_URI, values);
        if(insert == null)
            Toast.makeText(this, "Failed to Insert", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();

        writeBoard(title.getText().toString(),0);

    }

    private void writeBoard(String name,int cat) {
        BoardModel user = new BoardModel(name,cat);
        String userId = sharedPreferences.getString("userId","a");
        mDatabase.child("Users").child(userId).child("Boards").setValue(user);
    }

}
