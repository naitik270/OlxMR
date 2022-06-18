package developer.com.mr.olx.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import developer.com.mr.olx.R;
import developer.com.mr.olx.clicklistener.HeaderCategoryClickListener;
import developer.com.mr.olx.clicklistener.SaleCategoryClickListener;
import developer.com.mr.olx.clsApiClasses.clsAllCategory;


public class AllSaleCategoriesViewHolder extends RecyclerView.ViewHolder {

    private TextView txt_cat_name;
    private CardView cc_header;
    private HeaderCategoryClickListener headerCategoryClickListener;

    public AllSaleCategoriesViewHolder(@NonNull View itemView,
                                       HeaderCategoryClickListener headerCategoryClickListener) {
        super(itemView);
        this.headerCategoryClickListener = headerCategoryClickListener;
        init(itemView);
    }

    private void init(View itemView) {
        txt_cat_name = itemView.findViewById(R.id.txt_cat_name);
        cc_header = itemView.findViewById(R.id.cc_header);
    }

    public void setData(clsAllCategory modelClass) {
        txt_cat_name.setText(modelClass.getName());
    }

    public void SaleCategoriesClick(final clsAllCategory modelClass, int position,
                                     SaleCategoryClickListener browseCategoryClickListener) {
        cc_header.setOnClickListener(view -> {
            browseCategoryClickListener.onItemClick(modelClass, position);
        });
    }
}
