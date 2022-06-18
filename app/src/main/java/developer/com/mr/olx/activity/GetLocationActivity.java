package developer.com.mr.olx.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.global.AnimationAutoTextScroller;
import developer.com.mr.olx.global.ClsGlobal;
import developer.com.mr.olx.global.ClsPreferencesInfo;
import developer.com.mr.olx.global.LocationLiveData;

import static developer.com.mr.olx.global.ClsGlobal.isConnectedToInternet;

public class GetLocationActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 123;
    LinearLayout ll_get_current_location;
    TextView txtAddress;
    TextView txt_longitude;
    TextView txt_latitude;
    TextView txt_main_location;
    ProgressBar progressBar;
    double Latitude = 0.0;
    double Longitude = 0.0;
    String CaptureAddress = "";
    AnimationAutoTextScroller textscroller;
    String location_mode = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_current_location);

        init();
    }


    void init() {
        obj = new ClsPreferencesInfo();
        location_mode = getIntent().getStringExtra("location_mode");
        txt_main_location = findViewById(R.id.txt_main_location);
        txt_longitude = findViewById(R.id.txt_longitude);
        progressBar = findViewById(R.id.progressBar);
        txtAddress = findViewById(R.id.txtAddress);
        txt_latitude = findViewById(R.id.txt_latitude);
        ll_get_current_location = findViewById(R.id.ll_get_current_location);
        getFinalLocation("noClick");


        ll_get_current_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

            /*    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(GetLocationActivity.this, new String[]{
                                    Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSION);
                } else {
                    getFinalLocation();
                }
*/
                getFinalLocation("yesClick");
            }
        });
    }

    ClsPreferencesInfo obj;

    void getFinalLocation(String mode) {

        observeOnce(LocationLiveData.getInstance()
                        .getLiveLocation(GetLocationActivity.this)
                , LocationWithAddress -> {
                    Latitude = LocationWithAddress.getLatitude();
                    Longitude = LocationWithAddress.getLongitude();
                    CaptureAddress = LocationWithAddress.getAddress();
                });

        Log.d("--location--", "Latitude: " + Latitude);
        Log.d("--location--", "Longitude: " + Longitude);
        Log.d("--location--", "CaptureAddress: " + CaptureAddress);

        progressBar.setVisibility(View.GONE);
        txtAddress.setText(CaptureAddress);
        txt_latitude.setText("Latitude: " + Latitude);
        txt_longitude.setText("Longitude: " + Longitude);

        txt_main_location.setText(knownName.concat(", ")
                .concat(subLocality).concat(", ")
                .concat(localityString));

        if (mode != null && mode.equalsIgnoreCase("yesClick")) {
            if (location_mode != null) {
                if (location_mode.equalsIgnoreCase("inForm")) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent();
                            intent.putExtra("get_location", knownName.concat(", ").concat(subLocality).concat(", ").concat(localityString));
                            intent.putExtra("get_latitude", Latitude);
                            intent.putExtra("get_longitude", Longitude);
                            setResult(9990999, intent);
                            finish();
                        }
                    }, 1000);
                } else {
                    obj.setCurrentLocation(knownName.concat(", ").concat(subLocality).concat(", ").concat(localityString));
                    obj.setCurrentLatitude(Latitude);
                    obj.setCurrentLongitude(Longitude);
                    ClsGlobal.setPreferencesInfo(obj, GetLocationActivity.this);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1000);
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please set current location...!", Toast.LENGTH_LONG).show();
        }

    }

    public static <T> void observeOnce(final LiveData<T> liveData,
                                       final Observer<T> observer) {
        liveData.observeForever(new Observer<T>() {
            @Override
            public void onChanged(T t) {
                liveData.removeObserver(this);
                observer.onChanged(t);
            }
        });
    }

    public static String stateName = "";
    public static String country = "";
    public static String postalCode = "";
    public static String knownName = "";
    public static String zipcode = "";
    public static String region_code = "";
    public static String subLocality = "";
    public static String localityString = "";

    public static String getCurrentLocation(Context context, Double Latitude, Double Longitude) {
        String address = "";
        try {
            if (isConnectedToInternet(context) && Latitude != null
                    && Longitude != null) {
                Geocoder geocoder;
                List<Address> addresses = new ArrayList<>();
                geocoder = new Geocoder(context);
                addresses = geocoder.getFromLocation(Latitude, Longitude, 1);

                if (addresses.size() > 0) {

                    address = addresses.get(0).getAddressLine(0);
                    stateName = addresses.get(0).getAdminArea();
                    country = addresses.get(0).getCountryName();
                    postalCode = addresses.get(0).getPostalCode();
                    knownName = addresses.get(0).getFeatureName();
                    localityString = addresses.get(0).getLocality();
                    subLocality = addresses.get(0).getSubLocality();
                    region_code = addresses.get(0).getCountryCode();
                    zipcode = addresses.get(0).getPostalCode();

                    Log.d("--location--", "stateName: " + stateName);
                    Log.d("--location--", "country: " + country);
                    Log.d("--location--", "postalCode: " + postalCode);
                    Log.d("--location--", "knownName: " + knownName);
                    Log.d("--location--", "localityString: " + localityString);
                    Log.d("--location--", "subLocality: " + subLocality);
                    Log.d("--location--", "region_code: " + region_code);
                    Log.d("--location--", "zipcode: " + zipcode);

                }
            }
        } catch (Exception e) {
            Log.e("--location--", "Exception: " + e.getMessage());
        }
        return address;
    }

}
