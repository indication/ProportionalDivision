package com.github.indication.proportionaldivision.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.github.indication.proportionaldivision.R;
import com.github.indication.proportionaldivision.enumurations.CalcType;
import com.github.indication.proportionaldivision.form.CalcItemForm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ListCalcAdapter extends BaseAdapter {
	static public class Item {
		public CalcType type;
		public BigDecimal input;
		public boolean isValid;
		public Item(){

		}
		public Item(CalcType calcType,BigDecimal data){
			type = calcType;
			input = data;
			isValid = true;
		}
	}
	static public class ResultItem extends Item{
		public BigDecimal result;
		public ResultItem(){
			super();
		}

		public ResultItem(CalcType calcType, BigDecimal data) {
			super(calcType, data);
			result = BigDecimal.ZERO;
		}
		public ResultItem(Item in){
			importItem(in);
			result = BigDecimal.ZERO;
		}

		public void importItem(Item in){
			type = in.type;
			input = in.input;
			isValid = in.isValid;
		}

	}

	public List<Item> listRaw = new ArrayList<Item>();
	private List<ResultItem> listResult = new ArrayList<ResultItem>();
	@Override
	public void notifyDataSetChanged() {
		listResult.clear();
		BigDecimal current = null;
		for(Item wk :listRaw ){
			ResultItem item = new ResultItem(wk);
			if(current == null){
				current = new BigDecimal(wk.input.toString());
				item.type = CalcType.None;
			} else {
				// start calculate
				switch(wk.type){
					case None:
						//fall through
					default:
						break;
					case Plus:
						current = current.add(wk.input);
						break;
					case Minus:
						current = current.add(wk.input.negate());
						break;
					case Devide:
						current = current.divide(wk.input);
						break;
					case Multiple:
						current = current.multiply(wk.input);
						break;
				}
			}
			item.result = new BigDecimal(current.toString());
			listResult.add(item);
		}
		super.notifyDataSetChanged();
	}

	@Override
	public void notifyDataSetInvalidated() {
		super.notifyDataSetInvalidated();
		clear();
	}
	public void clear(){
		listRaw.clear();
		listResult.clear();
	}

	@Override
	public int getCount() {
		return listResult.size();
	}

	@Override
	public Object getItem(int i) {
		return listResult.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View convertView, ViewGroup viewGroup) {
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
			convertView = inflater.inflate(R.layout.item_calc_list, null);
		}
		if(convertView != null){
			ResultItem rec = (ResultItem)getItem(i);
			if(rec == null){
			} else {
				CalcItemForm form = new CalcItemForm(convertView);
				form.setValue(rec);
			}
		}
		return convertView;
	}
}
