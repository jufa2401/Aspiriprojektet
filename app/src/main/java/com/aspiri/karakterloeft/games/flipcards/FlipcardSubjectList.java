package com.aspiri.karakterloeft.games.flipcards;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

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
 * Created by Artem Kholodnyi on 9/3/16.
 */
public class FlipcardSubjectList extends AppCompatActivity {

    private MultiSelect<Flipcard> mMultiSelect;
    private MultipleChoiceDataBaseHelper dbHelper;
    private FlipcardBank flipcardBank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.aspiri.karakterloeft.R.layout.flipcard_subject_list);


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
                .withSidebarWidth(46 + 8 * 2); // ImageView width with paddings

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


                    for (int b = 0; b <= items.size(); b++) {
                        Flipcard flipcard1 = items.get(b);

                    }



                    Intent goToFlipCards = new Intent(this, FlipcardActivity.class);
                    goToFlipCards.putStringArrayListExtra("item", (ArrayList<String>) items);
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