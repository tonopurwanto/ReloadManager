package com.neosolusi.reloadmanager.features.signup.verification;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class VerificationPresenter implements VerificationContract.Presenter
{
    private VerificationContract.View mView;

    @Override public void setView(@NonNull VerificationContract.View verificationView)
    {
        mView = checkNotNull(verificationView);
    }

    @Override public void performVerification()
    {

    }
}
