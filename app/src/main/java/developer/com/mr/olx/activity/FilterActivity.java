package developer.com.mr.olx.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import developer.com.mr.olx.R;

public class FilterActivity extends AppCompatActivity {

    String[] mobileArray = {"Price", "Brand", "Change Sort"};

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.row_filter_category, mobileArray);

        ListView listView = (ListView) findViewById(R.id.lv_tabs);
        listView.setAdapter(adapter);

    }
}
