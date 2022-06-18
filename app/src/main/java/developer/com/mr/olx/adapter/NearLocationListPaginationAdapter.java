package developer.com.mr.olx.adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.clsApiClasses.ClsCategory;
import developer.com.mr.olx.clsApiClasses.ClsNearLocationMasterList;

public class NearLocationListPaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private List<ClsNearLocationMasterList> mList;
    private Context context;
    private boolean isLoadingAdded = false;

    public NearLocationListPaginationAdapter(Context context) {
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
        View v1 = inflater.inflate(R.layout.row_all_in_one, parent, false);
        viewHolder = new AdsListVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ClsNearLocationMasterList movie = mList.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                AdsListVH adsListVH = (AdsListVH) holder;
                adsListVH.txt_test.setText(String.valueOf(position));
                adsListVH.tvAllTitle.setText(movie.getName());
                adsListVH.tvAllPrice.setText("₹ " + movie.getPrice());
                Glide.with(adsListVH.ImgAllCoverPhoto).load(movie.getImage()).into(adsListVH.ImgAllCoverPhoto);

                if (movie.getCategory() != null) {
                    if (movie.getSubCategory().equalsIgnoreCase("Mobiles") ||
                            movie.getSubCategory().equalsIgnoreCase("Tablet")) {
                        adsListVH.hs_mobile_info.setVisibility(View.VISIBLE);
                        adsListVH.txt_mobile_val_one.setText(movie.getMobileRam().concat(" Ram"));
                        adsListVH.txt_mobile_val_two.setText(movie.getMobileRom().concat(" Rom"));

                    } else if (movie.getSubCategory().equalsIgnoreCase("Car Sell")) {

                        adsListVH.hs_car_info.setVisibility(View.VISIBLE);
                        adsListVH.txt_car_val_one.setText(String.valueOf(movie.getCarYearOfRegistration()));
                        adsListVH.txt_car_val_two.setText(String.valueOf(movie.getCarKm()));
                        adsListVH.txt_car_val_three.setText(movie.getOwner());

                    } else if (movie.getSubCategory().equalsIgnoreCase("Sell Property") ||
                            movie.getSubCategory().equalsIgnoreCase("Rent Property")) {

                        adsListVH.hs_real_estate_info.setVisibility(View.VISIBLE);
                        adsListVH.txt_val_real_one.setText(String.valueOf(movie.getBedroom()));
                        adsListVH.txt_val_real_two.setText(String.valueOf(movie.getBathroom()));
//                        adsListVH.txt_val_real_three.setText(String.valueOf(movie.getSquareFeet()));

                    } else if (movie.getSubCategory().equalsIgnoreCase("Sell office") ||
                            movie.getSubCategory().equalsIgnoreCase("Rent office")) {

                        adsListVH.hs_office_info.setVisibility(View.VISIBLE);
                        adsListVH.txt_office_val_one.setText(String.valueOf(movie.getCabin()));
//                        adsListVH.txt_office_val_two.setText(movie.get());
                        adsListVH.txt_office_val_three.setText(String.valueOf(movie.getSquareFeet()));
                    } else {
                        adsListVH.hs_office_info.setVisibility(View.GONE);
                        adsListVH.hs_mobile_info.setVisibility(View.GONE);
                        adsListVH.hs_car_info.setVisibility(View.GONE);
                        adsListVH.hs_real_estate_info.setVisibility(View.GONE);
                    }
                }
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

    public void add(ClsNearLocationMasterList mc) {
        mList.add(mc);
        notifyItemInserted(mList.size() - 1);
    }

    public void addAll(List<ClsNearLocationMasterList> mcList) {

        Log.d("--addAll--", "mcList: " + new Gson().toJson(mcList));

        for (ClsNearLocationMasterList mc : mcList) {
            add(mc);
        }
    }

    public void remove(ClsNearLocationMasterList city) {
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
        add(new ClsNearLocationMasterList());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mList.size() - 1;
        ClsNearLocationMasterList item = getItem(position);

        if (item != null) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public ClsNearLocationMasterList getItem(int position) {
        return mList.get(position);
    }


    protected class AdsListVH extends RecyclerView.ViewHolder {
        private TextView tvAllTitle, tvAllPrice, txt_test;
        private TextView txt_val_real_one, txt_val_real_two, txt_val_real_three;
        private TextView txt_office_val_one, txt_office_val_two, txt_office_val_three;
        private TextView txt_car_val_one, txt_car_val_two, txt_car_val_three;
        private TextView txt_mobile_val_one, txt_mobile_val_two;
        ImageView ImgAllCoverPhoto;
        HorizontalScrollView hs_office_info, hs_real_estate_info, hs_mobile_info, hs_car_info;

        public AdsListVH(View itemView) {
            super(itemView);

            txt_val_real_one = (TextView) itemView.findViewById(R.id.txt_val_real_one);
            txt_val_real_two = (TextView) itemView.findViewById(R.id.txt_val_real_two);
            txt_val_real_three = (TextView) itemView.findViewById(R.id.txt_val_real_three);

            hs_office_info = (HorizontalScrollView) itemView.findViewById(R.id.hs_office_info);
            hs_real_estate_info = (HorizontalScrollView) itemView.findViewById(R.id.hs_real_estate_info);
            hs_mobile_info = (HorizontalScrollView) itemView.findViewById(R.id.hs_mobile_info);
            hs_car_info = (HorizontalScrollView) itemView.findViewById(R.id.hs_car_info);

            txt_office_val_one = (TextView) itemView.findViewById(R.id.txt_office_val_one);
            txt_office_val_two = (TextView) itemView.findViewById(R.id.txt_office_val_two);
            txt_office_val_three = (TextView) itemView.findViewById(R.id.txt_office_val_three);

            txt_car_val_one = (TextView) itemView.findViewById(R.id.txt_car_val_one);
            txt_car_val_two = (TextView) itemView.findViewById(R.id.txt_car_val_two);
            txt_car_val_three = (TextView) itemView.findViewById(R.id.txt_car_val_three);

            txt_mobile_val_one = (TextView) itemView.findViewById(R.id.txt_mobile_val_one);
            txt_mobile_val_two = (TextView) itemView.findViewById(R.id.txt_mobile_val_two);

            txt_test = (TextView) itemView.findViewById(R.id.txt_test);
            tvAllPrice = (TextView) itemView.findViewById(R.id.tvAllPrice);
            tvAllTitle = (TextView) itemView.findViewById(R.id.tvAllTitle);
            ImgAllCoverPhoto = (ImageView) itemView.findViewById(R.id.ImgAllCoverPhoto);

        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

}
