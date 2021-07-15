package com.example.creditcaculatorapp.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.creditcaculatorapp.R;
import com.example.creditcaculatorapp.model.CreditInfo;

import java.util.List;

class CreditAdapter extends
        RecyclerView.Adapter<CreditAdapter.WordViewHolder>  {
    private final List<CreditInfo> creditList;
    private LayoutInflater mInflater;

    public CreditAdapter(Context context, List<CreditInfo> creditList) {
        mInflater = LayoutInflater.from(context);
        this.creditList = creditList;
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public final TextView subjectView, creditView, gradeView, semesterView;
        final CreditAdapter mAdapter;

        public WordViewHolder(View itemView, CreditAdapter adapter) {
            super(itemView);
            subjectView = itemView.findViewById(R.id.subject);
            creditView = itemView.findViewById(R.id.credit);
            gradeView = itemView.findViewById(R.id.grade);
            semesterView = itemView.findViewById(R.id.semester);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            // Use that to access the affected item in mWordList.
            CreditInfo element = creditList.get(mPosition);
            // Change the word in the mWordList.
            //creditList.set(mPosition, "Clicked! " + creditList.toString());
            System.out.println(element.toString());
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View mItemView = mInflater.inflate(R.layout.cardlist_item,
                parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        String subject =  creditList.get(position).getSubject();
        Float credit =  creditList.get(position).getCredit();
        Float grade =  creditList.get(position).getGrade();
        int semester = creditList.get(position).getSemester();
        holder.subjectView.setText(subject);
        holder.creditView.setText(credit.toString());
        holder.gradeView.setText(grade.toString());
        holder.semesterView.setText(semester+" 학기");
        System.out.println(position+credit.toString()+"____test");
    }

    @Override
    public int getItemCount() {
        return creditList.size();
    }
}
