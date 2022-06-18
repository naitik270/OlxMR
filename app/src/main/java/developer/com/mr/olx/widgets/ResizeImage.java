package developer.com.mr.olx.widgets;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ResizeImage {

    private int imageheight;
    private int imagwidth;
    private int maxResolution;
    private float orientation;

    private Bitmap originalbm = null;

    private float getImageOrientation(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 6) {
                return 90.0f;
            }
            if (attributeInt == 3) {
                return 180.0f;
            }
            if (attributeInt == 8) {
                return 270.0f;
            }
            return 0.0f;
        } catch (IOException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    private void getAspectRatio(String str) {
        float f;
        float f2;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        float f3 = ((float) options.outWidth) / ((float) options.outHeight);
        if (f3 > 1.0f) {
            f2 = (float) this.maxResolution;
            f = f2 / f3;
        } else {
            float f4 = (float) this.maxResolution;
            float f5 = f4;
            f2 = f3 * f4;
            f = f5;
        }
        this.imagwidth = (int) f2;
        this.imageheight = (int) f;
    }

    private Bitmap getResizedOriginalBitmap(String str) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(str), (Rect) null, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            int i4 = this.imagwidth;
            int i5 = this.imageheight;
            while (i2 / 2 > i4) {
                i2 /= 2;
                i3 /= 2;
                i *= 2;
            }
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inSampleSize = i;
            options.inScaled = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap decodeStream = BitmapFactory.decodeStream(new FileInputStream(str), (Rect) null, options);
            Matrix matrix = new Matrix();
            matrix.postScale(((float) i4) / ((float) i2), ((float) i5) / ((float) i3));
            matrix.postRotate(this.orientation);
            return Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), matrix, true);
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    public Bitmap getScaledBitamp(String str, int i) {
        this.maxResolution = i;
        this.orientation = getImageOrientation(str);
        getAspectRatio(str);
        Bitmap resizedOriginalBitmap = getResizedOriginalBitmap(str);
        this.originalbm = resizedOriginalBitmap;
        return resizedOriginalBitmap;
    }
}
