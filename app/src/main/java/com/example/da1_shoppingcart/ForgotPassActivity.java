package com.example.da1_shoppingcart;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {
    EditText emailedit;
    Button btnreset;
    ProgressBar progressBar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_forgot_pass);
        emailedit=findViewById (R.id.emailforgot);
        btnreset=findViewById (R.id.btnregister);
        progressBar=findViewById (R.id.progressBarforgot);
        auth= FirebaseAuth.getInstance ();
        btnreset.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                resetpassword();
            }
        });
    }
    private void resetpassword(){
        String email= emailedit.getText ().toString ().trim ();
        if (email.isEmpty ()){
            emailedit.setError ("Email is required");
            emailedit.requestFocus ();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher (email).matches ()){
            emailedit.setError ("please provide valid email");
            emailedit.requestFocus ();
            return;
        }
        progressBar.setVisibility (View.VISIBLE);
        auth.sendPasswordResetEmail (email).addOnCompleteListener (new OnCompleteListener<Void> () {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful ()){
                    Toast.makeText (ForgotPassActivity.this,"kiểm tra email của bạn",Toast.LENGTH_LONG).show ();
                }else {
                    Toast.makeText (ForgotPassActivity.this,"hãy thử lại",Toast.LENGTH_LONG).show ();
                }
            }
        });
    }

}