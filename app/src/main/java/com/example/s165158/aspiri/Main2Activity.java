package com.example.s165158.aspiri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s165158.aspiri.objects.CustomListAdapter;
import com.example.s165158.aspiri.other.activities.ClickOnList;

public class Main2Activity extends BaseActivity {
    ListView lst;


    //instantierer Arrays så det kan bruges uden for klassen, og refereres til fra Strings
    String[] subjectListArray;
    String[] subtextListArray;

    //Skal laves om!
    Integer[] imageArray = {R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.base_activity);

        subjectListArray = getResources().getStringArray(R.array.subject_list);
        subtextListArray = getResources().getStringArray(R.array.subtext_list);

        createList(subjectListArray,subtextListArray,imageArray);

        activateToolbar();
        activateFloatingActionButton();
        activateNavigationView();
    }



    public void createList(String[] title, String[] description, Integer[] picture) {
// Objekt for den scrollende liste
        CustomListAdapter whatever = new CustomListAdapter(this, subjectListArray, subtextListArray, imageArray);
        lst = (ListView) findViewById(R.id.list);
        lst.setAdapter(whatever);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "ID: " + id, Toast.LENGTH_SHORT).show();
//                Intent er hvad man bruger til at skifte imellem activities
                Intent intent = new Intent(Main2Activity.this, ClickOnList.class);
//                Laver en string variabel og positionerer den efter hvilken række i listen der blev valgt
                String message = subjectListArray[position];
//                Tilføjer nu strengen som en "extra" til Intent
                intent.putExtra("content", message);
                startActivity(intent);

            }
        });
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main2;
    }

    static class ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView iconImageView;
    }


}

