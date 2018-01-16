/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aspiri.karakterloeft.games;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aspiri.karakterloeft.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;

import java.util.ArrayList;

import static com.aspiri.karakterloeft.SubjectFragment.TAG;

/**
 * Demonstrates a "card-flip" animation using custom fragment transactions ({@link
 * android.app.FragmentTransaction#setCustomAnimations(int, int)}).
 *
 * <p>This sample shows an "info" action bar button that shows the back of a "card", rotating the
 * front of the card out and the back of the card in. The reverse animation is played when the user
 * presses the system Back button or the "photo" action bar button.</p>
 */
public class FlipcardActivity extends AppCompatActivity
        implements FragmentManager.OnBackStackChangedListener {
    FlipcardBank flipcardBank = new FlipcardBank();
    ArrayList<String> front;
    ArrayList<String> back;
    ArrayList<String> backExplanation;
    /**
     * A handler object, used for deferring UI operations.
     */
    private LinearLayout clickfrag;
    private Handler mHandler = new Handler();
    /**
     * Whether or not we're showing the back of the card (otherwise showing the front).
     */
    private boolean mShowingBack = false;
    private Fragment flipCardButtonFragment = new FlipcardButtonFragment();
    private int currentCardIndex = 0;
    private int cardStackSize;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);
        clickfrag = (LinearLayout) findViewById(R.id.card);

         front = getIntent().getStringArrayListExtra("front");
         back = getIntent().getStringArrayListExtra("back");
         backExplanation = getIntent().getStringArrayListExtra("backExplanation");

            setCardStackSize(front.size());
         Bundle b = new Bundle();
        b.putStringArrayList("front",front);
        b.putStringArrayList("back",back);
        b.putStringArrayList("back_explanation", backExplanation);
        flipcardBank.initFlipcards(getApplicationContext());
        CardFrontFragment frontFragment = new CardFrontFragment();
        frontFragment.setArguments(b);
        Trace myTrace = FirebasePerformance.getInstance().newTrace("test_trace");
        myTrace.start();


        if (savedInstanceState == null) {
            // If there is no saved instance state, add a fragment representing the
            // front of the card to this activity. If there is saved instance state,
            // this fragment will have already been added to the activity.
            mFragmentManager = getFragmentManager();
                    mFragmentManager.beginTransaction()
                    .add(R.id.container, frontFragment)
                    .add(R.id.button_container, flipCardButtonFragment)
                    .commit();
        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

        // Monitor back stack changes to ensure the action bar shows the appropriate
        // button (either "photo" or "info") .
        getFragmentManager().addOnBackStackChangedListener(this);

        myTrace.stop();

        //Firebase Deeplinks
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                        }

                        //  showToast("test2");

                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("AspiriApp", "getDynamicLink:onFailure", e);
                    }
                });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);
        switch (action){
            case(MotionEvent.ACTION_DOWN):
                Log.d("Flipcard", "ACTION_DOWN");
                flipCard();
                break;


//            case (MotionEvent.ACTION_MOVE) :
//                Log.d("Aspiri app","Action was MOVE");
//                flipCard();
//                return true;
//            case (MotionEvent.ACTION_UP) :
//                Log.d("Aspiri app","Action was UP");
//                flipCard();
//                return true;
//            case (MotionEvent.ACTION_CANCEL) :
//                Log.d("Aspiri app","Action was CANCEL");
//                flipCard();
//                return true;
//            case (MotionEvent.ACTION_OUTSIDE) :
//                Log.d("Aspiri app","Movement occurred outside bounds " +
//                        "of current screen element");
//                flipCard();
//                return true;
            default :
                return super.onTouchEvent(event);
            }return true;
         }



    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }

        // Flip to the back.

        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for the back of
        // the card, uses custom animations, and is part of the fragment manager's back stack.

        CardBackFragment cardBack = new CardBackFragment();
        Bundle cardBackBundle = new Bundle();
        cardBackBundle.putStringArrayList("front",front);
        cardBackBundle.putStringArrayList("back",back);
        cardBackBundle.putStringArrayList("backExplanation", backExplanation);
        cardBack.setArguments(cardBackBundle);
        getFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources representing
                // rotations when switching to the back of the card, as well as animator
                // resources representing rotations when flipping back to the front (e.g. when
                // the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a fragment
                // representing the next page (indicated by the just-incremented currentPage
                // variable).


                .replace(R.id.container, cardBack)

                // Add this transaction to the back stack, allowing users to press Back
                // to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit();

        // Defer an invalidation of the options menu (on modern devices, the action bar). This
        // can't be done immediately because the transaction may not yet be committed. Commits
        // are asynchronous in that they are posted to the main thread's message loop.
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);

        // When the back stack changes, invalidate the options menu (action bar).
        invalidateOptionsMenu();
    }

    public void showMessage(String messageForToast) {
        Log.d(TAG, "showToast Method was called");
        Toast.makeText(getApplicationContext(), messageForToast, Toast.LENGTH_SHORT).show();
    }
    public void nextFlipCard(Fragment fragment, String tag) {
        if (mFragmentManager == null)
            return;

        if(mShowingBack){
            flipCard();
        }

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("front",front);
        bundle.putStringArrayList("back",back);
        bundle.putStringArrayList("backExplanation",backExplanation);
        if(back == null){
            throw new NullPointerException("back object is null");


        }
        fragment.setArguments(bundle);


            fragmentTransaction
                    .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_out_right, R.animator.slide_in_right);

        fragmentTransaction.replace(R.id.container, fragment, tag).commit();
    }
    public int getCurrentCardIndex() {
        return currentCardIndex;
    }

    public void countUpCurrentCardIndex() {
        this.currentCardIndex++;
    }
    public void countDownCurrentCardIndex() {
        this.currentCardIndex--;
    }

    public int getCardStackSize() {
        return cardStackSize;
    }

    public void setCardStackSize(int cardStackSize) {
        this.cardStackSize = cardStackSize;
    }
}

    /**
     * A fragment representing the front of the card.
     */



