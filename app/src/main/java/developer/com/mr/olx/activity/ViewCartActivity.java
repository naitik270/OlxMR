package developer.com.mr.olx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import developer.com.mr.olx.R;

public class ViewCartActivity extends AppCompatActivity {

    RecyclerView rv_add_to_cart;
    LinearLayout ll_buy_now;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_cart_activity);

        ll_buy_now = findViewById(R.id.ll_buy_now);
        rv_add_to_cart = findViewById(R.id.rv_add_to_cart);
        rv_add_to_cart.setLayoutManager(new LinearLayoutManager(this));
        rv_add_to_cart.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        ll_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PackageDetailsActivity.class));
            }
        });

    }
}
