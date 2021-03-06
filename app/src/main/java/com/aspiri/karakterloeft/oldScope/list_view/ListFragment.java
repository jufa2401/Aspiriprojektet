package com.aspiri.karakterloeft.oldScope.list_view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspiri.karakterloeft.MainActivity;
import com.aspiri.karakterloeft.R;

import butterknife.BindView;
import butterknife.ButterKnife;

//import android.widget.ListAdapter;


/**
 * Created by s165158 on 04-11-2017.
 */

public class ListFragment extends Fragment {

    public static final String TAG = "ListFragment";
    @BindView(R.id.listRecyclerView)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Activity mActivity;
    @BindView(R.id.list_title)
    TextView listtitle;
    //Skal laves om! jeg har rodet med den 05 /01 - mikkel
    Integer[] imageArray = {
            R.drawable.titlelist_percent256,
            R.drawable.titlelist_x_squared256,
            R.drawable.titlelist_x_tothepowerof_y256,
            R.drawable.titlelist_calculator256,
            R.drawable.titlelist_function256x230,
            R.drawable.titlelist_statistics256,
            R.drawable.titlelist_f_ding_x280,
            R.drawable.titlelist_integral256,
            R.drawable.titlelist_log280,
            R.drawable.titlelist_protractor280,
            R.drawable.titlelist_arrows_in256,
            R.drawable.titlelist_arrows_out256,
            R.drawable.titlelist_area_circomference_and_volume256,
    };
    private String[] subjectListArray, subtextListArray;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity)
            mActivity = (AppCompatActivity) context;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_list, container, false);
        ButterKnife.bind(this, rootView);

        //Title list is not relevant here, but needed for the view
        listtitle.setHeight(0);

        subjectListArray = getResources().getStringArray(R.array.subject_list);
        subtextListArray = getResources().getStringArray(R.array.subtext_list);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerListAdapterList recyclerListAdapter = new RecyclerListAdapterList(getActivity(), subjectListArray, subtextListArray, imageArray);
        recyclerView.setAdapter(recyclerListAdapter);

        if (savedInstanceState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable("liste"));
        }
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) { // Understøttelse for skærmvending - kan evt udelades
        super.onSaveInstanceState(outState);
        outState.putParcelable("liste", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mActivity instanceof MainActivity) {
            ((MainActivity) mActivity).setDrawerIndicatorEnabled(true);
//        ((MainActivity) mActivity).setActionBarTitle(getString(R.string.app_name));
        }
    }
}

