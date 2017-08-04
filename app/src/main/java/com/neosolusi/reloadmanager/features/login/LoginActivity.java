package com.neosolusi.reloadmanager.features.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.features.main.MainActivity;
import com.neosolusi.reloadmanager.features.sync.SyncActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, TextView.OnEditorActionListener
{
    @Inject LoginContract.Presenter mPresenter;

    @BindView(R.id.email) AutoCompleteTextView mEmailView;
    @BindView(R.id.password) EditText mPasswordView;
    @BindView(R.id.login_progress) View mProgressView;
    @BindView(R.id.login_form) View mLoginFormView;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        ((ReloadManager) getApplication()).getComponent().inject(this);

        configureLayout();
    }

    private void configureLayout()
    {
        mPasswordView.setOnEditorActionListener(this);
    }

    @OnClick(R.id.email_sign_in_button)
    public void attemptLogin()
    {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Minimize keyboard.
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }

        mPresenter.signIn();
    }

    @Override public void showLoginFailed(String message)
    {
        new AlertDialog.Builder(this)
                .setTitle("Failed")
                .setMessage(message)
                .setIcon(ContextCompat.getDrawable(this, R.drawable.ic_error_outline_red_700_24dp))
                .show();
    }

    @Override public void showLoginSuccess()
    {
        Timber.d("Login success, begin synchronization");

        startActivity(new Intent(LoginActivity.this, SyncActivity.class));
        finish();
    }

    @Override public String getUsername()
    {
        return mEmailView.getText().toString();
    }

    @Override public String getPassword()
    {
        return mPasswordView.getText().toString();
    }

    @Override public void onResume()
    {
        super.onResume();

        mPresenter.attachView(this);
    }

    @Override public void onPause()
    {
        mPresenter.detachView();

        super.onPause();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu)
    {
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }

    @Override public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
    {
        if (actionId == R.id.login || actionId == EditorInfo.IME_NULL) {
            attemptLogin();

            return true;
        }

        return false;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override public void showLoading(final boolean show)
    {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}