package com.aspiri.karakterloeft.games;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aspiri.karakterloeft.R;


/**
 * Created by janus on 1/11/2018.
 */

public class FlipcardButtonFragment extends Fragment {
    FragmentManager mFragmentManager = getFragmentManager();
    private AppCompatActivity mActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flipcard_buttons,container,false);
        mActivity = (AppCompatActivity) getActivity();



        Button b1 = view.findViewById(R.id.flipcard_button_next);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((FlipcardActivity) mActivity).countUpCurrentCardIndex();
                    int currentindex = ((FlipcardActivity) mActivity).getCurrentCardIndex();
                    int size = ((FlipcardActivity) mActivity).getCardStackSize();
                    if(currentindex >= size){
                        b1.setClickable(false);
                    }
                    CardFrontFragment frontFragment = new CardFrontFragment();

                    int index = ((FlipcardActivity) mActivity).getCurrentCardIndex();
                    if (currentindex <  size) {
                        ((FlipcardActivity) mActivity).nextFlipCard(frontFragment, "next flipcard");


                    }

                    }
            });
        return view;
    }





}

