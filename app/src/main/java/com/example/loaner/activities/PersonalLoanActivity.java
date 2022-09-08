package com.example.loaner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.loaner.databinding.ActivityPersonalLoanBinding;

public class PersonalLoanActivity extends AppCompatActivity {
    private ActivityPersonalLoanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalLoanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> onBackPressed());
        binding.homeLoan.setOnClickListener(v ->  startActivity(new Intent(PersonalLoanActivity.this, HomeLoanActivity.class)));
        binding.educationLoan.setOnClickListener(v ->  startActivity(new Intent(PersonalLoanActivity.this, EducationalLoanActivity.class)));
        binding.carLoan.setOnClickListener(v ->  startActivity(new Intent(PersonalLoanActivity.this, CarLoanActivity.class)));
        binding.personalLoan.setOnClickListener(v ->  startActivity(new Intent(PersonalLoanActivity.this, PersonalActivity.class)));
    }
}