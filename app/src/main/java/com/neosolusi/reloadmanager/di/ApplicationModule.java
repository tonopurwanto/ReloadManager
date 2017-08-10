package com.neosolusi.reloadmanager.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.neosolusi.reloadmanager.BuildConfig;
import com.neosolusi.reloadmanager.data.CustomerRepo;
import com.neosolusi.reloadmanager.data.customer.CustomerLocalDataSource;
import com.neosolusi.reloadmanager.data.customer.CustomerRemoteDataSource;
import com.neosolusi.reloadmanager.data.customer.CustomerRepository;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule
{
    private Application application;

    public ApplicationModule(Application application)
    {
        this.application = application;
    }

    @Provides @Singleton
    public Context provideContext()
    {
        return this.application;
    }

    @Provides @Singleton
    public SharedPreferences provideSharedPreferences(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides @Singleton
    public EventBus provideEventBus()
    {
        return EventBus.builder()
                .logNoSubscriberMessages(BuildConfig.DEBUG)
                .sendNoSubscriberEvent(BuildConfig.DEBUG)
                .build();
    }

    @Provides @Singleton
    public CustomerLocalDataSource provideCustomerLocalDataSource()
    {
        return new CustomerLocalDataSource();
    }

    @Provides @Singleton
    public CustomerRemoteDataSource provideCustomerRemoteDataSource()
    {
        return new CustomerRemoteDataSource();
    }

    @Provides @Singleton
    public CustomerRepository provideCustomerRepository(@NonNull CustomerLocalDataSource localSource, @NonNull CustomerRemoteDataSource remoteSource)
    {
        return CustomerRepository.getInstance(localSource, remoteSource);
    }

    @Provides @Singleton
    public CustomerRepo provideCustomerRepo(Context context)
    {
        return new CustomerRepo(context);
    }
}
