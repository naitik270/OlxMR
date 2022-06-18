package developer.com.mr.olx.globalApi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import developer.com.mr.olx.clsApiClasses.ClsCategoriesResponse;
import developer.com.mr.olx.clsApiClasses.clsAllCategory;
import developer.com.mr.olx.clsApiClasses.ClsSubCategories;

public class SaleViewModel extends AndroidViewModel {

    private Repository repository;
    private MutableLiveData<List<clsAllCategory>> mMutableLiveDataCategory;
    private List<clsAllCategory> mLstAllCategory;


    public SaleViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public MutableLiveData<List<clsAllCategory>> loadData() {
        return mMutableLiveDataCategory;
    }


    public LiveData<ClsCategoriesResponse> getCategoriesAPIList() {
        return repository.getCategoriesAPIResponse();
    }

    public LiveData<ClsSubCategories> getSubCategoriesByID(int cat_id) {
        return repository.getSubCategoriesByID(cat_id);
    }


}
