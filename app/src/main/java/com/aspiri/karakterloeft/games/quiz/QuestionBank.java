package com.aspiri.karakterloeft.games.quiz;

/**
 * Created by Justin on 10/01/2018.
 */

import android.content.Context;

import com.aspiri.karakterloeft.games.ourDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    // declare list of Question objects
    List<Question> list = new ArrayList<>();
    ourDatabaseHelper myDataBaseHelper;

    // method returns number of questions in list
    public int getLength() {
        return list.size();
    }

    // method returns question from list based on list index
    public String getQuestion(int a) {
        return list.get(a).getQuestion();
    }

    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getChoice(int index, int num) {
        if(index >= list.size()){
            return null;
        }
        return list.get(index).getChoice(num-1);
    }

    //  method returns correct answer for the question based on list index
    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer();
    }


    public void initQuestions(Context context) {
        myDataBaseHelper = new ourDatabaseHelper(context);
        list = myDataBaseHelper.getAllQuestionsList();//get questions/choices/answers from database
        String[] q1 = new String[]{"$$ y=a \\cdot x+b $$", "$$ y=a\\frac{x}{a} $$", "$$ y=a\\frac{a}{x} $$", "$$ y=e^x $$"};
        String[] q2 = new String[]{"JVM", "Gradle", "Dalvik", "HAXM"};
        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers

            myDataBaseHelper.addInitialQuestion(new Question("2. What is the name of build toolkit for Android Studio?",
                    q2, q2[1]));
            myDataBaseHelper.addInitialQuestion(new Question("3. What widget can replace any use of radio buttons?",
                    new String[]{"Toggle Button", "Spinner", "Switch Button", "ImageButton"}, "Spinner"));
            myDataBaseHelper.addInitialQuestion(new Question("4. What is a widget in Android app?",
                    new String[]{"reusable GUI element", "Layout for Activity", "device placed in cans of beer", "build toolkit"}, "reusable GUI element"));
            myDataBaseHelper.addInitialQuestion(new Question("Hvad er formlen for en ret linje",
                    q1, q1[0]));
            list = myDataBaseHelper.getAllQuestionsList();//get list from database again

        }
    }

}