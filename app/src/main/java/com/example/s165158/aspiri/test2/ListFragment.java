package com.example.s165158.aspiri.test2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.s165158.aspiri.R;

//import android.widget.ListAdapter;


/**
 * Created by s165158 on 04-11-2017.
 */

public class ListFragment extends Fragment {

    private String[] subjectListArray,subtextListArray;

    //Skal laves om!
    Integer[] imageArray = {R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
    };
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInsanceState){
        View rootView = inflater.inflate(R.layout.fragment_list_test2, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.listRecyclerView);

        subjectListArray = getResources().getStringArray(R.array.subject_list);
        subtextListArray = getResources().getStringArray(R.array.subtext_list);


        RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(getActivity(),subjectListArray,subtextListArray,imageArray);

        recyclerView.setAdapter(recyclerListAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return rootView;
    }





}



