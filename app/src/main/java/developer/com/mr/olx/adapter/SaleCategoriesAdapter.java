package developer.com.mr.olx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.clsApiClasses.ClsCategory;

public class SaleCategoriesAdapter extends ArrayAdapter<ClsCategory> {

    Context mContext;
    List<ClsCategory> lst = new ArrayList<>();

    public SaleCategoriesAdapter(Context mContext) {
        super(mContext, 0);
        this.mContext = mContext;
        this.lst = lst;
    }

    public interface ItemClickListener {
        void OnClick(ClsCategory listResponse, int position);
    }

    private ItemClickListener itemClickListener;

    public void SetOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addItemList(List<ClsCategory> updateList) {
        lst = updateList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(mContext).inflate(R.layout.row_categories_items, parent, false);
        }
        ClsCategory mClsAllCategory = lst.get(position);
        TextView txt_cat_name = listitemView.findViewById(R.id.txt_cat_name);
        ImageView imgCategoies = listitemView.findViewById(R.id.imgCategoies);
        CardView cc_header = listitemView.findViewById(R.id.cc_header);
        cc_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.OnClick(mClsAllCategory, position);
            }
        });
        Glide.with(listitemView)
                .load(mClsAllCategory.getCatPictureLink())
                .fitCenter()
                .into(imgCategoies);
        txt_cat_name.setText(mClsAllCategory.getCatName());
        return listitemView;
    }
}
