package com.example.da1_shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ManHinhChoActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;
    // Variable
    Handler handler;
    Animation topAnim, bottomAnim;
    ImageView logoImage, logoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho);

        // hide cái màu ở trên top (primarycolordark) nhớ là trong style để noActionBar trước
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        // Hooks
//        logoImage = findViewById(R.id.ivLogoImage);
//        logoText = findViewById(R.id.ivlogoText);
//
//        logoImage.setAnimation(topAnim);
//        logoText.setAnimation(bottomAnim);

        // chờ một lúc và sau đó chuyển từ màn hình chờ sang màn hình login
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent(ManHinhChoActivity.this, Login2Activity.class);
                startActivity(dsp);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}