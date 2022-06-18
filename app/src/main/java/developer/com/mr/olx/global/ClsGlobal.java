package developer.com.mr.olx.global;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import developer.com.mr.olx.ModelClasses.SliderItem;
import developer.com.mr.olx.R;
import developer.com.mr.olx.matisse.Matisse;
import developer.com.mr.olx.matisse.MimeType;
import developer.com.mr.olx.matisse.engine.impl.GlideEngine;
import developer.com.mr.olx.matisse.internal.entity.CaptureStrategy;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class ClsGlobal {

    /*  public static ArrayList<clsSubCategories> mSubMobileList = new ArrayList<>();
      public static ArrayList<clsSubCategories> mSubCarsList = new ArrayList<>();
      public static ArrayList<clsSubCategories> mSubRealEstateList = new ArrayList<>();
      public static ArrayList<clsSubCategories> mSubTwoWheelerList = new ArrayList<>();
      public static ArrayList<clsSubCategories> mSubFurnitureList = new ArrayList<>();
      public static ArrayList<clsSubCategories> mSubCommercialVehiclesList = new ArrayList<>();
      public static ArrayList<clsSubCategories> mSubJobsList = new ArrayList<>();
      public static ArrayList<clsSubCategories> mSubPetsList = new ArrayList<>();
      public static ArrayList<clsSubCategories> mSubBooksList = new ArrayList<>();
      public static ArrayList<clsSubCategories> mSubFashionList = new ArrayList<>();
      public static ArrayList<clsSubCategories> mSubServiceList = new ArrayList<>();
      public static ArrayList<clsSubCategories> mSubElectricsList = new ArrayList<>();*/
    public static ArrayList<SliderItem> mPosterList = new ArrayList<>();
    public static Bitmap mBitmap;

    public static final String[] LOCATION_PERMISSIONS_ABOVE_30 = {
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
    };

    public static final String[] LOCATION_PERMISSIONS_BELOW_30 = {
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
    };

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static ProgressDialog _prProgressDialog(Context c, String msg, Boolean setCancelable) {
        ProgressDialog progressDialog = new ProgressDialog(c, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage(msg); // Setting Message
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.setCancelable(setCancelable);
        return progressDialog;
    }

    public static void PosterListData() {
        mPosterList.add(new SliderItem(R.drawable.ic_banner_one));
        mPosterList.add(new SliderItem(R.drawable.ic_banner_two));
        mPosterList.add(new SliderItem(R.drawable.ic_banner_three));
    }

    public static String getBytes(File filePath) {
        String result = "";
        Log.e("--global--", "getBytes: " + filePath.getAbsolutePath());
        byte[] byteArray = null;
        try {
            InputStream inputStream = new FileInputStream(filePath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024 * 8];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }

            byteArray = bos.toByteArray();
            result = Base64.encodeToString(byteArray, 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getBase64Drawable(Bitmap bitmapOrg) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, bao);
        byte[] imageBytes = bao.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }


    public static String getStringFile(File f) {
        InputStream inputStream = null;
        String encodedFile = "", lastVal;
        try {
            inputStream = new FileInputStream(f.getAbsolutePath());

            byte[] buffer = new byte[(int) f.length()];//specify the size to allow
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }
            output64.close();
            encodedFile = output.toString();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        lastVal = encodedFile;
        return lastVal;
    }


    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


    public static void errorMsg(Activity context, View v, String err_msg) {

        final Snackbar snackbar = Snackbar.make(v, "", Snackbar.LENGTH_LONG);

        View customSnackView = context.getLayoutInflater().inflate(R.layout.cust_error_msg, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);
        TextView txt_err_msg = customSnackView.findViewById(R.id.txt_err_msg);
        txt_err_msg.setText(err_msg);
        Button bGotoWebsite = customSnackView.findViewById(R.id.gotoWebsiteButton);
        bGotoWebsite.setOnClickListener(v1 -> {
            snackbar.dismiss();
        });
        snackbarLayout.addView(customSnackView, 0);
        snackbar.show();
    }


    public static void OnImageSelection(Activity mContext, int REQUEST_CODE_CHOOSE) {
        Matisse.from(mContext)
                .choose(MimeType.ofImage(), false)
                .countable(true)
                .captureStrategy(
                        new CaptureStrategy(true, "developer.com.mr.olx.fileprovider", "test"))
                .maxSelectable(10)
                .gridExpectedSize(
                        mContext.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .forResult(REQUEST_CODE_CHOOSE);
    }

    public static void storeMultipleImage(Context mContext, ArrayList<Bitmap> bmpArray) {
        try {
            String imagesDir = mContext.getExternalFilesDir(null).getAbsolutePath();
            Log.d("--file--", "imagesDir: " + imagesDir);
            File file = new File(imagesDir);
            Log.d("--file--", "file: " + file);
            if (!file.exists()) {
                file.mkdir();
            }

            ArrayList<File> fileArrayList = new ArrayList<>();

        /*    for (int i = 0; i < bmpArray.size(); i++) {
                fileArrayList.add(new File(file, System.currentTimeMillis() + i + ".jpeg"));
            }
*/
            for (int i = 0; i < bmpArray.size(); i++) {
                fileArrayList.add(new File(file, System.currentTimeMillis() + i + ".jpeg"));
                FileOutputStream fos = new FileOutputStream(fileArrayList.get(i));
                bmpArray.get(i).compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            }
        } catch (Exception e) {
            Log.e("Check", "Exception: " + e.getMessage());
        }
    }

    public static void deleteMultipleImagesFromFolder(Context mContext) {
        File dir = new File(mContext.getExternalFilesDir(null).getAbsolutePath());
        Log.d("--file--", "dir: " + dir);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                new File(dir, children[i]).delete();
            }
        }
    }


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    public static void setPreferencesInfo(ClsPreferencesInfo obj, Context c) {
        String my_preferences = "PreferencesInfo";
        SharedPreferences mPreferences = c.getSharedPreferences(my_preferences, MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        String currentLocation = "currentLocation";
        String currentLatitude = "currentLatitude";
        String currentLongitude = "currentLongitude";
        String UserID = "UserID";
        editor.putString(currentLocation, obj.getCurrentLocation());
        editor.putString(currentLatitude, String.valueOf(obj.getCurrentLatitude()));
        editor.putString(currentLongitude, String.valueOf(obj.getCurrentLongitude()));
        editor.putString(UserID, String.valueOf(obj.getUserID()));
        editor.apply();
        editor.commit();
    }

    public static ClsPreferencesInfo getPreferencesInfo(Context c) {
        ClsPreferencesInfo obj = new ClsPreferencesInfo();
        String my_preferences = "PreferencesInfo";
        SharedPreferences mPreferences = c.getSharedPreferences(my_preferences, MODE_PRIVATE);
        String currentLocation = "currentLocation";
        String currentLatitude = "currentLatitude";
        String currentLongitude = "currentLongitude";
        String UserID = "UserID";
        obj.setCurrentLocation(mPreferences.getString(currentLocation, obj.getCurrentLocation()));
        obj.setCurrentLatitude(Double.parseDouble(mPreferences.getString(currentLatitude, String.valueOf(obj.getCurrentLatitude()))));
        obj.setCurrentLongitude(Double.parseDouble(mPreferences.getString(currentLongitude, String.valueOf(obj.getCurrentLongitude()))));
        obj.setUserID(Integer.parseInt(mPreferences.getString(UserID, String.valueOf(obj.getUserID()))));
        return obj;
    }

    public static String getChangeDateFormat(String dateFormat) {
        final String outFormat = "dd MMM yyyy hh:mm aa";
        final String InFORMAT = "yyyy-MM-dd hh:mm:ss";
        String result = "";
        try {
            if (dateFormat != null && !dateFormat.isEmpty() && dateFormat != "") {
                Date date = new SimpleDateFormat(InFORMAT, Locale.ENGLISH).parse(dateFormat);
                DateFormat formatter = new SimpleDateFormat(outFormat, Locale.getDefault());
                result = formatter.format(date.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }



    public static String getDDMYYYYAndTimeAM_And_PMFormat(String dbFormateString) {
        final String outFormat = "dd/MM/yyyy hh:mm aa";
        final String InFORMAT = "yyyy-MM-dd hh:mm:ss";
        String result = "";
        try {
            if (dbFormateString != null && !dbFormateString.isEmpty() && dbFormateString != "") {
                Date date = new SimpleDateFormat(InFORMAT, Locale.ENGLISH).parse(dbFormateString);
                DateFormat formatter = new SimpleDateFormat(outFormat, Locale.getDefault());
                result = formatter.format(date.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }


}
