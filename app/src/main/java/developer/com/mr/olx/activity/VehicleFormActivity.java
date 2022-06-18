package developer.com.mr.olx.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import developer.com.mr.olx.R;

public class VehicleFormActivity extends AppCompatActivity {
    String sub_title = "";
    TextView txt_title;
    LinearLayout ll_vehicle_type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_form);
        sub_title = getIntent().getStringExtra("sub_title");
        ll_vehicle_type = findViewById(R.id.ll_vehicle_type);
        txt_title = findViewById(R.id.txt_title);
        txt_title.setText("Add " + sub_title + " details");
    }
}
