package com.example.s165158.aspiri;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        System.out.println("created new view");
    }


    //Knapper
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //Knap test udføres bare ved normale brugertest
            case R.id.button:
                super.onBackPressed();
                System.out.println("back button pressed");
                break;

            default:
                break;
        }

    }



//Metode der styrer
    public void onGetNameClick(View view) {

//        Ny Intent
        Intent getNameScreenIntent = new Intent(this, extra_screen.class);

//        Det som skal appendes - calling activity  er nøglen, som når brugt i extra screen er hvad der kan hentes i Main activity
        final int result =1;
        getNameScreenIntent.putExtra("callingActivity", "MainActivity");
        startActivityForResult(getNameScreenIntent, result);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Appender data
        TextView usersNameMessage = (TextView) findViewById(R.id.users_name_message);
        String nameSentBack = data.getStringExtra("UsersName");
        usersNameMessage.append(" " + nameSentBack);
    }


}
