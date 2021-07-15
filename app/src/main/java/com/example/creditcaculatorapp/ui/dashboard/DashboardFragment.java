package com.example.creditcaculatorapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.creditcaculatorapp.R;
import com.example.creditcaculatorapp.model.CreditInfo;
import com.example.creditcaculatorapp.model.CreditViewModel;
import com.example.creditcaculatorapp.model.ResourceProvider;

import java.util.List;

public class DashboardFragment extends Fragment {

    private CreditViewModel creditViewModel;
    private EditText subject, credit, grade, semester;
    private Button addButton;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        creditViewModel =
                ViewModelProviders.of(this).get(CreditViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_addsubject, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        subject = root.findViewById(R.id.subjectContent);
        credit = root.findViewById(R.id.creditContent);
        grade = root.findViewById(R.id.gradeContent);
        semester = root.findViewById(R.id.semesterContent);
        addButton = root.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                List<CreditInfo> creditList = creditViewModel.getCreditData().getValue();
                creditList.add(new CreditInfo(subject.getText().toString(), Float.parseFloat(credit.getText().toString()), Float.parseFloat(grade.getText().toString()), Integer.parseInt(semester.getText().toString())));
                creditViewModel.getCreditData().setValue(creditList);
                Toast.makeText(root.getContext(),"데이터 추가가 완료되었습니다.", Toast.LENGTH_LONG).show();
                subject.setText("");
                credit.setText("");
                grade.setText("");
                semester.setText("");
            }
        });

        creditViewModel.getCreditData().observe(getViewLifecycleOwner(), new Observer<List<CreditInfo>>() {
            @Override
            public void onChanged(List<CreditInfo> creditInfos) {
                textView.setText(creditInfos.size()+" 개의 데이터가 있습니다.");
            }
        });
        return root;
    }
}
