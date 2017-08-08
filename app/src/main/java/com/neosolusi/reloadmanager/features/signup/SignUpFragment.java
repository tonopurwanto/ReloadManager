package com.neosolusi.reloadmanager.features.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.TextView;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.features.signup.verification.VerificationActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends Fragment implements SignUpContract.View
{
    @Inject SignUpContract.Presenter mPresenter;

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

    @Override public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((ReloadManager) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(this, view);

        configureLayout();

        return view;
    }

    @Override public void onResume()
    {
        super.onResume();

        mPresenter.setView(this);
    }

    private void configureLayout()
    {
        AppCompatActivity mActivity = (AppCompatActivity) getActivity();
        mActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Sign Up");
        }
        setHasOptionsMenu(true);
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

    @Override public void showErrorMessage(@NonNull String message)
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
    }

    @Override public void showSignUpDialog(@NonNull String phone)
    {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = this.getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.sign_up_dialog, null);

        dialogBuilder.setView(view);
        dialogBuilder.setCancelable(false);

        final AlertDialog dialog = dialogBuilder.create();

        Button btnEdit = (Button) view.findViewById(R.id.buttonEdit);
        Button btnOK = (Button) view.findViewById(R.id.buttonOK);
        TextView textPhone = (TextView) view.findViewById(R.id.textSignUpPhone);

        textPhone.setText(phone);

        btnEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                dialog.dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                dialog.dismiss();
                startActivity(new Intent(getActivity(), VerificationActivity.class));
            }
        });

        dialog.show();
    }
}
