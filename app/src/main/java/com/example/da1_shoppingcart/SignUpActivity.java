package com.example.da1_shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.da1_shoppingcart.Home.HomeActivity;
import com.example.da1_shoppingcart.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {
    EditText emaildk,passwordEt1,fullName,phone,diachi;
    Button SignUpbtn;
    TextView SignInTv,Home;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth= FirebaseAuth.getInstance ();
        diachi=findViewById (R.id.diachi);
        emaildk=findViewById (R.id.usernamedk);
        passwordEt1=findViewById (R.id.password1);
        fullName=findViewById (R.id.fullName);
        phone=findViewById (R.id.sdt);
        progressBar=findViewById (R.id.progressBar);
        Home=findViewById (R.id.Home);
        Home.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (SignUpActivity.this,HomeActivity.class);
                startActivity (i);
                finish ();
            }
        });



        SignInTv=findViewById (R.id.signInTv);
        SignUpbtn=findViewById (R.id.register);
        SignUpbtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });
        SignInTv.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i=new Intent (SignUpActivity.this,LoginActivity.class);
                startActivity (i);
                finish ();
            }
        });
        // STATUS BAR COLOR
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.primary_color));

    }
    private void RegisterUser(){
        final String email=emaildk.getText ().toString ().trim ();
        String pass=passwordEt1.getText ().toString ().trim ();
        final String fullname=fullName.getText ().toString ().trim ();
        final String sdt= phone.getText ().toString ().trim ();
        final String dc= diachi.getText ().toString ().trim ();
        final String key=fullname;
        if (fullname.isEmpty ()){
            emaildk.setError ("full name is required");
            emaildk.requestFocus ();
            return;
        }
        if (sdt.isEmpty ()){
            phone.setError ("phone is required");
            phone.requestFocus ();
            return;
        }
        if (dc.isEmpty ()){
            diachi.setError ("phone is required");
            diachi.requestFocus ();
            return;
        }
        if (email.isEmpty ()){
            emaildk.setError ("email is required");
            emaildk.requestFocus ();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher (email).matches ()){
            emaildk.setError ("please provide valid email");
            emaildk.requestFocus ();
            return;
        }
        if (pass.isEmpty ()){
            passwordEt1.setError ("pass is required");
            passwordEt1.requestFocus ();
            return;
        }
        if (pass.length ()<6){
            passwordEt1.setError ("mật khẩu không ít hơn 6 ký tự");
            passwordEt1.requestFocus ();
            return;
        }
        progressBar.setVisibility (View.GONE);
        mAuth.createUserWithEmailAndPassword (email,pass)
                .addOnCompleteListener (new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful ()){
                            User user= new User (email,fullname,sdt,dc);
                            FirebaseDatabase.getInstance ().getReference ("Users").child (key)
                                    .setValue (user).addOnCompleteListener (new OnCompleteListener<Void> () {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful ()){
                                        Toast.makeText (SignUpActivity.this,"User has been registered successfully",Toast.LENGTH_LONG).show ();
                                        progressBar.setVisibility (View.GONE);
                                    }else{
                                        Toast.makeText (SignUpActivity.this,"failed",Toast.LENGTH_LONG).show ();
                                        progressBar.setVisibility (View.GONE);

                                    }
                                }
                            });
                        }else {
                            Toast.makeText (SignUpActivity.this,"failed to register",Toast.LENGTH_LONG).show ();
                            progressBar.setVisibility (View.GONE);
                        }
                    }
                });

    }
}