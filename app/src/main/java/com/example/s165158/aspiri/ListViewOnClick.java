package com.example.s165158.aspiri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Denne klasse håndterer den activity man ryger ind i  når man klikker på et list-objekt
public class ListViewOnClick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_subject2);

//        String savedExtra = getIntent().getStringExtra("content");
//        TextView myText = (TextView) findViewById(R.id.contentID);

        Log.d("AspiriApp","created new view");
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Appender data
        TextView usersNameMessage = (TextView) findViewById(R.id.subject_title);
        String nameSentBack = data.getStringExtra("UsersName");
        usersNameMessage.append(" " + nameSentBack);
    }


}
