package com.example.quizappv1.Fragments;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.R;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.Utilities.PreferenceManager;
import com.example.quizappv1.data.client.ClientApi;
import com.example.quizappv1.data.services.QuestionService;
import com.example.quizappv1.data.services.UserHasQuizService;
import com.example.quizappv1.databinding.QuestionLayoutBinding;
import com.example.quizappv1.model.Answer;
import com.example.quizappv1.model.QuestionAdmin;

import java.util.ArrayList;
import java.util.List;

public class FragmentShowAnswer extends Fragment implements NetworkRequestListener {

    QuestionLayoutBinding binding;
    List<Answer> answers;
    List<QuestionAdmin> questions;
    PreferenceManager manager;
    int quizID = 0;
    int questionNum;
    Answer answer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = QuestionLayoutBinding.inflate(inflater, container, false);
        answers = new ArrayList<>();
        manager = new PreferenceManager(requireActivity());
        ClientApi.openPreference(requireActivity());
        UserHasQuizService.listener = this;
        QuestionService.listener = this;
        Log.e("TAG", "onCreateView: opened");

        if (getArguments() != null) {
            quizID = getArguments().getInt(Constants.QUIZ_ID);
        }

        answers = UserHasQuizService
                .getUserHasQuizzesByUserIdAndQuizIdAnswer
                        (Integer.parseInt(manager.getString(Constants.CURRENT_USER_ID))
                                , quizID);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.progressInSeconds.setVisibility(View.INVISIBLE);
        binding.backToHistory.setVisibility(View.VISIBLE);
        setListener();
    }


    private void setListener() {
        binding.buttonNext.setOnClickListener(v -> {
            if (questionNum < answers.size()) {

                questionNum++;
                showQuestionAnswer(questionNum);
                if (questionNum == answers.size() - 1) binding.buttonNext.setText("Finish");
            } else {

                backToFragment();
            }
        });
        binding.buttonCancel.setOnClickListener(v -> {
            if (questionNum > 0) {
                questionNum--;
                showQuestionAnswer(questionNum);
            } else {
                backToFragment();
            }
        });

        binding.backToHistory.setOnClickListener(v -> {
            backToFragment();
        });

    }

    private void showQuestionAnswer(int questionNum) {

        undoSelection();
        if (questionNum < answers.size()) {
            answer = answers.get(questionNum);
            binding.questionNum.setText("Answer num " + (questionNum + 1));
            binding.questionText.setText(Html.fromHtml(answer.getContent()));
            binding.choiceAText.setText(answer.getOption1());
            binding.choiceBText.setText(answer.getOption2());
            binding.choiceCText.setText(answer.getOption3());
            binding.choiceDText.setText(answer.getOption4());
        }
        String givenAnswer = answer.getGivenAnswer();
        // problem
        String rightAnswer = answer.getAnswer();
        if (givenAnswer == null) {
            givenAnswer = "";
        }

        if (givenAnswer.equals(rightAnswer)) {
            markRightAnswer(rightAnswer, true);
        } else {
            markWrongAnswer(givenAnswer);
            markRightAnswer(rightAnswer, false);
        }

    }


    private void markWrongAnswer(String wrongAnswer) {

        if (binding.choiceAText.getText().equals(wrongAnswer)) {
            binding.choiceAText.setBackground(getResources().getDrawable(R.drawable.wrong_answer_bg));
            binding.choiceAButton.setBackground(getResources().getDrawable(R.drawable.chosen_choice_bg));
        } else if (binding.choiceBText.getText().equals(wrongAnswer)) {
            binding.choiceBText.setBackground(getResources().getDrawable(R.drawable.wrong_answer_bg));
            binding.choiceBButton.setBackground(getResources().getDrawable(R.drawable.chosen_choice_bg));
        } else if (binding.choiceCText.getText().equals(wrongAnswer)) {
            binding.choiceCText.setBackground(getResources().getDrawable(R.drawable.wrong_answer_bg));
            binding.choiceCButton.setBackground(getResources().getDrawable(R.drawable.chosen_choice_bg));
        } else if (binding.choiceDText.getText().equals(wrongAnswer)) {
            binding.choiceDText.setBackground(getResources().getDrawable(R.drawable.wrong_answer_bg));
            binding.choiceDButton.setBackground(getResources().getDrawable(R.drawable.chosen_choice_bg));
        }

    }


    private void markRightAnswer(String rightAnswer, boolean equal) {
        if (binding.choiceAText.getText().equals(rightAnswer)) {
            binding.choiceAText.setBackground(getResources().getDrawable(R.drawable.right_answer_bg));
            if (equal)
                binding.choiceAButton.setBackground(getResources().getDrawable(R.drawable.chosen_choice_bg));
        } else if (binding.choiceBText.getText().equals(rightAnswer)) {
            binding.choiceBText.setBackground(getResources().getDrawable(R.drawable.right_answer_bg));
            if (equal)
                binding.choiceBButton.setBackground(getResources().getDrawable(R.drawable.chosen_choice_bg));
        } else if (binding.choiceCText.getText().equals(rightAnswer)) {
            binding.choiceCText.setBackground(getResources().getDrawable(R.drawable.right_answer_bg));
            if (equal)
                binding.choiceCButton.setBackground(getResources().getDrawable(R.drawable.chosen_choice_bg));
        } else if (binding.choiceDText.getText().equals(rightAnswer)) {
            binding.choiceDText.setBackground(getResources().getDrawable(R.drawable.right_answer_bg));
            if (equal)
                binding.choiceDButton.setBackground(getResources().getDrawable(R.drawable.chosen_choice_bg));
        }
    }


    private void undoSelection() {
        binding.choiceAText.setBackground(getResources().getDrawable(R.drawable.answer_background));
        binding.choiceBText.setBackground(getResources().getDrawable(R.drawable.answer_background));
        binding.choiceCText.setBackground(getResources().getDrawable(R.drawable.answer_background));
        binding.choiceDText.setBackground(getResources().getDrawable(R.drawable.answer_background));
        binding.choiceAButton.setBackground(getResources().getDrawable(R.drawable.choice_background));
        binding.choiceBButton.setBackground(getResources().getDrawable(R.drawable.choice_background));
        binding.choiceCButton.setBackground(getResources().getDrawable(R.drawable.choice_background));
        binding.choiceDButton.setBackground(getResources().getDrawable(R.drawable.choice_background));

    }


    @Override
    public void onFinish(String key) {
        Log.e("TAG", "onFinish: " + key);
        if (key.equals(Constants.GET_USER_HAS_QUIZZES_BY_USER_ID_AND_QUIZ_ID_ANSWER)) {
            if (answers.size() > 0) {
                showQuestionAnswer(questionNum);
                binding.questionsContainer.setVisibility(View.VISIBLE);
            } else {
                questions = QuestionService.getQuestionsOfQuizAdmin(quizID);

            }

        } else if (key.equals(Constants.GET_QUESTIONS_OF_QUIZ_ADMIN)) {
            for (QuestionAdmin data : questions) {
                Answer answer = new Answer();
                answer.setContent(data.getContent());
                answer.setOption1(data.getOption1());
                answer.setOption2(data.getOption2());
                answer.setOption3(data.getOption3());
                answer.setOption4(data.getOption4());
                answer.setAnswer(data.getAnswer());
                answers.add(answer);
            }
            binding.questionsContainer.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
            showQuestionAnswer(questionNum);
        }
    }


    private void backToFragment() {

        FragmentQuizzesHistory fragmentQuizzesHistory = new FragmentQuizzesHistory();
        requireActivity().getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragments_container, fragmentQuizzesHistory).
                commit();

    }


}
