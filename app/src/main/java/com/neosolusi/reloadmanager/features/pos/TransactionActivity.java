package com.neosolusi.reloadmanager.features.pos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.features.shared.ActivityUtils;

import javax.inject.Inject;

public class TransactionActivity extends AppCompatActivity
{
    @Inject TransactionContract.Presenter mPresenter;

    private TransactionFragment mView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        ((ReloadManager) getApplication()).getComponent().inject(this);

        mView = (TransactionFragment) getSupportFragmentManager().findFragmentById(R.id.contentTransaction);
        if (mView == null) {
            mView = TransactionFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mView, R.id.contentTransaction);
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
