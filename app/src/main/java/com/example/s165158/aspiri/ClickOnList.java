package com.example.s165158.aspiri;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//Denne klasse håndterer den activity man ryger ind i  når man klikker på et list-objekt
public class ClickOnList extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_on_list);

        String savedExtra = getIntent().getStringExtra("content");
        TextView myText = (TextView) findViewById(R.id.contentID);
        myText.setText(savedExtra);

        Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            //Knap test udføres bare ved normale brugertest
            case R.id.button:
                Intent intent = new Intent(ClickOnList.this, Navigation.class);
                startActivity(intent);
                break;

            default:
                break;
        }

    }
}
