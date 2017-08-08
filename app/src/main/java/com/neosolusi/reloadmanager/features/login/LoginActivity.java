package com.neosolusi.reloadmanager.features.login;

import android.os.Bundle;
import android.os.Handler;
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
    private SyncFragment mViewSync;
    private LoginFragment mViewLogin;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewLogin = (LoginFragment) getSupportFragmentManager().findFragmentByTag(LoginFragment.TAG);
        if (mViewLogin == null) {
            mViewLogin = LoginFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mViewLogin, R.id.contentLogin);
        }
    }

    @Override public void onSwitchView()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override public void run()
            {
                mViewSync = (SyncFragment) getSupportFragmentManager().findFragmentByTag(SyncFragment.TAG);
                if (mViewSync == null) {
                    mViewSync = SyncFragment.getInstance();
                    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mViewSync, R.id.contentLogin);
                }
            }
        }, 250);
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