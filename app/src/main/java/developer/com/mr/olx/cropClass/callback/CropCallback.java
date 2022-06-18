package developer.com.mr.olx.cropClass.callback;

import android.graphics.Bitmap;

import javax.security.auth.callback.Callback;

public interface CropCallback extends Callback {
  void onSuccess(Bitmap cropped);
}
