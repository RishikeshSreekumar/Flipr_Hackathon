package com.example.android.hack40.Activities;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.hack40.Database.BoardContract;
import com.example.android.hack40.Model.BoardModel;
import com.example.android.hack40.Model.CardModel;
import com.example.android.hack40.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCardActivity extends AppCompatActivity {

    EditText title,desc;
    DatePicker datePicker;
    Button button;
    private DatabaseReference mDatabase;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        title = findViewById(R.id.card_name);
        desc = findViewById(R.id.desc);
        datePicker = findViewById(R.id.due_date);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref",0);
        button = findViewById(R.id.btn_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String title1 = title.getText().toString();
                String desc1 = desc.getText().toString();
                String date = datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth();*/

//                CardAdapter.add(new CardModel(title1,desc1,date));
                insert();
                finish();
            }
        });
    }

    private void insert() {
        ContentValues values = new ContentValues();

        String desc1 = desc.getText().toString();
        String date = datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth();
        values.put(BoardContract.CardEntry.COLUMN_CARD_NAME,title.getText().toString());
        values.put(BoardContract.CardEntry.COLUMN_LIST_NAME,getIntent().getStringExtra("list"));
        values.put(BoardContract.CardEntry.COLUMN_BOARD_NAME,getIntent().getStringExtra("board"));
        values.put(BoardContract.CardEntry.COLUMN_DUE_DATE,date);
        values.put(BoardContract.CardEntry.COLUMN_DESC,desc1);

        Uri insert = getContentResolver().insert(BoardContract.CardEntry.CONTENT_URI, values);
        if(insert == null)
            Toast.makeText(this, "Failed to Insert", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();

        writeCard(title.getText().toString(),getIntent().getStringExtra("board"),getIntent().getStringExtra("list"),desc1,date);
    }

    private void writeCard(String name,String list,String board,String desc,String date) {
        CardModel user = new CardModel(name,board,list,date,desc);
        String userId = sharedPreferences.getString("userId","a");
        mDatabase.child("Users").child(userId).child("Cards").setValue(user);
    }
}
