package developer.com.mr.olx.global;

import android.app.Activity;
import android.os.Build;

import androidx.lifecycle.LiveData;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import pub.devrel.easypermissions.EasyPermissions;

import static developer.com.mr.olx.activity.GetLocationActivity.getCurrentLocation;
import static developer.com.mr.olx.global.ClsGlobal.LOCATION_PERMISSIONS_ABOVE_30;
import static developer.com.mr.olx.global.ClsGlobal.LOCATION_PERMISSIONS_BELOW_30;

public class LocationLiveData extends LiveData<LocationWithAddress> {

    private static LocationLiveData instance;

    public static LocationLiveData getInstance() {
        if (instance == null) {
            instance = new LocationLiveData();
        }
        return instance;
    }

    public LocationLiveData getLiveLocation(Activity activity) {

        if (EasyPermissions.hasPermissions(activity, Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.S ?
                LOCATION_PERMISSIONS_ABOVE_30
                : LOCATION_PERMISSIONS_BELOW_30)) {

            AtomicReference<Double> Latitude = new AtomicReference<>((double) 0);
            AtomicReference<Double> Longitude = new AtomicReference<>((double) 0);

            AtomicReference<String> address = new AtomicReference<>("");


            MyLocation location = new MyLocation(activity);
            location.getFusedLocationLocation();
            location.setOnLocationReceivedListener(locationResult -> {

                if (locationResult != null) {

                    Latitude.set(locationResult.getLatitude());
                    Longitude.set(locationResult.getLongitude());

                    try {

                        CountDownLatch countDownLatch = new CountDownLatch(1);
                        new Thread(() -> {
                            address.set(getCurrentLocation(activity, Latitude.get()
                                    , Longitude.get()));

                            countDownLatch.countDown();
                        }).start();

                        countDownLatch.await();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    setValue(new LocationWithAddress(Latitude.get(), Longitude.get(), address.get()));
                } else {

                    setValue(new LocationWithAddress(0.0, 0.0, ""));
                }

            });

        } else {
            setValue(new LocationWithAddress(0.0, 0.0, ""));
        }

        return instance;
    }

    private LocationLiveData() {
    }


    @Override
    protected void onActive() {
        // We have observers, start working

    }

    @Override
    protected void onInactive() {
        // We have no observers, stop working


    }

}
