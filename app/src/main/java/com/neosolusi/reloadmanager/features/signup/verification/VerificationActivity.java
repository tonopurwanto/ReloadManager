package com.neosolusi.reloadmanager.features.signup.verification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.features.shared.ActivityUtils;

import javax.inject.Inject;

public class VerificationActivity extends AppCompatActivity
{
    @Inject VerificationContract.Presenter mPresenter;

    private VerificationFragment mView;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        ((ReloadManager) getApplication()).getComponent().inject(this);

        mView = (VerificationFragment) getSupportFragmentManager().findFragmentById(R.id.contentVerifycation);
        if (mView == null) {
            mView = VerificationFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mView, R.id.contentVerifycation);
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
