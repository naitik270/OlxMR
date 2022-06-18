package developer.com.mr.olx.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import developer.com.mr.olx.CarResponseClasses.DataCarClasses;
import developer.com.mr.olx.R;
import developer.com.mr.olx.clicklistener.CarClickListener;

public class CarsViewHolder extends RecyclerView.ViewHolder {

    private TextView tvCarPrice, tvCarTitle, tvCarTown, tvCarCity, tvExtraCar;
    private ImageView imgCarCoverImage, imgCarheart;
    private LinearLayout constraintLayout;
    private CarClickListener carClickListener;

    public CarsViewHolder(@NonNull View itemView, CarClickListener carClickListener) {
        super(itemView);
        this.carClickListener = carClickListener;
        initView(itemView);
    }

    private void initView(View itemView) {
        tvCarPrice = itemView.findViewById(R.id.PriceOfBrowseCategory);
        tvCarTitle = itemView.findViewById(R.id.TitleOfBrowseCategory);
        tvExtraCar = itemView.findViewById(R.id.ExtrasOfBrowseCategory);
        constraintLayout = itemView.findViewById(R.id.constraintLayout);
        tvCarTown = itemView.findViewById(R.id.tvTownOfBrowseCategory);
        tvCarCity = itemView.findViewById(R.id.CityOfBrowseCategory);
        imgCarCoverImage = itemView.findViewById(R.id.imgInBrowseCategory);
        imgCarheart = itemView.findViewById(R.id.imgBrowseCategoryHeart);
        imgCarheart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgCarheart.setImageResource(R.drawable.ic_heart_selected);
            }
        });
    }

    public void setData(DataCarClasses dataCarClasses) {
        try {
            tvCarPrice.setText(dataCarClasses.getPrice().getValue().getDisplay());
            tvCarTitle.setText(dataCarClasses.getTitle());
            tvExtraCar.setText(dataCarClasses.getMainInfo());
            tvCarTown.setText(dataCarClasses.getLocationsResolved().getADMINLEVEL3Name());
            tvCarCity.setText(dataCarClasses.getLocationsResolved().getADMINLEVEL1Name());
            Glide.with(imgCarCoverImage).load(dataCarClasses.getImages().get(0).getUrl()).into(imgCarCoverImage);
        } catch (Exception e) {
            tvExtraCar.setText("");
        }
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    carClickListener.onClick(dataCarClasses);
                }
            }
        });
    }
}
