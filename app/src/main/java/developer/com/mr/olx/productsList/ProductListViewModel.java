package developer.com.mr.olx.productsList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import developer.com.mr.olx.UserTest.Example;
import developer.com.mr.olx.globalApi.Repository;

public class ProductListViewModel extends AndroidViewModel {

    private Repository repository;

    public ProductListViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public MutableLiveData<List<clsProductGet>> getProfileList(int limit, String order) {
        return repository.getProductList(limit, order);
    }

    public MutableLiveData<List<Example>> getAllUserPaging(int page) {
        return repository.getUserListFromPaging(page);
    }

}
