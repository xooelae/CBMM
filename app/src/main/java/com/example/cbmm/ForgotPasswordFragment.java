package com.example.cbmm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPasswordFragment extends Fragment {

    private Button resetPassButton;
    private EditText resetPassEmailBox;
    private TextView createAccountText, loginText, emailSendConfirmation;
    private FrameLayout frameLayout;
    private ProgressBar progressBar;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private TextView goBack;
    private FirebaseAuth firebaseAuth;



    public ForgotPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        resetPassButton = view.findViewById(R.id.password_reset_button);
        resetPassEmailBox = view.findViewById(R.id.password_reset_email_box);
        createAccountText = view.findViewById(R.id.create_account_text);
        goBack = view.findViewById(R.id.forgotpass_page_go_back);
        emailSendConfirmation = view.findViewById(R.id.email_confirnmation_text);
        loginText = view.findViewById(R.id.login_account_text);
        frameLayout = getActivity().findViewById(R.id.authentication_frameLayout);
        progressBar = view.findViewById(R.id.forgot_pass_progress_bar);

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        resetPassEmailBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                CheckInputs();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        createAccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new RegisterFragment());
            }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new LoginFragment());
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new LoginFragment());
            }
        });

        resetPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetPasswordRequest();
            }
        });


    }

    private void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();


    }

    private void CheckInputs(){

        if (!(TextUtils.isEmpty(resetPassEmailBox.getText()))){
            resetPassButton.setEnabled(true);
            resetPassButton.setTextColor(Color.rgb(255, 135, 44));

        }
        else {
            resetPassButton.setTextColor(Color.argb(50,255, 135, 44));
            resetPassButton.setEnabled(false);
        }
    }

    private void ResetPasswordRequest(){

        if (resetPassEmailBox.getText().toString().matches(emailPattern)){
            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.sendPasswordResetEmail(resetPassEmailBox.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            resetPassButton.setEnabled(true);
                            resetPassButton.setTextColor(Color.rgb(255, 135, 44));



                            if (task.isSuccessful()){
                                progressBar.setVisibility(View.INVISIBLE);
                                emailSendConfirmation.setText("Request sent successfully.");
                                emailSendConfirmation.setVisibility(View.VISIBLE);
                                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                            }
                            else{

                                String error = task.getException().getMessage();
                                Toast.makeText(getActivity(), "Error: "+error, Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                emailSendConfirmation.setText("Error: "+error);
                                emailSendConfirmation.setTextColor(Color.rgb(255, 0, 34));
                                emailSendConfirmation.setVisibility(View.VISIBLE);



                            }
                        }
                    });

        }
        else {
            resetPassEmailBox.getText().clear();
            resetPassEmailBox.setHintTextColor(Color.rgb(255, 0, 34));
            resetPassEmailBox.setHint("Invalid Email");
        }

    }
}