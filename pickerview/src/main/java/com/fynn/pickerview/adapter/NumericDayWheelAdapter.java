package com.fynn.pickerview.adapter;

import com.fynn.adapter.WheelAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NumericDayWheelAdapter implements WheelAdapter {

    private List<Integer> data = new ArrayList<>();
    private boolean mType = false;
    private int minValue, maxValue;

    public NumericDayWheelAdapter(int minValue, int maxValue, int year, int month, List<Integer> type) {
        if (type != null && type.size() != 0) {
            mType = true;
            for (int i = minValue; i <= maxValue; i++) {
                int weekDay = getWeek(year + "-" + month + "-" + i);
                if (!type.contains(weekDay)) {
                    data.add(i);
                }

            }
        }else{
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

    @Override
    public int getItemsCount() {
        return !mType? maxValue - minValue + 1: data.size();
    }

    @Override
    public Object getItem(int index) {
        if (mType){
            return data.get(index);
        }else{
            if (index >= 0 && index < getItemsCount()){

                return minValue+index;
            }
        }
        return 0;
    }

    @Override
    public int indexOf(Object o) {
        if (mType){
            return data.indexOf((int)o);
        }else{
            try {
                return (int)o - minValue;
            } catch (Exception e) {
                return -1;
            }
        }
    }

    /**
     * 判断当前日期是星期几
     * @param  pTime     设置的需要判断的时间  //格式如2021-04-20
     * @return dayForWeek 判断结果
     */
    public static int getWeek(String pTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }
}
