package com.aspiri.karakterloeft;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aspiri.karakterloeft.games.quiz.MultipleChoiceFragment;
import com.aspiri.karakterloeft.oldScope.list_view.ListFragment;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.appinvite.FirebaseAppInvite;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.AddTrace;
import com.google.firebase.perf.metrics.Trace;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.aspiri.karakterloeft.R.string.invitation_image_link;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {
    private final static String MESSAGE = "MESSAGE";
    //For Firebase
    private static String TAG = MainActivity.class.getSimpleName();
    private static int REQUEST_INVITE = 0;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    //    ExpandableListAdapter mExpandableListAdapter;
    private int oldindex;
    private FragmentManager mFragmentManager;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private FirebaseAnalytics mFirebaseAnalytics;

    //On createmetode
    @Override
    @AddTrace(name = "onCreateTrace", enabled = true)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        // Fanebladet
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        setupDrawer();

        //mExpandableListView.setAdapter(mExpandableListAdapter);
        mFragmentManager = getFragmentManager();
        setDrawerIndicatorEnabled(false);
        if (savedInstanceState == null) {                                                        //Håndtering af skærmrotation
            replaceFragment(new ListFragment(), ListFragment.TAG);
        }

        //For Firebase
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //Firebase Dynamic Links
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData data) {
                        // Get deep link from result (may be null if no link is found)
/**                        Uri deepLink = null;
                        if (data != null) {
                            deepLink = Data.getLink();
**/
                        if(data == null){
                            Log.d("Inv data","getInvitation: no data");
                            return;
                    }
                    // Get the deep link
                    Uri deepLink = data.getLink();

                        //Extract invite
                        FirebaseAppInvite invite = FirebaseAppInvite.getInvitation(data);
                        if(invite != null){
                            String invitationId = invite.getInvitationId();
                        }

                        // Handle the deep link
                        // [Start exclude???]
                        Log.d("link ","deepLink: " + deepLink);
                        if (deepLink != null){
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setPackage(getPackageName());
                            intent.setData(deepLink);

                            startActivity(intent);
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("AspiriApp", "getDynamicLink:onFailure", e);
                    }
                });
    }

    //    Tredottede menu initialiseres her
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.three_dot_menu, menu);
        return true;
    }


    //    Onclick for tredottede menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.three_dot_quit:
                finish();
                Log.d("AspiriApp", "action_quit pressed");
                return true;

            case R.id.three_dot_settings:
                // User chose the "Settings" item, show the app settings UI...
                Intent goToSettings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(goToSettings);
                Log.d("AspiriApp", "action_settings pressed");
                return true;

            case R.id.addItem:              //Add item is visible exclusively in Multiple Choice Fragment for user adding of Quizzes, and is therefore not implemented here.
                return true;

            //Should be unused at all times
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /**
     * Method for handling Click Events in the navigationdrawer.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            //        Top functions
            case R.id.drawer_home:  // SKAL LUKKE DRAWEREN UDEN AT BRUGERN TRÆKKER DEN IND
                if (mFragmentManager.getBackStackEntryCount() == 0) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
                return true;

            case R.id.drawer_store:
                Intent goToStore = new Intent(Intent.ACTION_VIEW);
                goToStore.setData(Uri.parse(getString(R.string.drawer_URL_ToTheStore)));
                startActivity(goToStore);
                Log.d("AspiriApp", "Webstore pressed");
                return true;

            case R.id.drawer_send_us_mail:
                //Building Shared Preference Manager
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
                String mailToSend = sharedPref.getString(getString(R.string.mail_default_msg), "");

                //Initializing Intent
                Intent SendUsMail= new Intent(Intent.ACTION_SEND);
                SendUsMail.setType("message/rfc822");
                SendUsMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"jm@aspiri.dk","na@aspiri.dk"});
                SendUsMail.putExtra(Intent.EXTRA_SUBJECT, R.string.title_mail_header);
                SendUsMail.putExtra(Intent.EXTRA_TEXT, mailToSend);

                //Starting activity, doing log calls
                startActivity(Intent.createChooser(SendUsMail, getResources().getString(R.string.choose_email_client)));
                Log.d("AspiriApp", "Mail_icon pressed");
                return true;

            //Goes to flipcards.
            case R.id.drawer_flipcards:
                Intent goToFlipCardsSelection = new Intent(this, com.aspiri.karakterloeft.games.flipcards.FlipcardSubjectList.class);
                startActivity(goToFlipCardsSelection);
                Log.d("AspiriApp", "Flipcard_pressed");
                return true;
            case R.id.drawer_quiz:
                MultipleChoiceFragment fragment = new MultipleChoiceFragment();
                getFragmentManager().beginTransaction().addToBackStack("quiz from drawer")
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, R.animator.slide_out_right, R.animator.slide_in_right).replace(R.id.fragmentindhold, fragment)
                        .commit();
                drawer.closeDrawer(GravityCompat.START);
                return true;


            // Tester FB bot til KL, laver seperat knap til test.
            // Senere skal denne (hvis fungerende) benyttes et andet sted.
            case R.id.drawer_kontakt_test:

                try {
                    ApplicationInfo info = getPackageManager().
                            getApplicationInfo("com.facebook.katana", 0);
                    String uri = "fb://messaging/1137012216344735";
                    Intent contactFacebookBot = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(contactFacebookBot);
                    return true;
                } catch (PackageManager.NameNotFoundException e) {
                    showToast("Facebook ikke installeret");
                    Log.d(TAG, "Facebook not installed");
                    return true;
                }



            case R.id.drawer_share:

                Trace myTrace = FirebasePerformance.getInstance().newTrace("test_trace");
                myTrace.start();

                onInviteClicked();
                Log.d("SMS", "Der blev trykker på sms");

                myTrace.stop();
                return true;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * A method for replacing a fragment, if tag is not the first screen, then add to backstack, slide animations set
     * @param fragment the fragment that we want replaced in the the fragment container
     * @param tag a TAG that describes the Fragment
     */
    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (!tag.equals(ListFragment.TAG)) {
            fragmentTransaction
                    .addToBackStack(tag)
                    .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_out_right, R.animator.slide_in_right);
//            fragment.setEnterTransition(new Fade());
//            fragmentTransaction.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right);
        }

        fragmentTransaction.replace(R.id.fragmentindhold, fragment, tag).commit();
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    public void setActionBarTitle(String actionBarTitle) {
        if (!TextUtils.isEmpty(actionBarTitle))
//            mTextViewActionBarTitle.setText(actionBarTitle);
            setTitle(actionBarTitle);
    }

    public void setDrawerIndicatorEnabled(boolean value) {
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.setDrawerIndicatorEnabled(value);
            if (value == false) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                mActionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
                drawer.addDrawerListener(mActionBarDrawerToggle);
                mActionBarDrawerToggle.syncState();
            }
        }
    }

    private void setupDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
    }


    //Credit to firebase
    private void onInviteClicked() {
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
                .setCallToActionText(getString(R.string.invitation_cta))
                .setCustomImage(Uri.parse(getString(invitation_image_link)))
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }
    //Method implemented from Firebase
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode= " + requestCode + ", resultCode= " + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d(TAG, "onActivityResult: sent invitation " + id);
                }
            } else {
                showToast("Error, no invitation sent");
            }
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "On Connection failed" + connectionResult);
        showToast("Error OnconnectionFailed");
    }

    public void showToast(String messageForToast) {
        Log.d(TAG, "showToast() Method");
        Toast.makeText(getApplicationContext(), messageForToast, Toast.LENGTH_SHORT).show();
    }

    public int getOldindex() {
        return oldindex;
    }

    public void setOldindex(int oldindex) {
        this.oldindex = oldindex;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    //// ExpandableListAdapterCode
//        private void initItems() {
//        items = getResources().getStringArray(R.array.subject_list);
////    }
//
//    private void addDrawerItems() {
//        mExpandableListAdapter = new ExpandableListAdapter2(this, mExpandableListTitle, mExpandableListData);
//        mExpandableListView.setAdapter(mExpandableListAdapter);
//        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                getSupportActionBar().setTitle(mExpandableListTitle.get(groupPosition).toString());
//            }
//        });
//
//        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                getSupportActionBar().setTitle(R.string.drawer_subjects);
//            }
//        });
//
//        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//                String selectedItem = ((List) (mExpandableListData.get(mExpandableListTitle.get(groupPosition))))
//                        .get(childPosition).toString();
//                getSupportActionBar().setTitle(selectedItem);
//
//                final Bundle bundle = new Bundle();
//                SubjectFragment subjectFragment = new SubjectFragment();
//                setOldindex(groupPosition);
//                bundle.putInt("listindex",childPosition);
//
//                subjectFragment.setArguments(bundle);
//                nextFlipCard(subjectFragment, SubjectFragment.TAG);
//
//                drawer.closeDrawer(GravityCompat.START);
//                return false;
//            }
//        });
//    }


}





