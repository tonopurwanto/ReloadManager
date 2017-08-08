package com.neosolusi.reloadmanager.features.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.features.signup.SignUpActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginFragment extends Fragment implements LoginContract.View, TextView.OnEditorActionListener
{
    public static final String TAG = "LoginFragment";

    @Inject LoginContract.Presenter mPresenter;

    @BindView(R.id.input_username) EditText mTextUsername;
    @BindView(R.id.input_password) EditText mTextPassword;
    @BindView(R.id.txt_signup) TextView mTextSignUp;
    @BindView(R.id.btn_login) Button mButtonLogin;

    @BindView(R.id.progressBarLogin) ProgressBar mProgressBar;
    private OnFragmentInteractionListener mActivityListener;

    public LoginFragment()
    {
        // Required empty public constructor
    }

    public static LoginFragment getInstance()
    {
        return new LoginFragment();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((ReloadManager) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);

        mTextUsername.setText("tono.purwanto@live.com");
        mTextPassword.setText("makanan");

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

    @Override public void onResume()
    {
        super.onResume();

        mPresenter.setView(this);
    }

    @Override public void onAttach(Context context)
    {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mActivityListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override public void onDetach()
    {
        mActivityListener = null;

        super.onDetach();
    }

    @Override public void showLoginSuccess()
    {
        Timber.d("Login success, begin synchronization");

        mActivityListener.onSwitchView();

//        startActivity(new Intent(getActivity(), SyncActivity.class));
//        getActivity().finish();
    }

    @Override public void showLoginFailed(@NonNull String message)
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Failed")
                .setMessage(message)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_error_outline_red_700_24dp))
                .show();
    }

    @Override public void showLoading(final boolean show)
    {
        getActivity().runOnUiThread(new Runnable()
        {
            @Override public void run()
            {
                if (show) {
                    mProgressBar.setVisibility(ProgressBar.VISIBLE);
                    mButtonLogin.setText(getResources().getString(R.string.btn_log_in_loading));
                } else {
                    mProgressBar.setVisibility(ProgressBar.GONE);
                    mButtonLogin.setText(getResources().getString(R.string.btn_log_in));
                }
            }
        });
    }

    @Override public void showSignUp()
    {
        startActivity(new Intent(getActivity(), SignUpActivity.class));
    }

    @Override public void setActiveForm(boolean active)
    {
        mButtonLogin.setEnabled(active);
        mTextUsername.setEnabled(active);
        mTextPassword.setEnabled(active);
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

    public interface OnFragmentInteractionListener
    {
        void onSwitchView();
    }
}
