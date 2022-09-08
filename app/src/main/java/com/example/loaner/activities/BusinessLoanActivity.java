package com.example.loaner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.loaner.databinding.ActivityBusinessLoanBinding;

public class BusinessLoanActivity extends AppCompatActivity {

    private ActivityBusinessLoanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBusinessLoanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> onBackPressed());
        binding.termLoab.setOnClickListener(v ->  startActivity(new Intent(BusinessLoanActivity.this, TermLoanActivity.class)));
        binding.capitalLoan.setOnClickListener(v ->  startActivity(new Intent(BusinessLoanActivity.this, WorkingCapitalActivity.class)));
        binding.billDiscount.setOnClickListener(v ->  startActivity(new Intent(BusinessLoanActivity.this, BillDiscountingActivity.class)));
    }
}