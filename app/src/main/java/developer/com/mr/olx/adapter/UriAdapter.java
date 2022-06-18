package developer.com.mr.olx.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.Interfaces.ItemClickListener;
import developer.com.mr.olx.ModelClasses.clsPhoto;
import developer.com.mr.olx.R;
import developer.com.mr.olx.widgets.SquareImageView;

public class UriAdapter extends RecyclerView.Adapter<UriAdapter.UriViewHolder> {

    private List<Uri> mUris;
    private Context mContext;
    private ArrayList<clsPhoto> lstClsPhotos = new ArrayList<>();
    ItemClickListener listener;

    public UriAdapter(Context mContext, ArrayList<clsPhoto> lstClsPhotos) {
        this.mContext = mContext;
        this.lstClsPhotos = lstClsPhotos;
    }

    public void SetOnItemClickListener(ItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }

    @Override
    public UriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UriViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.uri_item, parent, false));
    }

    @Override
    public void onBindViewHolder(UriViewHolder holder, int position) {


     /*   Bitmap bitmap = BitmapFactory.decodeFile(lstClsPhotos.get(position).getPaths());
        Log.d("--URL--",
                "lstClsPhotos: " + lstClsPhotos.get(position).getPaths());*/

        Glide.with(this.mContext)
                .load(lstClsPhotos.get(position).getmBitmap()).apply((BaseRequestOptions<?>)
                new RequestOptions()).thumbnail(0.1f).into(holder.iv_image);

    /*    Glide.with(this.mContext)
                .load(lstClsPhotos.get(position).getPaths()).apply((BaseRequestOptions<?>)
                new RequestOptions()).thumbnail(0.1f).into(holder.iv_image);*/

        holder.mUri.setAlpha(position % 2 == 0 ? 1.0f : 0.54f);

        holder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(lstClsPhotos.get(position).getPaths(), position);
//                listener.onItemClick(Uri.parse(lstClsPhotos.get(position).paths), lstClsPhotos.get(position).paths, position);
//                listener.onItemClick(lstClsPhotos.get(position).getmBitmap(), position);
            }
        });

        holder.item_photo_close_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemDeleteClick(lstClsPhotos.get(position).paths, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstClsPhotos.size();
    }

    static class UriViewHolder extends RecyclerView.ViewHolder {

        private TextView mUri;
        private SquareImageView iv_image;
        private FrameLayout item_photo_close_container;

        UriViewHolder(View contentView) {
            super(contentView);
            mUri = (TextView) contentView.findViewById(R.id.uri);
            iv_image = (SquareImageView) contentView.findViewById(R.id.iv_image);
            item_photo_close_container = (FrameLayout) contentView.findViewById(R.id.item_photo_close_container);
        }
    }

}
