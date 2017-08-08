package com.neosolusi.reloadmanager.di;

import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.data.customer.CustomerRemoteDataSource;
import com.neosolusi.reloadmanager.features.customer.CustomerFragment;
import com.neosolusi.reloadmanager.features.login.LoginFragment;
import com.neosolusi.reloadmanager.features.pos.TransactionFragment;
import com.neosolusi.reloadmanager.features.shared.download.DownloadCustomer;
import com.neosolusi.reloadmanager.features.signup.SignUpFragment;
import com.neosolusi.reloadmanager.features.signup.verification.VerificationFragment;
import com.neosolusi.reloadmanager.features.sync.SyncFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, PresenterModule.class})
public interface ApplicationComponent
{
    void inject(ReloadManager target);

    // View Model
    void inject(LoginFragment target);
    void inject(SyncFragment target);
    void inject(TransactionFragment target);
    void inject(CustomerFragment target);
    void inject(SignUpFragment target);
    void inject(VerificationFragment target);

    void inject(CustomerRemoteDataSource target);

    // Data Sync
    void inject(DownloadCustomer target);
}
