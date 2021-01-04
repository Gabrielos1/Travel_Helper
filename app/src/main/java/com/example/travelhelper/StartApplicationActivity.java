package com.example.travelhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class StartApplicationActivity extends AppCompatActivity {

    //VARIABLES
    private static int SPLASH_SCREEN = 3000;
    private Animation topAnimation, bottomAnimation;
    private ImageView image;
    private TextView textLogo, slogan;
    private View startApplication;
    private Intent intent;
    private Pair[] pairs;
    private ActivityOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TURN OFF THE STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_application);

        /*//TURN OFF THE STATUS BAR
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            insetsController = getWindow().getInsetsController();
            if (insetsController != null) insetsController.hide(WindowInsets.Type.statusBars());
        } else
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        //HOOKS
        image = findViewById(R.id.activity_start_application_logo_image);
        textLogo = findViewById(R.id.activity_start_application_logo_text_view);
        slogan = findViewById(R.id.activity_start_application_slogan_text_view);
        //startApplication = findViewById(R.id.activity_start_application);

        //ANIMATIONS
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image.setAnimation(topAnimation);
        textLogo.setAnimation(bottomAnimation);
        slogan.setAnimation(bottomAnimation);

        //DELAY METHOD
        new Handler(Looper.myLooper()).postDelayed(() -> {

            intent = new Intent(StartApplicationActivity.this, LoginActivity.class);

            pairs = new Pair[2];
            pairs[0] = new Pair<View, String>(image, "logo_image");
            pairs[1] = new Pair<View, String>(textLogo, "logo_text");

            options = ActivityOptions.makeSceneTransitionAnimation(StartApplicationActivity.this, pairs);
            startActivity(intent, options.toBundle());
        }, SPLASH_SCREEN);

        /*startApplication.setOnClickListener(v -> {
            Intent intent = new Intent(StartApplicationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });*/
    }
}