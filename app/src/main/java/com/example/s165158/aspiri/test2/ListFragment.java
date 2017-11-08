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

    String[] subjectListArray;
    String[] subtextListArray;

    //Skal laves om!
    Integer[] imageArray = {R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
    };


    /// /    Button button_playagain;

    //    DatabaseHelper databaseHelper;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInsanceState){
        View rootView = inflater.inflate(R.layout.fragment_list_test2, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.listRecyclerView);
//        getActivity().setTitle(R.string.high_score);

//        databaseHelper = new DatabaseHelper(getActivity());

//        System.out.println(databaseHelper.getData().toString());


//        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        scores = prefs.getString("Forsøg","").split("\\|");
//        StringBuilder scorebuilder = new StringBuilder("");
//
//        for(String scorearray:scores){
//            scorebuilder.append(scorearray+"\n");
//        }

//
//        button_playagain = (Button)rootView.findViewById(R.id.button_play_again);
//        button_playagain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Menu menu = new Menu();
//// Insert the fragment by replacing any existing fragment
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, menu)
//                        .commit();
//
//            }
//        });

//        ListAdapter listAdapter = new ListAdapter(databaseHelper.getColumn(0),
//                databaseHelper.getColumn(1), databaseHelper.getColumn(2),
//                databaseHelper.getColumn(3),databaseHelper.getColumn(4));
        subjectListArray = getResources().getStringArray(R.array.subject_list);
        subtextListArray = getResources().getStringArray(R.array.subtext_list);

        RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(subjectListArray,subtextListArray,imageArray);

        recyclerView.setAdapter(recyclerListAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return rootView;
    }

    /**
     * customizable toast
     * @param message
     */




}



