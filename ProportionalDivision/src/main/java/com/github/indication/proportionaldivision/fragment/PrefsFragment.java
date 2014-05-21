package com.github.indication.proportionaldivision.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.github.indication.proportionaldivision.R;

public class PrefsFragment extends PreferenceFragment {

	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static PrefsFragment newInstance() {
		PrefsFragment fragment = new PrefsFragment();
		return fragment;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences);
	}
}