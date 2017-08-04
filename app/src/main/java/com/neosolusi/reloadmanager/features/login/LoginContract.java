package com.neosolusi.reloadmanager.features.login;

public interface LoginContract
{
    interface View
    {
        void showLoginSuccess();
        void showLoginFailed(String message);
        void showLoading(boolean show);
        String getUsername();
        String getPassword();
    }

    interface Presenter
    {
        void signIn();
        void attachView(LoginContract.View loginView);
        void detachView();
    }
}