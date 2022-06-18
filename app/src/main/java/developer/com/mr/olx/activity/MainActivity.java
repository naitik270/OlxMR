package developer.com.mr.olx.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import developer.com.mr.olx.R;
import developer.com.mr.olx.databinding.ActivityMainBinding;
import developer.com.mr.olx.fragments.HomeFragment;
import developer.com.mr.olx.fragments.MainFragment;
import developer.com.mr.olx.fragments.MainFragmentActivity;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.
                setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.im_home: {
                                Log.d("--click--", "im_home: ");
                            }
                            break;
                            case R.id.im_categories: {
                                Log.d("--click--", "im_chat: ");
                            }
                            break;
                            case R.id.im_post_ad: {
                                Log.d("--click--", "im_post_ad: ");
//                                startActivity(new Intent(getApplicationContext(), FillProductInformation.class));
                                startActivity(new Intent(getApplicationContext(), SaleCategoriesGridActivity.class));
                            }
                            break;
                            case R.id.im_fav: {
                                Log.d("--click--", "im_coin: ");
                            }
                            break;
                            case R.id.im_my_ads: {
                                defMainFragmentView();

                            }
                            break;
                        }
                        return true;
                    }
                });
        defView();
    }

    private void defView() {
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.flFragment,
                homeFragment, "home").commit();
    }

    private void defMainFragmentView() {
        MainFragment homeFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.flFragment,
                homeFragment, "home").commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       /* Intent intent = new Intent(this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);*/
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        finish();
    }
}