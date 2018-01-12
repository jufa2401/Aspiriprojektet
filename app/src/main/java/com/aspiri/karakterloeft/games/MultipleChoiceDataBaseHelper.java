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

public class MultipleChoiceDataBaseHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "KL_db";
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME_1 = "quiz_table";
    private static final String COL1_1 = "question";
    private static final String COL2_1 = "choice1";
    private static final String COL3_1 = "choice2";
    private static final String COL4_1 = "choice3";
    private static final String COL5_1 = "choice4";
    private static final String COL6_1 = "answer";
    private static final String TABLE_NAME_2 = "flipcard_table";
    private static final String COL1_2 = "category";
    private static final String COL2_2 = "front";
    private static final String COL3_2 = "back";
    private static final String COL4_2 = "photo";
    String createTableMultipleChoice = "CREATE TABLE " + TABLE_NAME_1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL1_1 + " TEXT, " + COL2_1 + " TEXT, " + COL3_1 + " TEXT, " + COL4_1 + " TEXT, " + COL5_1 + " TEXT, " + COL6_1 + " TEXT)";
    String createTableFlipcard =  "CREATE TABLE " + TABLE_NAME_2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL1_2 + " TEXT, " + COL2_2 + " TEXT, " + COL3_2 + " TEXT, " + COL4_2 + " TEXT)";

    public MultipleChoiceDataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(createTableMultipleChoice, "createtable MultipleChoiceDatabase");
        db.execSQL(createTableMultipleChoice);
        Log.d(createTableFlipcard, "createtable Flipcard");
        db.execSQL(createTableFlipcard);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME_1 + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME_2 + "'");

        onCreate(db);
    }

    public boolean addDataQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1_1, question.getQuestion());
        Log.d(TAG, "addDataQuestion: Adding " + question + " to " + TABLE_NAME_1);
        contentValues.put(COL2_1, question.getChoice(0));
        Log.d(TAG, "addDataQuestion: Adding " + question.getChoice(0) + " to " + TABLE_NAME_1);
        contentValues.put(COL3_1, question.getChoice(1));
        Log.d(TAG, "addDataQuestion: Adding " + question.getChoice(1) + " to " + TABLE_NAME_1);
        contentValues.put(COL4_1, question.getChoice(2));
        Log.d(TAG, "addDataQuestion: Adding " + question.getChoice(2) + " to " + TABLE_NAME_1);
        contentValues.put(COL5_1, question.getChoice(3));
        Log.d(TAG, "addDataQuestion: Adding " + question.getChoice(3) + " to " + TABLE_NAME_1);
        contentValues.put(COL6_1, question.getAnswer());
        Log.d(TAG, "addDataQuestion: Adding " + question.getAnswer() + " to " + TABLE_NAME_1);

        long result = db.insert(TABLE_NAME_1, null, contentValues);
        //if data as inserted incorrectly it will return -1
        Log.d("result was", result + "");
        return result != -1;
    }
    public boolean addDataFlipCard(Flipcard flipcard){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(COL1_2, flipcard.getCategory());
        Log.d(TAG, "addDataQuestion: Adding " + flipcard.getCategory() + " to " + TABLE_NAME_2);

        contentValues1.put(COL2_2, flipcard.getFront());
        Log.d(TAG, "addDataQuestion: Adding " + flipcard.getFront() + " to " + TABLE_NAME_2);

        contentValues1.put(COL3_2, flipcard.getBack());
        Log.d(TAG, "addDataQuestion: Adding " + flipcard.getBack() + " to " + TABLE_NAME_2);
        contentValues1.put(COL4_2, flipcard.getPhoto());
        Log.d(TAG, "addDataQuestion: Adding " + flipcard.getPhoto() + " to " + TABLE_NAME_2);


        long result = db.insert(TABLE_NAME_2, null, contentValues1);
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
        values.put(COL1_1, question.getQuestion());
        values.put(COL2_1, question.getChoice(0));
        values.put(COL3_1, question.getChoice(1));
        values.put(COL4_1, question.getChoice(2));
        values.put(COL5_1, question.getChoice(3));
        values.put(COL6_1, question.getAnswer());
        // insert row in question table
        long insert = db.insert(TABLE_NAME_1, null, values);
        String strLong = Long.toString(insert);
        Log.d(strLong, "LONG Output");
        return insert;
    }
    public long addInitialFlipcard(Flipcard flipcard) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(COL1_2, flipcard.getCategory());
        values.put(COL2_2, flipcard.getFront());
        values.put(COL3_2, flipcard.getBack());
        values.put(COL4_2, flipcard.getPhoto());

        // insert row in question table
        long insert = db.insert(TABLE_NAME_2, null, values);
        String strLong = Long.toString(insert);
        Log.d(strLong, "LONG Output");
        return insert;
    }



    /**
     * To extract data from database and save it Arraylist of data type
     * Question
     */
    public List<Question> getAllQuestionsList() {
        List<Question> questionArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + "'" + TABLE_NAME_1 + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all records and adding to the list
        if (c.moveToFirst()) {
            do {
                Question question = new Question();

                String questText = c.getString(c.getColumnIndex(COL1_1));
                question.setQuestion(questText);

                String choice1Text = c.getString(c.getColumnIndex(COL2_1));
                question.setChoice(0, choice1Text);

                String choice2Text = c.getString(c.getColumnIndex(COL3_1));
                question.setChoice(1, choice2Text);

                String choice3Text = c.getString(c.getColumnIndex(COL4_1));
                question.setChoice(2, choice3Text);

                String choice4Text = c.getString(c.getColumnIndex(COL5_1));
                question.setChoice(3, choice4Text);

                String answerText = c.getString(c.getColumnIndex(COL6_1));
                question.setAnswer(answerText);

                // adding to Questions list
                questionArrayList.add(question);
            } while (c.moveToNext());
            Collections.shuffle(questionArrayList);
        }
        return questionArrayList;
    }

    public List<Flipcard> getAllFlipcardsList() {
        List<Flipcard> flipcardArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + "'" + TABLE_NAME_2 + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        Flipcard flipcard = new Flipcard();
        // looping through all records and adding to the list
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex("ID"));
                flipcard.setId(id);

                String category = c.getString(c.getColumnIndex(COL1_2));
                flipcard.setCategory(category);

                String frontText = c.getString(c.getColumnIndex(COL2_2));
                flipcard.setFront(frontText);

                String backText = c.getString(c.getColumnIndex(COL3_2));
                flipcard.setBack(backText);

                String photo = c.getString(c.getColumnIndex(COL4_2));
                flipcard.setPhoto(photo);

                // adding to Questions list
                flipcardArrayList.add(flipcard);
            } while (c.moveToNext());
            Collections.shuffle(flipcardArrayList);
        }
        return flipcardArrayList;
    }

//    public int getID(){
//        String selectQuery = "SELECT * FROM " + "'" + TABLE_NAME_2 + "'";
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        int id =  c.getInt(c.getColumnIndex("ID"));;
//
//        return id;
//    }


}

