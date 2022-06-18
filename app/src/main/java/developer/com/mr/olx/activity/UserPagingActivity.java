package developer.com.mr.olx.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

import developer.com.mr.olx.R;
import developer.com.mr.olx.UserTest.Example;
import developer.com.mr.olx.adapter.UserRVAdapter;
import developer.com.mr.olx.globalApi.AddProductViewModel;
import developer.com.mr.olx.productsList.ProductListViewModel;
import developer.com.mr.olx.productsList.UserModal;

public class UserPagingActivity extends AppCompatActivity {


    private ArrayList<UserModal> userModalArrayList;
    private UserRVAdapter userRVAdapter;
    private RecyclerView userRV;
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;
    int pageNo = 0, limit = 2;
    private ProductListViewModel productListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaaaaaa);
        productListViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);

        // creating a new array list.
        userModalArrayList = new ArrayList<>();

        // initializing our views.
        userRV = findViewById(R.id.idRVUsers);
        loadingPB = findViewById(R.id.idPBLoading);
        nestedSV = findViewById(R.id.idNestedSV);

        try {
            callSaleCategory(2, 6);
        } catch (Exception e) {
            Log.d("--URL--", "Exception: " + e.getMessage());
        }

    }

    private void getDataFromAPI(int page, int limit) {
        if (page > limit) {
            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();
            loadingPB.setVisibility(View.GONE);
        }
    }

    void callSaleCategory(int pageNo, int limit) {

      /*  saleViewModel.loadData().observe(this, clsAllCategories -> {
            String[] oneHeroes = new String[clsAllCategories.size()];
            for (int i = 0; i < clsAllCategories.size(); i++) {
                oneHeroes[i] = clsAllCategories.get(i).getName();
            }
            Log.d("--URL--", "onChanged: " + oneHeroes.length);
            idGVcourses.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_1, oneHeroes));798109389 36acdind
        });*/

        if (pageNo > limit) {
            // checking if the page number is greater than limit.
            // displaying toast message in this case when page>limit.
            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();
            // hiding our progress bar.
            loadingPB.setVisibility(View.GONE);
            return;
        }

        productListViewModel.getAllUserPaging(pageNo).observe(this, userModals -> {
            try {
                Log.d("--URL--", "size: " + userModals.size());
            } catch (Exception e) {
                Log.d("--URL--", "Exception: " + e.getMessage());
            }
        });

    }


}
