package com.example.s165158.aspiri.test;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.s165158.aspiri.MainActivity;
import com.example.s165158.aspiri.R;
import com.example.s165158.aspiri.games.TestMultipleChoiceActivity;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import static android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
import static com.example.s165158.aspiri.R.id.game_button;

//import com.example.s165158.aspiri.test2.DrawerActivity;
//import com.example.s165158.aspiri.test2.DrawerUtil;

public class ExpandableTextViewExample extends Fragment {
    public static final String TAG = "ExpandableTextViewExample";
    private ExpandableTextView expandableTextView;
    private ImageView img;
    private TextView txt, title;
    private AppCompatActivity mActivity;
    private LinearLayout gamebutton, gamebuttonlayout;
    private int listindex;
    private String subject_title;


    //    example strings
    String shortText = "Spil et spil til dette emne!";
    String longText = "By so delight of showing neither believe he present. Deal sigh up in shew away when. Pursuit express no or prepare replied. Wholly formed old latter future but way she. Day her likewise smallest expenses judgment building man carriage gay. Considered introduced themselves mr to discretion at. Means among saw hopes for. Death mirth in oh learn he equal on. \n" +
            "\n" +
            "Barton did feebly change man she afford square add. Want eyes by neat so just must. Past draw tall up face show rent oh mr. Required is debating extended wondered as do. New get described applauded incommode shameless out extremity but. Resembled at perpetual no believing is otherwise sportsman. Is do he dispatched cultivated travelling astonished. Melancholy am considered possession on collecting everything. \n" +
            "\n" +
            "John draw real poor on call my from. May she mrs furnished discourse extremely. Ask doubt noisy shade guest did built her him. Ignorant repeated hastened it do. Consider bachelor he yourself expenses no. Her itself active giving for expect vulgar months. Discovery commanded fat mrs remaining son she principle middleton neglected. Be miss he in post sons held. No tried is defer do money scale rooms. \n" +
            "\n" +
            "Remember outweigh do he desirous no cheerful. Do of doors water ye guest. We if prosperous comparison middletons at. Park we in lose like at no. An so to preferred convinced distrusts he determine. In musical me my placing clothes comfort pleased hearing. Any residence you satisfied and rapturous certainty two. Procured outweigh as outlived so so. On in bringing graceful proposal blessing of marriage outlived. Son rent face our loud near. \n" +
            "\n" +
            "Greatly hearted has who believe. Drift allow green son walls years for blush. Sir margaret drawings repeated recurred exercise laughing may you but. Do repeated whatever to welcomed absolute no. Fat surprise although outlived and informed shy dissuade property. Musical by me through he drawing savings an. No we stand avoid decay heard mr. Common so wicket appear to sudden worthy on. Shade of offer ye whole stood hoped. \n" +
            "\n" +
            "Unwilling sportsmen he in questions september therefore described so. Attacks may set few believe moments was. Reasonably how possession shy way introduced age inquietude. Missed he engage no exeter of. Still tried means we aware order among on. Eldest father can design tastes did joy settle. Roused future he ye an marked. Arose mr rapid in so vexed words. Gay welcome led add lasting chiefly say looking. \n" +
            "\n" +
            "Raising say express had chiefly detract demands she. Quiet led own cause three him. Front no party young abode state up. Saved he do fruit woody of to. Met defective are allowance two perceived listening consulted contained. It chicken oh colonel pressed excited suppose to shortly. He improve started no we manners however effects. Prospect humoured mistress to by proposal marianne attended. Simplicity the far admiration preference everything. Up help home head spot an he room in. \n" +
            "\n" +
            "Made last it seen went no just when of by. Occasional entreaties comparison me difficulty so themselves. At brother inquiry of offices without do my service. As particular to companions at sentiments. Weather however luckily enquire so certain do. Aware did stood was day under ask. Dearest affixed enquire on explain opinion he. Reached who the mrs joy offices pleased. Towards did colonel article any parties. \n" +
            "\n" +
            "One advanced diverted domestic sex repeated bringing you old. Possible procured her trifling laughter thoughts property she met way. Companions shy had solicitude favourable own. Which could saw guest man now heard but. Lasted my coming uneasy marked so should. Gravity letters it amongst herself dearest an windows by. Wooded ladies she basket season age her uneasy saw. Discourse unwilling am no described dejection incommode no listening of. Before nature his parish boy. \n" +
            "\n" +
            "Name were we at hope. Remainder household direction zealously the unwilling bed sex. Lose and gay ham sake met that. Stood her place one ten spoke yet. Head case knew ever set why over. Marianne returned of peculiar replying in moderate. Roused get enable garret estate old county. Entreaties you devonshire law dissimilar terminated. \n" +
            "\n";



    @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof Activity)
                mActivity = (AppCompatActivity) context;

        }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInsanceState) {
        View rootView = inflater.inflate(R.layout.subject_sketch_unused, container, false);


        expandableTextView = rootView.findViewById(R.id.expand_text_view);
        expandableTextView.setText(longText);

        title = rootView.findViewById(R.id.subject_title);
        txt = rootView.findViewById(R.id.game_text);
        img = rootView.findViewById(R.id.game_thumb);

        txt.setText(shortText);
        img.setImageResource(R.drawable.ic_game);
        gamebutton = rootView.findViewById(game_button);

        Bundle args = getArguments();
        listindex = args.getInt("listindex");
        subject_title = args.getString("title");
        title.setText(subject_title);
        switch (listindex) {
            case 0:
                expandableTextView.setText("Subject 1 has been pressed Ay");
                break;
            case 1:
                expandableTextView.setText("Subject 2, 420 has been pressed Ay");
                break;
            case 2:
                expandableTextView.setText("Subject 1 has been pressed Ay din mor");
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            default:

        }

        gamebutton.setOnClickListener(new View.OnClickListener() {


            TestMultipleChoiceActivity quiz = new TestMultipleChoiceActivity();

            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .setTransition(TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.fragmentindhold, quiz)
                        .addToBackStack("back to subject_sketch_unused from quiz")

                        .commit();


            }
        });

           return rootView;

    }
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) mActivity).setActionBarTitle(subject_title);

    }


}
