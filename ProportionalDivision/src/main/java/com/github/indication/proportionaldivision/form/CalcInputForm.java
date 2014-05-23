package com.github.indication.proportionaldivision.form;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.github.indication.proportionaldivision.R;
import com.github.indication.proportionaldivision.enumurations.CalcType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CalcInputForm {
	public EditText textInput;
	public Switch switchClear;
	public List<Button> buttons = new ArrayList<Button>();
	public Button buttonClear;
	public ToggleButton buttonKeep;
	public ViewGroup layoutFirst;
	public ViewGroup layoutSecond;
	protected EventClear eventClear = new EventClear() {
		@Override
		public void onClearClicked() {

		}
	};
	protected EventPushNumber eventPushNumber = new EventPushNumber() {
		@Override
		public void onPushNumber(CalcType type, BigDecimal num) {

		}
	};

	public CalcInputForm(View view) {
		setup(view);
		setupEvent();
	}

	public void setOnClearClicked(EventClear event) {
		eventClear = event;
	}

	public void setOnPushNumber(EventPushNumber event) {
		eventPushNumber = event;
	}

	public void setup(View view) {
		textInput = (EditText) view.findViewById(R.id.editText);
		switchClear = (Switch) view.findViewById(R.id.switchClear);
		buttons.clear();
		for (int res : new int[]{
				R.id.buttonPlus
				, R.id.buttonMinus
				, R.id.buttonDivide
				, R.id.buttonMultiple
				, R.id.buttonGo
		}) {
			buttons.add((Button) view.findViewById(res));
		}
		buttonKeep = (ToggleButton) view.findViewById(R.id.toggleButtonKeep);
		buttonClear = (Button) view.findViewById(R.id.buttonClear);
		layoutFirst = (ViewGroup) view.findViewById(R.id.layoutFirst);
		layoutSecond = (ViewGroup) view.findViewById(R.id.layoutSecond);

	}

	public void clear() {
		eventClear.onClearClicked();
		textInput.setText("");
		setClearChecked(true);
		buttonKeep.setChecked(false);
	}

	public void setClearChecked(boolean status) {
		switchClear.setChecked(status);
		switchClear.requestLayout();
		if(status){
			layoutFirst.setVisibility(View.VISIBLE);
			layoutSecond.setVisibility(View.GONE);
		} else {
			layoutFirst.setVisibility(View.GONE);
			layoutSecond.setVisibility(View.VISIBLE);
		}
	}

	public void setupEvent() {
		switchClear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (!b || !(compoundButton instanceof Switch))
					return;
				clear();
			}
		});

		switchClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
		buttonClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				textInput.setText("");
				buttonKeep.setChecked(false);
			}
		});
		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				CalcType input = null;
				switch (view.getId()) {
					case R.id.buttonDivide:
						input = CalcType.Devide;
						break;
					case R.id.buttonMinus:
						input = CalcType.Minus;
						break;
					case R.id.buttonMultiple:
						input = CalcType.Multiple;
						break;
					case R.id.buttonPlus:
						input = CalcType.Plus;
						break;
					case R.id.buttonGo:
						input = CalcType.Plus;
						break;
					default:
						break;
				}
				if (input == null || TextUtils.isEmpty(textInput.getText()))
					return;

				setClearChecked(false);
				eventPushNumber.onPushNumber(input, new BigDecimal(textInput.getText().toString()));
				if (!buttonKeep.isChecked())
					textInput.setText("");
			}
		};
		for (Button item : buttons) {
			item.setOnClickListener(listener);
		}

	}

	public interface EventClear {
		public void onClearClicked();
	}

	public interface EventPushNumber {
		public void onPushNumber(CalcType type, BigDecimal num);
	}

}
