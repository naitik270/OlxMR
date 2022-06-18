package developer.com.mr.olx.Interfaces;

import android.net.Uri;

public interface ItemClickListener {

    void onItemClick(String selectedPath, int i);

    void onItemClick(Uri path, String selectedPath, int i);

    void onItemDeleteClick(String selectedPath, int i);

}
