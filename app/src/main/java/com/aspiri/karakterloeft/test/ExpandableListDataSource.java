//package com.aspiri.karakterloeft.test;
//
//import android.content.Context;
//
//import com.aspiri.karakterloeft.R;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//
///**
// * Created by Justin on 5/1/18.
// */
//public class ExpandableListDataSource {
//
//    /**
//     * Returns fake data of films
//     *
//     * @param context
//     * @return
//     */
//    public static Map<String, List<String>> getData(Context context) {
//        Map<String, List<String>> expandableListData = new TreeMap<>();
//
//        List<String> rentesregning = Arrays.asList(context.getResources().getStringArray(R.array.polynomier_list)); // mangler
//        List<String> kvadratsatninger = Arrays.asList(context.getResources().getStringArray(R.array.polynomier_list));  // mangler;
//        List<String> potensregneregler = Arrays.asList(context.getResources().getStringArray(R.array.polynomier_list)); // mangler
//        List<String> polynomier = Arrays.asList(context.getResources().getStringArray(R.array.polynomier_list));
//        List<String> linearexppotens = Arrays.asList(context.getResources().getStringArray(R.array.statistik_list)); // Mangler
//        List<String> statistik = Arrays.asList(context.getResources().getStringArray(R.array.statistik_list));
//        List<String> differentialregning = Arrays.asList(context.getResources().getStringArray(R.array.diffirentialregning_list));
//        List<String> integralregning = Arrays.asList(context.getResources().getStringArray(R.array.integralregning_list));
//        List<String> logaritmer = Arrays.asList(context.getResources().getStringArray(R.array.statistik_list));  // mangler
//        List<String> geometri = Arrays.asList(context.getResources().getStringArray(R.array.statistik_list)); // mangler
//        List<String> plangeometri = Arrays.asList(context.getResources().getStringArray(R.array.statistik_list)); // mangler
//        List<String> rumgeometri = Arrays.asList(context.getResources().getStringArray(R.array.statistik_list)); // mangler
//        List<String> arealomkreds = Arrays.asList(context.getResources().getStringArray(R.array.arealer_omkreds_rumfang_title));
//
//        List<String> titler = Arrays.asList(context.getResources().getStringArray(R.array.subject_list));
//
//        expandableListData.put(titler.get(0), rentesregning);
//        expandableListData.put(titler.get(1), kvadratsatninger);
//        expandableListData.put(titler.get(2), potensregneregler);
//        expandableListData.put(titler.get(3), polynomier);
//        expandableListData.put(titler.get(4), linearexppotens);
//        expandableListData.put(titler.get(5), statistik);
//        expandableListData.put(titler.get(6), differentialregning);
//        expandableListData.put(titler.get(7), integralregning);
//        expandableListData.put(titler.get(8), logaritmer);
//        expandableListData.put(titler.get(9), geometri);
//        expandableListData.put(titler.get(10), plangeometri);
//        expandableListData.put(titler.get(11), rumgeometri);
//        expandableListData.put(titler.get(12), arealomkreds);
//
//        return expandableListData;
//    }
//}