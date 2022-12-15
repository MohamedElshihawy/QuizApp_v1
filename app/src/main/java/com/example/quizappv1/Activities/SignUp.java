package com.example.quizappv1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.quizappv1.Interface.NetworkRequestListener;
import com.example.quizappv1.data.services.UserService;
import com.example.quizappv1.databinding.ActivitySignUpBinding;
import com.example.quizappv1.model.User.User;

public class SignUp extends AppCompatActivity implements NetworkRequestListener {

    ActivitySignUpBinding binding;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(LayoutInflater.from(this));
        UserService.listener = this;
        setContentView(binding.getRoot());


        setListeners();


    }

    private void setListeners() {
        binding.signUpButton.setOnClickListener(v -> signUp());
        binding.signInText.setOnClickListener(v -> {
            startActivity(new Intent(this, SignIn.class));
            finish();
        });

        binding.signInText.setOnClickListener(v -> openSignIn());
    }

    private void signUp() {
        if (checkInput()) {
            Log.e("TAG", "signUp: " + user.toString());
            UserService.createUser(user);
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Please Complete Your Account Data First", Toast.LENGTH_SHORT).show();
        }

    }

    private void openSignIn() {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
        finish();
    }

    private boolean checkInput() {
        boolean valid = true;
        user = new User();
        if (binding.editTextTextUserName.getText().toString().equals("")) {
            binding.editTextTextUserName.setError("Please Enter User Name");
            valid = false;
        } else {
            user.setUsername(binding.editTextTextUserName.getText().toString());
        }


        if (binding.editTextFirstName.getText().toString().equals("")) {
            binding.editTextFirstName.setError("Please Enter First Name");
            valid = false;
        } else {
            user.setFirstName(binding.editTextFirstName.getText().toString());
        }


        if (binding.editTextLastName.getText().toString().equals("")) {
            binding.editTextLastName.setError("Please Enter Last Name");
            valid = false;
        } else {
            user.setLastName(binding.editTextLastName.getText().toString());
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(binding.editTextPersonEmail.getText().toString()).matches()) {
            binding.editTextPersonEmail.setError("Please Enter a Valid E-Mail");
            valid = false;
        } else {
            user.setEmail(binding.editTextPersonEmail.getText().toString());
        }

        if (binding.editTextPassword.getText().toString().equals("")
                || binding.editTextPassword.getText().length() < 8) {
            binding.editTextPassword.setError("Please Enter a Valid password");
            valid = false;
        } else {
            user.setPassword(binding.editTextPassword.getText().toString());
        }
        user.setPhone(binding.editTextPhone.getText().toString());

        return valid;
    }

    @Override
    public void onFinish(String key) {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, FirstPage.class);
        startActivity(intent);
        finish();
    }
}