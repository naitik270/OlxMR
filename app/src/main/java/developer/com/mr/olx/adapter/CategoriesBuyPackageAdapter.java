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
import developer.com.mr.olx.clicklistener.CatBuyPackageListener;
import developer.com.mr.olx.clsApiClasses.ClsCategory;


public class CategoriesBuyPackageAdapter extends RecyclerView.Adapter<CategoriesBuyPackageAdapter.MyViewHolder> {
    private List<ClsCategory> mList = new ArrayList<>();
    private Context mContext;
    private CatBuyPackageListener mCatBuyPackageListener;

    public CategoriesBuyPackageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void AddItems(List<ClsCategory> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void SetOnClickListener(CatBuyPackageListener mCatBuyPackageListener) {
        this.mCatBuyPackageListener = mCatBuyPackageListener;
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

        holder.label.setText(mList.get(position).getCatName());
        holder.Bind(mList.get(position), mCatBuyPackageListener);
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

        void Bind(ClsCategory obj, CatBuyPackageListener mCatBuyPackageListener) {
            label.setOnClickListener(v -> {
                mCatBuyPackageListener.onItemClick(obj);
            });
        }
    }
}
