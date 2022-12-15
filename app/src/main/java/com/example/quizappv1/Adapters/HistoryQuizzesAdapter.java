package com.example.quizappv1.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizappv1.Interface.RecycleViewItemListener;
import com.example.quizappv1.Utilities.Date;
import com.example.quizappv1.databinding.QuizItemHistoryBinding;
import com.example.quizappv1.model.UserHasQuiz;

import java.util.ArrayList;
import java.util.List;

public class HistoryQuizzesAdapter extends RecyclerView.Adapter<HistoryQuizzesAdapter.myViewHolder> {


    List<UserHasQuiz> historyItems;
    RecycleViewItemListener listener;


    public HistoryQuizzesAdapter(RecycleViewItemListener listener) {
        this.listener = listener;
        this.historyItems = new ArrayList<>();
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new myViewHolder(QuizItemHistoryBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {


        UserHasQuiz item = historyItems.get(position);

        holder.binding.quizNameHistory.setText(item.getQuizTitle());
        holder.binding.quizScoreHistory.setText("Score: " + String.valueOf(item.getDegree()) + "/" + item.getQuizMark());
        if (item.getDataCreate() != null) {
            holder.binding.quizTimeTakenHistory.setText(Date.getDate(item.getLastUpdate().getTime()));
        }
    }

    @Override
    public int getItemCount() {
        if (historyItems == null) return 0;
        else return historyItems.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        QuizItemHistoryBinding binding;

        public myViewHolder(@NonNull QuizItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> listener.onClick(getAdapterPosition()));
            binding.quizStartAgainHistory.setOnClickListener(v -> listener.onClick(getAdapterPosition()));
        }


    }


    public void addALL(List<UserHasQuiz> quizs) {
        historyItems.addAll(quizs);
        notifyDataSetChanged();
    }


}
