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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspiri.karakterloeft.MainActivity;
import com.aspiri.karakterloeft.R;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import katex.hourglass.in.mathlib.MathView;

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
    @BindViews({R.id.quiz_math_view_1, R.id.quiz_math_view_2, R.id.quiz_math_view_3, R.id.quiz_math_view_4})
    MathView mathViews[];
    @BindViews({R.id.cb1, R.id.cb2, R.id.cb3, R.id.cb4})
    CheckBox checkBoxes[];

    @BindView(R.id.status_text)
    TextView statustekst;
    AppCompatActivity mActivity;
    MultipleChoiceDataBaseHelper dataBaseHelper = new MultipleChoiceDataBaseHelper(((MainActivity) mActivity));

    QuestionBank questionBank = new QuestionBank();
    private String trueanswer;
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

    @OnClick(R.id.cb1)
    void onmathview1Click() {
        mathViewPressed(0);
    }

    @OnClick(R.id.cb2)
    void onmathview2Click() {
        mathViewPressed(1);
    }

    @OnClick(R.id.cb3)
    void onmathview3Click() {
        mathViewPressed(2);
    }

    @OnClick(R.id.cb4)
    void onmathview4Click() {
        mathViewPressed(3);
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


        questionBank.initQuestions(mActivity.getApplicationContext());
        newQuestion();

//        question.setText(questiontxt);
//

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
        if (optionTexts[optionindex].getText().equals(trueanswer)) {

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
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    newQuestion();
                    for (int i = 0; i < 4; i++) {
                        optionButtons[i].setBackground(getResources().getDrawable(R.drawable.shadow));
                    }
                    statustekst.setText(R.string.presstostart);
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

    private void mathViewPressed(int optionindex) {
        int correct, wrong;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            correct = getResources().getColor(R.color.answer_correct, null);
            wrong = getResources().getColor(R.color.answer_wrong, null);
        } else {
            correct = getResources().getColor(R.color.answer_correct);      // Tager hensyn til gamle API er
            wrong = getResources().getColor(R.color.answer_wrong);
        }
        if (optionTexts[optionindex].getText().equals(trueanswer)) {

            mathViews[optionindex].setBackgroundColor(Color.GREEN);

            ObjectAnimator colorFade = ObjectAnimator.ofObject(mathViews[optionindex], "backgroundColor",
                    new ArgbEvaluator(), Color.argb(255, 33, 150, 44), correct);
            colorFade.setDuration(1000);
            colorFade.start();

            mathViews[0].setClickable(false);
            mathViews[1].setClickable(false);
            mathViews[2].setClickable(false);
            mathViews[3].setClickable(false);
            statustekst.setText(R.string.correct_choice);

            ((MainActivity) mActivity).showMessage("Tryk på skærmen for at fortsætte");

            getView().setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    newQuestion();
                    for (int i = 0; i < 4; i++) {
                        mathViews[i].setBackgroundColor(getResources().getColor(R.color.md_white_1000));
                    }
                    statustekst.setText(R.string.presstostart);
                    return false;
                }
            });

        } else {
            mathViews[optionindex].setBackgroundColor(0xf91400);
            ObjectAnimator colorFade = ObjectAnimator.ofObject(mathViews[optionindex], "backgroundColor",
                    new ArgbEvaluator(), Color.argb(255, 193, 37, 23), wrong);
            colorFade.setDuration(1000);
            colorFade.start();

            mathViews[optionindex].setClickable(false);
            statustekst.setText(R.string.wrong_choice);

//            optionButtons[optionindex].animate();
        }
    }
    private void newQuestion() {
        String[] option = new String[]{questionBank.getChoice(mQuestionNumber, 1), questionBank.getChoice(mQuestionNumber, 2), questionBank.getChoice(mQuestionNumber, 3), questionBank.getChoice(mQuestionNumber, 4)};
        if (mQuestionNumber < questionBank.getLength()) {
            question.setText(questionBank.getQuestion(mQuestionNumber));
            trueanswer = questionBank.getCorrectAnswer(mQuestionNumber);

            if (option[0].contains("$$")) {
                for (int i = 0; i < 4; i++) {
                    optionButtons[i].setVisibility(View.GONE);
                    mathViews[i].setDisplayText(option[i]);
                    mathViews[i].setVisibility(View.VISIBLE);
                    checkBoxes[i].setVisibility(View.VISIBLE);

                }
            }
            if (!option[0].contains("$$")) {
                for (int i = 0; i < 4; i++) {
                    optionTexts[i].setText(option[i]);
                    optionButtons[i].setClickable(true);
                    if (optionButtons[i].getVisibility() == View.GONE) {
                        optionButtons[i].setVisibility(View.VISIBLE);
                    }
                    if (mathViews[i].getVisibility() == View.VISIBLE) {
                        mathViews[i].setVisibility(View.GONE);
                        checkBoxes[i].setVisibility(View.GONE);

                    }
                }
                mQuestionNumber++;


            }


        }
        if (mQuestionNumber >= questionBank.getLength()) {
            ((MainActivity) mActivity).showMessage("That was the last question");
            MultipleChoiceFragment f = new MultipleChoiceFragment();
            ((MainActivity) mActivity).replaceFragment(f, MultipleChoiceFragment.TAG);
        }


    }
}

