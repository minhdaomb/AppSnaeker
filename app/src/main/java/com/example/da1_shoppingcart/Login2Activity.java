package com.example.da1_shoppingcart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.da1_shoppingcart.Home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login2Activity extends AppCompatActivity {

    // variable
    EditText emaildk,passwordEt;
    Button SignInbtn;
    TextView SignUpTv,forgot;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        // hooks
        firebaseAuth= FirebaseAuth.getInstance ();
        emaildk=findViewById (R.id.username);
        passwordEt=findViewById (R.id.password);
        SignUpTv=findViewById (R.id.signUpTv);
        SignInbtn=findViewById (R.id.btnlogin);
        forgot=findViewById (R.id.forgotPass);
        forgot.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (Login2Activity.this,ForgotPassActivity.class);
                startActivity (i);
                finish ();
            }
        });
        progressDialog=new ProgressDialog (this);
        SignInbtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        SignUpTv.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i=new Intent (Login2Activity.this,SignUpActivity.class);
                startActivity (i);
                finish ();
            }
        });
    }
    private void Login(){
        String email=emaildk.getText ().toString ();
        String password=passwordEt.getText ().toString ();
        if (TextUtils.isEmpty (email)){
            emaildk.setError ("Enter your email");
            return;
        }
        else if (TextUtils.isEmpty (password)){
            passwordEt.setError ("Enter your password");
            return;
        }
        progressDialog.setMessage ("Please wait");
        progressDialog.show ();
        progressDialog.setCanceledOnTouchOutside (false);
        firebaseAuth.signInWithEmailAndPassword (email,password).addOnCompleteListener (this,new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful ()){
                    FirebaseUser user= FirebaseAuth.getInstance ().getCurrentUser ();
                    if (user.isEmailVerified ()){
                        Toast.makeText (Login2Activity.this,"Login Succressfully",Toast.LENGTH_LONG).show ();
                        Intent i=new Intent (Login2Activity.this,HomeActivity.class);
                        startActivity (i);
                        finish ();
                    }else {
                        user.sendEmailVerification ();
                        Toast.makeText (Login2Activity.this,"Check your email to verity your account!",Toast.LENGTH_LONG).show ();
                    }

                }
                else {
                    Toast.makeText (Login2Activity.this,"Sign In fail",Toast.LENGTH_LONG).show ();
                }
                progressDialog.dismiss ();
            }
        });
    }
}