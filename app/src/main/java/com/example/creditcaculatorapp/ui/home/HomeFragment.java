package com.example.creditcaculatorapp.ui.home;

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

import java.util.List;

public class HomeFragment extends Fragment {

    private CreditViewModel creditViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        creditViewModel =
                ViewModelProviders.of(this).get(CreditViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        creditViewModel.getCreditData().observe(getViewLifecycleOwner(), new Observer<List<CreditInfo>>() {
            @Override
            public void onChanged(List<CreditInfo> creditInfos) {
                String text = "";
                for(CreditInfo creditInfo : creditInfos){
                    text += "[과목명: "+creditInfo.getSubject()+", 학점 : "+creditInfo.getCredit()+", 평점: "+creditInfo.getGrade()+", 수강 학기: "+creditInfo.getSemester()+"]\n\n";
                }
                textView.setText(text+creditInfos.size()+" 개의 데이터가 있습니다.");
            }
        });
        return root;
    }
}
