package com.aspiri.karakterloeft.test;

import android.content.Context;

import com.aspiri.karakterloeft.R;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by msahakyan on 22/10/15.
 */
public class ExpandableListDataSource {

    /**
     * Returns fake data of films
     *
     * @param context
     * @return
     */
    public static Map<String, List<String>> getData(Context context) {
        Map<String, List<String>> expandableListData = new TreeMap<>();

        List<String> rentesregning = Arrays.asList(context.getResources().getStringArray(R.array.arealer_omkreds_rumfang_title));

        List<String> kvadratsatninger = Arrays.asList(context.getResources().getStringArray(R.array.arealer_introducerende_tekst));
        List<String> potensregneregler = Arrays.asList(context.getResources().getStringArray(R.array.arealer_text_above_mathview2));
        List<String> polynomier = Arrays.asList(context.getResources().getStringArray(R.array.diffirentialregning_list));
        List<String> linearexppotens = Arrays.asList(context.getResources().getStringArray(R.array.polynomier_list));
        List<String> statistik = Arrays.asList(context.getResources().getStringArray(R.array.subtext_list));

        expandableListData.put(rentesregning.get(0), kvadratsatninger);
        expandableListData.put(rentesregning.get(1), potensregneregler);
        expandableListData.put(rentesregning.get(2), polynomier);
        expandableListData.put(rentesregning.get(3), linearexppotens);
        expandableListData.put(rentesregning.get(4), statistik);

        return expandableListData;
    }
}