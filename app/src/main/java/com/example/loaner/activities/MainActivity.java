package com.example.loaner.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;
import android.content.DialogInterface;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import com.example.loaner.databinding.ActivityMainBinding;
import com.example.loaner.utilities.Constants;
import com.example.loaner.utilities.PreferenceManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PreferenceManager preferenceManager;
    private FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        loadUserDetails();
        setListeners();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Welcome to Monetary Mind!\n" +
                "These terms and conditions outline the rules and regulations for the use of Monetary Mind.\n" +
                "Do not continue to use Monetary Mind if you do not agree to take all of the terms and conditions stated on this page.\n" +
                "Reservation of Rights\n" +
                "We reserve the right to request that you remove all links or any particular link to our App. \n" +
                "You approve to immediately remove all links to our App upon request. \n" +
                "We also reserve the right to amen these terms and conditions and it’s linking policy at any time. By continuously linking to our App, \n" +
                "you agree to be bound to and follow these linking terms and conditions.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void setListeners(){
        binding.imageSignOut.setOnClickListener(v -> signout());
        binding.buttonPersonalLoan.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OTPVerficationActivity.class)));
        binding.buttonBusinessLoan.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), OTPVerificationBusinessActivity.class)));
        binding.buttonEMICal.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), EMICalculatorActivity.class)));
    }

    private void loadUserDetails(){
        binding.textName.setText(preferenceManager.getString(Constants.KEY_NAME));
        byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        binding.imageProfile.setImageBitmap(bitmap);
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void signout(){
        showToast("Signing Out!");
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference = database.collection(Constants.KEY_COLLECTION_USERS).document(
                preferenceManager.getString(Constants.KEY_USER_ID));
        HashMap<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(unused ->{
                    preferenceManager.clear();
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> showToast("Unable To Sign Out"));
    }
}