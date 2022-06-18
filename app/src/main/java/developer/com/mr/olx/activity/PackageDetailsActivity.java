package developer.com.mr.olx.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.adapter.TabViewPagerAdapter;
import developer.com.mr.olx.fragments.ActivePkgFragment;
import developer.com.mr.olx.fragments.ExpiredPkgFragment;

public class PackageDetailsActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    TabLayout tablayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_details_activity);

        viewPager = findViewById(R.id.viewPager);
        tablayout = findViewById(R.id.tablayout);

        List<String> lst = new ArrayList<>();
        lst.add("Active Package");
        lst.add("Expired Package");

        if (viewPager != null) {
            viewPager.setCurrentItem(1);
            viewPager.setOffscreenPageLimit(2);
            setupViewPager(viewPager);
        }

        new TabLayoutMediator(tablayout, viewPager, (tab, position) ->
                tab.setText(lst.get(position))).attach();

    }

    private void setupViewPager(ViewPager2 viewPager) {

        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager(), getLifecycle());

        adapter.addFrag(new ActivePkgFragment());
        adapter.addFrag(new ExpiredPkgFragment());

        viewPager.setAdapter(adapter);
    }

}
