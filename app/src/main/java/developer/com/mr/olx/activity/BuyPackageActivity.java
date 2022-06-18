package developer.com.mr.olx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import developer.com.mr.olx.R;


public class BuyPackageActivity extends AppCompatActivity {

    LinearLayout ll_cat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_pkg_activity);

        ll_cat = findViewById(R.id.ll_cat);
        ll_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CategoriesForPackageActivity.class));
            }
        });

    }
}
