package com.neosolusi.reloadmanager.features.signup;

import android.support.annotation.NonNull;

public interface SignUpContract
{
    interface View
    {
        String getPhone();
        void signUp();
        void showErrorMessage(@NonNull String message);
        void showSignUpResult();
        void showSignUpDialog(@NonNull String phone);
    }

    interface Presenter
    {
        void signUp();
        void setView(@NonNull SignUpContract.View signupView);
    }
}
