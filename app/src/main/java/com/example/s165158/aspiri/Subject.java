//package com.example.s165158.aspiri;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
////Denne klasse håndterer den activity man ryger ind i  når man klikker på et list-objekt
//public class Subject extends AppCompatActivity implements View.OnClickListener {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.subject_1);
//
//        String savedExtra = getIntent().getStringExtra("content");
//        TextView myText = (TextView) findViewById(R.id.contentID);
//        myText.setText(savedExtra);
//
//        Button b1 = (Button) findViewById(R.id.button);
//        b1.setOnClickListener(this);
//
//        Log.d("AspiriApp","created new view");
//    }
//
//
//    //Knapper
//    @Override
//    public void onClick(View view) {
//
//        switch (view.getId()) {
//            //Knap test udføres bare ved normale brugertest
//            case R.id.button:
//                super.onBackPressed();
//
//                Log.d("AspiriApp","back button pressed");
//                break;
//
//            default:
//                break;
//        }
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
////        Appender data
//        TextView usersNameMessage = (TextView) findViewById(R.id.users_name_message);
//        String nameSentBack = data.getStringExtra("UsersName");
//        usersNameMessage.append(" " + nameSentBack);
//    }
//
//
//}
