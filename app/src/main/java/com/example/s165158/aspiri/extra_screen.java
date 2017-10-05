package com.example.s165158.aspiri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by jonas on 05/10/2017.
 */

public class extra_screen extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.extra_screen);

//                Lav Intent som appender texten i extra screen
        Intent activityThatCalled = getIntent();

//        Hent det som skal appendes fra ClickOnList.java
        String previousActivity = activityThatCalled.getExtras().getString("callingActivity");

//        Set append textstrengen på den vedrørende textview
        TextView callingActivityMessage = (TextView) findViewById(R.id.calling_activity_info_text_view);
        callingActivityMessage.append(" "  + previousActivity);
    }

    public void onSendUsersName(View view) {

//        Henter værdien af edit texten
        EditText usersNameET = (EditText) findViewById(R.id.users_edit_name_text);
        String usersName = String.valueOf(usersNameET.getText());

//        Vedlægger dataen når man går tilbage
        Intent goingBack = new Intent();
        goingBack.putExtra("UsersName", usersName);
        setResult(RESULT_OK, goingBack);

        finish();
    }

}
