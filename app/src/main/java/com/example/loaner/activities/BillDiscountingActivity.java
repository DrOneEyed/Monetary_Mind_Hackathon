package com.example.loaner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loaner.databinding.ActivityBillDiscountingBinding;
import com.example.loaner.databinding.ActivityHomeLoanBinding;
import com.example.loaner.utilities.PreferenceManager;
import com.google.firebase.firestore.FirebaseFirestore;

public class BillDiscountingActivity extends AppCompatActivity {
    private ActivityBillDiscountingBinding binding;
    private PreferenceManager preferenceManager;
    private boolean valid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager = new PreferenceManager(getApplicationContext());
        binding = ActivityBillDiscountingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String cID = binding.inputCustID.getText().toString();
        setListener();
    }

    private void showToast(String messasge){
        Toast.makeText(getApplicationContext(), messasge, Toast.LENGTH_SHORT).show();
    }

    private void setListener(){
        binding.buttonDet.setOnClickListener(v -> getDet());
        binding.buttonLoan.setOnClickListener(v -> execute());
    }

    private void getDet(){

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        TextView txtView = (TextView) findViewById(binding.bankName.getId());
        database.collection("Individual Bank")
                .whereEqualTo("Cust ID", binding.inputCustID.getText().toString())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size() > 0){
                        txtView.setVisibility(View.VISIBLE);
                        txtView.setText("Eligible To Apply For Loan");
                        valid = true;
                    }
                    else{
                        txtView.setVisibility(View.GONE);
                        valid = false;
                        showToast("Unable To Retrieve Data!");
                    }
                });
    }

    private void execute(){
        if(valid == true){
            Uri uri = Uri.parse("https://www.paisabazaar.com/business-loan/bill-discounting/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        else{
            showToast("Validate Yourself First!");
        }
    }
}