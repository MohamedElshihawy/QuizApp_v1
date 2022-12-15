package com.example.quizappv1.Fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quizappv1.Activities.MainActivity;
import com.example.quizappv1.Adapters.CategoriesAdapter;
import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.Interface.RecycleViewItemListener;
import com.example.quizappv1.R;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.data.client.ClientApi;
import com.example.quizappv1.data.services.CategoryService;
import com.example.quizappv1.databinding.CategoriesListLayoutBinding;
import com.example.quizappv1.model.Category;


import java.util.ArrayList;
import java.util.List;


public class FragmentCategoriesList extends Fragment implements RecycleViewItemListener, NetworkRequestListener {

    CategoriesListLayoutBinding binding;
    CategoriesAdapter adapter;
    List<Category> categories;

    public FragmentCategoriesList() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CategoryService.listener = this;
        categories = CategoryService.getCategories();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = CategoriesListLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        adapter = new CategoriesAdapter( this);

        binding.categoriesList.setVisibility(View.VISIBLE);

        binding.categoriesList.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        binding.categoriesList.setAdapter(adapter);

        binding.backButtonCategoriesList.setOnClickListener(v -> backToMainPage());
    }

    private void backToMainPage() {
        Intent intent = new Intent(requireActivity(), MainActivity.class);
        requireActivity().startActivity(intent);
    }

    @Override
    public void onClick(int position) {
        FragmentSingleCategory category = new FragmentSingleCategory();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.CATEGORY_KEY, categories.get(0).getId());
        category.setArguments(bundle);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragments_container, category)
                .commit();
    }

    @Override
    public void onFinish(String key) {

        if (key.equals(Constants.GET_ALL_CATEGORIES)) {
            adapter.addCategories(categories);
        } else {
            Toast.makeText(getActivity(), "Please check your connection and try again", Toast.LENGTH_SHORT).show();
        }
        binding.progressBar2.setVisibility(View.GONE);
    }
}