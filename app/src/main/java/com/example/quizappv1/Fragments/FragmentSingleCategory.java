package com.example.quizappv1.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.quizappv1.Activities.MainActivity;
import com.example.quizappv1.Adapters.CategoryQuizzesAdapter;
import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.Interface.RecycleViewItemListener;
import com.example.quizappv1.R;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.Utilities.PreferenceManager;
import com.example.quizappv1.data.services.QuestionService;
import com.example.quizappv1.data.services.QuizService;
import com.example.quizappv1.data.services.UserHasQuizService;
import com.example.quizappv1.databinding.SingleCategoryLayoutBinding;
import com.example.quizappv1.model.Quiz;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FragmentSingleCategory extends Fragment implements RecycleViewItemListener, NetworkRequestListener {

    SingleCategoryLayoutBinding binding;
    CategoryQuizzesAdapter adapter;
    List<Quiz> quizzes;
    boolean examinedBefore = false;
    PreferenceManager manager;
    int position;


    public FragmentSingleCategory() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        quizzes = new ArrayList<>();
        adapter = new CategoryQuizzesAdapter(this);
        QuizService.listener = this;
        UserHasQuizService.listener = this;
        manager = new PreferenceManager(requireActivity());

        assert getArguments() != null;
        long categoryID = getArguments().getLong(Constants.CATEGORY_KEY);
        quizzes = QuizService.getActiveQuizzesOfCategory((int) categoryID);
        binding = SingleCategoryLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.quizzesListSingleCategory.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.quizzesListSingleCategory.setAdapter(adapter);
        binding.backButton.setOnClickListener(v -> backToMainPage());
    }


    private void backToMainPage() {
        Intent intent = new Intent(requireActivity(), MainActivity.class);
        requireActivity().startActivity(intent);

    }

    @Override
    public void onClick(int position) {

        examinedBefore = UserHasQuizService
                .checkUserExamBefore(Integer.parseInt(manager.getString(Constants.CURRENT_USER_ID))
                        , (int) quizzes.get(position).getId());
        this.position = position;
    }

    @Override
    public void onFinish(String key) {
        switch (key) {
            case Constants.GET_ACTIVE_QUIZZES_OF_CATEGORY:
                adapter.addQuizzes(quizzes);
                break;
            case Constants.CHECK_USER_EXAM_BEFORE:
                // Toast.makeText(requireActivity(), "You took this exam before ", Toast.LENGTH_SHORT).show();
                showConfirmDialuoge();
                break;
            case Constants.DIDNT_TAKE_QUIZ_BEFORE:
                startExamDialouge();
                break;
        }
        binding.progressBar3.setVisibility(View.GONE);
    }

    private void showConfirmDialuoge() {
        new SweetAlertDialog(requireActivity(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("You took this exam before")
                .setConfirmText("Show Answer!")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    showExamAnswer();
                })
                .setCancelButton("Cancel", SweetAlertDialog::dismissWithAnimation)
                .show();
    }

    private void startExamDialouge() {
        new SweetAlertDialog(requireActivity(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("You want to start the exam")
                .setConfirmText("Start!")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    FragmentQuizQuestion quizPage = new FragmentQuizQuestion();
                    Bundle bundle = new Bundle();
                    bundle.putLong(Constants.QUIZ_KEY, quizzes.get(position).getId());
                    quizPage.setArguments(bundle);
                    requireActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragments_container, quizPage)
                            .commit();
                })
                .setCancelButton("Cancel", SweetAlertDialog::dismissWithAnimation)
                .show();
    }

    private void showExamAnswer() {
        int quizID = (int) quizzes.get(position).getId();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.QUIZ_ID, quizID);
        FragmentShowAnswer answer = new FragmentShowAnswer();
        answer.setArguments(bundle);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragments_container, answer)
                .commit();
    }


}
