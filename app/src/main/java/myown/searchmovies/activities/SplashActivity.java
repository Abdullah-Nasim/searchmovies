package myown.searchmovies.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import myown.searchmovies.R;
import myown.searchmovies.utils.NavigationController;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                //Call the NavigationController to start main activity
                NavigationController.startMainActivity(SplashActivity.this);
                finish();

            }
        }, SPLASH_LENGTH);
    }
}
