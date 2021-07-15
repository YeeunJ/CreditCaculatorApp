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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.creditcaculatorapp.R;
import com.example.creditcaculatorapp.model.CreditInfo;
import com.example.creditcaculatorapp.model.CreditViewModel;

import java.util.LinkedList;
import java.util.List;

public class HomeFragment extends Fragment {

    private CreditViewModel creditViewModel;
    private RecyclerView mRecyclerView;
    private CreditAdapter mAdapter;
    private final LinkedList<CreditInfo> creditList = new LinkedList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        creditViewModel =
                ViewModelProviders.of(this).get(CreditViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = root.findViewById(R.id.recyclerview);
        mAdapter = new CreditAdapter(root.getContext(), creditViewModel.getCreditData().getValue());
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        //final TextView textView = root.findViewById(R.id.text_home);
        creditViewModel.getCreditData().observe(getViewLifecycleOwner(), new Observer<List<CreditInfo>>() {
            @Override
            public void onChanged(List<CreditInfo> creditInfos) {
                int wordListSize = creditInfos.size();
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(wordListSize);
                /*String text = "";
                for(CreditInfo creditInfo : creditInfos){
                    text += "[과목명: "+creditInfo.getSubject()+", 학점 : "+creditInfo.getCredit()+", 평점: "+creditInfo.getGrade()+", 수강 학기: "+creditInfo.getSemester()+"]\n\n";
                }*/
                //textView.setText(text+creditInfos.size()+" 개의 데이터가 있습니다.");
            }
        });
        return root;
    }
}
