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
    private String[] title, subject, intro, text_following_picture_1, text_following_picture_2, text_above_picture_3, mathView1, mathView2, mathView3, mathView4,
            text_above_mathview_1, text_above_mathview_2, text_above_mathview_3, text_above_mathview_4;
    private int listindex, oldindex;


    private int[] picture_1, picture_2, picture_3;
    //    Leger med at bruge butterknife, det virker meget smartere at skrive det heroppe i stedet for at det skal fylde i en OnCreate metode med findViewbyId og OnClick
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
            R.id.subject_fragment_text_following_picture_2,  //4
            R.id.subject_text_above_picture_3,               //5
            R.id.subject_fragment_text_above_mathview1,      //6
            R.id.subject_fragment_text_above_mathview2,
            R.id.subject_fragment_text_above_mathview3,
            R.id.subject_fragment_text_above_mathview4
    })
    TextView textsViews[];             // Et tilsvarende array skal laves for billeder matematikformler etc.


    @BindViews({R.id.subject_fragment_picture_1, R.id.subject_fragment_picture_2, R.id.subject_fragment_picture_3})
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

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInsanceState) {
        View view = inflater.inflate(R.layout.test, container, false);
        ButterKnife.bind(this, view);   // Laver findViewById for alle mine erklæringer ovenfor med annotationen @BindView(s)

        Bundle args = getArguments();

        listindex = args.getInt("listindex");
//        Når vi skal udvide vores app til at undestøtte strings fra flere forskellige overemner, er vi nødt til at lave en switch case for hvert overemne.
        oldindex = args.getInt("oldindex");
// Her skal der være en switch case for hvert eneste "super" overemne.
        switch (oldindex) {
            case 0:
                //              Henter MathView Strings
                mathView1 = getResources().getStringArray(R.array.arealer_mathView1);
                mathView2 = getResources().getStringArray(R.array.arealer_mathView2);
                mathView3 = getResources().getStringArray(R.array.arealer_mathView3);
                mathView4 = getResources().getStringArray(R.array.arealer_mathView4);
//                Henter Resten af Stringsne
                title =  getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subject = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                intro = getResources().getStringArray(R.array.arealer_introducerende_tekst);
                text_following_picture_1 = getResources().getStringArray(R.array.arealer_text_following_picture_1);
                text_following_picture_2 = getResources().getStringArray(R.array.arealer_text_following_picture_2);
                text_above_picture_3 = getResources().getStringArray(R.array.text_above_picture_3);

                text_above_mathview_1 = getResources().getStringArray(R.array.arealer_text_above_mathview1);
                text_above_mathview_2 = getResources().getStringArray(R.array.arealer_text_above_mathview2);
                text_above_mathview_3 = getResources().getStringArray(R.array.arealer_text_above_mathview3);
                text_above_mathview_4 = getResources().getStringArray(R.array.arealer_text_above_mathview4);


//               henter billeder
                picture_1 = new int[]{R.drawable.kegle_ting_man_skal_beregne, R.drawable.kegle_ting_man_skal_beregne, R.drawable.kegle_ting_man_skal_beregne, R.drawable.kegle_ting_man_skal_beregne, R.drawable.kegle_ting_man_skal_beregne, R.drawable.kugle_ting_man_skal_beregne, R.drawable.kvartratsaetninger_table1};
                picture_2 = new int[]{R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1, R.drawable.kvartratsaetninger_table1};
                picture_3 = new int[]{R.drawable.kvartratsaetninger_table1, 0, 0, 0, 0, 0, 0};

//               Sætter alt
                setTexts(title[listindex], subject[listindex], intro[listindex], text_following_picture_1[listindex], text_following_picture_2[listindex], text_above_picture_3[listindex],
                        text_above_mathview_1[listindex], text_above_mathview_2[listindex], text_above_mathview_3[listindex], text_above_mathview_4[listindex]);
                setPictures(picture_1[listindex], picture_2[listindex], picture_3[listindex]);
                setMathViews(mathView1[listindex], mathView2[listindex], mathView3[listindex], mathView4[listindex]);
                break;
            //

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
                title =  getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                subject = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                intro = getResources().getStringArray(R.array.arealer_introducerende_tekst);
                text_following_picture_1 = getResources().getStringArray(R.array.arealer_text_following_picture_1);
                text_following_picture_2 = getResources().getStringArray(R.array.arealer_text_following_picture_2);

                setTexts(title[listindex], subject[listindex], intro[listindex], text_following_picture_1[listindex], text_following_picture_2[listindex], text_above_picture_3[listindex],
                        text_above_mathview_1[listindex], text_above_mathview_2[listindex], text_above_mathview_3[listindex], text_above_mathview_4[listindex]);
                break;
        }


//      example mathview


        return view;
    }


    //  Enkapslerende metoder
    private void setTexts(String title, String subject, String intro, String text_following_picture_1, String text_following_picture_2, String text_above_picture_3, String text_above_mathview_1, String text_above_mathview_2, String text_above_mathview_3, String text_above_mathview_4) {
        if (!title.equals("0")) textsViews[0].setText(title);
        else textsViews[0].setVisibility(View.GONE);
        if (!subject.equals("0")) textsViews[1].setText(subject);
        else textsViews[1].setVisibility(View.GONE);
        if (!intro.equals("0")) textsViews[2].setText(intro);
        else textsViews[2].setVisibility(View.GONE);
        if (!text_following_picture_1.equals("0")) textsViews[3].setText(text_following_picture_1);
        else textsViews[3].setVisibility(View.GONE);
        if (!text_following_picture_2.equals("0")) textsViews[4].setText(text_following_picture_2);
        else textsViews[4].setVisibility(View.GONE);
        if (!text_above_picture_3.equals("0")) textsViews[5].setText(text_above_picture_3);
        else textsViews[5].setVisibility(View.GONE);
        if (!text_above_mathview_1.equals("0")) textsViews[6].setText(text_above_mathview_1);
        else textsViews[6].setVisibility(View.GONE);
        if (!text_above_mathview_2.equals("0")) textsViews[7].setText(text_above_mathview_2);
        else textsViews[7].setVisibility(View.GONE);
        if (!text_above_mathview_3.equals("0")) textsViews[8].setText(text_above_mathview_3);
        else textsViews[8].setVisibility(View.GONE);
        if (!text_above_mathview_4.equals("0")) textsViews[9].setText(text_above_mathview_4);
        else textsViews[9].setVisibility(View.GONE);

    }

    private void setMathViews(String mathView1, String mathView2, String mathView3, String mathView4) {
        if (!mathView1.equals("0")) mathViews[0].setDisplayText(mathView1);
        else mathViews[0].setVisibility(View.GONE);
        if (!mathView2.equals("0")) mathViews[1].setDisplayText(mathView2);
        else mathViews[0].setVisibility(View.GONE);
        if (!mathView3.equals("0")) mathViews[2].setDisplayText(mathView3);
        else mathViews[0].setVisibility(View.GONE);
        if (!mathView4.equals("0")) mathViews[3].setDisplayText(mathView4);
        else mathViews[0].setVisibility(View.GONE);

    }

    private void setPictures(int picture1, int picture2, int picture3) {
        if (picture1 != 0)
            subject_pictures[0].setImageDrawable(getResources().getDrawable(picture1));
        else subject_pictures[0].setVisibility(View.GONE);
        if (picture2 != 0)
            subject_pictures[1].setImageDrawable(getResources().getDrawable(picture2));
        else subject_pictures[1].setVisibility(View.GONE);
        if (picture3 != 0) {
            subject_pictures[2].setImageDrawable(getResources().getDrawable(picture3));
        } else subject_pictures[2].setVisibility(View.GONE);
    }


}
