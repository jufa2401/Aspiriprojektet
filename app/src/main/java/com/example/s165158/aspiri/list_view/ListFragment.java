package com.example.s165158.aspiri.list_view;

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

import com.example.s165158.aspiri.MainActivity;
import com.example.s165158.aspiri.R;

//import android.widget.ListAdapter;


/**
 * Created by s165158 on 04-11-2017.
 */

public class ListFragment extends Fragment {

    public static final String TAG = "ListFragment";
    private String[] subjectListArray,subtextListArray;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Activity mActivity;
    private TextView listtitle;

    //Skal laves om!
    Integer[] imageArray = {R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity)
            mActivity = (AppCompatActivity) context;
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInsanceState){
        View rootView = inflater.inflate(R.layout.recycler_list, container, false);
        recyclerView = rootView.findViewById(R.id.listRecyclerView);

        listtitle = rootView.findViewById(R.id.list_title);
        if (listtitle.getVisibility() == View.VISIBLE) {
            listtitle.setVisibility(View.INVISIBLE);
        }

        subjectListArray = getResources().getStringArray(R.array.subject_list);
        subtextListArray = getResources().getStringArray(R.array.subtext_list);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(getActivity(),subjectListArray,subtextListArray,imageArray);
        recyclerView.setAdapter(recyclerListAdapter);


        return rootView;




    }
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) mActivity).setDrawerIndicatorEnabled(true);
//        ((MainActivity) mActivity).setActionBarTitle(getString(R.string.app_name));
    }
}

