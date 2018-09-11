package com.example.shaban.caraccessories.utils.firebase_utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.shaban.caraccessories.CategoriesActivity;
import com.example.shaban.caraccessories.MainActivity;
import com.example.shaban.caraccessories.utils.CustomToast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.rmiri.buttonloading.ButtonLoading;

/**
 * Created by shaban on 9/10/2018.
 */

public class Authentication {

    private boolean state = false;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public Authentication() {
        auth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        auth.addAuthStateListener(mAuthListener);
    }

    public void signIn(String email, final String password, final Activity activity, final ButtonLoading loginButton) {
        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loginButton.setProgress(false);
                        if (task.isSuccessful())
                            activity.startActivity(new Intent(activity, CategoriesActivity.class));
                        else
                            Toast.makeText(activity,"Sign In Failed",Toast.LENGTH_LONG).show();
                    }
                });
        stop();
    }

    public void signUp(String name, String email, String phoneNumber, String password, final Activity activity, final ButtonLoading signUpButton) {
        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        signUpButton.setProgress(false);
                        if (task.isSuccessful())
                            new MainActivity().replaceLoginFragment();
                        else
                            Toast.makeText(activity,"Sign Up Failed",Toast.LENGTH_LONG).show();
                    }
                });
        stop();
    }

    public void resetPassword(String email) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    new MainActivity().replaceLoginFragment();
                }
            });
        stop();
    }

    public boolean signOut(String email , String password) {
        return false;
    }

    public void stop() {
        if (mAuthListener != null) {
            auth.removeAuthStateListener(mAuthListener);
        }
    }
}
