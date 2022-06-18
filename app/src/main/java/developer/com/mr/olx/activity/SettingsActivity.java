package developer.com.mr.olx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import developer.com.mr.olx.R;

public class SettingsActivity extends AppCompatActivity {

    LinearLayout ll_change_pass;
    LinearLayout ll_notification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        ll_change_pass = findViewById(R.id.ll_change_pass);
        ll_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChangePassActivity.class));
            }
        });
        ll_notification = findViewById(R.id.ll_notification);
        ll_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NotificationSettingsActivity.class));
            }
        });
    }
}
