package com.neosolusi.reloadmanager.features.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.data.ReloadManagerApi;
import com.neosolusi.reloadmanager.features.shared.Wrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.neosolusi.reloadmanager.Constants.API_TOKEN;

public class LoginPresenter implements LoginContract.Presenter
{
    private Context mContext;
    private ReloadManagerApi mReloadManagerApi;
    private SharedPreferences mPreferences;
    private LoginContract.View mLoginView;

    public LoginPresenter(Context context, ReloadManagerApi api, SharedPreferences prefs)
    {
        this.mContext = context;
        this.mReloadManagerApi = api;
        this.mPreferences = prefs;
    }

    private boolean isUsernameValid()
    {
        return mLoginView.getUsername().contains("@");
    }

    private boolean isPasswordValid()
    {
        return mLoginView.getPassword().length() > 4;
    }

    @Override public void signIn()
    {
        String username = mLoginView.getUsername();
        String password = mLoginView.getPassword();

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(username) && !isPasswordValid()) {
            mLoginView.showLoginFailed(mContext.getString(R.string.error_invalid_password));
            return;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mLoginView.showLoginFailed(mContext.getString(R.string.error_field_required));
            return;
        } else if (!isUsernameValid()) {
            mLoginView.showLoginFailed(mContext.getString(R.string.error_invalid_email));
            return;
        }

        // Show a progress spinner, and kick off a background task to
        // perform the user login attempt.
        mLoginView.showLoading(true);

        mReloadManagerApi.login(username, password).enqueue(new Callback<Wrapper>()
        {
            @Override public void onResponse(Call<Wrapper> call, Response<Wrapper> response)
            {
                if (response.isSuccessful()) {
                    SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putString(API_TOKEN, response.headers().get("token"));
                    editor.apply();

                    mLoginView.showLoginSuccess();

                    Timber.i("Login success with token: " + mPreferences.getString(API_TOKEN, ""));
                } else {
                    mLoginView.showLoginFailed(response.message());

                    Timber.e("Login failed", "code: " + response.code() + " message: " + response.message());
                }

                mLoginView.showLoading(false);
            }

            @Override public void onFailure(Call<Wrapper> call, Throwable t)
            {
                mLoginView.showLoading(false);
                mLoginView.showLoginFailed("Login Failed");

                Timber.e(t, "Unable to load the customers data from API");
            }
        });
    }

    @Override public void attachView(LoginContract.View loginView)
    {
        mLoginView = loginView;
    }

    @Override public void detachView()
    {
        mLoginView = null;
    }
}
