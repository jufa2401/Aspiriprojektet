package com.example.s165158.aspiri;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import katex.hourglass.in.mathlib.MathView;

//import butterknife.BindView;

/**
 * Created by jonas on 21/11/2017.
 */

public class mathviewTEST extends Fragment {
    private String[] subject, intro, text_following_picture_1, text_following_picture_2;
    private int listindex, oldindex;
    private String title;

    //    Leger med at bruge butterknife, det virker meget smartere at skrive det heroppe i stedet for at det skal fylde i en OnCreate metoder med findViewbyId og OnClick
    // Fordi jeg kan sætte det hele som et array her sparer jeg samtlige linjer kode
    @BindView(R.id.math_view_1)
    MathView mathView1;


    //    Alle TextViews forinden eksempler
    @BindViews({
            R.id.subject_fragment_title,                    //0
            R.id.subject_fragment_subject,                  //1
            R.id.subject_fragment_intro,                    //2
            R.id.subject_fragment_text_following_picture_1, //3
            R.id.subject_fragment_text_following_picture_2  //4
    })
    TextView textsBeforeExamples[];             // Et tilsvarende array skal laves for billeder matematikformler etc.

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInsanceState){
        View view = inflater.inflate(R.layout.test, container, false);
        ButterKnife.bind(this, view);   // Laver findViewById for alle mine erklæringer ovenfor med annotationen @BindView(s)

        Bundle args = getArguments();
        listindex = args.getInt("listindex");
//        Når vi skal udvide vores app til at undestøtte strings fra flere forskellige overemner, er vi nødt til at lave en switch case for hvert overemne.
        oldindex = args.getInt("oldindex");

        title = getResources().getString(R.string.Title_Teori);
        subject = getResources().getStringArray(R.array.Arealer_omkreds_rumfang_list);
        // intro = getResources().getStringArray(R.array.introductionTexts);
        //text_following_picture_1 = getResources().getStringArray(R.array.texts_following_picture_1);
        //text_following_picture_2 = getResources().getStringArray(R.array.texts_following_picture_2);

// Her skal der være en switch case for hvert eneste "super" overemne.
        switch (oldindex) {
            case 0:
                setTexts(title, subject[listindex], intro[listindex], text_following_picture_1[listindex], text_following_picture_2[listindex]);
                break;
            default:
                setTexts(title, subject[listindex], intro[listindex], text_following_picture_1[listindex], text_following_picture_2[listindex]);
                break;
        }



//      example mathview
        mathView1.setDisplayText("$$x=\\frac{1+y}{1+2z^2}$$");


        return view;
    }

    private void setTexts(String title, String subject, String intro, String text_following_picture_1, String text_following_picture_2) {
        textsBeforeExamples[0].setText(title);
        textsBeforeExamples[1].setText(subject);
        textsBeforeExamples[2].setText(intro);
        textsBeforeExamples[3].setText(text_following_picture_1);
        textsBeforeExamples[4].setText(text_following_picture_2);
    }
    @Override
    public void onResume() {
        super.onResume();

    }

}
