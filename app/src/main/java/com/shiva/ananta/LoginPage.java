package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginPage extends AppCompatActivity {

    TextView loginbutton,skipbutton,create_account_button;
    EditText mobileno;
    ProgressBar progessbar;
    View loadingbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        loginbutton = findViewById(R.id.loginbutton);
        skipbutton = findViewById(R.id.skipbutton);
        create_account_button = findViewById(R.id.create_account_button);

        mobileno = findViewById(R.id.signin_emailorno);
        progessbar = findViewById(R.id.progessbar);
        loadingbar = findViewById(R.id.loadingbar);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(LoginPage.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
        }


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mobileno.getText().toString().trim().isEmpty()) {
                    if((mobileno.getText().toString().trim()).length() == 10){

                        loadingbar.setVisibility(View.VISIBLE);
                        loginbutton.setVisibility(View.GONE);


                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + mobileno.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                LoginPage.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        handleVerificationCompleted(phoneAuthCredential);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        handleVerificationFailed(e);
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        handleCodeSent(s);
                                    }
                                });



                    }else {
                        Toast.makeText(LoginPage.this, "Please enter correct number!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    mobileno.requestFocus();
                    mobileno.setError("Enter your mobile number");
                    return;

                }
                }
        });

    }

    private void handleVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
        hideProgressAndShowLoginButton();
    }

    private void handleVerificationFailed(FirebaseException e) {
        hideProgressAndShowLoginButton();
        Toast.makeText(LoginPage.this, "Verification Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        Log.d("Verification",""+e.getMessage());
    }

    private void handleCodeSent(String verificationId) {
        hideProgressAndShowLoginButton();
        Intent intent = new Intent(LoginPage.this, SignUp.class);
        intent.putExtra("mobile", mobileno.getText().toString());
        intent.putExtra("mobileotp", verificationId);
        startActivity(intent);
    }
    private void hideProgressAndShowLoginButton() {
        loadingbar.setVisibility(View.GONE);
        loginbutton.setVisibility(View.VISIBLE);
    }

}