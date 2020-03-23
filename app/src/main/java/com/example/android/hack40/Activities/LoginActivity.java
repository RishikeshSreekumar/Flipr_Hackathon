package com.example.android.hack40.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.hack40.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText et_email,pass;
    Button login;
    TextView forgotpass;
    private FirebaseAuth mAuth;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.btn_login);
        forgotpass = findViewById(R.id.forgot_pass);

        sharedPref = getApplication().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        mAuth = FirebaseAuth.getInstance();

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_email.getText().toString().equals(""))
                FirebaseAuth.getInstance().sendPasswordResetEmail(et_email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("TAG", "Email sent. Check you email.");
                                }
                            }
                        });
                else
                    et_email.setError("Enter email");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void login(View v){

        final String email = et_email.getText().toString();
        String password = pass.getText().toString();
        Log.d("TAG", "signIn:" + email);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            boolean userId = editor.putString("userId", user.getUid()).commit();
                            Toast.makeText(LoginActivity.this, "Logging in",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        if (!task.isSuccessful()) {
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user){
        Intent i = new Intent(this,BoardActivity.class);
        startActivity(i);
    }
}
