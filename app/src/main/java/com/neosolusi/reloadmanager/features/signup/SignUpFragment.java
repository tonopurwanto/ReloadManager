package com.neosolusi.reloadmanager.features.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.features.signup.verification.VerificationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends Fragment implements SignUpContract.View
{
    private SignUpContract.Presenter mPresenter;
    private AppCompatActivity mActivity;

    @BindView(R.id.toolbarSignUp) Toolbar mToolbar;
    @BindView(R.id.textPhone) EditText mTextPhone;
    @BindView(R.id.buttonSignUp) Button mButtonSignUp;

    public SignUpFragment()
    {
        // Required empty public constructor
    }

    public static SignUpFragment getInstance()
    {
        return new SignUpFragment();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(this, view);

        configureLayout();

        return view;
    }

    @Override public void setPresenter(SignUpContract.Presenter presenter)
    {
        mPresenter = presenter;
    }

    @Override public void onAttach(Context context)
    {
        super.onAttach(context);

        mActivity = (AppCompatActivity) getActivity();
    }

    @Override public void onDetach()
    {
        mPresenter.detach();

        super.onDetach();
    }

    private void configureLayout()
    {
        mActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        setHasOptionsMenu(true);

        mActivity.getSupportActionBar().setTitle("Sign Up");
    }

    @Override public String getPhone()
    {
        return mTextPhone.getText().toString();
    }

    @OnClick(R.id.buttonSignUp)
    @Override public void signUp()
    {
        mPresenter.signUp();
    }

    @Override public void showErrorMessage(String message)
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Failed")
                .setMessage(message)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_error_outline_red_700_24dp))
                .show();
    }

    @Override public void showSignUpResult()
    {
        startActivity(new Intent(getActivity(), VerificationActivity.class));

        getActivity().finish();
    }
}
