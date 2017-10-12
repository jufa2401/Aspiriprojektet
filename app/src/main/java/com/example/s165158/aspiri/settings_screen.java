package com.example.s165158.aspiri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jonas on 12/10/2017.
 */

public class settings_screen extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_screen);
    }


    public void goBack(View view) {
        finish();
    }

}
