package developer.com.mr.olx.globalApi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.ApiInterface.InterfaceAddProduct;
import developer.com.mr.olx.ApiInterface.InterfaceBikeScooter;
import developer.com.mr.olx.ApiInterface.InterfaceCarSell;
import developer.com.mr.olx.ApiInterface.InterfaceCategories;
import developer.com.mr.olx.ApiInterface.InterfaceCommercialVehicle;
import developer.com.mr.olx.ApiInterface.InterfaceFillProduct;
import developer.com.mr.olx.ApiInterface.InterfaceInventory;
import developer.com.mr.olx.ApiInterface.InterfaceLoginWithPhone;
import developer.com.mr.olx.ApiInterface.InterfaceMobile;
import developer.com.mr.olx.ApiInterface.InterfaceNearLocationMast;
import developer.com.mr.olx.ApiInterface.InterfaceProductList;
import developer.com.mr.olx.ApiInterface.InterfaceProfile;
import developer.com.mr.olx.ApiInterface.InterfaceProperty;
import developer.com.mr.olx.ApiInterface.InterfaceUserMaster;
import developer.com.mr.olx.ApiInterface.InterfaceUserPage;
import developer.com.mr.olx.UserTest.Datum;
import developer.com.mr.olx.UserTest.Example;
import developer.com.mr.olx.clsApiClasses.ClsAddCarRent;
import developer.com.mr.olx.clsApiClasses.ClsAddCarSell;
import developer.com.mr.olx.clsApiClasses.ClsAddMobileDetails;
import developer.com.mr.olx.clsApiClasses.ClsBikeScooter;
import developer.com.mr.olx.clsApiClasses.ClsCategoriesResponse;
import developer.com.mr.olx.clsApiClasses.ClsCommercialVehicleParams;
import developer.com.mr.olx.clsApiClasses.ClsFillProductPostParams;
import developer.com.mr.olx.clsApiClasses.ClsLoginVerifiedOTPParams;
import developer.com.mr.olx.clsApiClasses.ClsLoginWithPhoneParams;
import developer.com.mr.olx.clsApiClasses.ClsNearLocationMastResponse;
import developer.com.mr.olx.clsApiClasses.ClsNearLocationParams;
import developer.com.mr.olx.clsApiClasses.ClsRentProperty;
import developer.com.mr.olx.clsApiClasses.ClsOffice;
import developer.com.mr.olx.clsApiClasses.ClsSellProperty;
import developer.com.mr.olx.clsApiClasses.ClsUserMasterFillParams;
import developer.com.mr.olx.clsApiClasses.ClsUserMasterFillResponse;
import developer.com.mr.olx.clsApiClasses.clsAddProductParams;
import developer.com.mr.olx.clsApiClasses.clsAllCategory;
import developer.com.mr.olx.clsApiClasses.ClsSubCategories;
import developer.com.mr.olx.productsList.clsInventoryMaster;
import developer.com.mr.olx.productsList.clsProductGet;
import developer.com.mr.olx.userMaster.ClsUserMaster;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private Context context;
    private AppExecutor mAppExecutor;

    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiNjIyNzc4MGI2YTBlZjljN2Y0Y2UxM2RmIn0sImlhdCI6MTY0NzQ5MjUzMH0.8iO30Q4sjv_rs-49qwYdaRiUP8da7jxlDU7yJLNeoII";

    public Repository(Context context) {
        this.mAppExecutor = new AppExecutor();
        this.context = context;
        countryList = new ArrayList<>();
    }

    List<clsAllCategory> countryList;

    public LiveData<ClsFillProductPostParams> postFillProduct(ClsFillProductPostParams clsCustomerAddress) {
        final MutableLiveData<ClsFillProductPostParams> mutableLiveData = new MutableLiveData<>();
        InterfaceFillProduct myInterface = ApiClient.getRetrofitInstance().create(InterfaceFillProduct.class);

        Call<ClsFillProductPostParams> call = myInterface.PostFillProduct(clsCustomerAddress);
        Log.e("--URL--", "call: " + call.request().url());

        call.enqueue(new Callback<ClsFillProductPostParams>() {
            @Override
            public void onResponse(Call<ClsFillProductPostParams> call, Response<ClsFillProductPostParams> response) {

                if (response.body() != null) {
                    Log.d("--URL--", "IDIFIFIF");
                } else {
                    Log.d("--URL--", "ELSLSELE");
                }
               /* ClsFillProductPostParams clsFillProductPost = response.body();
                Gson gson = new Gson();
                String jsonInString = gson.toJson(clsFillProductPost);
                Log.d("--URL--", "onResponse: " + jsonInString);*/
            }

            @Override
            public void onFailure(Call<ClsFillProductPostParams> call, Throwable t) {
                try {
                    mutableLiveData.postValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: " + t.toString());
            }
        });
        return mutableLiveData;
    }

    public LiveData<clsAddProductParams> postAddProduct(clsAddProductParams clsCustomerAddress) {
        final MutableLiveData<clsAddProductParams> mutableLiveData = new MutableLiveData<>();
        InterfaceAddProduct myInterface = ApiClient.getRetrofitInstance().create(InterfaceAddProduct.class);

        Call<clsAddProductParams> call =
                myInterface.PostFillProduct(token,
                        clsCustomerAddress);
        Log.e("--URL--", "call: " + call.request().url());

        call.enqueue(new Callback<clsAddProductParams>() {
            @Override
            public void onResponse(Call<clsAddProductParams> call, Response<clsAddProductParams> response) {
                clsAddProductParams clsFillProductPost = response.body();

                if (clsFillProductPost != null) {
                    Toast.makeText(context,
                            String.format("User: ",
                                    clsFillProductPost.getTitle(),
                                    clsFillProductPost.getCategory(),
                                    clsFillProductPost.getPrice(),
                                    clsFillProductPost.getDescription()),
                            Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(context,
                            String.format("Response is %s", String.valueOf(response.code()))
                            , Toast.LENGTH_SHORT).show();
                }

                mutableLiveData.postValue(clsFillProductPost);

               /* clsAddProductParams clsFillProductPost = response.body();
                Gson gson = new Gson();
                String jsonInString = gson.toJson(clsFillProductPost);
                Log.d("--URL--", "onResponse: " + jsonInString);*/
            }

            @Override
            public void onFailure(Call<clsAddProductParams> call, Throwable t) {
                try {
                    mutableLiveData.postValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: " + t.toString());
            }
        });
        return mutableLiveData;
    }


    public MutableLiveData<List<clsProductGet>> getProductList(int limit, String order) {
        final MutableLiveData<List<clsProductGet>> mutableLiveData = new MutableLiveData<>();

        InterfaceProductList myInterface = ApiClient.getRetrofitInstance().create(InterfaceProductList.class);
        Call<List<clsProductGet>> call = myInterface.getProductList(token, limit, order);

        Log.e("--URL--", "getProductList: " + call.request().url());

        call.enqueue(new Callback<List<clsProductGet>>() {
            @Override
            public void onResponse(Call<List<clsProductGet>> call, Response<List<clsProductGet>> response) {

                mutableLiveData.setValue(response.body());
                Gson gson = new Gson();
                String jsonInString = gson.toJson(response.body());
                Log.d("--URL--", "onResponse: " + jsonInString);
                Log.e("--URL--", "code: " + response.code());

            }

            @Override
            public void onFailure(Call<List<clsProductGet>> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure : getProfile " + t.toString());
            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<List<Example>> getUserListFromPaging(int page) {
        final MutableLiveData<List<Example>> mutableLiveData = new MutableLiveData<>();

        InterfaceUserPage myInterface = ApiClientUser.getRetrofitInstance().create(InterfaceUserPage.class);
        Call<Example> call = myInterface.getAllUserList(page);

        Log.e("--URL--", "getProductList: " + call.request().url());

        /*call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                final List<Example>[] listState = new List[]{response.body()};

//                mutableLiveData.setValue(response.body());

                Gson gson = new Gson();
                String jsonInString = gson.toJson(responseLst);
                Log.d("--URL--", "onResponse: " + jsonInString);
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                try {
//                    mutableLiveData.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure : getProfile " + t.toString());
            }
        });*/


        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.body() != null) {
                    List<Datum> responseLst = response.body().getData();
                    Log.e("--URL--", "code: " + responseLst.size());
                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(responseLst);
                    Log.d("--URL--", "onResponse: " + jsonInString);
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                try {
//                    mutableLiveData.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure : getProfile " + t.toString());
            }
        });
        return mutableLiveData;
    }

    public LiveData<clsInventoryMaster> AddNewInventory(clsInventoryMaster obj) {
        final MutableLiveData<clsInventoryMaster> mutableLiveData = new MutableLiveData<>();
        InterfaceInventory myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceInventory.class);
        Call<clsInventoryMaster> call = myInterface.postAddInventory(obj);
        Log.e("--response--", "NewAppointments: " + call.request().url());
        call.enqueue(new Callback<clsInventoryMaster>() {
            @Override
            public void onResponse(Call<clsInventoryMaster> call, Response<clsInventoryMaster> response) {
                Log.d("--response--", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<clsInventoryMaster> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                    Log.d("--response--", "onFailure: " + t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return mutableLiveData;
    }

    public LiveData<ClsAddMobileDetails> AddNewMobileDetails(ClsAddMobileDetails obj) {
        final MutableLiveData<ClsAddMobileDetails> mutableLiveData = new MutableLiveData<>();
        InterfaceMobile myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceMobile.class);
        Call<ClsAddMobileDetails> call = myInterface.postAddMobile(obj);
        Log.e("--Mobile--", "NewAppointments: " + call.request().url());

        call.enqueue(new Callback<ClsAddMobileDetails>() {
            @Override
            public void onResponse(Call<ClsAddMobileDetails> call, Response<ClsAddMobileDetails> response) {
                Log.d("--Mobile--", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsAddMobileDetails> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                    Log.d("--Mobile--", "onFailure: " + t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return mutableLiveData;
    }

    public LiveData<ClsAddCarSell> AddCarSell(ClsAddCarSell obj) {
        final MutableLiveData<ClsAddCarSell> mutableLiveData = new MutableLiveData<>();
        InterfaceCarSell myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceCarSell.class);
        Call<ClsAddCarSell> call = myInterface.postAddCarSell(obj);
        Log.e("--Mobile--", "NewAppointments: " + call.request().url());

        call.enqueue(new Callback<ClsAddCarSell>() {
            @Override
            public void onResponse(Call<ClsAddCarSell> call, Response<ClsAddCarSell> response) {
                Log.d("--Mobile--", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsAddCarSell> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                    Log.d("--Mobile--", "onFailure: " + t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return mutableLiveData;
    }

    public LiveData<ClsAddCarRent> AddCarRent(ClsAddCarRent obj) {
        final MutableLiveData<ClsAddCarRent> mutableLiveData = new MutableLiveData<>();
        InterfaceCarSell myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceCarSell.class);
        Call<ClsAddCarRent> call = myInterface.postAddCarRent(obj);
        Log.e("--Mobile--", "NewAppointments: " + call.request().url());

        call.enqueue(new Callback<ClsAddCarRent>() {
            @Override
            public void onResponse(Call<ClsAddCarRent> call, Response<ClsAddCarRent> response) {
                Log.d("--Mobile--", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsAddCarRent> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                    Log.d("--Mobile--", "onFailure: " + t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return mutableLiveData;
    }

    public LiveData<ClsCategoriesResponse> getCategoriesAPIResponse() {

        final MutableLiveData<ClsCategoriesResponse> mCategoriesLst = new MutableLiveData<>();
        InterfaceCategories myInterface =
                ApiClient.getRetrofitInstanceNewAPI().create(InterfaceCategories.class);
        Call<ClsCategoriesResponse> call = myInterface.getCategoryList();

        Log.e("--repo--", "URL: " + call.request().url());

        call.enqueue(new Callback<ClsCategoriesResponse>() {
            @Override
            public void onResponse(Call<ClsCategoriesResponse> call, Response<ClsCategoriesResponse> response) {
                Log.e("--repo--", "onRequestResponse: " + response.code());
                if (response.body() != null && response.code() == 200) {
                    mCategoriesLst.setValue(response.body());
                    Log.d("--repo--", "onResponse: " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ClsCategoriesResponse> call, Throwable t) {
                try {
                    mCategoriesLst.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--repo--", "onFailure: " + t.toString());
            }
        });
        return mCategoriesLst;
    }

    public LiveData<ClsSubCategories> getSubCategoriesByID(int cat_id) {
        final MutableLiveData<ClsSubCategories> mSubCatLst = new MutableLiveData<>();
        InterfaceCategories myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceCategories.class);

        Call<ClsSubCategories> call = myInterface.getSubCategories(cat_id);
        Log.e("--subCat--", "getSubCategoriesByID: " + call.request().url());

        call.enqueue(new Callback<ClsSubCategories>() {
            @Override
            public void onResponse(Call<ClsSubCategories> call,
                                   Response<ClsSubCategories> response) {
                if (response.body() != null && response.code() == 200) {
                    mSubCatLst.setValue(response.body());
                    Log.d("--subCat--", "onResponse: " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ClsSubCategories> call, Throwable t) {
                try {
                    mSubCatLst.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return mSubCatLst;
    }


    public LiveData<ClsUserMaster> insertUserInfoPOST(ClsUserMaster obj) {
        final MutableLiveData<ClsUserMaster> mutableLiveData = new MutableLiveData<>();
        InterfaceProfile myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceProfile.class);
        Call<ClsUserMaster> call = myInterface.postProfile(obj);
        Log.e("--pro--", "NewAppointments: " + call.request().url());
        call.enqueue(new Callback<ClsUserMaster>() {
            @Override
            public void onResponse(Call<ClsUserMaster> call, Response<ClsUserMaster> response) {
                Log.e("--pro--", "code:" + response.code());
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                    Log.d("--pro--", "onResponse: " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ClsUserMaster> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--pro--", "onFailure: " + t.toString());
            }
        });
        return mutableLiveData;
    }


    public LiveData<ClsSellProperty> postSellPropertyFormAPI(ClsSellProperty obj) {
        final MutableLiveData<ClsSellProperty> mutableLiveData = new MutableLiveData<>();
        InterfaceProperty myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceProperty.class);
        Call<ClsSellProperty> call = myInterface.postSellProperty(obj);
        Log.e("--Mobile--", "NewAppointments: " + call.request().url());

        call.enqueue(new Callback<ClsSellProperty>() {
            @Override
            public void onResponse(Call<ClsSellProperty> call, Response<ClsSellProperty> response) {
                Log.d("--Mobile--", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsSellProperty> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                    Log.d("--Mobile--", "onFailure: " + t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return mutableLiveData;
    }

    public LiveData<ClsRentProperty> postRentPropertyFormAPI(ClsRentProperty obj) {
        final MutableLiveData<ClsRentProperty> mutableLiveData = new MutableLiveData<>();
        InterfaceProperty myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceProperty.class);
        Call<ClsRentProperty> call = myInterface.postRentProperty(obj);
        Log.e("--Mobile--", "NewAppointments: " + call.request().url());

        call.enqueue(new Callback<ClsRentProperty>() {
            @Override
            public void onResponse(Call<ClsRentProperty> call, Response<ClsRentProperty> response) {
                Log.d("--Mobile--", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsRentProperty> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                    Log.d("--Mobile--", "onFailure: " + t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return mutableLiveData;
    }

    public LiveData<ClsOffice> postOfficeFormAPI(ClsOffice obj) {
        final MutableLiveData<ClsOffice> mutableLiveData = new MutableLiveData<>();
        InterfaceProperty myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceProperty.class);
        Call<ClsOffice> call = myInterface.postOffice(obj);
        Log.e("--Mobile--", "NewAppointments: " + call.request().url());

        call.enqueue(new Callback<ClsOffice>() {
            @Override
            public void onResponse(Call<ClsOffice> call, Response<ClsOffice> response) {
                Log.d("--Mobile--", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsOffice> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                    Log.d("--Mobile--", "onFailure: " + t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return mutableLiveData;
    }

    public LiveData<ClsBikeScooter> postBikeScooterFormAPI(ClsBikeScooter obj) {
        final MutableLiveData<ClsBikeScooter> mutableLiveData = new MutableLiveData<>();
        InterfaceBikeScooter myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceBikeScooter.class);
        Call<ClsBikeScooter> call = myInterface.postBikeScooter(obj);
        Log.e("--Mobile--", "NewAppointments: " + call.request().url());

        call.enqueue(new Callback<ClsBikeScooter>() {
            @Override
            public void onResponse(Call<ClsBikeScooter> call, Response<ClsBikeScooter> response) {
                Log.d("--Mobile--", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsBikeScooter> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                    Log.d("--Mobile--", "onFailure: " + t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return mutableLiveData;
    }

    public LiveData<ClsCommercialVehicleParams> postCommercialVehicleFormAPI(ClsCommercialVehicleParams obj) {
        final MutableLiveData<ClsCommercialVehicleParams> mutableLiveData = new MutableLiveData<>();
        InterfaceCommercialVehicle myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceCommercialVehicle.class);
        Call<ClsCommercialVehicleParams> call = myInterface.postCommercialVehicle(obj);
        Log.e("--CV--", "NewAppointments: " + call.request().url());

        call.enqueue(new Callback<ClsCommercialVehicleParams>() {
            @Override
            public void onResponse(Call<ClsCommercialVehicleParams> call, Response<ClsCommercialVehicleParams> response) {
                Log.d("--CV--", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsCommercialVehicleParams> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                    Log.d("--CV--", "onFailure: " + t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return mutableLiveData;
    }


    public LiveData<ClsNearLocationMastResponse> getNearLocationItemList() {

        final MutableLiveData<ClsNearLocationMastResponse> mCategoriesLst = new MutableLiveData<>();
        InterfaceNearLocationMast myInterface =
                ApiClient.getRetrofitInstanceGET().create(InterfaceNearLocationMast.class);
        Call<ClsNearLocationMastResponse> call = myInterface.getNearLocationList();

        Log.e("--repo--", "URL: " + call.request().url());

        call.enqueue(new Callback<ClsNearLocationMastResponse>() {
            @Override
            public void onResponse(Call<ClsNearLocationMastResponse> call, Response<ClsNearLocationMastResponse> response) {
                Log.e("--repo--", "onRequestResponse: " + response.code());
                if (response.body() != null && response.code() == 200) {
                    mCategoriesLst.setValue(response.body());
                    Log.d("--repo--", "onResponse: " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ClsNearLocationMastResponse> call, Throwable t) {
                try {
                    mCategoriesLst.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--repo--", "onFailure: " + t.toString());
            }
        });
        return mCategoriesLst;
    }

    public LiveData<ClsNearLocationParams> getNearLocationItemListPost(ClsNearLocationParams obj) {

        final MutableLiveData<ClsNearLocationParams> mCategoriesLst = new MutableLiveData<>();
        InterfaceNearLocationMast myInterface =
                ApiClient.getRetrofitInstanceNewAPI().create(InterfaceNearLocationMast.class);
        Call<ClsNearLocationParams> call = myInterface.getNearLocationListPost(obj);

        Log.e("--repo--", "URL: " + call.request().url());

        call.enqueue(new Callback<ClsNearLocationParams>() {
            @Override
            public void onResponse(Call<ClsNearLocationParams> call, Response<ClsNearLocationParams> response) {
                Log.e("--repo--", "onRequestResponse: " + response.code());
                if (response.body() != null && response.code() == 200) {
                    mCategoriesLst.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsNearLocationParams> call, Throwable t) {
                try {
                    mCategoriesLst.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--repo--", "onFailure: " + t.toString());
            }
        });
        return mCategoriesLst;
    }


    public LiveData<ClsLoginWithPhoneParams> postUserMasterOTPApi(ClsLoginWithPhoneParams obj) {
        final MutableLiveData<ClsLoginWithPhoneParams> mutableLiveData = new MutableLiveData<>();
        InterfaceLoginWithPhone myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceLoginWithPhone.class);
        Call<ClsLoginWithPhoneParams> call = myInterface.postUserSendOTP(obj);
        Log.e("--Mobile--", "NewAppointments: " + call.request().url());

        call.enqueue(new Callback<ClsLoginWithPhoneParams>() {
            @Override
            public void onResponse(Call<ClsLoginWithPhoneParams> call, Response<ClsLoginWithPhoneParams> response) {
                Log.d("--Mobile--", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsLoginWithPhoneParams> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                    Log.d("--Mobile--", "onFailure: " + t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return mutableLiveData;
    }


    public LiveData<ClsLoginVerifiedOTPParams> postUserVerifiedOTPApi(ClsLoginVerifiedOTPParams obj) {
        final MutableLiveData<ClsLoginVerifiedOTPParams> mutableLiveData = new MutableLiveData<>();
        InterfaceLoginWithPhone myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceLoginWithPhone.class);
        Call<ClsLoginVerifiedOTPParams> call = myInterface.postVerifiedOTP(obj);
        Log.e("--Mobile--", "NewAppointments: " + call.request().url());

        call.enqueue(new Callback<ClsLoginVerifiedOTPParams>() {
            @Override
            public void onResponse(Call<ClsLoginVerifiedOTPParams> call, Response<ClsLoginVerifiedOTPParams> response) {
                Log.d("--Mobile--", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsLoginVerifiedOTPParams> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                    Log.d("--Mobile--", "onFailure: " + t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return mutableLiveData;
    }

    public LiveData<ClsUserMasterFillParams> postUserMasterFillApi(ClsUserMasterFillParams obj) {
        final MutableLiveData<ClsUserMasterFillParams> mutableLiveData = new MutableLiveData<>();
        InterfaceUserMaster myInterface = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceUserMaster.class);
        Call<ClsUserMasterFillParams> call = myInterface.postUserMasterFill(obj);
        Log.e("--FillAPI--", "NewAppointments: " + call.request().url());

        call.enqueue(new Callback<ClsUserMasterFillParams>() {
            @Override
            public void onResponse(Call<ClsUserMasterFillParams> call, Response<ClsUserMasterFillParams> response) {
                Log.d("--FillAPI--", "onResponse: " + new Gson().toJson(response.body()));
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsUserMasterFillParams> call, Throwable t) {
                try {
                    mutableLiveData.setValue(null);
                    Log.d("--FillAPI--", "onFailure: " + t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return mutableLiveData;
    }

    public LiveData<ClsUserMasterFillParams> getUserMasterFillByLoginID(ClsUserMasterFillParams obj) {
        final MutableLiveData<ClsUserMasterFillParams> mSubCatLst = new MutableLiveData<>();
        InterfaceUserMaster myInterface = ApiClient.getRetrofitInstanceGET().create(InterfaceUserMaster.class);
        Call<ClsUserMasterFillParams> call = myInterface.getUserMasterFill(obj);
        Log.e("--subCat--", "getSubCategoriesByID: " + call.request().url());
        call.enqueue(new Callback<ClsUserMasterFillParams>() {
            @Override
            public void onResponse(Call<ClsUserMasterFillParams> call,
                                   Response<ClsUserMasterFillParams> response) {
                if (response.body() != null && response.code() == 200) {
                    mSubCatLst.setValue(response.body());
                    Log.d("--subCat--", "onResponse: " + new Gson().toJson(response.body()));
                }
            }
            @Override
            public void onFailure(Call<ClsUserMasterFillParams> call, Throwable t) {
                try {
                    mSubCatLst.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return mSubCatLst;
    }


    public LiveData<ClsNearLocationParams> getNearLocationItemListPostNew(ClsNearLocationParams obj) {

        final MutableLiveData<ClsNearLocationParams> mCategoriesLst = new MutableLiveData<>();
        InterfaceNearLocationMast myInterface =
                ApiClient.getRetrofitInstanceNewAPI().create(InterfaceNearLocationMast.class);
        Call<ClsNearLocationParams> call = myInterface.getNearLocationListPost(obj);

        Log.e("--repo--", "URL: " + call.request().url());

        call.enqueue(new Callback<ClsNearLocationParams>() {
            @Override
            public void onResponse(Call<ClsNearLocationParams> call, Response<ClsNearLocationParams> response) {
                Log.e("--repo--", "onRequestResponse: " + response.code());
                if (response.body() != null && response.code() == 200) {
                    mCategoriesLst.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsNearLocationParams> call, Throwable t) {
                try {
                    mCategoriesLst.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--repo--", "onFailure: " + t.toString());
            }
        });
        return mCategoriesLst;
    }





}
