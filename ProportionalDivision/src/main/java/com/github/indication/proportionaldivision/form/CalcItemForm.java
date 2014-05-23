package com.github.indication.proportionaldivision.form;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.indication.proportionaldivision.R;
import com.github.indication.proportionaldivision.adapter.ListCalcAdapter;
import com.github.indication.proportionaldivision.enumurations.CalcType;

import java.math.BigDecimal;

public class CalcItemForm {
	public TextView textResult;
	public TextView textKey;
	public TextView textView;
	public TextView textSymbolEquals;
	public TextView textException;

	public CalcItemForm(View view) {
		setup(view);
	}

	public void setup(View view) {
		textView = (TextView) view.findViewById(R.id.textView);
		textResult = (TextView) view.findViewById(R.id.textResult);
		textKey = (TextView) view.findViewById(R.id.textKey);
		textSymbolEquals = (TextView) view.findViewById(R.id.textSymbolEquals);
		textException = (TextView) view.findViewById(R.id.textException);
	}


	public void setValue(ListCalcAdapter.ResultItem item) {
		if (item == null)
			item = new ListCalcAdapter.ResultItem(CalcType.None, BigDecimal.ZERO);
		int visible = CalcType.None.equals(item.type) ? View.GONE : View.VISIBLE;
		textView.setText(item.input.toString());
		textView.setVisibility(visible);
		textResult.setText(item.result.toString());
		textKey.setText(item.type.getSymbol());
		textSymbolEquals.setVisibility(visible);
		if(TextUtils.isEmpty(item.exception)){
			textException.setVisibility(View.GONE);
		} else {
			textException.setVisibility(View.VISIBLE);
			textException.setText(item.exception);
		}
	}
}
