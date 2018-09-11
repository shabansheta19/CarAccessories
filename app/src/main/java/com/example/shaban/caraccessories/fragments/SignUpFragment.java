package com.example.shaban.caraccessories.fragments;

/**
 * Created by shaban on 9/10/2018.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.shaban.caraccessories.MainActivity;
import com.example.shaban.caraccessories.utils.CustomToast;
import com.example.shaban.caraccessories.R;
import com.example.shaban.caraccessories.utils.Utils;
import com.example.shaban.caraccessories.utils.firebase_utils.Authentication;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.rmiri.buttonloading.ButtonLoading;

public class SignUpFragment extends Fragment implements OnClickListener {

    private static View view;
    private static EditText fullName, emailId, mobileNumber, password, confirmPassword;
    private static TextView login;
    private static ButtonLoading signUpButton;

    public SignUpFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signup_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initialize all views
    private void initViews() {
        fullName = (EditText) view.findViewById(R.id.fullName);
        emailId = (EditText) view.findViewById(R.id.userEmailId);
        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        password = (EditText) view.findViewById(R.id.password);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
        signUpButton = (ButtonLoading) view.findViewById(R.id.signUpBtn);
        login = (TextView) view.findViewById(R.id.already_user);

        //
        signUpButton.setOnButtonLoadingListener(new ButtonLoading.OnButtonLoadingListener() {
            @Override
            public void onClick() {
                checkValidation();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }
        });

        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.textview_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
            login.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        //signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.already_user:
                // Replace login fragment
                new MainActivity().replaceLoginFragment();
                break;
        }
    }

    // Check Validation Method
    private void checkValidation() {

        // Get all edittext texts
        String getFullName = fullName.getText().toString();
        String getEmailId = emailId.getText().toString();
        String getMobileNumber = mobileNumber.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();

        // Pattern match for email id
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        // Check if all strings are null or not
        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0) {
            signUpButton.setProgress(false);
            new CustomToast().Show_Toast(getActivity(), view, "All fields are required.");
        }
        // Check if email id valid or not
        else if (!m.find()) {
            signUpButton.setProgress(false);
            new CustomToast().Show_Toast(getActivity(), view, "Your Email Id is Invalid.");
        }
        // Check if both password should be equal
        else if (!getConfirmPassword.equals(getPassword)) {
            signUpButton.setProgress(false);
            new CustomToast().Show_Toast(getActivity(), view, "Both password doesn't match.");
        }
        // Else do signup or do your stuff
        else
            new Authentication().signUp(getFullName,getEmailId,getMobileNumber,getPassword,getActivity(),signUpButton);
    }
}
