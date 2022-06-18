package developer.com.mr.olx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import developer.com.mr.olx.databinding.ActivitySplashBinding;
import developer.com.mr.olx.fragments.MainFragmentActivity;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding activityMainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inflating our xml layout in our activity main binding
        activityMainBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                startActivity(new Intent(SplashActivity.this, GetLocationActivity.class));
//                startActivity(new Intent(SplashActivity.this, MainFragmentActivity.class));
                finish();
            }
        }, 2000);
    }
}
