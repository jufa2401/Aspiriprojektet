package com.aspiri.karakterloeft;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;


//Kraftigt inpireret af https://www.androidhive.info/2017/07/android-implementing-preferences-settings-screen/
public class SettingsActivity extends AppCompatPreferenceActivity  {
    private static final String TAG = SettingsActivity.class.getSimpleName();
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();

            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof EditTextPreference) {
                if (preference.getKey().equals("key_gallery_name")) {
                    // update the changed gallery name to summary filed
                    preference.setSummary(stringValue);
                }
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    //public String varNumber = getTagVersionNumber();
    private FirebaseAnalytics mFirebaseAnalytics;

    private static void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    //Kan Sender feedback til HQ
    public static void sendFeedback(Context context) {
        String body = null;
        try {
            body = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            body = "\n\n-----------------------------\n" +
                    "Vær venlig ikke at fjerne denne information" +
                    "\n Enhed OS: Android " +
                    "\n Enhed OS version: " + Build.VERSION.RELEASE + "" +
                    "\n App Version: " + body +
                    "\n Enheds mærke: " + Build.BRAND +
                    "\n Enhed Model: " + Build.MODEL +
                    "\n Fabrikant: " + Build.MANUFACTURER +
                    "\n-----------------------------\n";
        } catch (PackageManager.NameNotFoundException e) {
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"jm@aspiri.dk","na@aspiri.dk"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Fejlmeddelese fra KL App");
        intent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.choose_email_client)));
    }

    //Henter Versionsnummer
    public String getTagVersionNumber() {
        String tempInfo = null;
        try {
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            tempInfo = pInfo.versionName.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return tempInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        // load settings fragment
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MainPreferenceFragment()).commit();

    }

    //TODO: Få kaldt denne metode i den statiske onCreate
    private void setVersionNumber() {
        //Preference varNumber = findPreference(getString(R.string.title_version));
        //varNumber.setTitle(version);
    }

    //Selecting options
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public static class MainPreferenceFragment extends PreferenceFragment{


        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_main);

            //Lav versionsnummeret om til 1.0:
            Preference versionTitle;
            versionTitle = findPreference("test1");
            //versionTitle.setSummary();

            // gallery EditText change listener
            bindPreferenceSummaryToValue(findPreference(getString(R.string.mail_default_msg)));

            // feedback preference click listener
            Preference myPref = findPreference(getString(R.string.key_send_feedback));
            myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    sendFeedback(getActivity());
                    Log.d("Feedback","Sender Feedback");
                    return true;
                }
            });

        }
    }

}