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
import com.example.quizappv1.Adapters.HistoryQuizzesAdapter;
import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.Interface.RecycleViewItemListener;
import com.example.quizappv1.R;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.Utilities.PreferenceManager;
import com.example.quizappv1.data.client.ClientApi;
import com.example.quizappv1.data.services.UserHasQuizService;
import com.example.quizappv1.databinding.QuizzesHistoryLayoutBinding;
import com.example.quizappv1.model.UserHasQuiz;

import java.util.List;

public class FragmentQuizzesHistory extends Fragment implements NetworkRequestListener, RecycleViewItemListener {

    QuizzesHistoryLayoutBinding binding;
    HistoryQuizzesAdapter adapter;
    List<UserHasQuiz> historyItems;
    PreferenceManager manager;

    public FragmentQuizzesHistory() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = QuizzesHistoryLayoutBinding.inflate(inflater, container, false);

        UserHasQuizService.listener = this;
        manager = new PreferenceManager(requireActivity());
        ClientApi.openPreference(requireActivity());
        historyItems = UserHasQuizService.
                getUserHasQuizzesByUserId
                        (Integer.parseInt(manager.getString(Constants.CURRENT_USER_ID)));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        adapter = new HistoryQuizzesAdapter(this);
        binding.quizzesHistoryRecycleView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.quizzesHistoryRecycleView.setAdapter(adapter);
        binding.backButtonQuizzesHistory.setOnClickListener(view1 -> backToMainPage());

    }

    private void backToMainPage() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        requireActivity().startActivity(intent);
    }

    @Override
    public void onFinish(String key) {
        if (key.equals(Constants.GET_USER_HAS_QUIZZES_BY_USER_ID)) {
            if (!historyItems.isEmpty()) {
                adapter.addALL(historyItems);
                binding.progressBar4.setVisibility(View.GONE);
            } else {
                Toast.makeText(requireActivity(), "No history for current user is found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireActivity(), "Please check your connection and try again", Toast.LENGTH_SHORT).show();
        }
        binding.progressBar4.setVisibility(View.GONE);
    }

    @Override
    public void onClick(int position) {
        int quizId = (int) historyItems.get(position).getQuizId();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.QUIZ_ID, quizId);
        FragmentShowAnswer question = new FragmentShowAnswer();
        question.setArguments(bundle);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragments_container, question)
                .commit();
    }


}
