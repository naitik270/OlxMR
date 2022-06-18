package developer.com.mr.olx.globalApi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import developer.com.mr.olx.clsApiClasses.ClsNearLocationMastResponse;
import developer.com.mr.olx.clsApiClasses.ClsNearLocationParams;

public class NearLocationListViewModel extends AndroidViewModel {

    private Repository repository;

    public NearLocationListViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public LiveData<ClsNearLocationMastResponse> getNearLocationItemAPIList() {
        return repository.getNearLocationItemList();
    }

    public LiveData<ClsNearLocationParams> getNearLocationItemList(ClsNearLocationParams obj) {
        return repository.getNearLocationItemListPost(obj);
    }

    public LiveData<ClsNearLocationParams> getNearLocationItemListNew(ClsNearLocationParams obj) {
        return repository.getNearLocationItemListPostNew(obj);
    }

}
