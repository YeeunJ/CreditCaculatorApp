package com.example.creditcaculatorapp.ui.notifications;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.creditcaculatorapp.R;
import com.example.creditcaculatorapp.model.CreditInfo;
import com.example.creditcaculatorapp.model.CreditViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private CreditViewModel creditViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        creditViewModel =
                ViewModelProviders.of(this).get(CreditViewModel.class);

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);

        final BarChart chart = (BarChart) root.findViewById(R.id.chart);

        BarData data = new BarData(getDataSet(creditViewModel.getCreditData().getValue()));
        chart.setData(data);
        Description description = new Description();
        description.setText("My Chart");
        chart.setDescription(description);
        chart.animateXY(2000, 2000);
        chart.invalidate();

        creditViewModel.getCreditData().observe(getViewLifecycleOwner(), new Observer<List<CreditInfo>>() {
            @Override
            public void onChanged(List<CreditInfo> creditInfos) {
                textView.setText(creditInfos.size() + " 개의 데이터가 있습니다.\n\n학기별 평균 평점 차트");

                ArrayList dataSets = new ArrayList();;
                ArrayList valueSet1 = new ArrayList();
                float[] totalCredit = new float[8];
                float[] totalGrade = new float[8];

                for (CreditInfo creditInfo : creditInfos) {
                    totalCredit[creditInfo.getSemester() - 1] += creditInfo.getCredit();
                    totalGrade[creditInfo.getSemester() - 1] += creditInfo.getCredit()*creditInfo.getGrade();
                }
                for(int i=1; i<= 8; i++){
                    if(totalGrade[i-1] != 0.0f)
                        valueSet1.add(new BarEntry(i, totalGrade[i-1]/totalCredit[i-1]));
                    else
                        valueSet1.add(new BarEntry(i, 0.0f));
                }
                BarDataSet barDataSet1 = new BarDataSet(valueSet1, "학기");
                barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

                dataSets.add(barDataSet1);

                BarData data = new BarData(dataSets);
                chart.setData(data);
//                Description description = new Description();
//                description.setText("My Chart");
//                chart.setDescription(description);
//                chart.animateXY(2000, 2000);
                chart.invalidate();
            }
        });
        return root;
    }
    private ArrayList getDataSet(List<CreditInfo> creditInfos) {
        ArrayList dataSets = new ArrayList();;
        ArrayList valueSet1 = new ArrayList();
        float[] totalCredit = new float[8];
        float[] totalGrade = new float[8];
        System.out.println(creditInfos.size());
        for (CreditInfo creditInfo : creditInfos) {
            totalCredit[creditInfo.getSemester() - 1] += creditInfo.getCredit();
            totalGrade[creditInfo.getSemester() - 1] += creditInfo.getGrade();
        }
        for(int i=1; i<= 8; i++){
            valueSet1.add(new BarEntry(i, 0.0f));
        }
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "학기");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList getXAxisValues() {
        ArrayList xAxis = new ArrayList();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        return xAxis;
    }
}

