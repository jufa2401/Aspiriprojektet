package com.aspiri.karakterloeft.games;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Justin on 10/01/2018.
 */

class MultipleChoiceDataBaseHelper extends SQLiteOpenHelper {


    private static final String TAG = "DatabaseHelper";




    private static final String TABLE_NAME = "quiz_table";
    private static final String COL1 = "question";
    private static final String COL2 = "choice1";
    private static final String COL3 = "choice2";
    private static final String COL4 = "choice3";
    private static final String COL5 = "choice4";
    private static final String COL6 = "answer";
    String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL1 + " TEXT" + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT" + COL5 + " TEXT" + COL6 + " TEXT)";
    public MultipleChoiceDataBaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");

        onCreate(db);
    }

    public boolean addData(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, question.getQuestion());
        Log.d(TAG, "addData: Adding " + question + " to " + TABLE_NAME);
        contentValues.put(COL2, question.getChoice(0));
        Log.d(TAG, "addData: Adding " + question.getChoice(0) + " to " + TABLE_NAME);
        contentValues.put(COL3, question.getChoice(1));
        Log.d(TAG, "addData: Adding " + question.getChoice(1) + " to " + TABLE_NAME);
        contentValues.put(COL3, question.getChoice(2));
        Log.d(TAG, "addData: Adding " + question.getChoice(2) + " to " + TABLE_NAME);
        contentValues.put(COL3, question.getChoice(3));
        Log.d(TAG, "addData: Adding " + question.getChoice(3) + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);
        //if data as inserted incorrectly it will return -1
        Log.d("result was", result + "");
        return result != -1;
    }

    /**
     * Returns all the data from database but the including ID
     * @return
     */


    /**
     * This method is used to add question detail in question Table
     */
    public long addInitialQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(COL1, question.getQuestion());
        values.put(COL2, question.getChoice(0));
        values.put(COL3, question.getChoice(1));
        values.put(COL4, question.getChoice(2));
        values.put(COL5, question.getChoice(3));
        values.put(COL6, question.getAnswer());
        // insert row in question table
        long insert = db.insert(TABLE_NAME, null, values);
        return insert;
    }

    /**
     * To extract data from database and save it Arraylist of data type
     * Question
     */
    public List<Question> getAllQuestionsList() {
        List<Question> questionArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + "'" + TABLE_NAME + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all records and adding to the list
        if (c.moveToFirst()) {
            do {
                Question question = new Question();

                String questText = c.getString(c.getColumnIndex(COL1));
                question.setQuestion(questText);

                String choice1Text = c.getString(c.getColumnIndex(COL2));
                question.setChoice(0, choice1Text);

                String choice2Text = c.getString(c.getColumnIndex(COL3));
                question.setChoice(1, choice2Text);

                String choice3Text = c.getString(c.getColumnIndex(COL4));
                question.setChoice(2, choice3Text);

                String choice4Text = c.getString(c.getColumnIndex(COL5));
                question.setChoice(3, choice4Text);

                String answerText = c.getString(c.getColumnIndex(COL6));
                question.setAnswer(answerText);

                // adding to Questions list
                questionArrayList.add(question);
            } while (c.moveToNext());
            Collections.shuffle(questionArrayList);
        }
        return questionArrayList;
    }
}

