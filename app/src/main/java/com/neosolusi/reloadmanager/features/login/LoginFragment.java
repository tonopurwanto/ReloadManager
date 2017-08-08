package com.neosolusi.reloadmanager.features.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.features.signup.SignUpActivity;
import com.neosolusi.reloadmanager.features.sync.SyncActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginFragment extends Fragment implements LoginContract.View, TextView.OnEditorActionListener
{
    @BindView(R.id.input_username) EditText mTextUsername;
    @BindView(R.id.input_password) EditText mTextPassword;
    @BindView(R.id.txt_signup) TextView mTextSignUp;

    private LoginContract.Presenter mPresenter;

    public LoginFragment()
    {
        // Required empty public constructor
    }

    public static LoginFragment getInstance()
    {
        return new LoginFragment();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);

        mTextPassword.setOnEditorActionListener(this);

        mTextSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                mPresenter.signUp();
            }
        });

        return view;
    }

    @Override public void showLoginSuccess()
    {
        Timber.d("Login success, begin synchronization");

        startActivity(new Intent(getActivity(), SyncActivity.class));
        getActivity().finish();
    }

    @Override public void showLoginFailed(String message)
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Failed")
                .setMessage(message)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_error_outline_red_700_24dp))
                .show();
    }

    @Override public void showLoading(boolean show)
    {
        // Empty
    }

    @Override public void showSignUp()
    {
        startActivity(new Intent(getActivity(), SignUpActivity.class));
    }

    @Override public String getUsername()
    {
        return mTextUsername.getText().toString();
    }

    @Override public String getPassword()
    {
        return mTextPassword.getText().toString();
    }

    @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
    {
        if (actionId == R.id.login || actionId == EditorInfo.IME_NULL) {
            attemptLogin();

            return true;
        }

        return false;
    }

    @OnClick(R.id.btn_login)
    public void attemptLogin()
    {
        // Reset errors.
        mTextUsername.setError(null);
        mTextPassword.setError(null);

        // Minimize keyboard.
        View currentFocus = getActivity().getCurrentFocus();
        if (currentFocus != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }

        mPresenter.signIn();
    }

    @Override public void setPresenter(LoginContract.Presenter presenter)
    {
        mPresenter = presenter;
    }
}
