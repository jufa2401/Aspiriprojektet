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

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aspiri.karakterloeft.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;

import static com.aspiri.karakterloeft.SubjectFragment.TAG;

/**
 * Demonstrates a "card-flip" animation using custom fragment transactions ({@link
 * android.app.FragmentTransaction#setCustomAnimations(int, int)}).
 *
 * <p>This sample shows an "info" action bar button that shows the back of a "card", rotating the
 * front of the card out and the back of the card in. The reverse animation is played when the user
 * presses the system Back button or the "photo" action bar button.</p>
 */
public class FlipcardActivity extends Activity
        implements FragmentManager.OnBackStackChangedListener {
    FlipcardBank flipcardBank = new FlipcardBank();
    /**
     * A handler object, used for deferring UI operations.
     */
    private LinearLayout clickfrag;
    private Handler mHandler = new Handler();
    /**
     * Whether or not we're showing the back of the card (otherwise showing the front).
     */
    private boolean mShowingBack = false;
    private Fragment flipCardButtonFragment = new FlipCardButtonFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);
        clickfrag = findViewById(R.id.card);

        flipcardBank.initFlipcards(getApplicationContext());

        Trace myTrace = FirebasePerformance.getInstance().newTrace("test_trace");
        myTrace.start();


        if (savedInstanceState == null) {
            // If there is no saved instance state, add a fragment representing the
            // front of the card to this activity. If there is saved instance state,
            // this fragment will have already been added to the activity.
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
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

                    //  showMessage("test2");

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
                return true;
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
            }
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
//        cardBackBundle.putString("front",front);
//        cardBackBundle.putString("back",back);
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
        Log.d(TAG, "showMessage Method was called");
        Toast.makeText(getApplicationContext(), messageForToast, Toast.LENGTH_SHORT).show();
    }

    /**
     * A fragment representing the front of the card.
     */
    public static class CardFrontFragment extends Fragment {
        public CardFrontFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_front, container, false);
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    public static class CardBackFragment extends Fragment {
        public CardBackFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_back, container, false);
        }
    }
}