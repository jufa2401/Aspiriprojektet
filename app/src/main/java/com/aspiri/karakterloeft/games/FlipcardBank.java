package com.aspiri.karakterloeft.games;

/**
 * Created by Justin on 10/01/2018.
 */

import android.content.Context;

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

    // method returns question from list based on list index
    public String getFront(int a) { return list.get(a).getFront(); }

    public String getBack(int a){return list.get(a).getBack();}
    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getPhoto(int a){return list.get(a).getPhoto();}



    public void initQuestions(Context context) {
        myDataBaseHelper = new MultipleChoiceDataBaseHelper(context);
        list = myDataBaseHelper.getAllFlipcardsList();//get questions/choices/answers from database
        String[] q1 = new String[]{"$$ y=a \\cdot x+b $$", "$$ y=a\\frac{x}{a} $$", "$$ y=a\\frac{a}{x} $$", "$$ y=e^x $$"};
        String[] q2 = new String[]{"JVM", "Gradle", "Dalvik", "HAXM"};
        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            myDataBaseHelper.addInitialFlipcard(new Flipcard("Hvad er formlen for en ret linje",
                    "sdsds", "dsas"));
            myDataBaseHelper.addInitialFlipcard(new Flipcard("2. What is the name of build toolkit for Android Studio?",
                    "sds", "sdsdsd"));
            myDataBaseHelper.addInitialFlipcard(new Flipcard("sdasd","sdssaa","dsasad"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("4. What is a widget in Android app?","hheh","sdsdsd"));

            list = myDataBaseHelper.getAllFlipcardsList();//get list from database again

        }
    }

}