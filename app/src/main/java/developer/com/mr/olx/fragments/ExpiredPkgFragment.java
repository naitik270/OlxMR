package developer.com.mr.olx.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import developer.com.mr.olx.R;

public class ExpiredPkgFragment extends Fragment {

    RecyclerView rv_show_pkg;
    private ProgressDialog pd;

    public ExpiredPkgFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pkg_details, container, false);
        rv_show_pkg = view.findViewById(R.id.rv_show_pkg);
        rv_show_pkg.setLayoutManager(new LinearLayoutManager(getActivity()));

        pd = new ProgressDialog(getActivity());
//        pd.show();

        return view;
    }


}