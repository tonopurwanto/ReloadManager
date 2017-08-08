package com.neosolusi.reloadmanager.features.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neosolusi.reloadmanager.R;

public class LoginSplashFragment extends Fragment implements LoginContract.View
{
    public LoginSplashFragment()
    {
        // Required empty public constructor
    }

    public static LoginSplashFragment getInstance()
    {
        return new LoginSplashFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_login_splash, container, false);
    }

    @Override public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override public void onDetach()
    {
        super.onDetach();
    }

    @Override public void setPresenter(LoginContract.Presenter presenter)
    {

    }

    @Override public void showLoginSuccess()
    {

    }

    @Override public void showLoginFailed(String message)
    {

    }

    @Override public void showLoading(boolean show)
    {

    }

    @Override public void showSignUp()
    {

    }

    @Override public void setActiveForm(boolean active)
    {

    }

    @Override public String getUsername()
    {
        return null;
    }

    @Override public String getPassword()
    {
        return null;
    }
}
