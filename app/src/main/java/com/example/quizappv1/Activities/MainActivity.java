package com.example.quizappv1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.quizappv1.Fragments.FragmentCategoriesList;
import com.example.quizappv1.Fragments.FragmentQuizQuestion;
import com.example.quizappv1.Fragments.FragmentQuizzesHistory;
import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.Utilities.PreferenceManager;
import com.example.quizappv1.data.client.ClientApi;
import com.example.quizappv1.data.services.AuthenticateService;
import com.example.quizappv1.data.services.UserService;
import com.example.quizappv1.databinding.MainScreenBinding;
import com.example.quizappv1.model.User.User;

public class MainActivity extends AppCompatActivity {

    MainScreenBinding binding;
    boolean closeApp = false;
    User user;
    PreferenceManager manager;

    public static ClientApi retrofitService = new ClientApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainScreenBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        manager = new PreferenceManager(this);

        if (manager.getString(Constants.CURRENT_USER_ID).equals("not Found")) {
            user = AuthenticateService.getCurrentUser();
        }

        setListeners();
    }


    private void setListeners() {
        binding.signOut.setOnClickListener(v -> signOut());
        binding.quizCategory.setOnClickListener(v -> openTaskFragment("2"));
        binding.quizHistory.setOnClickListener(v -> openTaskFragment("3"));
    }

    private void signOut() {
        manager.clearData();
        Intent intent = new Intent(this, FirstPage.class);
        startActivity(intent);
    }

    private void openTaskFragment(CharSequence text) {

        Fragment fragment;
        switch (text.toString()) {
            case "1":
                fragment = new FragmentQuizQuestion();
                getSupportFragmentManager().
                        beginTransaction().
                        replace(binding.fragmentsContainer.getId(), fragment).
                        commit();
                break;
            case "2":
                fragment = new FragmentCategoriesList();
                getSupportFragmentManager().
                        beginTransaction().
                        replace(binding.fragmentsContainer.getId(), fragment).
                        commit();
                break;
            case "3":
                fragment = new FragmentQuizzesHistory();
                getSupportFragmentManager().
                        beginTransaction().
                        replace(binding.fragmentsContainer.getId(), fragment).
                        commit();
        }

    }


    @Override
    public void onBackPressed() {
        if (closeApp) {
            finish();
            System.exit(0);
            return;
        }
        closeApp = true;
        Toast.makeText(this, "Slide again", Toast.LENGTH_SHORT).show();
    }

}