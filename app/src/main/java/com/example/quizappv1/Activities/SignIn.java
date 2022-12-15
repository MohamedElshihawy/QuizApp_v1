package com.example.quizappv1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.Utilities.Constants;
import com.example.quizappv1.Utilities.PreferenceManager;
import com.example.quizappv1.data.services.AuthenticateService;
import com.example.quizappv1.databinding.ActivitySignInBinding;
import com.example.quizappv1.model.User.Login;
import com.example.quizappv1.model.User.User;

public class SignIn extends AppCompatActivity implements NetworkRequestListener {

    ActivitySignInBinding binding;
    Login login;
    PreferenceManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        AuthenticateService.listener = this;
        setListener();
        manager = new PreferenceManager(this);
        AuthenticateService.openPreferenceManger(this);

    }

    private void setListener() {
        binding.signInButton.setOnClickListener(v -> signIn());
        binding.textSignUp.setOnClickListener(v -> openSignUp());
    }

    private void openSignUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }

    private void signIn() {
        if (checkIfUserExist()) {
            Log.e("TAG", "signIn: " +login.toString());
            AuthenticateService.generateToken(login);
        } else {
            Toast.makeText(this, "Please Enter User Name Or Password", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkIfUserExist() {
        boolean valid = true;
        login = new Login();
        if (binding.editTextTextPersonName.getText().toString().equals("")) {
            binding.editTextTextPersonName.setError("Please Enter a User Name");
            valid = false;
        } else {
            login.setUsername(binding.editTextTextPersonName.getText().toString());
        }


        if (binding.editTextPassword.getText().toString().equals("")) {
            binding.editTextPassword.setError("Please Enter a Password");
            valid = false;
        } else {
            login.setPassword(binding.editTextPassword.getText().toString());
        }

        return valid;
    }

    @Override
    public void onFinish(String key) {

        if (key.equals(Constants.GENERATE_TOKEN_KEY)) {
            if (!manager.getString(Constants.GENERATED_TOKEN_KEY).equals("")) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "User Name Or Password Are Wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, FirstPage.class);
        startActivity(intent);
        finish();
    }
}