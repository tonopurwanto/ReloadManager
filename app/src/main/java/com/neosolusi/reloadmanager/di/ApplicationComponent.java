package com.neosolusi.reloadmanager.di;

import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.data.customer.CustomerRemoteDataSource;
import com.neosolusi.reloadmanager.features.customer.CustomerActivity;
import com.neosolusi.reloadmanager.features.customer.CustomerFagment;
import com.neosolusi.reloadmanager.features.login.LoginActivity;
import com.neosolusi.reloadmanager.features.main.MainActivity;
import com.neosolusi.reloadmanager.features.pos.TransactionActivity;
import com.neosolusi.reloadmanager.features.shared.download.DownloadCustomer;
import com.neosolusi.reloadmanager.features.signup.SignUpActivity;
import com.neosolusi.reloadmanager.features.signup.verification.VerificationActivity;
import com.neosolusi.reloadmanager.features.sync.SyncActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, PresenterModule.class})
public interface ApplicationComponent
{
    void inject(ReloadManager target);

    void inject(LoginActivity target);
    void inject(MainActivity target);
    void inject(SyncActivity target);
    void inject(CustomerActivity target);
    void inject(TransactionActivity target);
    void inject(SignUpActivity target);
    void inject(VerificationActivity target);
    void inject(CustomerFagment target);

    void inject(CustomerRemoteDataSource target);

    // Data Sync
    void inject(DownloadCustomer target);
}
