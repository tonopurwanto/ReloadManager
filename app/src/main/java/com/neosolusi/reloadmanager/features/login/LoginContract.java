package com.neosolusi.reloadmanager.features.login;

import android.support.annotation.NonNull;

public interface LoginContract
{
    interface View
    {
        void showLoginSuccess();
        void showLoginFailed(@NonNull String message);
        void showLoading(boolean show);
        void showSignUp();
        void setActiveForm(boolean active);
        String getUsername();
        String getPassword();
    }

    interface Presenter
    {
        void setView(@NonNull LoginContract.View loginView);
        void signIn();
        void signUp();
    }
}