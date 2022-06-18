package developer.com.mr.olx.matisse.listener;

import android.net.Uri;

import androidx.annotation.NonNull;

import java.util.List;

public interface OnSelectedListener {

    void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList);
}
