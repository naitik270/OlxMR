package developer.com.mr.olx.cropClass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

import developer.com.mr.olx.R;
import developer.com.mr.olx.cropClass.Request.CropImageView;
import developer.com.mr.olx.global.ClsGlobal;

public class CropActivity extends AppCompatActivity {
    String selectImg = "";
    ArrayList<String> listPhoto = new ArrayList<>();

    private final View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View view) {
            int id = view.getId();
            if (id != R.id.iv_back) {
                switch (id) {
                    case R.id.buttonDone /*2131361951*/:
                        saveImage();
                        return;
                    default:
                        switch (id) {
                            case R.id.buttonFlip1 /*2131361953*/:
                                if (!flagForFlip) {
                                    mCropView.setRotationY(360.0f);
                                    boolean unused = flagForFlip = true;
                                    return;
                                }
                                mCropView.setRotationY(180.0f);
                                boolean unused2 = flagForFlip = false;
                                return;
                            case R.id.buttonFlip2 /*2131361954*/:
                                if (!flagForFlip2) {
                                    mCropView.setRotationX(360.0f);
                                    boolean unused3 = flagForFlip2 = true;
                                    return;
                                }
                                mCropView.setRotationX(180.0f);
                                boolean unused4 = flagForFlip2 = false;
                                return;
                            case R.id.buttonRotateLeft /*2131361958*/:
                                mCropView.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D);
                                return;
                            case R.id.buttonRotateRight /*2131361959*/:
                                mCropView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
                                return;
                            default:
                                return;
                        }
                }
            } else {
                finish();
            }
        }
    };

    public boolean flagForFlip = true;
    public boolean flagForFlip2 = true;
    public ImageView iv_169;
    public ImageView iv_916;
    public ImageView iv_circle;
    public ImageView iv_fitImage;
    public ImageView iv_free;
    public CropImageView mCropView;
    public TextView tv_circle;
    Bitmap scaledBitmap;
    int position = 0;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_basic);
        bindViews();
    }

    private void bindViews() {

        selectImg = getIntent().getStringExtra("selectImg");
        listPhoto = getIntent().getStringArrayListExtra("PHOTO");
        position = getIntent().getIntExtra("POSITION", 0);

        this.mCropView = (CropImageView) findViewById(R.id.cropImageView);
        findViewById(R.id.buttonDone).setOnClickListener(this.btnListener);
        findViewById(R.id.iv_back).setOnClickListener(this.btnListener);

        findViewById(R.id.buttonRotateLeft).setOnClickListener(this.btnListener);
        findViewById(R.id.buttonRotateRight).setOnClickListener(this.btnListener);
        findViewById(R.id.buttonFlip1).setOnClickListener(this.btnListener);
        findViewById(R.id.buttonFlip2).setOnClickListener(this.btnListener);

        this.mCropView.setCropMode(CropImageView.CropMode.FREE);
        getIntent().getExtras();
        getWindowManager();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        /*scaledBitmap = new ResizeImage()
                .getScaledBitamp(selectImg, displayMetrics.widthPixels);*/

        Log.d("--click--", "scaledBitamp: " + scaledBitmap);
        if (position >= this.listPhoto.size()) {
            finish();
        } else {
            Glide.with((FragmentActivity) this).load(selectImg)
                    .into(this.mCropView);
        }


//        this.mCropView.setImageBitmap(scaledBitmap);
    }

    public void saveImage() {

        ClsGlobal.mBitmap = this.mCropView.getCroppedBitmap();
        Log.d("--click--", "saveImage: " + ClsGlobal.mBitmap);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        Log.d("--click--", "saveImage_width: " + width);
        Log.d("--click--", "saveImage_height: " + height);

        if (ClsGlobal.mBitmap.getHeight() > ClsGlobal.mBitmap.getWidth()) {
            if (ClsGlobal.mBitmap.getHeight() > height) {
                ClsGlobal.mBitmap = Bitmap.createScaledBitmap(ClsGlobal.mBitmap,
                        (ClsGlobal.mBitmap.getWidth() * height) / ClsGlobal.mBitmap.getHeight(), height, false);
            }
            if (ClsGlobal.mBitmap.getWidth() > width) {
                ClsGlobal.mBitmap = Bitmap.createScaledBitmap(ClsGlobal.mBitmap, width,
                        (ClsGlobal.mBitmap.getHeight() * width) / ClsGlobal.mBitmap.getWidth(), false);
            }
            Log.d("--click--", "IFIFIFIFIF");
        } else {
            if (ClsGlobal.mBitmap.getWidth() > width) {
                ClsGlobal.mBitmap = Bitmap.createScaledBitmap(ClsGlobal.mBitmap, width,
                        (ClsGlobal.mBitmap.getHeight() * width) / ClsGlobal.mBitmap.getWidth(), false);
            }
            if (ClsGlobal.mBitmap.getHeight() > height) {
                ClsGlobal.mBitmap = Bitmap.createScaledBitmap(ClsGlobal.mBitmap,
                        (ClsGlobal.mBitmap.getWidth() * height) / ClsGlobal.mBitmap.getHeight(), height, false);
            }
            Log.d("--click--", "ELSELSE");
        }


        Intent intent = new Intent();
        intent.putExtra("selectImg", selectImg);
        intent.putExtra("POSITION", position);
//        intent.putStringArrayListExtra("AFTER", listPhoto);
        setResult(123, intent);
        finish();

//        startActivity(new Intent(getApplicationContext(), setImageActivity.class));
    }


    private void getData() {

    }

}




