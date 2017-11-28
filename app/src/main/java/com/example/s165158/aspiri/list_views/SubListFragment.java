package com.example.s165158.aspiri.list_views;

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

public class SubListFragment extends Fragment {

    public static final String TAG = "SubListFragment";
    private String[] subjectListArray, subtextListArray;
    private String listtitle;
    private AppCompatActivity mActivity;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private int listindex;
    private TextView listtitle_TextView;

    //Skal laves om!
    Integer[] imageArray = {R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity)
            mActivity = (AppCompatActivity) context;
    }



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInsanceState) {
        View rootView = inflater.inflate(R.layout.recycler_list, container, false);
        recyclerView = rootView.findViewById(R.id.listRecyclerView);

        Bundle args = getArguments();
        listindex = args.getInt("listindex");
        listtitle = args.getString("title");
        listtitle_TextView = rootView.findViewById(R.id.list_title);
        listtitle_TextView.setVisibility(View.VISIBLE);
        listtitle_TextView.setText(listtitle);

//        subjectListArray = getResources().getStringArray(R.array.subject_list);
//        subtextListArray = getResources().getStringArray(R.array.subtext_list);


        switch (listindex) {
            case 0:
                subjectListArray = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subtextListArray = getResources().getStringArray(R.array.arealer_sublist);
                imageArray = new Integer[]{R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
                };
                break;
            case 1:
                subjectListArray = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subtextListArray = getResources().getStringArray(R.array.arealer_sublist);
                imageArray = new Integer[]{R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
                };

                break;
            case 2:
                subjectListArray = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subtextListArray = getResources().getStringArray(R.array.arealer_sublist);
                imageArray = new Integer[]{R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
                };

                break;
            case 3:
                subjectListArray = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subtextListArray = getResources().getStringArray(R.array.arealer_sublist);
                imageArray = new Integer[]{R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
                };
                break;
            case 4:
                subjectListArray = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subtextListArray = getResources().getStringArray(R.array.arealer_sublist);
                imageArray = new Integer[]{R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
                };
                break;
            case 5:
                subjectListArray = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subtextListArray = getResources().getStringArray(R.array.arealer_sublist);
                imageArray = new Integer[]{R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
                };
                break;
            case 6:
                subjectListArray = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subtextListArray = getResources().getStringArray(R.array.arealer_sublist);
                imageArray = new Integer[]{R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
                };
                break;
            case 7:
                subjectListArray = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subtextListArray = getResources().getStringArray(R.array.arealer_sublist);
                imageArray = new Integer[]{R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
                };
                break;
            case 8:
                subjectListArray = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subtextListArray = getResources().getStringArray(R.array.arealer_sublist);
                imageArray = new Integer[]{R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
                };
                break;
            case 9:
                subjectListArray = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subtextListArray = getResources().getStringArray(R.array.arealer_sublist);
                imageArray = new Integer[]{R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
                };
                break;
            case 10:
                subjectListArray = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subtextListArray = getResources().getStringArray(R.array.arealer_sublist);
                imageArray = new Integer[]{R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig, R.drawable.trig
                };
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            default:

        }


        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(getActivity(), subjectListArray, subtextListArray, imageArray);
        recyclerView.setAdapter(recyclerListAdapter);


        return rootView;


    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) mActivity).setDrawerIndicatorEnabled(false);
        ((MainActivity) mActivity).setActionBarTitle(getString(R.string.app_name));
    }
}

