package com.example.s165158.aspiri.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s165158.aspiri.R;
import com.example.s165158.aspiri.objects.CustomListAdapter;

public class MyListFragment extends Fragment {
    ListView lst;


    //instantierer Arrays så det kan bruges uden for klassen, og refereres til fra Strings
    String[] subjectListArray;
    String[] subtextListArray;

    //Skal laves om!
    Integer[] imageArray = {R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
    };
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_activity);

//        if (savedInstanceState != null) {
//
//            subjectListArray = getResources().getStringArray(R.array.subject_list);
//            subtextListArray = getResources().getStringArray(R.array.subtext_list);
//
//            createList(subjectListArray, subtextListArray, imageArray);
//        }
//    }



            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.list_view, container, false);
                lst = (ListView) view.findViewById(R.id.listview);

                if (savedInstanceState != null) {
                    subjectListArray = getResources().getStringArray(R.array.subject_list);
                    subtextListArray = getResources().getStringArray(R.array.subtext_list);
                    createList(subjectListArray, subtextListArray, imageArray);


                }
                return view;
            }

    public void createList(String[] title, String[] description, Integer[] picture) {
// Objekt for den scrollende liste

        final Activity activity = getActivity();
        CustomListAdapter whatever = new CustomListAdapter(activity, subjectListArray, subtextListArray, imageArray);

        lst.setAdapter(whatever);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(activity.getApplicationContext(), "ID: " + id, Toast.LENGTH_SHORT).show();
//                Intent er hvad man bruger til at skifte imellem activities
//                Intent intent = new Intent(this, ClickOnList.class);
                //                Laver en string variabel og positionerer den efter hvilken række i listen der blev valgt
                String message = subjectListArray[position];
//                Tilføjer nu strengen som en "extra" til Intent
//                intent.putExtra("content", message);
//                startActivity(intent);

            }
        });
    }

    static class ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView iconImageView;
    }


}

