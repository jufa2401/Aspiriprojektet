package com.example.s165158.aspiri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
//Denne klasse håndterer den activity man ryger ind i når man klikker på et list-objekt
public class ClickOnList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_on_list);


        String savedExtra = getIntent().getStringExtra("animal");
        TextView myText = (TextView) findViewById(R.id.content_id);
        myText.setText(savedExtra);
    }
}
