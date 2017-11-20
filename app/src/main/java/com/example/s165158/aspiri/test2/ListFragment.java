package com.example.s165158.aspiri.test2;

import android.app.Activity;
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
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Activity parentActivity;

    //Skal laves om!
    Integer[] imageArray = {R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
    };

    public void onCreate(Bundle savedinstanceSate) {
        // listen to backstack changes


        // other fragment init stuff
        super.onCreate(savedinstanceSate);
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInsanceState){
        View rootView = inflater.inflate(R.layout.recycler_list, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.listRecyclerView);

        subjectListArray = getResources().getStringArray(R.array.subject_list);
        subtextListArray = getResources().getStringArray(R.array.subtext_list);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(getActivity(),subjectListArray,subtextListArray,imageArray);
        recyclerView.setAdapter(recyclerListAdapter);


        return rootView;




    }
}

