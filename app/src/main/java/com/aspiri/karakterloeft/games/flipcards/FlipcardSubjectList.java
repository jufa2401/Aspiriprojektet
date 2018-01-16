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
import android.widget.EditText;
import android.widget.ListView;

import com.aspiri.karakterloeft.R;
import com.aspiri.karakterloeft.games.Flipcard;
import com.aspiri.karakterloeft.games.FlipcardActivity;
import com.aspiri.karakterloeft.games.FlipcardBank;
import com.aspiri.karakterloeft.games.MultipleChoiceDataBaseHelper;
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
    private MultipleChoiceDataBaseHelper dbHelper;
    private FlipcardBank flipcardBank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.aspiri.karakterloeft.R.layout.flipcard_subject_list);
        ArrayList<String> front = getIntent().getStringArrayListExtra("front");
        ArrayList<String> back = getIntent().getStringArrayListExtra("back");


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

        dbHelper = new MultipleChoiceDataBaseHelper(getApplicationContext());
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
            if (item.getItemId() == R.id.create_card){
                // If the create card icon is pressed:
                //TODO: Prøv om det kan lade sig gøre, at få det til at fungere med alertDialog, hvis ikke kan der laves et fragment til textinput.
                ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.Theme_dialog_create_flipcard);
                AlertDialog.Builder alert = new AlertDialog.Builder(ctw);
                alert.setTitle("Lav dit eget flipcard");
                alert.setMessage("Indtast titlen på kategorien");
                final EditText inputCatagory = new EditText(this);


                alert.setView(inputCatagory);


                alert.setPositiveButton("Bekræft", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
//                        Editable question = inputQuestion.getText();
                        Editable catagoryinp = inputCatagory.getText();
                        String catagory = catagoryinp.toString();

                    }
                });

                alert.setNegativeButton("Annuller", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.

                    }
                });

                alert.show();

                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_flipcard_creation_lv);

                ListView lv = dialog.findViewById(R.id.flipcard_create_listview);
                dialog.setCancelable(true);
                dialog.setTitle("Lav dit eget flipcard");


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
                    //TODO: Handler som læser elementerne fra items og sender dem videdre til en ny evt expandable list:
                    // Der er lavet et midlertidigt intent som går fra flipcard select til et default flipkort.

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