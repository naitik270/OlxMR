package developer.com.mr.olx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.clsApiClasses.ClsCategory;


public class HeaderCategoriesAdapter extends RecyclerView.Adapter<HeaderCategoriesAdapter.MyViewHolder> {

    private List<ClsCategory> mList = new ArrayList<>();
    private Context mContext;
    private ItemClickListener itemClickListener;

    public HeaderCategoriesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addItemList(List<ClsCategory> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void OnClick(ClsCategory listResponse, int position);
    }

    public void SetOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_header_categories, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_cat_name.setText(mList.get(position).getCatName());

        Glide.with(mContext)
                .load(mList.get(position).getCatPictureLink())
                .centerInside()
                .into(holder.imgCategory);

       /* Glide.with(mContext)
                .asBitmap()
                .load(mList.get(position).getCatPictureLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        holder.imgCategory.setImageBitmap(resource);
                        holder.imgCategory.buildDrawingCache();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });*/

//        Glide.with(holder.imgCategory).load(mList.get(position).getCatPictureLink()).into(holder.imgCategory);

        holder.Bind(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCategory;
        private TextView txt_cat_name;
        private CardView cc_header;

        public MyViewHolder(View itemView) {
            super(itemView);

            imgCategory = itemView.findViewById(R.id.imgCategory);
            txt_cat_name = itemView.findViewById(R.id.txt_cat_name);
            cc_header = itemView.findViewById(R.id.cc_header);
        }

        void Bind(ClsCategory obj, int position) {
            cc_header.setOnClickListener(v -> {
                itemClickListener.OnClick(obj, position);
            });
        }
    }
}
