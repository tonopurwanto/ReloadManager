package com.neosolusi.reloadmanager.features.signup.verification;

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

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.ReloadManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerificationFragment extends Fragment implements VerificationContract.View
{
    @Inject VerificationContract.Presenter mPresenter;

    @BindView(R.id.toolbarVerification) Toolbar mToolbar;

    public VerificationFragment()
    {
        // Required empty public constructor
    }

    public static VerificationFragment getInstance()
    {
        return new VerificationFragment();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((ReloadManager) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_verification, container, false);

        ButterKnife.bind(this, view);

        configureLayout();

        return view;
    }

    private void configureLayout()
    {
        AppCompatActivity mActivity = (AppCompatActivity) getActivity();
        mActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Verifikasi");
        }
        setHasOptionsMenu(true);
    }

    @Override public void onResume()
    {
        super.onResume();

        mPresenter.setView(this);
    }

    @Override public void showErrorMessage(@NonNull String message)
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Failed")
                .setMessage(message)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_error_outline_red_700_24dp))
                .show();
    }
}
