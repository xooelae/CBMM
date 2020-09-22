package com.example.cbmm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.DocumentsContract;
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
import android.widget.Toast;


import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterFragment extends Fragment {

    public RegisterFragment(){
    }

    private Button logInButton,createAccountButton;
    private FrameLayout parentFrameLayout;
    private EditText fullNameBox, emailBox, phoneBox, passwordBox, confirmPasswordBox;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private FirebaseFirestore firebaseFirestore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        parentFrameLayout = getActivity().findViewById(R.id.authentication_frameLayout);
        logInButton = view.findViewById(R.id.signup_page_signin_btn);
        createAccountButton = view.findViewById(R.id.create_account_button);
        fullNameBox = view.findViewById(R.id.full_name_input_box);
        emailBox = view.findViewById(R.id.signup_email_box);
        phoneBox = view.findViewById(R.id.signup_phone_box);
        passwordBox = view.findViewById(R.id.signup_password_box);
        confirmPasswordBox = view.findViewById(R.id.signup_confirm_password_box);


        firebaseAuth = FirebaseAuth.getInstance();

        progressBar = view.findViewById(R.id.signup_progress_bar);

        firebaseFirestore = FirebaseFirestore.getInstance();



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new LoginFragment());


            }
        });

        emailBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        fullNameBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        emailBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passwordBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmPasswordBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();

            }
        });
    }

    private void Validation() {

        if (emailBox.getText().toString().matches(emailPattern)){

            if (passwordBox.getText().toString().equals(confirmPasswordBox.getText().toString())){

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(emailBox.getText().toString(), passwordBox.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Map<String, Object> userData= new HashMap<>();
                                    userData.put("fullname", fullNameBox.getText().toString());
                                    userData.put("email", emailBox.getText().toString());
                                    userData.put("phone", phoneBox.getText().toString());
                                    firebaseFirestore.collection("USERS")
                                            .add(userData)
                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                                    if (task.isSuccessful()){
                                                        Intent mainintent = new Intent(getActivity(), MainActivity.class);
                                                        startActivity(mainintent);
                                                        getActivity().finish();
                                                    }
                                                    else {
                                                        createAccountButton.setEnabled(false);
                                                        createAccountButton.setTextColor(Color.argb(50,255, 135, 44));
                                                        String error = task.getException().toString();
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        Toast.makeText(getActivity(), "Error: " +error, Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });

                                }else{
                                    String error = task.getException().toString();
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else {
                passwordBox.getText().clear();
                confirmPasswordBox.getText().clear();
                passwordBox.setHintTextColor(Color.rgb(255, 0, 34));
                confirmPasswordBox.setHintTextColor(Color.rgb(255, 0, 34));
                passwordBox.setHint("Password Doesn't match");
                confirmPasswordBox.setHint("Password Doesn't match");
                Toast.makeText(getActivity(), "Password doesn't match", Toast.LENGTH_SHORT).show();

            }

        }else {

            emailBox.getText().clear();
            emailBox.setHintTextColor(Color.rgb(255, 0, 34));
            emailBox.setHint("Invalid Email");
            Toast.makeText(getActivity(), "Invalid email", Toast.LENGTH_SHORT).show();


        }

    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
    private void checkInputs(){

        if (!TextUtils.isEmpty(emailBox.getText())){
            if (!TextUtils.isEmpty(fullNameBox.getText())){
                if (!TextUtils.isEmpty(phoneBox.getText())){
                    if (!TextUtils.isEmpty(passwordBox.getText()) && passwordBox.length() >= 6){
                        if (!TextUtils.isEmpty(confirmPasswordBox.getText())){
                            createAccountButton.setEnabled(true);
                            createAccountButton.setTextColor(Color.rgb(255, 135, 44));

                        }
                        else {
                            createAccountButton.setEnabled(false);
                            createAccountButton.setTextColor(Color.argb(50,255, 135, 44));


                        }

                    }
                    else {
                        createAccountButton.setEnabled(false);
                        createAccountButton.setTextColor(Color.argb(50,255, 135, 44));

                    }
                }
                else {
                    createAccountButton.setEnabled(false);
                    createAccountButton.setTextColor(Color.argb(50,255, 135, 44));

                }
            }
            else{
                createAccountButton.setEnabled(false);
                createAccountButton.setTextColor(Color.argb(50,255, 135, 44));

            }

        }
        else{
            createAccountButton.setEnabled(false);
            createAccountButton.setTextColor(Color.argb(50,255, 135, 44));

        }
    }
}






