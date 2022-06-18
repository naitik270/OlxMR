package developer.com.mr.olx.global;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import android.location.LocationListener;

import pub.devrel.easypermissions.EasyPermissions;
import developer.com.mr.olx.globalApi.AppExecutor;

import static developer.com.mr.olx.global.ClsGlobal.LOCATION_PERMISSIONS_ABOVE_30;
import static developer.com.mr.olx.global.ClsGlobal.LOCATION_PERMISSIONS_BELOW_30;

public class MyLocation {



    protected LocationManager locationManager;

    OnLocationReceivedListener onLocationReceivedListener;
    AppExecutor mAppExecutor;
    private FusedLocationProviderClient fusedLocationClient;
    Context context;
    LocationCallback locationCallback;
    @SuppressLint("StaticFieldLeak")


    public MyLocation(Context context) {
        this.context = context;
        mAppExecutor = new AppExecutor();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);


    }

    public void setOnLocationReceivedListener(OnLocationReceivedListener onLocationReceivedListener) {
        this.onLocationReceivedListener = onLocationReceivedListener;
    }

    @SuppressLint("MissingPermission")
    public void getLocation(String provider) {


        locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);


        Criteria locationCriteria = new Criteria();
        locationCriteria.setAccuracy(Criteria.ACCURACY_FINE);

        if (EasyPermissions.hasPermissions(context, Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
                ? LOCATION_PERMISSIONS_ABOVE_30 : LOCATION_PERMISSIONS_BELOW_30)) {
            if (locationManager != null && locationManager
                    .isProviderEnabled(provider)) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    try {
                        locationManager.getCurrentLocation(
                                LocationManager.GPS_PROVIDER,
                                null,
                                mAppExecutor.mainThread(), location -> {

                                    if (onLocationReceivedListener != null) {

                                        onLocationReceivedListener.OnLocationReceived(location);
                                    }

                                });
                    } catch (Exception e) {
                        if (onLocationReceivedListener != null) {
                            onLocationReceivedListener.OnLocationReceived(null);
                        }

                    }


                } else {
                    try {
                        locationManager.requestSingleUpdate(locationCriteria,
                                new LocationListener() {
                                    @Override
                                    public void onLocationChanged(Location location) {
                                        Log.e("tag", "isFromMockProvider sdf: "
                                                + location.isFromMockProvider());
                                        if (onLocationReceivedListener != null) {
                                            onLocationReceivedListener.OnLocationReceived(location);
                                        }
                                        if (locationManager != null) {
                                            locationManager.removeUpdates(this);
                                        }

                                    }

                                    @Override
                                    public void onStatusChanged(String provider,
                                                                int status, Bundle extras) {
                                    }

                                    @Override
                                    public void onProviderEnabled(String provider) {
                                    }

                                    @Override
                                    public void onProviderDisabled(String provider) {

                                    }
                                }, null);
                    } catch (Exception ignored) {
                        if (onLocationReceivedListener != null) {

                            onLocationReceivedListener.OnLocationReceived(null);
                        }

                    }

                }

            } else {
                if (onLocationReceivedListener != null) {

                    onLocationReceivedListener.OnLocationReceived(null);
                }
            }
        } else {
            if (onLocationReceivedListener != null) {

                onLocationReceivedListener.OnLocationReceived(null);
            }
        }


    }


    @SuppressLint("MissingPermission")
    public void getFusedLocationLocation() {

        if (EasyPermissions.hasPermissions(context, Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.Q ?
                LOCATION_PERMISSIONS_ABOVE_30
                : LOCATION_PERMISSIONS_BELOW_30)) {
            long interval = 10 * 1000;
            long fastestInterval = 5 * 1000;
            LocationRequest mLocationRequest = LocationRequest.create();
            mLocationRequest.setInterval(interval);
            mLocationRequest.setFastestInterval(fastestInterval);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setSmallestDisplacement(0);

            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    super.onLocationResult(locationResult);

                    if (onLocationReceivedListener != null) {

                        onLocationReceivedListener.OnLocationReceived(
                                locationResult.getLastLocation());
                    }

                    fusedLocationClient.removeLocationUpdates(this);
                }

                @Override
                public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
                    super.onLocationAvailability(locationAvailability);

                    if (!locationAvailability.isLocationAvailable()) {
                        getLastLocation();
//                    onLocationReceivedListener.OnLocationReceived(null);
                    }
//                    else {
//                        Log.d("tag", "locationAvailability.isLocationAvailable() null" );
//                        onLocationReceivedListener.OnLocationReceived(null);
//                    }

                }
            };


            fusedLocationClient.requestLocationUpdates(mLocationRequest,
                    locationCallback, Looper.myLooper());

        } else {

            if (onLocationReceivedListener != null) {

                onLocationReceivedListener.OnLocationReceived(null);
            }
        }


    }


    @SuppressLint("MissingPermission")
    public void getLastLocation() {

        if (EasyPermissions.hasPermissions(context, Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.Q ?
                LOCATION_PERMISSIONS_ABOVE_30
                : LOCATION_PERMISSIONS_BELOW_30)) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(location -> {

                        if (onLocationReceivedListener != null) {
                            onLocationReceivedListener.OnLocationReceived(location);
                        }

                        if (locationCallback != null) {
                            fusedLocationClient.removeLocationUpdates(locationCallback);
                        }

                    }).addOnFailureListener(e -> {


//                onLocationReceivedListener.OnLocationReceived(null);
                getLocation(LocationManager.GPS_PROVIDER);
                if (locationCallback != null) {
                    fusedLocationClient.removeLocationUpdates(locationCallback);
                }

            });

        } else {
            if (onLocationReceivedListener != null) {
                onLocationReceivedListener.OnLocationReceived(null);
            }
        }


    }


    public interface OnLocationReceivedListener {
        void OnLocationReceived(Location location);
    }


}
