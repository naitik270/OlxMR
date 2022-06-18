package developer.com.mr.olx.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.clsApiClasses.clsAllCategory;

public class CourseGVAdapter extends ArrayAdapter<clsAllCategory> {

    public CourseGVAdapter(@NonNull Context context, List<clsAllCategory> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;

        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.row_sale_category_name, parent, false);
        }

        clsAllCategory courseModel = getItem(position);

        Log.d("--URL--", "getView: " + position);
        TextView txt_cat_name = listitemView.findViewById(R.id.txt_cat_name);
        txt_cat_name.setText(courseModel.getName());
        return listitemView;
    }
}