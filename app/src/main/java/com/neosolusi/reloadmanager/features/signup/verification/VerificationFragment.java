package com.neosolusi.reloadmanager.features.signup.verification;

import android.content.Context;
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

import com.neosolusi.reloadmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerificationFragment extends Fragment implements VerificationContract.View
{
    private VerificationContract.Presenter mPresenter;
    private AppCompatActivity mActivity;

    @BindView(R.id.toolbarVerification) Toolbar mToolbar;

    public VerificationFragment()
    {
        // Required empty public constructor
    }

    public static VerificationFragment getInstance()
    {
        return new VerificationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_verification, container, false);

        ButterKnife.bind(this, view);

        configureLayout();

        return view;
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

        mActivity.getSupportActionBar().setTitle("Verifikasi");
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

    @Override public void setPresenter(VerificationContract.Presenter presenter)
    {
        mPresenter = presenter;
    }

    @Override public void showErrorMessage(String message)
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Failed")
                .setMessage(message)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_error_outline_red_700_24dp))
                .show();
    }
}
