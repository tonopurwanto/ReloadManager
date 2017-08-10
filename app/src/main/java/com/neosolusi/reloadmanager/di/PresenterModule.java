package com.neosolusi.reloadmanager.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.neosolusi.reloadmanager.data.CustomerRepo;
import com.neosolusi.reloadmanager.data.ReloadManagerApi;
import com.neosolusi.reloadmanager.data.customer.CustomerLocalDataSource;
import com.neosolusi.reloadmanager.data.customer.CustomerRemoteDataSource;
import com.neosolusi.reloadmanager.data.customer.CustomerRepository;
import com.neosolusi.reloadmanager.features.customer.CustomerContract;
import com.neosolusi.reloadmanager.features.customer.CustomerPresenter;
import com.neosolusi.reloadmanager.features.login.LoginContract;
import com.neosolusi.reloadmanager.features.login.LoginPresenter;
import com.neosolusi.reloadmanager.features.pos.TransactionContract;
import com.neosolusi.reloadmanager.features.pos.TransactionPresenter;
import com.neosolusi.reloadmanager.features.pos.grosir.MkiosContract;
import com.neosolusi.reloadmanager.features.pos.grosir.MkiosPresenter;
import com.neosolusi.reloadmanager.features.shared.download.DownloadContract;
import com.neosolusi.reloadmanager.features.signup.SignUpContract;
import com.neosolusi.reloadmanager.features.signup.SignUpPresenter;
import com.neosolusi.reloadmanager.features.signup.verification.VerificationContract;
import com.neosolusi.reloadmanager.features.signup.verification.VerificationPresenter;
import com.neosolusi.reloadmanager.features.sync.SyncContract;
import com.neosolusi.reloadmanager.features.sync.SyncPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule
{
    @Provides
    public LoginContract.Presenter provideLoginPresenter(@NonNull Context context, @NonNull ReloadManagerApi api, @NonNull SharedPreferences prefs)
    {
        return new LoginPresenter(context, api, prefs);
    }

    @Provides
    public SyncContract.Presenter provideSyncPresenter(@NonNull List<DownloadContract> dataSync, @NonNull EventBus bus)
    {
        return new SyncPresenter(dataSync, bus);
    }

    @Provides
    public CustomerContract.Presenter provideCustomerPresenter(@NonNull CustomerRepo repo, @NonNull EventBus bus)
    {
        return new CustomerPresenter(repo, bus);
    }

    @Provides
    public TransactionContract.Presenter provideTransactionPresenter()
    {
        return new TransactionPresenter();
    }

    @Provides
    public SignUpContract.Presenter provideSignUpPresenter()
    {
        return new SignUpPresenter();
    }

    @Provides
    public VerificationContract.Presenter provideVerificationPresenter()
    {
        return new VerificationPresenter();
    }

    @Provides
    public MkiosContract.Presenter provideMkiosPresenter(@NonNull CustomerRepository customerRepository)
    {
        return new MkiosPresenter(customerRepository);
    }
}