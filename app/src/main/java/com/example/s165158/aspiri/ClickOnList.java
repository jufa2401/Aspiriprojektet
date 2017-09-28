package com.example.s165158.aspiri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
//Denne klasse håndterer den activity man ryger ind i  når man klikker på et list-objekt
public class ClickOnList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_on_list);


//        Henter "Den extra String" fra MainActivity
        String savedExtra = getIntent().getStringExtra("content");
//        Vi erklærer textview i java som peger på vores textview contentID i clickonlist.xml
        TextView myText = (TextView) findViewById(R.id.contentID);
//        Vi sætter den hentede String til at ryge ind i TextViewet
        myText.setText(savedExtra);
    }
}
