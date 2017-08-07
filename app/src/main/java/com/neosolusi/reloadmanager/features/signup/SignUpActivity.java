package com.neosolusi.reloadmanager.features.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.features.shared.ActivityUtils;

import javax.inject.Inject;

public class SignUpActivity extends AppCompatActivity
{
    @Inject SignUpContract.Presenter mPresenter;

    private SignUpFragment mView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ((ReloadManager) getApplication()).getComponent().inject(this);

        mView = (SignUpFragment) getSupportFragmentManager().findFragmentById(R.id.contentSignUp);
        if (mView == null) {
            mView = SignUpFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mView, R.id.contentSignUp);
        }

        mView.setPresenter(mPresenter);
    }

    @Override protected void onResume()
    {
        super.onResume();

        mPresenter.attach(mView);
        mPresenter.start();
    }

    @Override protected void onPause()
    {
        mPresenter.detach();
        
        super.onPause();
    }
}
