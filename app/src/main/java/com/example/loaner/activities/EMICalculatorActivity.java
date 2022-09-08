package com.example.loaner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.loaner.databinding.ActivityEmiCalculatorBinding;

public class EMICalculatorActivity extends AppCompatActivity {

    private ActivityEmiCalculatorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmiCalculatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> onBackPressed());
        binding.buttonCalculate.setOnClickListener(v -> emiCal());
    }

    private void emiCal(){
        double principal = Double.parseDouble(binding.inputPrinciple.getText().toString());
        double rate = Double.parseDouble(binding.inputRate.getText().toString());
        double time = Double.parseDouble(binding.inpurtTime.getText().toString());

        rate = rate/1200;
        time = time*12;
        double emi =Math.round(((principal*rate*Math.pow(1+rate,time))/(Math.pow(1+rate,time)-1))*100)/100.0;
        double emiQuart = emi * 4;
        double emiYear = emi * 12;

        TextView txtView = (TextView) findViewById(binding.outputMon.getId());
        TextView txtView2 = (TextView) findViewById(binding.textMon.getId());
        txtView2.setVisibility(View.VISIBLE);
        txtView.setText(Double.toString(emi));
        txtView.setVisibility(View.VISIBLE);

        txtView = (TextView) findViewById(binding.outputQuart.getId());
        txtView2 = (TextView) findViewById(binding.textQuat.getId());
        txtView2.setVisibility(View.VISIBLE);
        txtView.setText(Double.toString(emiQuart));
        txtView.setVisibility(View.VISIBLE);

        txtView = (TextView) findViewById(binding.outputYear.getId());
        txtView2 = (TextView) findViewById(binding.textYear.getId());
        txtView2.setVisibility(View.VISIBLE);
        txtView.setText(Double.toString(emiYear));
        txtView.setVisibility(View.VISIBLE);
    }
}