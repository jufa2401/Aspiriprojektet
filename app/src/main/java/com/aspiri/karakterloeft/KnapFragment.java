package com.aspiri.karakterloeft;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspiri.karakterloeft.list_view.ListFragment;

/**
 * Created by Justin on 10/01/2018.
 */

public class KnapFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInsanceState) {
        View rootView = inflater.inflate(R.layout.knapframe_content, container, false);

        return rootView;
    }
}