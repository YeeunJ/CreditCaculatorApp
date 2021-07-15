package com.example.creditcaculatorapp.model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CreditViewModel extends ViewModel {
    private MutableLiveData<List<CreditInfo>> creditData;

    public CreditViewModel() {

        ResourceProvider resourceProvider = new ResourceProvider();

        creditData = new MutableLiveData<>();
        //creditData.setValue("This is dashboard fragment");
        //List<CreditInfo> creditList = new ArrayList<CreditInfo>();
        /*creditList.add(new CreditInfo("운영체제", (float)3.0, (float)4.5));
        creditList.add(new CreditInfo("선형대수학", (float)3.0, (float)3.5));
        creditList.add(new CreditInfo("캡스톤 디자인", (float)4.0, (float)4.5));
        creditList.add(new CreditInfo("철학개론", (float)3.0, (float)4.0));
        creditList.add(new CreditInfo("데이터베이스", (float)3.0, (float)3.0));*/
        creditData.setValue(resourceProvider.getData());
    }

    public LiveData<List<CreditInfo>> getCreditData() {
        return creditData;
    }
}
