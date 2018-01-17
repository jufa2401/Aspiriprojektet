package com.aspiri.karakterloeft.games.flipcards;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aspiri.karakterloeft.R;
import com.aspiri.karakterloeft.games.ourDatabaseHelper;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yalantis.multiselection.lib.MultiSelect;
import com.yalantis.multiselection.lib.MultiSelectBuilder;

import java.util.ArrayList;
import java.util.List;

//import com.aspiri.karakterloeft.games.Contact;

/**
 * Csreated by Artem Kholodnyi on 9/3/16.
 */
public class FlipcardSubjectList extends AppCompatActivity {

    private MultiSelect<Flipcard> mMultiSelect;
    private ourDatabaseHelper dbHelper;
    private FlipcardBank flipcardBank;
    private String category;
    private String question;
    private String shortAnswer;
    private String longAnswer;
    private String photoTemp;
    LeftAdapter leftAdapter;
    RightAdapter rightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View cView = getLayoutInflater().inflate(R.layout.dialog_flipcard_creation, null);
        super.onCreate(savedInstanceState);
        setContentView(com.aspiri.karakterloeft.R.layout.flipcard_subject_list);
        ArrayList<String> front = getIntent().getStringArrayListExtra("front");
        ArrayList<String> back = getIntent().getStringArrayListExtra("back");

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setUpToolbar((Toolbar) findViewById(R.id.toolbar));
        askPermissions();

    }

    //TODO: skal ændres så det er kameraet der spørges om lov til, med henblik på at kunne tage billder af grafer, til egne flipcards.
    private void askPermissions() {
        RxPermissions.getInstance(this)
                .request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
//TODO: Tag billede, eller find fil på storage
                        loadFlipcards();
                        Log.d("Permission granted? ", " YES");
                    } else {
                        View content = findViewById(android.R.id.content);
                        Snackbar.make(content, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.allow, v -> askPermissions())
                                .show();
                    }
                });
    }

    private void loadFlipcards() {

        dbHelper = new ourDatabaseHelper(getApplicationContext());
        flipcardBank = new FlipcardBank();
        flipcardBank.initFlipcards(getApplicationContext());
        List<Flipcard> flipcards = dbHelper.getAllFlipcardsList();


        MultiSelectBuilder<Flipcard> builder = new MultiSelectBuilder<>(Flipcard.class)
                .withContext(this)
                .mountOn((ViewGroup) findViewById(R.id.mount_point))
                .withSidebarWidth(100 + 8 * 2)
                ; // ImageView width with paddings


        setUpAdapters(builder, flipcards);
        mMultiSelect = builder.build();

        setUpDecoration();
    }

    private void setUpDecoration() {
        FlipcardItemDecorator itemDecorator = new FlipcardItemDecorator(
                getResources().getDimensionPixelSize(R.dimen.decoration_size));
        mMultiSelect.getRecyclerLeft().addItemDecoration(itemDecorator);
        mMultiSelect.getRecyclerRight().addItemDecoration(itemDecorator);
    }

    private void setUpAdapters(MultiSelectBuilder<Flipcard> builder, List<Flipcard> flipcards) {
        LeftAdapter leftAdapter = new LeftAdapter(position -> mMultiSelect.select(position));
        RightAdapter rightAdapter = new RightAdapter(position -> mMultiSelect.deselect(position));

        leftAdapter.addAll(flipcards);


        builder.withLeftAdapter(leftAdapter)
                .withRightAdapter(rightAdapter);
    }


    private void setUpToolbar(final Toolbar toolbar) {
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setOnMenuItemClickListener(item -> {
            //TODO: Lav onBack pressed.
            // DOOO IT. IT's Not working. .
            if (item.getItemId() == R.drawable.ic_back){
            onBackPressed();
//                setSupportActionBar(toolbar);
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Log.d("Back button ", "Pressed");
            }

            if (item.getItemId() == R.id.create_card){
                // If the create card icon is pressed:
                //TODO: Prøv om det kan lade sig gøre, at få det til at fungere med alertDialog, hvis ikke kan der laves et fragment til textinput.
                AlertDialog.Builder cBuilder = new AlertDialog.Builder(FlipcardSubjectList.this);
                View cView = getLayoutInflater().inflate(R.layout.dialog_flipcard_creation, null);
                EditText cCategory = (EditText) cView.findViewById(R.id.card_category);
                EditText cQuestionFront = (EditText) cView.findViewById(R.id.card_question_front);
                EditText cShortAnswerBack = (EditText) cView.findViewById(R.id.card_shortAnswer_back);
                EditText cLongAnswerBack = (EditText) cView.findViewById(R.id.card_longAnswer_back);
                Button cAcknowledgeCardCreation = (Button) cView.findViewById(R.id.card_creation_acknowledge);
                               //If all fields are filled with data (NOT REGEX YET):
                cAcknowledgeCardCreation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!cCategory.getText().toString().isEmpty() &&
                                !cQuestionFront.getText().toString().isEmpty() &&
                                !cShortAnswerBack.getText().toString().isEmpty() &&
                                !cLongAnswerBack.getText().toString().isEmpty()) {
                            Toast.makeText(FlipcardSubjectList.this, "Success - kortet er lavet", Toast.LENGTH_SHORT).show();

                            Log.d("FC_category: ", cCategory.getText().toString());
                            Log.d("FC_question: ", cQuestionFront.getText().toString());
                            Log.d("FC_shortAnswer: ", cShortAnswerBack.getText().toString());
                            Log.d("FC_longAnswer: ", cLongAnswerBack.getText().toString());

                            //Saving EditText input to strings,
                            category = cCategory.getText().toString();
                            question = cQuestionFront.getText().toString();
                            shortAnswer = cShortAnswerBack.getText().toString();
                            longAnswer = cLongAnswerBack.getText().toString();
                            photoTemp = "null";

                            Flipcard newFlipcard = new Flipcard(category,question,shortAnswer,longAnswer,photoTemp);
                            Log.d("flipcard: ", newFlipcard.toString());


                            //TODO: Update leftAdapter layout after card creation:


                            dbHelper.addDataFlipCard(newFlipcard);

                            cCategory.getText().clear();
                            cQuestionFront.getText().clear();
                            cShortAnswerBack.getText().clear();
                            cLongAnswerBack.getText().clear();



                        } else {
                            Toast.makeText(FlipcardSubjectList.this, "Fejl, udfyld alle felter", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                cBuilder.setView(cView);
                AlertDialog alert = cBuilder.create();
                alert.show();



            }
            if (item.getItemId() == R.id.select) {
                List<Flipcard> items = mMultiSelect.getSelectedItems();
                final int selectedCount = items.size();
                final String msg;
                if (selectedCount == 0) {
                    msg = getString(R.string.nothing_selected);
                    mMultiSelect.showNotSelectedPage();
                } else {
                    msg = getResources().getQuantityString(R.plurals.you_selected_x_subjects,
                            selectedCount, selectedCount);
                    mMultiSelect.showSelectedPage();

                    ArrayList<String> front = new ArrayList<String>();
                    ArrayList<String> back = new ArrayList<String>();
                    ArrayList<String> backExplalation = new ArrayList<>();
                    for (int b = 0; b < items.size(); b++) {
                        Flipcard flipcard1 = items.get(b);
                        front.add(flipcard1.getFront());
                        back.add(flipcard1.getBack());
                        backExplalation.add(flipcard1.getBackExplanation());
                    }


                    Intent goToFlipCards = new Intent(this, FlipcardActivity.class);
                    goToFlipCards.putStringArrayListExtra("front", front);
                    goToFlipCards.putStringArrayListExtra("back", back);
                    goToFlipCards.putStringArrayListExtra("backExplanation", backExplalation);
                    //                    goToFlipCards.putStringArrayListExtra("item", (<String>) items);
                    startActivity(goToFlipCards);
                    Log.d("AspiriApp", "Flipcard_pressed");

                    Log.d(" Valgte emner: ", items.toString());
                }
                Snackbar.make(toolbar, msg, Snackbar.LENGTH_LONG).show();
                return true;
            } else {
                return false;
            }
        });
    }

}