package com.hamsofts.simpleblogapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText LoginEmailText;
    private EditText LoginPassText;
    private Button LoginBtn;
    private Button LoginRegButton;
    private ProgressBar LoginProgress;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        LoginEmailText = findViewById(R.id.reg_email);
        LoginPassText = findViewById(R.id.reg_confirm_pass);
        LoginBtn = findViewById(R.id.login_btn);
        LoginRegButton = findViewById(R.id.login_reg_btn);
        LoginProgress = findViewById(R.id.login_progress);

        LoginRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regIntent);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String LoginEmail = LoginEmailText.getText().toString();
                String LoginPass = LoginPassText.getText().toString();

                if (!TextUtils.isEmpty(LoginEmail) && !TextUtils.isEmpty(LoginPass)) {
                    LoginProgress.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(LoginEmail, LoginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                sendToMain();

                            } else {
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error :" + errorMessage, Toast.LENGTH_LONG).show();

                            }
                            LoginProgress.setVisibility(View.INVISIBLE);

                        }
                    });


                }

            }
        });
    }

    protected void onStart () {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
// check if user is logged in
        if (currentUser != null){
            sendToMain();

        }

    }

    private void sendToMain() {
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
