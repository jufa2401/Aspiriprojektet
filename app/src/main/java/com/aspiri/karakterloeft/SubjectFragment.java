package com.aspiri.karakterloeft;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class SubjectFragment extends Fragment {
    public static final String TAG = "SubjectFragment";
    private String[] title, intro, text_following_picture_1, text_following_picture_2, text_above_picture_3, mathView1, mathView2, mathView3, mathView4,
            text_above_mathview_1, text_above_mathview_2, text_above_mathview_3, text_above_mathview_4;  // These strings are not set for Arealer nor the default layout, however they could be useful later in other subjects

    private int listindex, oldindex;
    private AppCompatActivity mActivity;


    private int[] picture_1, picture_2, picture_3;

    //    Leger med at bruge butterknife, det virker meget smartere at skrive det heroppe i stedet for at det skal fylde i en OnCreate metode med findViewbyId og OnClick
    // Fordi jeg kan sætte det hele som et array her sparer jeg samtlige linjer kode

    //    Alle MathViews
    @BindViews({R.id.subject_fragment_math_view_1,          //1
            R.id.subject_fragment_math_view_2,              //2
            R.id.subject_fragment_math_view_3,              //3
            R.id.subject_fragment_math_view_4               //4
    })
    MathView mathViews[];

    //    Alle TextViews forinden eksempler
    @BindViews({
            R.id.subject_fragment_title,
//            R.id.subject_fragment_subject,
            R.id.subject_fragment_intro,
            R.id.subject_fragment_text_following_picture_1,
            R.id.subject_fragment_text_following_picture_2,
            R.id.subject_text_above_picture_3,
            R.id.subject_fragment_text_above_mathview1,
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
        View view = inflater.inflate(R.layout.subject_fragment, container, false);
        ButterKnife.bind(this, view);   // Laver findViewById for alle mine erklæringer ovenfor med annotationen @BindView(s)
        mActivity = (AppCompatActivity) getActivity();
        Bundle args = getArguments();

        listindex = args.getInt("listindex");
//        Når vi skal udvide vores app til at undestøtte strings fra flere forskellige overemner, er vi nødt til at lave en switch case for hvert overemne.
//        oldindex = args.getInt("oldindex");
        oldindex = ((MainActivity) mActivity).getOldindex();
// Her skal der være en switch case for hvert eneste "super" overemne.
        switch (oldindex) {
//Areal og rumfang
            case 0:
//                Henter  af Strings
                title = getResources().getStringArray(R.array.arealer_omkreds_rumfang_title);
//                subject = getResources().getStringArray(R.array.arealer_omkreds_rumfang_list);
                intro = getResources().getStringArray(R.array.arealer_introducerende_tekst);
                text_following_picture_1 = getResources().getStringArray(R.array.arealer_text_following_picture_1);
                text_following_picture_2 = getResources().getStringArray(R.array.arealer_text_following_picture_2);
                text_above_picture_3 = getResources().getStringArray(R.array.text_above_picture_3);

//               henter billeder
                picture_1 = new int[]{R.drawable.cirkeldef, R.drawable.trekantdef, R.drawable.paradef, R.drawable.trapezdef, R.drawable.kegledef, R.drawable.kugledef, R.drawable.cylledef};
                picture_2 = new int[]{R.drawable.cirkel1, R.drawable.trekant1, R.drawable.paralellogram1, R.drawable.trapez1, R.drawable.kegleformler, R.drawable.kugleformler, R.drawable.cylle1};
                picture_3 = new int[]{R.drawable.cirkel2, 0, 0, 0, 0, 0, 0};

//               Sætter alt, der er ingen MathViews på noget indeks for nogen af emnerne i arealer, derfor null.
                setTexts(title[listindex], intro[listindex], text_following_picture_1[listindex], text_following_picture_2[listindex], text_above_picture_3[listindex]);
                setPictures(picture_1[listindex], picture_2[listindex], picture_3[listindex]);
                Log.d("entered_subject", "You're in: " + SubjectFragment.TAG + "\nYou're on " + title[listindex] + " @listindex: " + listindex + " from @oldindex: " + oldindex);
                break;
// Rentesregning
            case 1:
                // Title and intro
                title = getResources().getStringArray(R.array.RentesRegning_title);
                intro = getResources().getStringArray(R.array.RentesRegning_introTekst);


                // Text after pictures
                text_following_picture_1 = getResources().getStringArray(R.array.RentesRegning_text_following_picture_1);
                text_following_picture_2 = getResources().getStringArray(R.array.RentesRegning_text_following_picture_2);
                text_above_picture_3 = getResources().getStringArray(R.array.RentesRegning_above_picture_3);

                // Actual Pictures
                picture_1 = new int[]{0,0,0,0,0,0};
                picture_2 = new int[]{0,0,0,0,0,0};
                picture_3 = new int[]{0,0,0,0,0,0};

                //Mathviews
                mathView1 = getResources().getStringArray(R.array.RentesRegning_mathview1);
                mathView2 = getResources().getStringArray(R.array.RentesRegning_mathview1);
                mathView3 = getResources().getStringArray(R.array.RentesRegning_mathview1);
                mathView4 = getResources().getStringArray(R.array.RentesRegning_mathview1);

                //Text above mathview
                text_above_mathview_1 = getResources().getStringArray(R.array.RentesRegning_text_above_mathview1);
                text_above_mathview_2 = getResources().getStringArray(R.array.RentesRegning_text_above_mathview2);
                text_above_mathview_3 = getResources().getStringArray(R.array.RentesRegning_text_above_mathview3);
                text_above_mathview_4 = getResources().getStringArray(R.array.RentesRegning_text_above_mathview4);


                // Setting Texts
                setTexts(title[listindex], intro[listindex], text_following_picture_1[listindex], text_following_picture_2[listindex], text_above_picture_3[listindex]);
                setTextAboveMathviews(text_above_mathview_1[listindex], text_above_mathview_2[listindex], text_above_mathview_3[listindex], text_above_mathview_4[listindex]);

                // Setting Pictures
                setPictures(picture_1[listindex], picture_2[listindex], picture_3[listindex]);

                //Setting Mathviews
                setMathViews(mathView1[listindex],mathView2[listindex],mathView3[listindex],mathView4[listindex]);

                //Setting Text Above Mathviews

                // Log calls
                Log.d("entered_subject", "You're in: " + SubjectFragment.TAG + "\nYou're on " + title[listindex] + " @listindex: " + listindex + " from @oldindex: " + oldindex);
                break;

//Kvadratsætningerne
            case 2:
                // Title and intro
                title = getResources().getStringArray(R.array.kvadratætningerne_title);
                intro = getResources().getStringArray(R.array.kvadratsætningerne_introTekst);

                // Text after pictures
                text_following_picture_1 = getResources().getStringArray(R.array.kvadratsætningerne_text_following_picture_1);
                text_following_picture_2 = getResources().getStringArray(R.array.kvadratsætningerne_text_following_picture_2);
                text_above_picture_3 = getResources().getStringArray(R.array.kvadratsætningerne_above_picture_3);

                // Actual Pictures
                picture_1 = new int[]{0,0,0};
                picture_2 = new int[]{0,0,0};
                picture_3 = new int[]{0,0,0};

                // Assigning Pictures and texts
                setTexts(title[listindex], intro[listindex], text_following_picture_1[listindex], text_following_picture_2[listindex], text_above_picture_3[listindex]);
                setPictures(picture_1[listindex], picture_2[listindex], picture_3[listindex]);

                // Log calls
                Log.d("entered_subject", "You're in: " + SubjectFragment.TAG + "\nYou're on " + title[listindex] + " @listindex: " + listindex + " from @oldindex: " + oldindex);
                break;
//Potensregneregler
            case 3:
                break;

//Polynomier
            case 4:
                break;
//Line;r, eksponentiel og potenssammenh;nge

            case 5:
                break;

//Statistkik
            case 6:
                break;

//Differentialregning
            case 7:
                break;

//Integralregning
            case 8:
                break;

//Logaritmer
            case 9:
                break;

//Geometri
            case 10:
                break;

// Plangeometri med Vektorer
            case 11:
                title = getResources().getStringArray(R.array.RumGeometri_list_title);
                intro = getResources().getStringArray(R.array.RumGeometri_list);

                // Text after pictures
                text_following_picture_1 = getResources().getStringArray(R.array.RumGeometri_text_following_picture_1);
                text_following_picture_2 = getResources().getStringArray(R.array.RumGeometri_text_following_picture_2);
                text_above_picture_3 = getResources().getStringArray(R.array.RumGeometri_text_above_picture_3);

                // Actual Pictures
                picture_1 = new int[]{0,0,0,0};
                picture_2 = new int[]{0,0,0,0};
                picture_3 = new int[]{0,0,0,0};
//Mathviews
                mathView1 = getResources().getStringArray(R.array.RumGeometri_mathView1);
                mathView2 = getResources().getStringArray(R.array.RumGeometri_mathView2);
                mathView3 = getResources().getStringArray(R.array.RumGeometri_mathView1);
                mathView4 = getResources().getStringArray(R.array.RumGeometri_mathView2);

                //Text above mathview
                text_above_mathview_1 = getResources().getStringArray(R.array.RumGeometri_text_above_mathview1);
                text_above_mathview_2 = getResources().getStringArray(R.array.RumGeometri_text_above_mathview2);
                text_above_mathview_3 = getResources().getStringArray(R.array.RumGeometri_text_above_mathview1);
                text_above_mathview_4 = getResources().getStringArray(R.array.RumGeometri_text_above_mathview1);
                // Assigning Pictures and texts
                setTexts(title[listindex], intro[listindex], text_following_picture_1[listindex], text_following_picture_2[listindex], text_above_picture_3[listindex]);
                setPictures(picture_1[listindex], picture_2[listindex], picture_3[listindex]);

                // Log calls
                Log.d("entered_subject", "You're in: " + SubjectFragment.TAG + "\nYou're on " + title[listindex] + " @listindex: " + listindex + " from @oldindex: " + oldindex);

                break;

//Rumgeometri med vektorer
            case 12:
                break;
        }
        return view;
    }


    //  Enkapslerende metoder
    private void setTexts(String title, String intro, String text_following_picture_1, String text_following_picture_2, String text_above_picture_3) {
        if (title != null) {
            if (!title.equals("0"))     //Man kan ikke lave .equals på et null objekt, så der tjekkes først om objektet er null
                textsViews[0].setText(title);
            textsViews[0].setVisibility(View.VISIBLE);
        }
        if (intro != null) {
            if (!intro.equals("0"))
                textsViews[1].setText(intro);
            textsViews[1].setVisibility(View.VISIBLE);
        }
        if (text_following_picture_1 != null) {
            if (!text_following_picture_1.equals("0"))
                textsViews[2].setText(text_following_picture_1);
            textsViews[2].setVisibility(View.VISIBLE);
        }
        if (text_following_picture_2 != null) {
            if (!text_following_picture_2.equals("0"))
                textsViews[3].setText(text_following_picture_2);
            textsViews[3].setVisibility(View.VISIBLE);

        }
        if (text_above_picture_3 != null) {
            if (!text_above_picture_3.equals("0"))
                textsViews[4].setText(text_above_picture_3);
            textsViews[4].setVisibility(View.VISIBLE);

        }
    }

    //Setting the text above Mathviews
    private void setTextAboveMathviews(String text_above_mathview1, String text_above_mathview_2,  String text_above_mathview_3, String text_above_mathview_4) {
        if (text_above_mathview_1 != null) {
            if (!text_above_mathview_1.equals("0"))
                textsViews[5].setText(text_above_mathview1);
            textsViews[5].setVisibility(View.VISIBLE);
        }
        if (text_above_mathview_2 != null) {
            if (!text_above_mathview_2.equals("0"))
                textsViews[6].setText(text_above_mathview_2);
            textsViews[6].setVisibility(View.VISIBLE);
        }
        if (text_above_mathview_3 != null) {
            if (!text_above_mathview_3.equals("0"))
                textsViews[7].setText(text_above_mathview_3);
            textsViews[7].setVisibility(View.VISIBLE);
        }
        if (text_above_mathview_4 != null) {
            if (!text_above_mathview_4.equals("0"))
                textsViews[8].setText(text_above_mathview_4);
            textsViews[8].setVisibility(View.VISIBLE);
        }
    }

    //    not used for case 0
    private void setMathViews(String mathView1, String mathView2, String mathView3, String mathView4) {
        if (mathView1 != null) {
            if (!mathView1.equals("0"))
                mathViews[0].setDisplayText(mathView1);
            mathViews[0].setVisibility(View.VISIBLE);
        }
        if (mathView2 != null) {
            if (!mathView1.equals("0"))
                mathViews[1].setDisplayText(mathView2);
            mathViews[1].setVisibility(View.VISIBLE);
        }
        if (mathView3 != null) {
            if (!mathView1.equals("0"))
                mathViews[2].setDisplayText(mathView3);
            mathViews[2].setVisibility(View.VISIBLE);
        }
        if (mathView4 != null) {
            if (!mathView4.equals("0"))
                mathViews[3].setDisplayText(mathView4);
            mathViews[3].setVisibility(View.VISIBLE);
        }
    }

    private void setPictures(int picture1, int picture2, int picture3) {
        if (picture1 != 0) {
            subject_pictures[0].setImageDrawable(getResources().getDrawable(picture1));
            subject_pictures[0].setVisibility(View.VISIBLE);
        }
        if (picture2 != 0) {
            subject_pictures[1].setImageDrawable(getResources().getDrawable(picture2));
            subject_pictures[1].setVisibility(View.VISIBLE);
        }
        if (picture3 != 0) {
            subject_pictures[2].setImageDrawable(getResources().getDrawable(picture3));
            subject_pictures[2].setVisibility(View.VISIBLE);
        }
    }

}
