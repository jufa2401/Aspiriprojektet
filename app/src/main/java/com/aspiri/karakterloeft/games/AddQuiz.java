package com.aspiri.karakterloeft.games;

import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.aspiri.karakterloeft.R;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by Justin on 15/01/2018.
 */

public class AddQuiz extends Fragment {
    @BindView(R.id.editTextQuizTitle)
    EditText editTextTitle;
    @BindViews({R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4})
    EditText[] editTextsAnswers;
    @BindViews({R.id.addQuiz_answer1, R.id.addQuiz_answer2, R.id.addQuiz_answer3, R.id.addQuiz_answer4})
    TextView[] answerTextViews;
    @BindView(R.id.add_quiz_title)
    TextView addQuiz_title;

    @BindView(R.id.buttonAddQuiz)
    Button button;

    @BindViews({R.id.checkBox1, R.id.checkBox2, R.id.checkBox3, R.id.checkBox4})
    CheckBox checkBoxes[];


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


        button.setOnClickListener(new View.OnClickListener() {
            String[] questionEditText = new String[4];
            String quizTitle;
            boolean[] correctAnswer = new boolean[4];
            int correctAnswerIndex;

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.add_question)
                        .setTitle(R.string.are_you_sure)
                        .setNegativeButton("Nej", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Editable editTitle = editTextTitle.getText();
                                quizTitle = editTitle.toString();
                                Editable editQuiz[] = new Editable[editTextsAnswers.length];
                                for (int i = 0; i < editTextsAnswers.length; i++) {
                                    editQuiz[i] = editTextsAnswers[i].getText();
                                    questionEditText[i] = editQuiz[i].toString();
                                    correctAnswer[i] = checkBoxes[i].isChecked();
                                }
                                for (int i = 0; i < correctAnswer.length; i++) {
                                    if (correctAnswer[i]) {
                                        correctAnswerIndex = i;
                                    }
                                }
                                dataBaseHelper.addDataQuestion(new Question(quizTitle, questionEditText, questionEditText[correctAnswerIndex]));
                                Log.d("databaseHelper", "Question added to local database");


                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });



        return view;
    }

}
