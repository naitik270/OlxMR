package developer.com.mr.olx.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import developer.com.mr.olx.CarResponseClasses.DataCarClasses;
import developer.com.mr.olx.R;
import developer.com.mr.olx.clicklistener.CarClickListener;
import developer.com.mr.olx.viewholders.CarsViewHolder;

public class CarViewAdapter extends RecyclerView.Adapter<CarsViewHolder> {
    private List<DataCarClasses> dataCarClassesList;
    private CarClickListener carClickListener;

    public CarViewAdapter(List<DataCarClasses> dataCarClassesList, CarClickListener carClickListener) {
        this.dataCarClassesList = dataCarClassesList;
        this.carClickListener = carClickListener;
    }

    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse_category_on_click_items, parent, false);
        return new CarsViewHolder(view,carClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
        DataCarClasses dataCarClasses = dataCarClassesList.get(position);
        holder.setData(dataCarClasses);
    }

    @Override
    public int getItemCount() {
        return dataCarClassesList.size();
    }

    public void updateData(List<DataCarClasses> dataCarClasses) {
        this.dataCarClassesList = dataCarClasses;
        notifyDataSetChanged();
    }
}
