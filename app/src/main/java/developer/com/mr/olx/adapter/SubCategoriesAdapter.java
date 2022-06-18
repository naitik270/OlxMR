package developer.com.mr.olx.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.clicklistener.SubCatClickListener;
import developer.com.mr.olx.clsApiClasses.ClsSubCategories;


public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.MyViewHolder> {
    private List<ClsSubCategories> mList = new ArrayList<>();
    private Context mContext;
    private SubCatClickListener subCatClickListener;

    public SubCategoriesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void AddItems(List<ClsSubCategories> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void SetOnClickListener(SubCatClickListener subCatClickListener) {
        this.subCatClickListener = subCatClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_filter_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("--subCat--", "size: " + mList.size());

        holder.label.setText(mList.get(position).getSubCatName());
        holder.Bind(mList.get(position), subCatClickListener);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView label;

        public MyViewHolder(View itemView) {
            super(itemView);

            label = itemView.findViewById(R.id.label);
        }

        void Bind(ClsSubCategories obj, SubCatClickListener subCatClickListener) {
            label.setOnClickListener(v -> {
                subCatClickListener.onItemClick(obj);
            });
        }
    }
}
