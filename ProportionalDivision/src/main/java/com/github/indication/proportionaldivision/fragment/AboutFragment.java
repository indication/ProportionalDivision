package com.github.indication.proportionaldivision.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.indication.proportionaldivision.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class AboutFragment extends Fragment {

	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static AboutFragment newInstance() {
		AboutFragment fragment = new AboutFragment();
		return fragment;
	}

	public AboutFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_about, container, false);
		setupHtmlView(rootView, R.id.textDescription, R.string.app_description);
		setupHtmlView(rootView, R.id.textLicense, R.string.app_license_body);
		setupHtmlView(rootView, R.id.textVersion, R.string.app_version);
		return rootView;
	}
	//
	private void setupHtmlView(View view,int id,int resource){
		TextView item = (TextView)view.findViewById(id);
		String data = view.getContext().getString(resource);
		if(TextUtils.isEmpty(data) || item == null)
			return;
		item.setText(Html.fromHtml(data.replaceAll("[\\r\\n]+", "<br>")));
	}

}