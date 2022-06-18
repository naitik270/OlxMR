package developer.com.mr.olx.ModelClasses;

import android.graphics.Bitmap;

import java.io.Serializable;

public class clsPhoto implements Serializable {
    public long id;
    public String paths;

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public Bitmap mBitmap = null;
    int position;
    public clsPhoto(long j, String str) {
        this.id = j;
        this.paths = str;
    }

    public clsPhoto(int position, String str,Bitmap mBitmap) {
        this.position = position;
        this.paths = str;
        this.mBitmap = mBitmap;
    }

    public clsPhoto(int position,Bitmap mBitmap) {
        this.position = position;
        this.mBitmap = mBitmap;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getPaths() {
        return this.paths;
    }

    public void setPaths(String str) {
        this.paths = str;
    }
}
