package com.neosolusi.reloadmanager.features.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.features.shared.ActivityUtils;
import com.neosolusi.reloadmanager.features.sync.SyncContract;
import com.neosolusi.reloadmanager.features.sync.SyncFragment;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener
{
    @Inject LoginContract.Presenter mPresenter;
    @Inject SyncContract.Presenter mSyncPresenter;

    private LoginFragment mView;
    private SyncFragment mViewSync;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((ReloadManager) getApplication()).getComponent().inject(this);

        mView = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentLogin);
        if (mView == null) {
            mView = LoginFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mView, R.id.contentLogin);
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

    @Override public void onSwitchView()
    {
//        mViewSync = (SyncFragment) getSupportFragmentManager().findFragmentById(R.id.contentLogin);
        if (mViewSync == null) {
            mViewSync = SyncFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mViewSync, R.id.contentLogin);
        }

        mViewSync.setPresenter(mSyncPresenter);
    }

    //    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//    @Override public void showLoading(final boolean show)
//    {
//        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
//        // for very easy animations. If available, use these APIs to fade-in
//        // the progress spinner.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
//
//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 0 : 1).setListener(new AnimatorListenerAdapter()
//            {
//                @Override
//                public void onAnimationEnd(Animator animation)
//                {
//                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//                }
//            });
//
//            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mProgressView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 1 : 0).setListener(new AnimatorListenerAdapter()
//            {
//                @Override
//                public void onAnimationEnd(Animator animation)
//                {
//                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//                }
//            });
//        } else {
//            // The ViewPropertyAnimator APIs are not available, so simply show
//            // and hide the relevant UI components.
//            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//        }
//    }
}