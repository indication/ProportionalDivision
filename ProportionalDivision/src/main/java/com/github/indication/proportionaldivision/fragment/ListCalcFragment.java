package com.github.indication.proportionaldivision.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.indication.proportionaldivision.R;
import com.github.indication.proportionaldivision.adapter.ListCalcAdapter;
import com.github.indication.proportionaldivision.enumurations.CalcType;
import com.github.indication.proportionaldivision.form.CalcInputForm;

import java.math.BigDecimal;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListCalcFragment extends ListFragment {
	private CalcInputForm form;
	private ListCalcAdapter adapter;

	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static ListCalcFragment newInstance() {
		ListCalcFragment fragment = new ListCalcFragment();
		return fragment;
	}

	public ListCalcFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_calc_list, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		form = new CalcInputForm(getView());
		form.clear();
		adapter = new ListCalcAdapter();

		form.setOnClearClicked(new CalcInputForm.EventClear() {
			@Override
			public void onClearClicked() {
				adapter.notifyDataSetInvalidated();
			}
		});
		form.setOnPushNumber(new CalcInputForm.EventPushNumber() {
			@Override
			public void onPushNumber(CalcType type, BigDecimal num) {
				adapter.listRaw.add(new ListCalcAdapter.Item(type, num));
				adapter.notifyDataSetChanged();
				getListView().smoothScrollToPosition(getListView().getCount());
			}
		});
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		sharedPrefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
				setupAdapterFromPrefs(sharedPreferences);
				adapter.notifyDataSetChanged();
			}
		});
		setupAdapterFromPrefs(sharedPrefs);
		setListAdapter(adapter);
	}

	protected void setupAdapterFromPrefs(SharedPreferences sharedPrefs){
		adapter.setupRounding(sharedPrefs.getString("preference_round","NONE"));
		adapter.setupScale(sharedPrefs.getString("preference_round_scale",""));
	}
}