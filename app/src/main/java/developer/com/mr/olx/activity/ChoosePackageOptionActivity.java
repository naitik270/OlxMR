package developer.com.mr.olx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import developer.com.mr.olx.R;

public class ChoosePackageOptionActivity extends AppCompatActivity {

    String cat_name = "";
    int cat_id = 0;
    LinearLayout ll_buy_now;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_pkg_option_activity);

        ll_buy_now = findViewById(R.id.ll_buy_now);
        cat_id = getIntent().getIntExtra("cat_id", 0);
        cat_name = getIntent().getStringExtra("cat_name");

        ll_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), ViewCartActivity.class));


            }
        });

    }


}
