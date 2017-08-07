package com.neosolusi.reloadmanager.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.neosolusi.reloadmanager.data.CustomerRepo;
import com.neosolusi.reloadmanager.data.ReloadManagerApi;
import com.neosolusi.reloadmanager.features.customer.CustomerContract;
import com.neosolusi.reloadmanager.features.customer.CustomerPresenter;
import com.neosolusi.reloadmanager.features.login.LoginContract;
import com.neosolusi.reloadmanager.features.login.LoginPresenter;
import com.neosolusi.reloadmanager.features.pos.TransactionContract;
import com.neosolusi.reloadmanager.features.pos.TransactionPresenter;
import com.neosolusi.reloadmanager.features.shared.download.DownloadContract;
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
    public LoginContract.Presenter provideLoginPresenter(Context context, ReloadManagerApi api, SharedPreferences prefs)
    {
        return new LoginPresenter(context, api, prefs);
    }

    @Provides
    public SyncContract.Presenter provideSyncPresenter(List<DownloadContract> dataSync, EventBus bus)
    {
        return new SyncPresenter(dataSync, bus);
    }

    @Provides
    public CustomerContract.Presenter provideCustomerPresenter(CustomerRepo repo, EventBus bus)
    {
        return new CustomerPresenter(repo, bus);
    }

    @Provides
    public TransactionContract.Presenter provideTransactionPresenter()
    {
        return new TransactionPresenter();
    }
}