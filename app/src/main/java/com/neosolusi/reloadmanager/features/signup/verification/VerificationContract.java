package com.neosolusi.reloadmanager.features.signup.verification;

import android.support.annotation.NonNull;

import com.neosolusi.reloadmanager.features.shared.BasePresenterContract;
import com.neosolusi.reloadmanager.features.shared.BaseViewContract;

public interface VerificationContract
{
    interface View
    {
        void showErrorMessage(@NonNull String message);
    }

    interface Presenter
    {
        void setView(@NonNull VerificationContract.View verificationView);
        void performVerification();
    }
}
