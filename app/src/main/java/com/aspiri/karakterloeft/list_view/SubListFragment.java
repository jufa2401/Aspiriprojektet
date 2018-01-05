package com.aspiri.karakterloeft.list_view;

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
    Integer[] imageArray = {R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle, R.drawable.titlelist_triangle
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
//                 Renter
            case 0:
                subjectListArray = getResources().getStringArray(R.array.RentesRegning_introTekst);
                subtextListArray = getResources().getStringArray(R.array.RentesRegning_list_title);
                imageArray = new Integer[]{
                        R.drawable.subject_rente_money256,
                        R.drawable.subject_rente_percent256,
                        R.drawable.subject_rente_time256,
                        R.drawable.subject_rente_singlemoney256,
                        R.drawable.subject_rente_piggie256,
                        R.drawable.subject_rente_squiggily256,
                };
                break;
// Kvadrartsætninger
            case 1:
                subjectListArray = getResources().getStringArray(R.array.Kvadratsætningerne_introTekst);
                subtextListArray = getResources().getStringArray(R.array.Kvadratsætningerne_list_title);
                imageArray = new Integer[]{
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,

                };
                break;
//                Potensregneregler
            case 2:
                subjectListArray = getResources().getStringArray(R.array.Potensregneregler_title);
                subtextListArray = getResources().getStringArray(R.array.Potensregneregler_introTekst);
                imageArray = new Integer[]{
                        R.drawable.subject_rente_money256,
                        R.drawable.subject_rente_percent256,
                        R.drawable.subject_rente_time256,
                };
                break;
//                Polynomier
            case 3:
                subjectListArray = getResources().getStringArray(R.array.Polynomier_title);
                subtextListArray = getResources().getStringArray(R.array.Polynomier_introTekst);
                imageArray = new Integer[]{
                        R.drawable.subject_square_rules_1_256,
                        R.drawable.subject_square_rules_2_256,
                        R.drawable.subject_square_rules_3_256,
                };
                break;

//                 Linærer og eksponentiel og potenssammenhænge
            case 4:
                subjectListArray = getResources().getStringArray(R.array.Lineær_eksponentiel_potensregning_list);
                subtextListArray = getResources().getStringArray(R.array.Lineær_eksponentiel_potensregning_introTekst);
                imageArray = new Integer[]{
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                };
                break;

//               Statistik
            case 5:
                subjectListArray = getResources().getStringArray(R.array.Statistik_list);
                subtextListArray = getResources().getStringArray(R.array.Statistik_sublist);
                imageArray = new Integer[]{
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                };
                break;

//                Differentialeregning
            case 6:
                subjectListArray = getResources().getStringArray(R.array.Diffirentialregning_list);
                subtextListArray = getResources().getStringArray(R.array.Diffirentialregning_sublist);
                imageArray = new Integer[]{
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                };
                break;

//                Integralregning
            case 7:
                subjectListArray = getResources().getStringArray(R.array.Integralregning_list);
                subtextListArray = getResources().getStringArray(R.array.Integralregning_sublist);
                imageArray = new Integer[]{
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                };
                break;

//               Logaritmer
            case 8:
                subjectListArray = getResources().getStringArray(R.array.Logaritmer_list);
                subtextListArray = getResources().getStringArray(R.array.Logaritmer_sublist);
                imageArray = new Integer[]{
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                };
                break;

//                Geometri
            case 9:
                subjectListArray = getResources().getStringArray(R.array.Geometri_list_title);
                subtextListArray = getResources().getStringArray(R.array.Geometri_sublist);
                imageArray = new Integer[]{
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                };
                break;

//                Vektorer i planen
            case 10:
                subjectListArray = getResources().getStringArray(R.array.RumGeometri_list_title);
                subtextListArray = getResources().getStringArray(R.array.RumGeometri_sublist);
                imageArray = new Integer[]{
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                };
                break;
//                Vektorer i rum
            case 11:
                subjectListArray = getResources().getStringArray(R.array.RumGeometri_list_title);
                subtextListArray = getResources().getStringArray(R.array.RumGeometri_sublist);
                imageArray = new Integer[]{
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                        R.drawable.titlelist_triangle,
                };
                break;

//                 Areal, omkreds og rumfang
            case 12:

                subjectListArray = getResources().getStringArray(R.array.arealer_omkreds_rumfang_title);
                subtextListArray = getResources().getStringArray(R.array.arealer_sublist);
                imageArray = new Integer[]{
                        R.drawable.subject_area_c_v_circle256,
                        R.drawable.subject_area_c_v_triangle256,
                        R.drawable.subject_area_c_v_paralellogram256,
                        R.drawable.subject_area_c_v_trapez256,
                        R.drawable.subject_area_c_v_cone256,
                        R.drawable.subject_area_c_v_sphere256,
                        R.drawable.subject_area_c_v_cylinder231x256};
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
        if (mActivity instanceof MainActivity) {
            ((MainActivity) mActivity).setDrawerIndicatorEnabled(false);
//        ((MainActivity) mActivity).setActionBarTitle(getString(R.string.app_name));
        }
    }
}

