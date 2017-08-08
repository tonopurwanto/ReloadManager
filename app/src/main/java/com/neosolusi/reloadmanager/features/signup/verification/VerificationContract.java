package com.neosolusi.reloadmanager.features.signup.verification;

import com.neosolusi.reloadmanager.features.shared.BasePresenterContract;
import com.neosolusi.reloadmanager.features.shared.BaseViewContract;

public interface VerificationContract
{
    interface View extends BaseViewContract<VerificationContract.Presenter>
    {
        void showErrorMessage(String message);
    }

    interface Presenter extends BasePresenterContract<VerificationContract.View>
    {
        void performVerification();
    }
}
