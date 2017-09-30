package com.example.s165158.aspiri.other.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.s165158.aspiri.BaseActivity;
import com.example.s165158.aspiri.Main2Activity;
import com.example.s165158.aspiri.R;

//Denne klasse håndterer den activity man ryger ind i  når man klikker på et list-objekt
public class ClickOnList extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_on_list);

        String savedExtra = getIntent().getStringExtra("content");
        TextView myText = (TextView) findViewById(R.id.contentID);
        myText.setText(savedExtra);

        Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(this);

        System.out.println("created new view");
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_click_on_list;
    }


    //Knapper
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            //Knap test udføres bare ved normale brugertest
            case R.id.button:
                Intent intent = new Intent(ClickOnList.this, Main2Activity.class);
                startActivity(intent);
                System.out.println("back button pressed");
                break;

            default:
                break;
        }

    }
}
