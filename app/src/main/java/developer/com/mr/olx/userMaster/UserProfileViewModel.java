package developer.com.mr.olx.userMaster;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import developer.com.mr.olx.globalApi.Repository;
import developer.com.mr.olx.productsList.clsInventoryMaster;

public class UserProfileViewModel extends AndroidViewModel {

    private Repository repository;

    public UserProfileViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public LiveData<ClsUserMaster> insertUserInfo(ClsUserMaster obj) {
        return repository.insertUserInfoPOST(obj);
    }

}
