package developer.com.mr.olx.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.classes.DataClasses;
import developer.com.mr.olx.clicklistener.AllInOneClickListener;
import developer.com.mr.olx.viewholders.AllInOneViewHolder;


public class AllInOneViewAdapter extends RecyclerView.Adapter<AllInOneViewHolder> {
    private List<DataClasses> dataClassesArrayList;
    private AllInOneClickListener allInOneClickListener;

    public AllInOneViewAdapter(List<DataClasses> dataClassesArrayList, AllInOneClickListener allInOneClickListener) {
        this.dataClassesArrayList = dataClassesArrayList;
        this.allInOneClickListener = allInOneClickListener;
    }

    @NonNull
    @Override
    public AllInOneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_all_in_one, parent, false);
        return new AllInOneViewHolder(view, allInOneClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AllInOneViewHolder holder, int position) {
        DataClasses dataClasses = dataClassesArrayList.get(position);
        holder.setData(dataClasses);
    }

    @Override
    public int getItemCount() {
        return dataClassesArrayList.size();
    }

    public void updateDataFromApi(List<DataClasses> dataClasses) {
        this.dataClassesArrayList = dataClasses;
        notifyDataSetChanged();
    }
}
