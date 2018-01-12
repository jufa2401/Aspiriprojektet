package com.aspiri.karakterloeft.games;

/**
 * Created by Justin on 10/01/2018.
 */

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FlipcardBank {

    // declare list of Question objects
    List<Flipcard> list = new ArrayList<>();
    MultipleChoiceDataBaseHelper myDataBaseHelper;

    // method returns number of questions in list
    public int getLength() {
        return list.size();
    }

    public String getCategory(int a) {
        return list.get(a).getCategory();
    }
    // method returns question from list based on list index
    public String getFront(int a) { return list.get(a).getFront(); }

    public String getBack(int a){return list.get(a).getBack();}
    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getPhoto(int a){return list.get(a).getPhoto();}


    public void initFlipcards(Context context) {
        myDataBaseHelper = new MultipleChoiceDataBaseHelper(context);
        list = myDataBaseHelper.getAllFlipcardsList();//get questions/choices/answers from database
        String q1 = "$$ y=a \\cdot x+b $$";

        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            myDataBaseHelper.addInitialFlipcard(new Flipcard("ret linje", "Hvad er formlen for en ret linje",
                    q1, "RET LINJE PHOTO"));
            myDataBaseHelper.addInitialFlipcard(new Flipcard("Rentes Regning", "Renter FRONT",
                    "sds", "sdsdsd"));
            myDataBaseHelper.addInitialFlipcard(new Flipcard("Geometri", "Geometri FRONT", "Geometri BACK", "Geometri PHOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Areal", "AREAL FRONT", "AREAL BACK", "AREAÆ PHOTO"));

            list = myDataBaseHelper.getAllFlipcardsList();//get list from database again
            Log.d("Init database", list.toString());

        }
    }


}