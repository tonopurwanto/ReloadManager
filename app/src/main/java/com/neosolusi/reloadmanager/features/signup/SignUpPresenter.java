package com.neosolusi.reloadmanager.features.signup;

import android.text.TextUtils;

public class SignUpPresenter implements SignUpContract.Presenter
{
    private SignUpContract.View mView;

    private boolean isPhoneValid()
    {
        return TextUtils.isDigitsOnly(mView.getPhone())
                && mView.getPhone().length() > 10
                && mView.getPhone().length() <= 13;
    }

    @Override public void attach(SignUpContract.View view)
    {
        this.mView = view;
    }

    @Override public void detach()
    {
        this.mView = null;
    }

    @Override public void start()
    {

    }

    @Override public void signUp()
    {
        if (TextUtils.isEmpty(mView.getPhone())) {
            mView.showErrorMessage("Nomor HP harus diisi");
            return;
        }

        if (! isPhoneValid()) {
            mView.showErrorMessage("Nomor HP tidak valid");
            return;
        }

        mView.showSignUpDialog(mView.getPhone());
    }
}
