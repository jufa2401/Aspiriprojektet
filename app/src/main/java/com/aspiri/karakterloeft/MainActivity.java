package com.aspiri.karakterloeft;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.aspiri.karakterloeft.games.FlipcardActivity;
import com.aspiri.karakterloeft.games.MultipleChoiceFragment;
import com.aspiri.karakterloeft.list_view.ListFragment;
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

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.aspiri.karakterloeft.R.string.invitation_image_link;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {
    private final static String MESSAGE = "MESSAGE";
    private int oldindex;
    private FragmentManager mFragmentManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    //For Firebase
    private static String TAG = MainActivity.class.getSimpleName();
    private static int REQUEST_INVITE = 0;
    private FirebaseAnalytics mFirebaseAnalytics;

    // test
    private List<String> mExpandableListTitle;
    private String[] items;
    ExpandableListAdapter mExpandableListAdapter;

    //     Der er problemer med ExpandableListView ikke adapteren!
    @BindView(R.id.navList)
    ExpandableListView mExpandableListView;


    private Map<String, List<String>> mExpandableListData;


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


        // sets mActionBarDrawerToggle
//        mExpandableListData = ExpandableListDataSource.getData(this);
//        mExpandableListTitle = new ArrayList(mExpandableListData.keySet());
//        // Add drawer items requires ExpandableListTitle and ExpandableListData to be initialized.
//        addDrawerItems();
        setupDrawer();

        //mExpandableListView.setAdapter(mExpandableListAdapter);

        setDrawerIndicatorEnabled(false);
        mFragmentManager = getFragmentManager();
        replaceFragment(new ListFragment(), ListFragment.TAG);

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
                showMessage("Yet to be implemented");
                Log.d("AspiriApp", "action_settings pressed");
                return true;

            case R.id.three_dot_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite..  .
                showMessage("Action Favorite is yet to be implemented");
                Log.d("AspiriApp", "action_favorite pressed");
                return true;

            //Should be unused at all times
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // On click for de forskellige optioner i drawer.
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
                Intent sendUsMail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jm@aspiri.dk", null));
                sendUsMail.putExtra(Intent.EXTRA_SUBJECT, R.string.mail_subject);
                sendUsMail.putExtra(Intent.EXTRA_TEXT, getString(R.string.mail_beginning));
                startActivity(Intent.createChooser(sendUsMail, "Choose an Email client :"));
                Log.d("AspiriApp", "Mail_icon pressed");
                return true;

            //Goes to flipcards.
            case R.id.drawer_flipcards:
                Intent goToFlipCards = new Intent(this, FlipcardActivity.class);
                startActivity(goToFlipCards);
                Log.d("AspiriApp", "Flipcard_pressed");
                return true;
            case R.id.drawer_quiz:
                MultipleChoiceFragment fragment = new MultipleChoiceFragment();
                replaceFragment(fragment, "he");
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
                    showMessage("Facebook ikke installeret");
                    return true;
                }



            case R.id.drawer_share:

                Trace myTrace = FirebasePerformance.getInstance().newTrace("test_trace");
                myTrace.start();

                onInviteClicked();
                showMessage("Drawer share was pressed");

                myTrace.stop();
                return true;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment, String tag) {
        if (mFragmentManager == null)
            return;

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (!tag.equals(ListFragment.TAG)) {
            fragmentTransaction
                    .addToBackStack(tag)

                    .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
//            fragment.setEnterTransition(new Fade());
//            fragmentTransaction.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right);
        }

        fragmentTransaction.replace(R.id.fragmentindhold, fragment, tag).commit();
    }
    //    TODO: FIX HOME AS UP

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
                getSupportActionBar().setTitle(R.string.find_way);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
    }

// EXPERIMENTAL CODE THAT DID NOT WORK!
//        private void initItems() {
//        items = getResources().getStringArray(R.array.subject_list);
//    }
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
//                if (items[0].equals(mExpandableListTitle.get(groupPosition))) {
//                    // Navigation Logic
//                } else if (items[1].equals(mExpandableListTitle.get(groupPosition))) {
//                  // Navigation logic
//                } else if (items[2].equals(mExpandableListTitle.get(groupPosition))) {
//                    // Navigation logic
//                } else if (items[3].equals(mExpandableListTitle.get(groupPosition))) {
//                   // Navigation logic
//                } else if (items[4].equals(mExpandableListTitle.get(groupPosition))) {
//                   // NavigationLogic
//                } else {
//                    throw new IllegalArgumentException("Not supported fragment type");
//                }
//
//                drawer.closeDrawer(GravityCompat.START);
//                return false;
//            }
//        });
//    }


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
                showMessage("Error, no invitation sent");
            }
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "On Connection failed" + connectionResult);
        showMessage("Error OnconnectionFailed");
    }

    public void showMessage(String messageForToast) {
        Log.d(TAG, "showMessage Methodwas called");
        Toast.makeText(getApplicationContext(), messageForToast, Toast.LENGTH_SHORT).show();
    }

    public int getOldindex() {
        return oldindex;
    }

    public void setOldindex(int oldindex) {
        this.oldindex = oldindex;
    }
}





