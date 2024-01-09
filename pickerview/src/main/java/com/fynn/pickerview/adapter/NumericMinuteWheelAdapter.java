package com.fynn.pickerview.adapter;


import com.fynn.adapter.WheelAdapter;

import java.util.ArrayList;

/**
 * Numeric Wheel adapter.
 */
public class NumericMinuteWheelAdapter implements WheelAdapter {

	private int minValue;
	private int maxValue;
	private boolean hasInterval = false;
	private ArrayList<Integer> mDatas = new ArrayList<>();
	/**
	 * Constructor
	 * @param minValue the wheel min value
	 * @param maxValue the wheel max value
	 */
	public NumericMinuteWheelAdapter(int minValue, int maxValue, int interval) {
		if (interval != 0) {
			hasInterval = true;
			for (int i = minValue; i <= maxValue; i++) {
				if (i % interval == 0) {
					mDatas.add(i);
				}
			}
		}else{
			hasInterval = false;
			this.minValue = minValue;
			this.maxValue = maxValue;
		}
	}

	@Override
	public Object getItem(int index) {
		if (index >= 0 && index < getItemsCount()) {
			if (hasInterval){
				return mDatas.get(index);
			}else {
				return minValue + index;
			}
		}
		return 0;
	}

	@Override
	public int getItemsCount() {
		return hasInterval?mDatas.size():maxValue - minValue + 1;
	}
	
	@Override
	public int indexOf(Object o){
		try {
			if (hasInterval) {
				return mDatas.indexOf(o);
			}else{
				return (int)o - minValue;
			}
		} catch (Exception e) {
			return -1;
		}

	}
}
