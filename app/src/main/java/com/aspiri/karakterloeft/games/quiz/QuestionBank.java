package com.aspiri.karakterloeft.games.quiz;

/**
 * Created by Justin on 10/01/2018.
 * Largely inspired by https://github.com/CreatorB/quiz-android
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

        String[] db1 = new String[]{"$$ \\pi \\cdot r^{2}$$", "$$ \\pi \\cdot 2 \\cdot r $$", "$$ \\pi \\cdot 4\\cdot r $$", "$$\\pi \\cdot 2 \\cdot r $$"};
        String[] db2 = new String[]{"$$ \\sqrt(\\frac{a}{\\pi} $$", "$$ \\frac{d}{2} $$", "$$ \\pi \\cdot 2\\cdot r $$", "$$ h \\cdot g $$"};
        String[] db3 = new String[]{"Størrelsen af cirklen", "Arealet af den indskrevne firkant", "Længden på linjen gennem centrum, afgrænset af cirkelperiferien", "Radius ganget med Pi"};
        String[] db4 = new String[]{"$$ 14,67 $$", "$$ \\pi $$", "$$ 3,1499 $$", "$$ 1 $$"};
        String[] db5 = new String[]{"$$ 2\\cdot 3 $$", "$$ 2\\cdot r \\cdot \\pi $$", "$$ \\frac{a}{\\pi} $$", "$$ d \\cdot \\pi $$"};
        String[] db6 = new String[]{"En linje som går igennem en cirkel", "Netop den linje som går igennem centrum af cirklen", "Højden af en cirkel", "En linje som er afgrænset af cirklens periferi, og rører denne 2 gange"};
        String[] db7 = new String[]{"$$ 2\\cdot d \\cdot (sin(\\frac{v}{2})) $$", "$$ 2\\cdot r \\cdot (sin(\\frac{v}{2}))\\ $$", "$$ 2 \\cdot r \\cdot (cos(\\frac{v}{2})) $$", "$$ 4 \\cdot d \\cdot sin(\\frac{v}{2})) $$"};
        String[] db8 = new String[]{"To piger", "Tre mændt", "Femten drenge", "det kan man"};
        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers

            myDataBaseHelper.addInitialQuestion(new Question("Hvad er areal for en cirkel?",
                    db1, db1[0]));
            myDataBaseHelper.addInitialQuestion(new Question("Hvad er radius for en cirkel?",
                    db2, db2[0]));
            myDataBaseHelper.addInitialQuestion(new Question("Hvad er diameteren et udtryk for?",
                    db3, db3[2]));
            myDataBaseHelper.addInitialQuestion(new Question("Hvad er arealet for en cirkel med radius på 1",
                    db4, db4[1]));
            myDataBaseHelper.addInitialQuestion(new Question("Hvad er formlen for en omkreds af en cirkel",
                    db5, db5[3]));
            myDataBaseHelper.addInitialQuestion(new Question("Hvad er en korde?",
                    db6, db6[3]));
            myDataBaseHelper.addInitialQuestion(new Question("Hvad er en formlen for en korde?",
                    db7, db7[2]));
            myDataBaseHelper.addInitialQuestion(new Question("Hvordan kan man huske hvad arealet for en cirkel er?",
                    db8, db8[0]));

            list = myDataBaseHelper.getAllQuestionsList();//get list from database again

        }
    }

}