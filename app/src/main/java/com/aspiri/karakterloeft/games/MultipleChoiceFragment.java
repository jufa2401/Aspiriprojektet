package com.aspiri.karakterloeft.games;

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

import com.aspiri.karakterloeft.MainActivity;
import com.aspiri.karakterloeft.R;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MultipleChoiceFragment extends Fragment {

    @BindView(R.id.question_text)
    TextView question;
    @BindViews({R.id.option1_text, R.id.option2_text, R.id.option3_text, R.id.option4_text})
    TextView optionTexts[];
    @BindViews({R.id.option1_thumb, R.id.option2_thumb, R.id.option3_thumb, R.id.option4_thumb})
    ImageView optionThumbs[];
    @BindViews({R.id.option1_button, R.id.option2_button, R.id.option3_button, R.id.option4_button})
    LinearLayout optionButtons[];
    private String trueanswer;

    @OnClick(R.id.option1_button)
    void onOption1Click() {

    }

    @OnClick(R.id.option2_button)
    void onOption2Click() {

    }

    @OnClick(R.id.option3_button)
    void onOption3Click() {

    }

    @OnClick(R.id.option4_button)
    void onClickOption4() {

    }

    AppCompatActivity mActivity;

    LinearLayout answerbutton1,answerbutton2,answerbutton3,answerbutton4;

    String[] answertxt = {"4", "4", "4", "4"};
    String questiontxt = "Hvad er kvadratroden af 16";
    boolean solution;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity)
            mActivity = (AppCompatActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInsanceState){
        View view = inflater.inflate(R.layout.multiple_choice_quiz, container, false);
        ButterKnife.bind(this, view);

        question.setText(questiontxt);

        optionTexts[0].setText(answertxt[0]);
        optionTexts[1].setText(answertxt[1]);
        optionTexts[2].setText(answertxt[2]);
        optionTexts[3].setText(answertxt[3]);

        optionThumbs[0].setImageResource(R.drawable.ic_game);
        optionThumbs[1].setImageResource(R.drawable.ic_game);
        optionThumbs[2].setImageResource(R.drawable.ic_game);
        optionThumbs[3].setImageResource(R.drawable.ic_game);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) mActivity).setDrawerIndicatorEnabled(false);
    }

    private void answerPressed(boolean value, int optionindex) {
        if (answertxt[optionindex] == trueanswer)

            if (value == false) {

            }
    }
}

