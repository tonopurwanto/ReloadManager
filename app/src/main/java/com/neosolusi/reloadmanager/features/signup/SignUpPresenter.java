package com.neosolusi.reloadmanager.features.signup;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class SignUpPresenter implements SignUpContract.Presenter
{
    private SignUpContract.View mView;

    private boolean isPhoneValid()
    {
        return TextUtils.isDigitsOnly(mView.getPhone())
                && mView.getPhone().length() > 10
                && mView.getPhone().length() <= 13;
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

    @Override public void setView(@NonNull SignUpContract.View signupView)
    {
        mView = checkNotNull(signupView);
    }
}
