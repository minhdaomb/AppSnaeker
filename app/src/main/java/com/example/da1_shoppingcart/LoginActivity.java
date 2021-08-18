package com.example.da1_shoppingcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1_shoppingcart.Home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    // variable
    EditText tvEmail, tvPassword;
    Button btnDangNhap;
    ImageView imgLoginWithGoogle;
    ImageView imgLoginWithFacebook;
    TextView tvRegister;
    TextView tvForgotPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // STATUS BAR COLOR
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.primary_color));

        // hooks
        tvEmail = findViewById(R.id.inputEmail);
        tvPassword = findViewById(R.id.inputPassword);
        btnDangNhap = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassWord);
        imgLoginWithGoogle = findViewById(R.id.img_login_google);
        imgLoginWithFacebook = findViewById(R.id.img_login_facebook);
        tvRegister = findViewById(R.id.tvRegister);
        mAuth = FirebaseAuth.getInstance();

        // login with email
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                String email = tvEmail.getText().toString();
                String password = tvPassword.getText().toString();

                // create user with email and password, after that is login
                signInWithEmailAndPassword(email, password);
                //Toast.makeText(LoginActivity.this, "Button login" + email + password, Toast.LENGTH_SHORT).show();

            }
        });

        // Forgot password
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Pressed textView forgot password", Toast.LENGTH_SHORT).show();
            }
        });

        // login with google
        imgLoginWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Pressed img login with google", Toast.LENGTH_SHORT).show();
            }
        });

        // login with facebook
        imgLoginWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();

                Toast.makeText(LoginActivity.this, "Pressed img login with facebook", Toast.LENGTH_SHORT).show();
            }
        });

        // Register
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        // check current auth state
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            updateUI(currentUser);
        }
        super.onStart();
    }

    // Sign up new users and after that login
    private void createUserWithEmailAndPassword(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    // Sign in existing users
    private void signInWithEmailAndPassword(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    // signOut
    private void signOut(){
        FirebaseAuth.getInstance().signOut();
    }

    // updateUI when login success
    private void updateUI(FirebaseUser user){
        String userName = user.getEmail();
        Toast.makeText(LoginActivity.this, "Hi: " + userName, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }


}

