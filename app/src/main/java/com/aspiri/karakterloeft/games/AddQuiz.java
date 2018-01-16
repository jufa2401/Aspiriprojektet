package com.aspiri.karakterloeft.games;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;
import com.aspiri.karakterloeft.R;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import katex.hourglass.in.mathlib.MathView;

/**
 * Created by Justin on 15/01/2018.
 */

public class AddQuiz extends Fragment {
    @BindView(R.id.editTextQuizTitle)
    FormEditText editTextTitle;
    @BindViews({R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4})
    FormEditText[] editTextsAnswers;
    @BindViews({R.id.addQuiz_answer1, R.id.addQuiz_answer2, R.id.addQuiz_answer3, R.id.addQuiz_answer4})
    TextView[] answerTextViews;
    @BindView(R.id.add_quiz_title)
    TextView addQuiz_title;

    @BindView(R.id.buttonAddQuiz)
    Button finishUpdate;
    @BindView(R.id.buttonPreview)
    Button buttonPreview;
    @BindViews({R.id.checkBox1, R.id.checkBox2, R.id.checkBox3, R.id.checkBox4})
    CheckBox checkBoxes[];
    String[] questionEditString = new String[4];
    String quizTitleEditString;
    boolean[] correctAnswer = new boolean[4];

    @OnClick(R.id.buttonPreview)
    void onPreviewClick() {
        Dialog previewDialog;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            previewDialog = new Dialog(getContext());
        } else {
            previewDialog = new Dialog(getActivity());              //Gamle APIER
        }
        previewDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            View preview = getLayoutInflater().inflate(R.layout.preview_mathview, (ViewGroup) getView().getParent(), false);
            previewDialog.setContentView(preview);
            MathView preview1 = preview.findViewById(R.id.previewMath1);        // Disse views kan ikke bindes som før, da de ligger i et "nyt layout"
            MathView preview2 = preview.findViewById(R.id.previewMath2);
            MathView preview3 = preview.findViewById(R.id.previewMath3);
            MathView preview4 = preview.findViewById(R.id.previewMath4);

            String[] displayText = new String[editTextsAnswers.length];
            for (int i = 0; i < editTextsAnswers.length; i++) {
                displayText[i] = editTextsAnswers[i].getText().toString();
            }

            preview1.setDisplayText(displayText[0]);
            preview2.setDisplayText(displayText[1]);
            preview3.setDisplayText(displayText[2]);
            preview4.setDisplayText(displayText[3]);

        }

        previewDialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_quiz_fragment, container, false);
        ButterKnife.bind(this, view);


        Context context;            // Low level API's do not have the getContext() method.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context = getContext();
        } else {
            context = getActivity();
        }
        MultipleChoiceDataBaseHelper dataBaseHelper = new MultipleChoiceDataBaseHelper(context);


        finishUpdate.setOnClickListener(new View.OnClickListener() {


            int correctAnswerIndex;

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.add_question)
                        .setTitle(R.string.are_you_sure)
                        .setNegativeButton("Nej", null)
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                quizTitleEditString = editTextTitle.getText().toString();
                                Editable editQuiz[] = new Editable[editTextsAnswers.length];
                                for (int i = 0; i < editTextsAnswers.length; i++) {
                                    if (!editTextsAnswers[i].testValidity() || !editTextTitle.testValidity()) {
                                        Toast.makeText(context, "Fejl, test dine felter for fejl", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    editQuiz[i] = editTextsAnswers[i].getText();
                                    questionEditString[i] = editQuiz[i].toString();
                                    correctAnswer[i] = checkBoxes[i].isChecked();
                                }
                                for (int i = 0; i < correctAnswer.length; i++) {
                                    if (correctAnswer[i]) {
                                        correctAnswerIndex = i;
                                    }
                                }
                                dataBaseHelper.addDataQuestion(new Question(quizTitleEditString, questionEditString, questionEditString[correctAnswerIndex]));
                                Log.d("databaseHelper", "Question added to local database");

                                fragmentTransaction(new MultipleChoiceFragment());
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


        if (savedInstanceState != null) {
            questionEditString = savedInstanceState.getStringArray("questionEditString");
            quizTitleEditString = savedInstanceState.getString("quizTitleEditString");
            correctAnswer = savedInstanceState.getBooleanArray("checkedBoxes");

            for (int i = 0; i < checkBoxes.length; i++) {
                checkBoxes[i].setChecked(correctAnswer[i]);
            }
        }


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        for (int i = 0; i < editTextsAnswers.length; i++) {
            questionEditString[i] = editTextsAnswers[i].toString();
        }
        for (int i = 0; i < checkBoxes.length; i++) {
            correctAnswer[i] = checkBoxes[i].isChecked();
        }

        quizTitleEditString = editTextTitle.toString();
        outState.putBooleanArray("checkedBoxes", correctAnswer);
        outState.putStringArray("questionEditString", questionEditString);
        outState.putString("quizTitleEditString", quizTitleEditString);
    }

    private void fragmentTransaction(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fragmentindhold, fragment);     // Der addes ikke til backstck
    }


}


