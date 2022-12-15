package com.example.quizappv1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.quizappv1.R;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.Utilities.PreferenceManager;
import com.example.quizappv1.data.client.ClientApi;
import com.example.quizappv1.databinding.ActivityFirstPageBinding;

public class FirstPage extends AppCompatActivity {

    ActivityFirstPageBinding binding;
    PreferenceManager manager;
    boolean closeApp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstPageBinding.inflate(LayoutInflater.from(this));
        manager = new PreferenceManager(this);
        setContentView(binding.getRoot());
        ClientApi.openPreference(this);
        Log.e("TAG", "from manger: " + manager.getString(Constants.GENERATED_TOKEN_KEY));

        //  manager.clearData();

        if (!manager.getString(Constants.GENERATED_TOKEN_KEY).equalsIgnoreCase("not Found")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        setListeners();

    }

    private void setListeners() {

        binding.signInFirstPage.setOnClickListener(v -> openSignIn());
        binding.signUpFirstPage.setOnClickListener(v -> openSignUp());
    }

    private void openSignIn() {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
        finish();
    }

    private void openSignUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
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