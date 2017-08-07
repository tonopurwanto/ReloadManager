package com.neosolusi.reloadmanager.features.signup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neosolusi.reloadmanager.R;

public class SignUpFragment extends Fragment implements SignUpContract.View
{
    private SignUpContract.Presenter mPresenter;

    public SignUpFragment()
    {
        // Required empty public constructor
    }

    public static SignUpFragment getInstance()
    {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override public void setPresenter(SignUpContract.Presenter presenter)
    {
        mPresenter = presenter;
    }
}
