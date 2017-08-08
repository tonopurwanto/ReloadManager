package com.neosolusi.reloadmanager.features.login;

import com.neosolusi.reloadmanager.features.shared.BasePresenterContract;
import com.neosolusi.reloadmanager.features.shared.BaseViewContract;

public interface LoginContract
{
    interface View extends BaseViewContract<LoginContract.Presenter>
    {
        void showLoginSuccess();
        void showLoginFailed(String message);
        void showLoading(boolean show);
        void showSignUp();
        void setActiveForm(boolean active);
        String getUsername();
        String getPassword();
    }

    interface Presenter extends BasePresenterContract<LoginContract.View>
    {
        void signIn();
        void signUp();
    }
}