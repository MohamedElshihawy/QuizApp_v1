package com.example.quizappv1.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizappv1.Interface.RecycleViewItemListener;
import com.example.quizappv1.R;
import com.example.quizappv1.databinding.QuizItemCategoryBinding;
import com.example.quizappv1.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class CategoryQuizzesAdapter extends RecyclerView.Adapter<CategoryQuizzesAdapter.myViewHolder> {

    private List<Quiz> quizzes;
    RecycleViewItemListener listener;

    public CategoryQuizzesAdapter( RecycleViewItemListener listener) {
        this.quizzes = new ArrayList<>();
        this.listener = listener;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(QuizItemCategoryBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        Quiz quiz = quizzes.get(position);
        holder.binding.quizNameSingleCategory.setText(quiz.getTitle());
        holder.binding.numOfDegreesSingleCategory.setText(quiz.getMark() + " Max Marks");
        holder.binding.quizDescSingleCategory.setText(quiz.getDescription());
        holder.binding.quizTime.setText(quiz.getTime() + " Min");

        if (!quiz.isActive()) {
            holder.binding.container.setBackgroundResource(R.color.grey);
        }
    }




    @Override
    public int getItemCount() {
        if (quizzes == null) return 0;
        else return quizzes.size();
    }

public class myViewHolder extends RecyclerView.ViewHolder {

    QuizItemCategoryBinding binding;

    public myViewHolder(@NonNull QuizItemCategoryBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        binding.container.setOnClickListener(v->listener.onClick(getAdapterPosition()));
    }

}

    public void addQuizzes(List<Quiz> quizzes) {
        this.quizzes.addAll(quizzes);
        notifyDataSetChanged();
    }


}
