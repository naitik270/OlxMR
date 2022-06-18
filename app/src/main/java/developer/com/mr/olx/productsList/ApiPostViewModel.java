package developer.com.mr.olx.productsList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import developer.com.mr.olx.clsApiClasses.ClsAddCarRent;
import developer.com.mr.olx.clsApiClasses.ClsAddCarSell;
import developer.com.mr.olx.clsApiClasses.ClsAddMobileDetails;
import developer.com.mr.olx.clsApiClasses.ClsBikeScooter;
import developer.com.mr.olx.clsApiClasses.ClsCommercialVehicleParams;
import developer.com.mr.olx.clsApiClasses.ClsLoginVerifiedOTPParams;
import developer.com.mr.olx.clsApiClasses.ClsLoginWithPhoneParams;
import developer.com.mr.olx.clsApiClasses.ClsRentProperty;
import developer.com.mr.olx.clsApiClasses.ClsOffice;
import developer.com.mr.olx.clsApiClasses.ClsSellProperty;
import developer.com.mr.olx.clsApiClasses.ClsSubCategories;
import developer.com.mr.olx.clsApiClasses.ClsUserMasterFillParams;
import developer.com.mr.olx.clsApiClasses.ClsUserMasterFillResponse;
import developer.com.mr.olx.globalApi.Repository;

public class ApiPostViewModel extends AndroidViewModel {

    private Repository repository;

    public ApiPostViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public LiveData<clsInventoryMaster> AddNewInventory(clsInventoryMaster obj) {
        return repository.AddNewInventory(obj);
    }

    public LiveData<ClsAddMobileDetails> AddNewMobileDetails(ClsAddMobileDetails obj) {
        return repository.AddNewMobileDetails(obj);
    }

    public LiveData<ClsAddCarSell> AddNewCarSellDetails(ClsAddCarSell obj) {
        return repository.AddCarSell(obj);
    }

    public LiveData<ClsAddCarRent> AddNewCarRentDetails(ClsAddCarRent obj) {
        return repository.AddCarRent(obj);
    }

    public LiveData<ClsSellProperty> FillSellPropertyForm(ClsSellProperty obj) {
        return repository.postSellPropertyFormAPI(obj);
    }

    public LiveData<ClsRentProperty> FillRentPropertyForm(ClsRentProperty obj) {
        return repository.postRentPropertyFormAPI(obj);
    }

    public LiveData<ClsOffice> FillOfficeForm(ClsOffice obj) {
        return repository.postOfficeFormAPI(obj);
    }

    public LiveData<ClsBikeScooter> FillBikeScooterForm(ClsBikeScooter obj) {
        return repository.postBikeScooterFormAPI(obj);
    }

    public LiveData<ClsCommercialVehicleParams> FillCommercialVehicleForm(ClsCommercialVehicleParams obj) {
        return repository.postCommercialVehicleFormAPI(obj);
    }

    public LiveData<ClsLoginWithPhoneParams> UserMasterOTP(ClsLoginWithPhoneParams obj) {
        return repository.postUserMasterOTPApi(obj);
    }

    public LiveData<ClsLoginVerifiedOTPParams> UserVerifiedMasterOTP(ClsLoginVerifiedOTPParams obj) {
        return repository.postUserVerifiedOTPApi(obj);
    }

    public LiveData<ClsUserMasterFillParams> UserMasterFill(ClsUserMasterFillParams obj) {
        return repository.postUserMasterFillApi(obj);
    }


    public LiveData<ClsUserMasterFillParams> getUserMasterFillByLoginID(ClsUserMasterFillParams obj) {
        return repository.getUserMasterFillByLoginID(obj);
    }


}
