package com.neosolusi.reloadmanager;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.facebook.stetho.Stetho;
import com.neosolusi.reloadmanager.di.ApplicationComponent;
import com.neosolusi.reloadmanager.di.ApplicationModule;
import com.neosolusi.reloadmanager.di.DaggerApplicationComponent;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class ReloadManager extends Application
{
    private static ReloadManager instance;
    private ApplicationComponent component;

    @Override public void onCreate()
    {
        super.onCreate();

        instance = this;

        // Dagger configuration
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(instance)).build();

        // Realm configuration
        Realm.init(instance);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("reloadmanager.realm")
                .build();
        Realm.deleteRealm(realmConfiguration); // Clean slate
        Realm.setDefaultConfiguration(realmConfiguration);

        // Stetho Realm browser configuration
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        // Timber configuration
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        Timber.i("Creating our Application");
    }

    public static ReloadManager getInstance()
    {
        return instance;
    }

    public ApplicationComponent getComponent()
    {
        return component;
    }

    public boolean checkIfHasNetwork()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean hasNetwork()
    {
        return instance.checkIfHasNetwork();
    }
}
