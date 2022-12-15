package com.example.quizappv1.Adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizappv1.Interface.RecycleViewItemListener;
import com.example.quizappv1.R;
import com.example.quizappv1.model.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.myViewHolder> {
    List<Category> categories;
    RecycleViewItemListener listener;


    public CategoriesAdapter( RecycleViewItemListener listener) {

        categories = new ArrayList<>();

        this.listener = listener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.categoryText.setText(categories.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
      return categories.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView categoryImage;
        TextView categoryText;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            categoryText = view.findViewById(R.id.category_item_text);
            itemView.setOnClickListener(view -> listener.onClick(getAdapterPosition()));

        }

    }

    public void addCategories( List<Category> categories){
        this.categories.addAll(categories);
        notifyDataSetChanged();
    }



}
