package com.aspiri.karakterloeft.games.quiz;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @BindView(R.id.multipleChoice)
    ConstraintLayout screen;

    AppCompatActivity mActivity;
    QuestionBank questionBank = new QuestionBank();
    ObjectAnimator colorFade;
    private String trueanswer;
    private int mQuestionNumber = 0;
    private String[] questionOption;

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
        questionOption = getQuestion(); //Erklæres ude for kaldet
        opdaterUI(questionOption);

        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.three_dot_menu, menu);
        MenuItem addItem = menu.findItem(R.id.addItem);
        addItem.setVisible(true);
        addItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ((MainActivity) mActivity).replaceFragment(new AddQuestion(), "add quiz fragment");
                return false;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) mActivity).setDrawerIndicatorEnabled(false);
    }

    @SuppressLint("ClickableViewAccessibility")
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

            ((MainActivity) mActivity).showToast("Tryk XX på skærmen for at fortsætte");


            Log.d("onTouchListener", "touchlistener on " + getActivity().getCurrentFocus());
            screen.setOnTouchListener((View v, MotionEvent event) -> {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        statustekst.setText(R.string.presstostart);
                        mQuestionNumber++;
                        opdaterUI(questionOption);
                        screen.setOnTouchListener(null);
                        break;
                }
                return true;

            });

        } else {

            colorFade = ObjectAnimator.ofObject(optionButtons[optionindex], "backgroundColor",
                    new ArgbEvaluator(), Color.argb(255,193,37,23), wrong);
            colorFade.setDuration(1000);
            colorFade.start();

            optionButtons[optionindex].setClickable(false);
            statustekst.setText(R.string.wrong_choice);

        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void mathViewPressed(int optionindex) {
        int correct, wrong;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            correct = getResources().getColor(R.color.answer_correct, null);
            wrong = getResources().getColor(R.color.answer_wrong, null);
        } else {
            correct = getResources().getColor(R.color.answer_correct);      // Tager hensyn til gamle API er
            wrong = getResources().getColor(R.color.answer_wrong);
        }
        if (questionOption[optionindex].equals(trueanswer)) {

            mathViews[optionindex].setBackgroundColor(Color.GREEN);

            ObjectAnimator colorFade = ObjectAnimator.ofObject(mathViews[optionindex], "backgroundColor",
                    new ArgbEvaluator(), Color.argb(255, 33, 150, 44), correct);
            colorFade.setDuration(1000);
            colorFade.start();

            checkBoxes[0].setClickable(false);
            checkBoxes[1].setClickable(false);
            checkBoxes[2].setClickable(false);
            checkBoxes[3].setClickable(false);
            statustekst.setText(R.string.correct_choice);

            ((MainActivity) mActivity).showToast("Tryk på skærmen for at fortsætte");

            screen.setOnTouchListener((View v, MotionEvent event) -> {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_UP:
                                statustekst.setText(R.string.presstostart);
                                mQuestionNumber++;
                                opdaterUI(questionOption);
                                screen.setOnTouchListener(null);
                        }
                        return true;
                    }
            );


        } else {
            mathViews[optionindex].setBackgroundColor(0xf91400);
            ObjectAnimator colorFade = ObjectAnimator.ofObject(mathViews[optionindex], "backgroundColor",
                    new ArgbEvaluator(), Color.argb(255, 193, 37, 23), wrong);
            colorFade.setDuration(500);
            colorFade.start();

            mathViews[optionindex].setClickable(false);
            statustekst.setText(R.string.wrong_choice);

//            optionButtons[optionindex].animate();
        }
    }

    private void opdaterUI(String[] option) {
        option = new String[]{questionBank.getChoice(mQuestionNumber, 1), questionBank.getChoice(mQuestionNumber, 2), questionBank.getChoice(mQuestionNumber, 3), questionBank.getChoice(mQuestionNumber, 4)};
        if (mQuestionNumber < questionBank.getLength()) {
            question.setText(questionBank.getQuestion(mQuestionNumber));
            trueanswer = questionBank.getCorrectAnswer(mQuestionNumber);

            if (shouldUseMathViews()) {
                for (int i = 0; i < 4; i++) {
                    optionButtons[i].setClickable(false);
                    optionButtons[i].setVisibility(View.GONE);

//                    if(colorFade!=null) {colorFade.end();}

                    mathViews[i].setBackgroundColor(Color.WHITE);
                    mathViews[i].setVisibility(View.VISIBLE);
                    mathViews[i].setClickable(true);
                    mathViews[i].setDisplayText(option[i]);
                    checkBoxes[i].setVisibility(View.VISIBLE);
                    checkBoxes[i].setChecked(false);
                    checkBoxes[i].setClickable(true);


                }
            }
            if (!shouldUseMathViews()) {
                for (int i = 0; i < 4; i++) {
                    Log.d("opdaterUI() Method " + i, "optionbuttons at " + i + " Visibility visible");
                    mathViews[i].setClickable(false);
                    mathViews[i].setVisibility(View.GONE);
                    checkBoxes[i].setClickable(false);
                    checkBoxes[i].setVisibility(View.GONE);

//                    if(colorFade!=null) {colorFade.end();}

                    optionButtons[i].setBackgroundColor(Color.WHITE);
                    optionButtons[i].setVisibility(View.VISIBLE);
                    optionButtons[i].setClickable(true);
                    optionButtons[i].setBackground(getResources().getDrawable(R.drawable.shadow));
                    optionTexts[i].setText(option[i]);
                }
            }

            Log.d("opdaterUI() Method" + mQuestionNumber, "mQuestionNumber value: " + mQuestionNumber);

        } else {
            ((MainActivity) mActivity).showToast("That was the last question");
            MultipleChoiceFragment f = new MultipleChoiceFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction
                    .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_out_right, R.animator.slide_in_right)
                    .replace(R.id.fragmentindhold, f, "end of questions").commit();
        }
    }


    /**
     * Returns true if UI should display a MathView.* @return
     */
    private boolean shouldUseMathViews() {
        if (questionOption[0].contains("$$")) {
            return true;
        }
        if (questionOption[1].contains("$$")) {
            return true;
        }
        if (questionOption[2].contains("$$")) {
            return true;
        }
        return questionOption[3].contains("$$");
    }

    private String[] getQuestion() {
        questionOption = new String[]{questionBank.getChoice(mQuestionNumber, 1),
                questionBank.getChoice(mQuestionNumber, 2),
                questionBank.getChoice(mQuestionNumber, 3),
                questionBank.getChoice(mQuestionNumber, 4)};
        return questionOption;
    }

}



