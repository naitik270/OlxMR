package developer.com.mr.olx.globalApi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import developer.com.mr.olx.clsApiClasses.ClsFillProductPost;
import developer.com.mr.olx.clsApiClasses.ClsFillProductPostParams;
import developer.com.mr.olx.clsApiClasses.clsAddProductParams;

public class AddProductViewModel extends AndroidViewModel {
    private Repository repository;

    public AddProductViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public LiveData<ClsFillProductPostParams> addProductDetails(ClsFillProductPostParams clsFillProductPost) {
        return repository.postFillProduct(clsFillProductPost);
    }

    public LiveData<clsAddProductParams> addProductDetails(clsAddProductParams obj) {
        return repository.postAddProduct(obj);
    }

}
