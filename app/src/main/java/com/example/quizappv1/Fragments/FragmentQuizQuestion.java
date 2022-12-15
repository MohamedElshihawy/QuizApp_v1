package com.example.quizappv1.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quizappv1.Activities.MainActivity;
import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.R;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.Utilities.Date;
import com.example.quizappv1.Utilities.PreferenceManager;
import com.example.quizappv1.data.services.AnswerService;
import com.example.quizappv1.data.services.QuestionService;
import com.example.quizappv1.data.services.UserHasQuizService;
import com.example.quizappv1.databinding.QuestionLayoutBinding;
import com.example.quizappv1.model.Answer;
import com.example.quizappv1.model.QuestionUser;
import com.example.quizappv1.model.Result;
import com.example.quizappv1.model.UserHasQuiz;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FragmentQuizQuestion extends Fragment implements NetworkRequestListener {
    QuestionLayoutBinding binding;
    ArrayList<QuestionUser> questionList;

    int questionNum = 0;
    int oldChoice = -1;
    int newChoice = -1;
    CountDownTimer timer;
    QuestionUser question;
    List<QuestionUser> questionUserList;
    Result result;
    UserHasQuiz userHasQuiz;
    PreferenceManager manager;
    long quizID;

    public FragmentQuizQuestion() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = QuestionLayoutBinding.inflate(inflater, container, false);
        questionList = new ArrayList<>();
        QuestionService.listener = this;
        UserHasQuizService.listener = this;
        AnswerService.listener = this;
        manager = new PreferenceManager(requireActivity());
        userHasQuiz = new UserHasQuiz();


        if (getArguments() != null) {
            quizID = getArguments().getLong(Constants.QUIZZES_KEY);
            userHasQuiz.setQuizId(quizID);
            userHasQuiz.setUserId(Long.parseLong(manager.getString(Constants.CURRENT_USER_ID)));
            userHasQuiz.setDegree(0);
            userHasQuiz.setTookExam(true);
            userHasQuiz = UserHasQuizService.addUserHasQuiz(userHasQuiz);
            questionUserList = QuestionService.getQuestionOfQuizForTest((int) quizID);
        }
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.progressBar.setMax(600);

        updateTimeAndProgress();
        binding.questionText.setMovementMethod(new ScrollingMovementMethod());
        setListeners();
    }

    private void updateQuestion(int questionNum) {

        if (questionNum < questionList.size()) {
            question = questionList.get(questionNum);
            undoChoice();
            newChoice = oldChoice = -1;
            binding.questionNum.setText("Question num " + (questionNum + 1));
            binding.questionText.setText(Html.fromHtml(question.getContent()));
            binding.choiceAText.setText(question.getOption1());
            binding.choiceBText.setText(question.getOption2());
            binding.choiceCText.setText(question.getOption3());
            binding.choiceDText.setText(question.getOption4());
        }
    }

    private void updateTimeAndProgress() {

        timer = new CountDownTimer(600000, 1000) {
            @Override
            public void onTick(long l) {
                int timeRemaining = (int) (l / 1000);
                binding.progressBar.setProgress(timeRemaining);
                binding.progressInSeconds.setText(Date.formatTime(timeRemaining));
            }

            @Override
            public void onFinish() {
                endExamAndGetResults();
            }
        }.start();
    }

    private void setListeners() {
        binding.choiceAText.setOnClickListener(view -> choseAnswer(binding.choiceAButton.getText().toString()));
        binding.choiceAButton.setOnClickListener(view -> choseAnswer(binding.choiceAButton.getText().toString()));
        binding.choiceBText.setOnClickListener(view -> choseAnswer(binding.choiceBButton.getText().toString()));
        binding.choiceBButton.setOnClickListener(view -> choseAnswer(binding.choiceBButton.getText().toString()));
        binding.choiceCText.setOnClickListener(view -> choseAnswer(binding.choiceCButton.getText().toString()));
        binding.choiceCButton.setOnClickListener(view -> choseAnswer(binding.choiceCButton.getText().toString()));
        binding.choiceDText.setOnClickListener(view -> choseAnswer(binding.choiceDButton.getText().toString()));
        binding.choiceDButton.setOnClickListener(view -> choseAnswer(binding.choiceDButton.getText().toString()));
        binding.quizSubmissionScreen.button.setOnClickListener(v -> backToMainPage());
        binding.buttonNext.setOnClickListener(view -> {
            Log.e("TAG", "setListeners: "+questionNum );
            if (questionNum < questionList.size()) {
                questionNum++;
                updateQuestion(questionNum);
                if (questionNum == questionList.size() - 1) {
                    binding.buttonNext.setText("Submit");
                }
            } else {
                showConfirmDialuoge();
            }

        });
        binding.buttonCancel.setOnClickListener(view -> previousQuestion());

    }


    private void choseAnswer(String choice) {
        switch (choice) {
            case "A":
                binding.choiceAButton.setTextColor(Color.WHITE);
                binding.choiceAButton.setBackground(getResources().getDrawable(R.drawable.chosen_choice_bg));
                newChoice = 0;
                questionList.get(questionNum).setGivenAnswer(binding.choiceAText.getText().toString());
                break;
            case "B":
                binding.choiceBButton.setTextColor(Color.WHITE);
                binding.choiceBButton.setBackground(getResources().getDrawable(R.drawable.chosen_choice_bg));
                newChoice = 1;
                questionList.get(questionNum).setGivenAnswer(binding.choiceBText.getText().toString());
                break;
            case "C":
                binding.choiceCButton.setTextColor(Color.WHITE);
                binding.choiceCButton.setBackground(getResources().getDrawable(R.drawable.chosen_choice_bg));
                newChoice = 2;
                questionList.get(questionNum).setGivenAnswer(binding.choiceCText.getText().toString());
                break;
            case "D":
                binding.choiceDButton.setTextColor(Color.WHITE);
                binding.choiceDButton.setBackground(getResources().getDrawable(R.drawable.chosen_choice_bg));
                newChoice = 3;
                questionList.get(questionNum).setGivenAnswer(binding.choiceDText.getText().toString());
                break;
        }

        undoChoice();
    }

    private void undoChoice() {
        if (oldChoice != -1) {
            switch (oldChoice) {
                case 0:
                    binding.choiceAButton.setTextColor(getResources().getColor(R.color.dark_purple));
                    binding.choiceAButton.setBackground(getResources().getDrawable(R.drawable.choice_background));
                    break;
                case 1:
                    binding.choiceBButton.setTextColor(getResources().getColor(R.color.dark_purple));
                    binding.choiceBButton.setBackground(getResources().getDrawable(R.drawable.choice_background));
                    break;
                case 2:
                    binding.choiceCButton.setTextColor(getResources().getColor(R.color.dark_purple));
                    binding.choiceCButton.setBackground(getResources().getDrawable(R.drawable.choice_background));
                    break;
                case 3:
                    binding.choiceDButton.setTextColor(getResources().getColor(R.color.dark_purple));
                    binding.choiceDButton.setBackground(getResources().getDrawable(R.drawable.choice_background));
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + oldChoice);
            }
        }
        oldChoice = newChoice;

    }


    private void backToMainPage() {
        Intent intent = new Intent(requireActivity(), MainActivity.class);
        requireActivity().startActivity(intent);
    }

    private void previousQuestion() {
        if (questionNum > 0) {
            questionNum--;
            updateQuestion(questionNum);
        }

    }

    @Override
    public void onFinish(String key) {
        switch (key) {
            case Constants.GET_QUESTION_OF_QUIZ_FOR_TEST:
                questionList.addAll(questionUserList);
                updateQuestion(questionNum);
                binding.clicksMask.setVisibility(View.GONE);
                binding.questionsContainer.setVisibility(View.VISIBLE);
                break;
            case Constants.EVAL_QUIZ:
                result = QuestionService.result;
                endExamAndGetResults();
                break;
            case Constants.ADD_USER_HAS_QUIZ:
                userHasQuiz = UserHasQuizService.userHasQuizzes;
                break;
            default:
             //   Toast.makeText(requireActivity(), "Please Check Your Network Connection and try again", Toast.LENGTH_SHORT).show();
                break;
        }


    }

    private void updateUser() {
        UserHasQuiz user = new UserHasQuiz();
        user.setId(userHasQuiz.getId());
        user.setUserId(userHasQuiz.getUserId());
        user.setQuizId(userHasQuiz.getQuizId());
        user.setTookExam(true);
        user.setDegree((int) result.getMarksGot());
        UserHasQuizService.updateUserHasQuiz(user);

    }

    private void endExamAndGetResults() {
        if (result != null) {
            Log.e("TAG", "onFinish: " + (userHasQuiz == null));
            binding.questionsContainer.setVisibility(View.GONE);
            binding.quizSubmissionScreen.numOfSolvedQuestions.setText("You Solved " + result.getAttempted() + " Questions");
            binding.quizSubmissionScreen.userDegree.setText("and you got " + result.getMarksGot() + " Degrees");
            binding.quizSubmissionScreen.numOfRightAnswers.setText("and you got " + result.getCorrectAnswers() + " Degrees");
            binding.quizSubmissionScreen.getRoot().setVisibility(View.VISIBLE);
            binding.questionsContainer.setVisibility(View.GONE);
            List<Answer> answers = new ArrayList<>();

            updateUser();

            for (QuestionUser question : questionList) {
                Answer answer = new Answer();
                answer.setUserQuizId(userHasQuiz.getId());
                answer.setGivenAnswer(question.getGivenAnswer());
                answer.setQuestionId(question.getId());
                answers.add(answer);
            }
            AnswerService.addAnswer(answers);
        }
    }


    private void showConfirmDialuoge() {
        new SweetAlertDialog(requireActivity(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("You will submit the exam and it will be evaluated")
                .setConfirmText("Submit!")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    result = QuestionService.evalQuiz(questionList);
                })
                .setCancelButton("Cancel", SweetAlertDialog::dismissWithAnimation)
                .show();
    }


}



