package com.example.s165158.aspiri.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.s165158.aspiri.R;

public class TestMultipleChoiceActivity extends AppCompatActivity {

    TextView question, answer1,answer2,answer3,answer4;
    ImageView img1,img2,img3,img4;


    LinearLayout answerbutton1,answerbutton2,answerbutton3,answerbutton4;

    String answertxt = "4";
    String questiontxt = "Hvad er kvadratroden af 16";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_choice_quiz);


        question = (TextView) findViewById(R.id.question_text);
        question.setText(questiontxt);

        answer1 = (TextView)findViewById(R.id.option1_text);
        answer2 =(TextView)findViewById(R.id.option2_text);
        answer3 =(TextView)findViewById(R.id.option3_text);
        answer4 =(TextView)findViewById(R.id.option4_text);

        img1 = (ImageView)findViewById(R.id.option1_thumb);
        img2 = (ImageView)findViewById(R.id.option2_thumb);
        img3 = (ImageView)findViewById(R.id.option3_thumb);
        img4 = (ImageView)findViewById(R.id.option4_thumb);

        answer1.setText(answertxt);
        answer2.setText(answertxt);
        answer3.setText(answertxt);
        answer4.setText(answertxt);


        img1.setImageResource(R.drawable.ic_game);
        img2.setImageResource(R.drawable.ic_game);
        img3.setImageResource(R.drawable.ic_game);
        img4.setImageResource(R.drawable.ic_game);



        answerbutton1 = (LinearLayout) findViewById(R.id.option1_button);
        answerbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        answerbutton2 = (LinearLayout) findViewById(R.id.option2_button);
        answerbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        answerbutton3 = (LinearLayout) findViewById(R.id.option3_button);
        answerbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        answerbutton4 = (LinearLayout) findViewById(R.id.option4_button);
        answerbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        //        img1.setImageDrawable(R);


    }

}

