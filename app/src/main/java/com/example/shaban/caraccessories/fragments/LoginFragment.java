package com.example.shaban.caraccessories.fragments;

/**
 * Created by shaban on 9/10/2018.
 */

import com.example.shaban.caraccessories.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.shaban.caraccessories.utils.CustomToast;
import com.example.shaban.caraccessories.utils.Utils;
import com.example.shaban.caraccessories.utils.firebase_utils.Authentication;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import io.rmiri.buttonloading.ButtonLoading;

public class LoginFragment extends Fragment implements OnClickListener {

    private static View view;
    private static EditText emailId, password;
    private static ButtonLoading loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        emailId = (EditText) view.findViewById(R.id.login_emailid);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (ButtonLoading) view.findViewById(R.id.loginBtn);
        forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        signUp = (TextView) view.findViewById(R.id.createAccount);
        show_hide_password = (CheckBox) view.findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

        //
        loginButton.setOnButtonLoadingListener(new ButtonLoading.OnButtonLoadingListener() {
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

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake_animation_x);

        // Setting text selector over text views
        XmlResourceParser xrp = getResources().getXml(R.drawable.textview_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
            forgotPassword.setTextColor(csl);
            show_hide_password.setTextColor(csl);
            signUp.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        //loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);
        // Set check listener over checkbox for showing and hiding password
        show_hide_password.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                // If it is checked then show password else hide
                // password
                if (isChecked) {
                    show_hide_password.setText(R.string.hide_pwd);// change
                    // checkbox
                    // text
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());// show password
                } else {
                    show_hide_password.setText(R.string.show_pwd);// change
                    // checkbox
                    // text
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());// hide password
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forgot_password:
                // Replace forgot password fragment with animation
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.right_enter_animation, R.anim.left_exit_animation)
                        .replace(R.id.frameContainer, new ForgotPasswordFragment(), Utils.ForgotPassword_Fragment).commit();
                break;
            case R.id.createAccount:
                // Replace sign up fragment with animation
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.right_enter_animation, R.anim.left_exit_animation)
                        .replace(R.id.frameContainer, new SignUpFragment(), Utils.SignUp_Fragment).commit();
                break;
        }
    }

    // Check Validation before login
    private void checkValidation() {
        // Get email id and password
        String getEmailId = emailId.getText().toString();
        String getPassword = password.getText().toString();
        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);
        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0 || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            loginButton.setProgress(false);
            new CustomToast().Show_Toast(getActivity(), view, "Enter both credentials.");
        }
        // Check if email id is valid or not
        else if (!m.find()) {
            loginButton.setProgress(false);
            new CustomToast().Show_Toast(getActivity(), view, "Your Email Id is Invalid.");
        }
        // Else do login and do your stuff
        else
            new Authentication().signIn(getEmailId,getPassword,getActivity(),loginButton);
    }
}
