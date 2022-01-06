package com.android.demo.activity.demo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.android.demo.R;

/**
 * <p>
 *
 * @author anlc
 * @date 2020-01-13
 */
public class PreferenceDemoActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_demo);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, new GeneralPreferenceFragment())
                .commit();
    }

    public static class GeneralPreferenceFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.fragment_prefernce_demo);
        }
    }
}
