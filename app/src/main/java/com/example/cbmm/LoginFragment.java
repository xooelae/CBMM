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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.cbmm.AuthenticationActivity.onRegisterFragment;
import static com.example.cbmm.AuthenticationActivity.onResetPassFragment;

public class LoginFragment extends Fragment {


     public LoginFragment(){
     }

     private Button createNewAccountButton, loginButton;
     private EditText loginEmailBox, loginPasswordBox;
     private FrameLayout frameLayout;
     private TextView skipText, forgotPasswordText;
     private FirebaseAuth firebaseAuth;
     private ProgressBar progressBar;
     private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        frameLayout = getActivity().findViewById(R.id.authentication_frameLayout);
        createNewAccountButton = view.findViewById(R.id.create_new_account_button);
        loginEmailBox = view.findViewById(R.id.login_id_box);
        loginPasswordBox = view.findViewById(R.id.login_password_box);
        skipText = view.findViewById(R.id.skip_login_page_text);
        firebaseAuth = FirebaseAuth.getInstance();
        loginButton = view.findViewById(R.id.signin_button);
        progressBar = view.findViewById(R.id.login_progress_bar);
        forgotPasswordText = view.findViewById(R.id.forgot_password);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginEmailBox.addTextChangedListener(new TextWatcher() {
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
        loginPasswordBox.addTextChangedListener(new TextWatcher() {
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




        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        forgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResetPassFragment = true;
                ForgotPassword();
            }
        });

        createNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterFragment = true;
                setFragment(new RegisterFragment());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();
            }
        });




    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
    private void CheckInputs(){
        if (!TextUtils.isEmpty(loginEmailBox.getText())){
            if (!TextUtils.isEmpty(loginPasswordBox.getText())){
                loginButton.setEnabled(true);
                loginButton.setTextColor(Color.rgb(255, 135, 44));

            }else{
                loginButton.setEnabled(false);
                loginButton.setTextColor(Color.argb(50,255, 135, 44));

            }
        }else{
            loginButton.setEnabled(false);
            loginButton.setTextColor(Color.argb(50,255, 135, 44));

        }

    }
    private void Validation(){
        String email= loginEmailBox.getText().toString();
        String password = loginPasswordBox.getText().toString();
        progressBar.setVisibility(View.VISIBLE);


        if (loginEmailBox.getText().toString().matches(emailPattern)){

            if (loginPasswordBox.getText().toString().length() >= 6){


                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){
                                    Toast.makeText(getActivity(), "Login Success!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();

                                }
                                else {
                                    loginButton.setEnabled(false);
                                    loginButton.setTextColor(Color.argb(50,255, 135, 44));
                                    String error = task.getException().toString();
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getActivity(), "Error: " +error, Toast.LENGTH_SHORT).show();

                                }

                            }
                        });


            }else{

                loginPasswordBox.getText().clear();
                loginPasswordBox.setHintTextColor(Color.rgb(255, 0, 34));
                loginPasswordBox.setHint("Incorrect Password");
                Toast.makeText(getActivity(), "Incorrect Password", Toast.LENGTH_SHORT).show();


            }
        }else{
            loginEmailBox.getText().clear();
            loginEmailBox.setHintTextColor(Color.rgb(255, 0, 34));
            loginEmailBox.setHint("Invalid Email");
            Toast.makeText(getActivity(), "Invalid email", Toast.LENGTH_SHORT).show();
        }

    }
    private  void ForgotPassword(){
        setFragment(new ForgotPasswordFragment());
    }
}