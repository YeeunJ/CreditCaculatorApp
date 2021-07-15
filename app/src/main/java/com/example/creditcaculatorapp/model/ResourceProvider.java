package com.example.creditcaculatorapp.model;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class ResourceProvider {

    private Context mContext;
    private static List<CreditInfo> creditList;
    public ResourceProvider(Context mContext) {
        this.mContext = mContext;
    }

    public ResourceProvider() { }

    public void setData(int subjectId, int creditId, int gradeId, int semesterId) {
        String[] subjects = mContext.getResources().getStringArray(subjectId);
        //Float[] creditList = getResources().obtainTypedArray(R.array.credit).;

        creditList = new ArrayList<CreditInfo>();
        TypedArray credits =
                mContext.getResources().obtainTypedArray(creditId);
        TypedArray grades =
                mContext.getResources().obtainTypedArray(gradeId);

        TypedArray semesters =
                mContext.getResources().obtainTypedArray(semesterId);
        // Create the ArrayList of Sports objects with titles and
        // information about each sport.

        for (int i = 0; i < subjects.length; i++) {
            creditList.add(new CreditInfo(subjects[i], credits.getFloat(i, (float) 0.0),
                    grades.getFloat(i, (float) 0.0), semesters.getInt(i, 0)));
        }

    }

    public List<CreditInfo> getData() {
        return creditList;
    }
}