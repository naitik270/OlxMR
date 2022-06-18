package developer.com.mr.olx.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.clsApiClasses.ClsShowCategoryDataResponseList;

public class ShowAllAdsPaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private List<ClsShowCategoryDataResponseList> mList;
    private Context context;
    private boolean isLoadingAdded = false;

    public ShowAllAdsPaginationAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.row_show_category_data, parent, false);
        viewHolder = new AllAdsHolder(v1);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ClsShowCategoryDataResponseList movie = mList.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                AllAdsHolder movieVH = (AllAdsHolder) holder;
                movieVH.txt_ad_title.setText(movie.getName().trim());
                movieVH.txt_ad_price.setText("Rs. " + movie.getPrice());
                movieVH.txt_extra_details.setText(movie.getMobileRam().concat(" | ").concat(movie.getMobileRom()));
                movieVH.txt_ad_location.setText(movie.getMobileRam());
                movieVH.txt_ad_date.setText(movie.getCreateDate());
                Glide.with(movieVH.iv_set_browse_img).load(movie.getImage()).into(movieVH.iv_set_browse_img);
                break;
            case LOADING:
//                Do nothing
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void add(ClsShowCategoryDataResponseList mc) {
        mList.add(mc);
        notifyItemInserted(mList.size() - 1);
    }

    public void addAll(List<ClsShowCategoryDataResponseList> mcList) {
        Log.d("--addAll--", "mcList: " + new Gson().toJson(mcList));
        for (ClsShowCategoryDataResponseList mc : mcList) {
            add(mc);
        }
    }

    public void remove(ClsShowCategoryDataResponseList city) {
        int position = mList.indexOf(city);
        if (position > -1) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new ClsShowCategoryDataResponseList());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mList.size() - 1;
        ClsShowCategoryDataResponseList item = getItem(position);

        if (item != null) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public ClsShowCategoryDataResponseList getItem(int position) {
        return mList.get(position);
    }


   /*
   View Holders
   _________________________________________________________________________________________________
    */

    /**
     * Main list's content ViewHolder
     */
    protected class AllAdsHolder extends RecyclerView.ViewHolder {
        private TextView txt_ad_title, txt_extra_details, txt_ad_price, txt_ad_location, txt_ad_date;
        ImageView iv_set_browse_img;

        public AllAdsHolder(View itemView) {
            super(itemView);
            iv_set_browse_img = (ImageView) itemView.findViewById(R.id.iv_set_browse_img);
            txt_ad_title = itemView.findViewById(R.id.txt_ad_title);
            txt_extra_details = itemView.findViewById(R.id.txt_extra_details);
            txt_ad_price = itemView.findViewById(R.id.txt_ad_price);
            txt_ad_location = itemView.findViewById(R.id.txt_ad_location);
            txt_ad_date = itemView.findViewById(R.id.txt_ad_date);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {
        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

}
