package com.example.s165158.aspiri;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private String[] subject, intro, text_following_picture_1, text_following_picture_2, mathView1, mathView2, mathView3, mathView4;
    private int listindex, oldindex;
    private String title;

    private int[] picture_1, picture_2;
    //    Leger med at bruge butterknife, det virker meget smartere at skrive det heroppe i stedet for at det skal fylde i en OnCreate metoder med findViewbyId og OnClick
    // Fordi jeg kan sætte det hele som et array her sparer jeg samtlige linjer kode


    //    Alle MathViews
    @BindViews({
            R.id.subject_fragment_math_view_1,              //1
            R.id.subject_fragment_math_view_2,              //2
            R.id.subject_fragment_math_view_3,              //3
            R.id.subject_fragment_math_view_4               //4
    })
    MathView mathViews[];

    //    Alle TextViews forinden eksempler
    @BindViews({
            R.id.subject_fragment_title,                    //0
            R.id.subject_fragment_subject,                  //1
            R.id.subject_fragment_intro,                    //2
            R.id.subject_fragment_text_following_picture_1, //3
            R.id.subject_fragment_text_following_picture_2  //4
    })
    TextView textsBeforeExamples[];             // Et tilsvarende array skal laves for billeder matematikformler etc.


    @BindViews({R.id.subject_fragment_picture_1, R.id.subject_fragment_picture_2})
    ImageView subject_pictures[];

    //    Gamebutton pr. default er game_button layout med GONE visibility. Hvis der skal være en spilknap sætter man den med setVisibility .VISIBLE
    @BindView(R.id.subject_fragment_game_button_layout)
    LinearLayout game_button_layout;
    @BindView(R.id.subject_fragment_game_button)
    LinearLayout game_button;
    @BindView(R.id.subject_fragment_game_text)
    TextView game_text;
    @BindView(R.id.subject_fragment_game_thumb)
    ImageView game_thumb;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInsanceState){
        View view = inflater.inflate(R.layout.test, container, false);
        ButterKnife.bind(this, view);   // Laver findViewById for alle mine erklæringer ovenfor med annotationen @BindView(s)

        Bundle args = getArguments();

        listindex = args.getInt("listindex");
//        Når vi skal udvide vores app til at undestøtte strings fra flere forskellige overemner, er vi nødt til at lave en switch case for hvert overemne.
        oldindex = args.getInt("oldindex");
// Her skal der være en switch case for hvert eneste "super" overemne.
        switch (oldindex) {
            case 0:
//                Henter MathView Strings
                mathView1 = getResources().getStringArray(R.array.arealer_mathView1);
                mathView2 = getResources().getStringArray(R.array.arealer_mathView2);
                mathView3 = getResources().getStringArray(R.array.arealer_mathView3);
                mathView4 = getResources().getStringArray(R.array.arealer_mathView4);
//                Henter Resten af Stringsne
                title = getResources().getString(R.string.Title_Teori);
                subject = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                intro = getResources().getStringArray(R.array.arealer_introducerende_tekst);
                text_following_picture_1 = getResources().getStringArray(R.array.arealer_text_following_picture_1);
                text_following_picture_2 = getResources().getStringArray(R.array.arealer_text_following_picture_2);

//               henter billeder
                picture_1 = new int[]{R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1};
                picture_2 = new int[]{R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1};

//               Sætter alt
                setTexts(title, subject[listindex], intro[listindex], text_following_picture_1[listindex], text_following_picture_2[listindex]);
                setPictures(picture_1[listindex], picture_2[listindex]);
                setMathViews(mathView1[listindex], mathView2[listindex], mathView3[listindex], mathView4[listindex]);
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            default:
                title = getResources().getString(R.string.sample_subject);
                subject = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                intro = getResources().getStringArray(R.array.arealer_introducerende_tekst);
                text_following_picture_1 = getResources().getStringArray(R.array.arealer_text_following_picture_1);
                text_following_picture_2 = getResources().getStringArray(R.array.arealer_text_following_picture_2);

                setTexts(title, subject[listindex], intro[listindex], text_following_picture_1[listindex], text_following_picture_2[listindex]);
                break;
        }



//      example mathview



        return view;
    }


    //  Enkapslerende metoder
    private void setTexts(String title, String subject, String intro, String text_following_picture_1, String text_following_picture_2) {
        if (title != null) textsBeforeExamples[0].setText(title);
        if (subject != null) textsBeforeExamples[1].setText(subject);
        if (intro != null) textsBeforeExamples[2].setText(intro);
        if (text_following_picture_1 != null)
            textsBeforeExamples[3].setText(text_following_picture_1);
        if (text_following_picture_2 != null)
            textsBeforeExamples[4].setText(text_following_picture_2);
    }

    private void setMathViews(String mathView1, String mathView2, String mathView3, String mathView4) {
        if (mathView1 != null) mathViews[0].setDisplayText(mathView1);
        if (mathView2 != null) mathViews[1].setDisplayText(mathView2);
        if (mathView3 != null) mathViews[2].setDisplayText(mathView3);
        if (mathView4 != null) mathViews[3].setDisplayText(mathView4);

    }

    private void setPictures(int picture1, int picture2) {
        subject_pictures[0].setImageDrawable(getResources().getDrawable(picture1));
        subject_pictures[1].setImageDrawable(getResources().getDrawable(picture2));
    }


}
