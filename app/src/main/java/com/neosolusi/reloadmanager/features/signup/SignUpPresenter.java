package com.neosolusi.reloadmanager.features.signup;

public class SignUpPresenter implements SignUpContract.Presenter
{
    private SignUpContract.View mView;

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
        mView.showSignUpResult();
    }
}
