package com.neosolusi.reloadmanager.features.signup.verification;

public class VerificationPresenter implements VerificationContract.Presenter
{
    private VerificationContract.View mView;

    @Override public void attach(VerificationContract.View view)
    {
        mView = view;
    }

    @Override public void detach()
    {
        mView = null;
    }

    @Override public void start()
    {

    }

    @Override public void performVerification()
    {

    }
}
