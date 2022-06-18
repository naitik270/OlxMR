package developer.com.mr.olx.ModelClasses;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import developer.com.mr.olx.ApiInterface.InterfaceMobile;
import developer.com.mr.olx.globalApi.ApiClient;
import developer.com.mr.olx.productsList.ClsInventoryMasterFormData;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MultipartJobFormAPI {

    private FileUploaderCallback mFileUploaderCallback;
    long fileSize = 0l;
    private boolean showProgressBar = false;
    Context mContext;
    String mode = "";

    public MultipartJobFormAPI(Context context) {
        this.mContext = context;
    }

    public void showUploadProgressBar(boolean showProgressBar) {
        this.showProgressBar = showProgressBar;
    }

    public void uploadFormDataFormAPI(int job_inventory_id, int user_id, String j_title,
                                      double from_salary, double to_salary,
                                      File file1,
                                      File file2,
                                      File file3,
                                      File file4,
                                      File file5,
                                      File file6,
                                      File file7,
                                      File file8,
                                      File file9,
                                      File file10,
                                      int j_type_id, int s_p_id, int p_type_id,
                                      String j_designation, int j_show_mo_no, String j_description,
                                      String j_location, double latitude, double longitude, int cat_id, int sub_cat_id) {

        InterfaceMobile interfacePlaceOrder = ApiClient.getRetrofitInstanceNewAPI()
                .create(InterfaceMobile.class);

        PRRequestBody mFile1 = new PRRequestBody(file1);
        PRRequestBody mFile2 = new PRRequestBody(file2);
        PRRequestBody mFile3 = new PRRequestBody(file3);
        PRRequestBody mFile4 = new PRRequestBody(file4);
        PRRequestBody mFile5 = new PRRequestBody(file5);
        PRRequestBody mFile6 = new PRRequestBody(file6);
        PRRequestBody mFile7 = new PRRequestBody(file7);
        PRRequestBody mFile8 = new PRRequestBody(file8);
        PRRequestBody mFile9 = new PRRequestBody(file9);
        PRRequestBody mFile10 = new PRRequestBody(file10);

        MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("j_picture_link_1", file1.getName(), mFile1);
        MultipartBody.Part fileToUpload2 = MultipartBody.Part.createFormData("j_picture_link_2", file2.getName(), mFile2);
        MultipartBody.Part fileToUpload3 = MultipartBody.Part.createFormData("j_picture_link_3", file3.getName(), mFile3);
        MultipartBody.Part fileToUpload4 = MultipartBody.Part.createFormData("j_picture_link_4", file4.getName(), mFile4);
        MultipartBody.Part fileToUpload5 = MultipartBody.Part.createFormData("j_picture_link_5", file5.getName(), mFile5);
        MultipartBody.Part fileToUpload6 = MultipartBody.Part.createFormData("j_picture_link_6", file6.getName(), mFile6);
        MultipartBody.Part fileToUpload7 = MultipartBody.Part.createFormData("j_picture_link_7", file7.getName(), mFile7);
        MultipartBody.Part fileToUpload8 = MultipartBody.Part.createFormData("j_picture_link_8", file8.getName(), mFile8);
        MultipartBody.Part fileToUpload9 = MultipartBody.Part.createFormData("j_picture_link_9", file9.getName(), mFile9);
        MultipartBody.Part fileToUpload10 = MultipartBody.Part.createFormData("j_picture_link_10", file10.getName(), mFile10);

        RequestBody job_inventory_id_body = RequestBody.create(MultipartBody.FORM, String.valueOf(job_inventory_id));

        RequestBody user_id_body = RequestBody.create(MultipartBody.FORM, String.valueOf(user_id));

        RequestBody j_title_body = RequestBody.create(MultipartBody.FORM, j_title);

        RequestBody from_salary_body = RequestBody.create(MultipartBody.FORM, String.valueOf(from_salary));

        RequestBody to_salary_body = RequestBody.create(MultipartBody.FORM, String.valueOf(to_salary));

        RequestBody j_type_id_body = RequestBody.create(MultipartBody.FORM, String.valueOf(j_type_id));

        RequestBody s_p_id_body = RequestBody.create(MultipartBody.FORM, String.valueOf(s_p_id));

        RequestBody p_type_id_body = RequestBody.create(MultipartBody.FORM, String.valueOf(p_type_id));

        RequestBody j_designation_body = RequestBody.create(MultipartBody.FORM, j_designation);

        RequestBody j_show_mo_no_body = RequestBody.create(MultipartBody.FORM, String.valueOf(j_show_mo_no));

        RequestBody j_description_body = RequestBody.create(MultipartBody.FORM, j_description);

        RequestBody location_body = RequestBody.create(MultipartBody.FORM, j_location);

        RequestBody latitude_body = RequestBody.create(MultipartBody.FORM, String.valueOf(latitude));

        RequestBody longitude_body = RequestBody.create(MultipartBody.FORM, String.valueOf(longitude));

        RequestBody cat_id_body = RequestBody.create(MultipartBody.FORM, String.valueOf(cat_id));

        RequestBody sub_cat_id_body = RequestBody.create(MultipartBody.FORM, String.valueOf(sub_cat_id));

        Call<ClsInventoryMasterFormData> callPlaceOrder =
                interfacePlaceOrder.postJobFormAPI(job_inventory_id_body, user_id_body, j_title_body, from_salary_body,
                        to_salary_body,
                        fileToUpload1,
                        fileToUpload2,
                        fileToUpload3,
                        fileToUpload4,
                        fileToUpload5,
                        fileToUpload6,
                        fileToUpload7,
                        fileToUpload8,
                        fileToUpload9,
                        fileToUpload10,
                        j_type_id_body, s_p_id_body, p_type_id_body,
                        j_designation_body, j_show_mo_no_body, j_description_body,
                        location_body, latitude_body, longitude_body, cat_id_body, sub_cat_id_body);

        Log.e("--Gson--", "callPlaceOrder: " + callPlaceOrder.request().url());

        callPlaceOrder.enqueue(new Callback<ClsInventoryMasterFormData>() {
            @Override
            public void onResponse(@NonNull Call<ClsInventoryMasterFormData> call,
                                   @NonNull Response<ClsInventoryMasterFormData> response) {
                if (response.body() != null && response.isSuccessful()) {
                    Log.e("--Gson--", "response: " + response);
                    Log.e("--Gson--", "call: " + call);

                    mFileUploaderCallback.onFinish(response.body());

                }
            }

            @Override
            public void onFailure(@NonNull Call<ClsInventoryMasterFormData> call,
                                  @NonNull Throwable t) {
                mFileUploaderCallback.onError();
                Log.e("--Gson--", "onFailure: " + call);
                Log.e("--Gson--", "t: " + t);
            }
        });
    }

    public void SetCallBack(FileUploaderCallback fileUploaderCallback) {
        this.mFileUploaderCallback = fileUploaderCallback;
    }

    public class PRRequestBody extends RequestBody {
        private File mFile;

        private int DEFAULT_BUFFER_SIZE = 20000048;

        PRRequestBody(final File file) {
            mFile = file;
            if (mFile.length() < 524288000) {
                DEFAULT_BUFFER_SIZE = 399999;
            }
        }

        @Override
        public MediaType contentType() {
            // i want to upload only images
            return MediaType.parse("multipart/form-data");
        }

        @Override
        public long contentLength() throws IOException {
            return mFile.length();
        }

        @Override
        public void writeTo(BufferedSink sink) throws IOException {
            long fileLength = mFile.length();
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            FileInputStream in = new FileInputStream(mFile);
            long uploaded = 0;
//            Source source = null;
            try {
                int read;
                Handler handler = new Handler(Looper.getMainLooper());
                while ((read = in.read(buffer)) != -1) {
                    // update progress on UI thread
                    // update progress on UI thread
                    if (mode.equalsIgnoreCase("Manual")
                            || mode.equalsIgnoreCase("Manual and Auto") || showProgressBar) {
                        handler.post(new ProgressUpdater(uploaded, fileLength));
                    }
                    uploaded += read;
                    sink.write(buffer, 0, read);
                }
            } catch (Exception e) {
                Log.e("Exception", e.getMessage());
            } finally {
                in.close();
            }
        }
    }

    private class ProgressUpdater implements Runnable {
        private long mUploaded;
        private long mTotal;

        ProgressUpdater(long uploaded, long total) {
            mUploaded = uploaded;
            mTotal = total;
        }

        @Override
        public void run() {
            int current_percent = (int) (100 * mUploaded / mTotal);
            int total_percent = (int) (100 * mUploaded / mTotal);

            if (mFileUploaderCallback != null) {
                mFileUploaderCallback.onProgressUpdate(current_percent, total_percent,
                        "File Size: " + readableFileSize(mUploaded) +
                                "/" + readableFileSize(fileSize));
            }
        }
    }

    public interface FileUploaderCallback {
        void onError();

        void onFinish(ClsInventoryMasterFormData responses);

        void onProgressUpdate(int currentPercent, int totalPercent, String msg);
    }

    public static String readableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups))
                + " " + units[digitGroups];
    }

}
