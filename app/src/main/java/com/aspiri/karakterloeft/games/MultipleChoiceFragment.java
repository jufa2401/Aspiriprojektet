package com.aspiri.karakterloeft.games;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

    public static final String TAG = "MultipleChoiceFragment";
    @BindView(R.id.question_text)
    TextView question;
    @BindViews({R.id.option1_text, R.id.option2_text, R.id.option3_text, R.id.option4_text})
    TextView optionTexts[];
    @BindViews({R.id.option1_thumb, R.id.option2_thumb, R.id.option3_thumb, R.id.option4_thumb})
    ImageView optionThumbs[];
    @BindViews({R.id.option1_button, R.id.option2_button, R.id.option3_button, R.id.option4_button})
    LinearLayout optionButtons[];
    @BindView(R.id.status_text)
    TextView statustekst;
    AppCompatActivity mActivity;
    MultipleChoiceDataBaseHelper dataBaseHelper = new MultipleChoiceDataBaseHelper(((MainActivity) mActivity));
    LinearLayout answerbutton1, answerbutton2, answerbutton3, answerbutton4;
    String[] answertxt = {"4", "12", "16", "24"};
    String questiontxt = "Hvad er kvadratroden af 16";
    QuestionBank questionBank = new QuestionBank();
    private String trueanswer = "4";
    private int mQuestionNumber = 0;

    @OnClick(R.id.option1_button)
    void onOption1Click() {
        answerPressed(0);
    }

    @OnClick(R.id.option2_button)
    void onOption2Click() {
        answerPressed(1);
    }

    @OnClick(R.id.option3_button)
    void onOption3Click() {
        answerPressed(2);
    }

    @OnClick(R.id.option4_button)
    void onClickOption4() {
        answerPressed(3);
    }

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


        questionBank.initQuestions(getContext());


//        question.setText(questiontxt);
//
//        optionTexts[0].setText(answertxt[0]);
//        optionTexts[1].setText(answertxt[1]);
//        optionTexts[2].setText(answertxt[2]);
//        optionTexts[3].setText(answertxt[3]);
//
//        optionThumbs[0].setImageResource(R.drawable.ic_game);
//        optionThumbs[1].setImageResource(R.drawable.ic_game);
//        optionThumbs[2].setImageResource(R.drawable.ic_game);
//        optionThumbs[3].setImageResource(R.drawable.ic_game);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) mActivity).setDrawerIndicatorEnabled(false);
    }

    private void answerPressed(int optionindex) {
        int correct, wrong;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            correct = getResources().getColor(R.color.answer_correct, null);
            wrong = getResources().getColor(R.color.answer_wrong, null);
        } else {
            correct = getResources().getColor(R.color.answer_correct);      // Tager hensyn til gamle API er
            wrong = getResources().getColor(R.color.answer_wrong);
        }
        if (answertxt[optionindex].equals(trueanswer)) {

            optionButtons[optionindex].setBackgroundColor(Color.GREEN);

            ObjectAnimator colorFade = ObjectAnimator.ofObject(optionButtons[optionindex], "backgroundColor",
                    new ArgbEvaluator(), Color.argb(255,33,150,44), correct);
            colorFade.setDuration(1000);
            colorFade.start();

            optionButtons[0].setClickable(false);
            optionButtons[1].setClickable(false);
            optionButtons[2].setClickable(false);
            optionButtons[3].setClickable(false);
            statustekst.setText(R.string.correct_choice);

            ((MainActivity) mActivity).showMessage("Tryk på skærmen for at fortsætte");

            getView().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    newQuestion();
                    return false;
                }
            });

        } else {
            optionButtons[optionindex].setBackgroundColor(0xf91400);
            ObjectAnimator colorFade = ObjectAnimator.ofObject(optionButtons[optionindex], "backgroundColor",
                    new ArgbEvaluator(), Color.argb(255,193,37,23), wrong);
            colorFade.setDuration(1000);
            colorFade.start();

            optionButtons[optionindex].setClickable(false);
            statustekst.setText(R.string.wrong_choice);

//            optionButtons[optionindex].animate();
        }

    }

    private void newQuestion() {

        if (mQuestionNumber < questionBank.getLength()) {


            question.setText(questionBank.getQuestion(mQuestionNumber));

            optionTexts[0].setText(questionBank.getChoice(mQuestionNumber, 1));
            optionTexts[1].setText(questionBank.getChoice(mQuestionNumber, 2));
            optionTexts[2].setText(questionBank.getChoice(mQuestionNumber, 3));
            optionTexts[3].setText(questionBank.getChoice(mQuestionNumber, 4));
            trueanswer = questionBank.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;

            optionButtons[0].setClickable(true);
            optionButtons[1].setClickable(true);
            optionButtons[2].setClickable(true);
            optionButtons[3].setClickable(true);
        } else {
            ((MainActivity) mActivity).showMessage("That was the last question");
            MultipleChoiceFragment f = new MultipleChoiceFragment();
            ((MainActivity) mActivity).replaceFragment(f, MultipleChoiceFragment.TAG);
        }


    }
}

