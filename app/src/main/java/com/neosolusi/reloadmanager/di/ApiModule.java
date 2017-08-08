package com.neosolusi.reloadmanager.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.compat.BuildConfig;

import com.google.gson.GsonBuilder;
import com.neosolusi.reloadmanager.data.CustomerService;
import com.neosolusi.reloadmanager.data.ReloadManagerApi;
import com.neosolusi.reloadmanager.features.shared.download.DownloadCustomer;
import com.neosolusi.reloadmanager.features.shared.download.DownloadContract;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static com.neosolusi.reloadmanager.Constants.API_TOKEN;
import static com.neosolusi.reloadmanager.Constants.AUTHORIZATION;
import static com.neosolusi.reloadmanager.Constants.BASE_URL;
import static com.neosolusi.reloadmanager.Constants.RELOADMANAGER_ANDROID_APP;
import static com.neosolusi.reloadmanager.Constants.USER_AGENT;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

@Module
public class ApiModule
{
    @Provides @Singleton
    public Retrofit provideRetrofit(OkHttpClient client, GsonBuilder builder)
    {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build();
    }

    @Provides @Singleton
    public GsonBuilder provideGsonBuilder()
    {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Provides @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLog, Interceptor urlAndHeaderInterceptor, Cache httpCache)
    {
        return new OkHttpClient.Builder()
                .addInterceptor(urlAndHeaderInterceptor)
                .addInterceptor(httpLog)
                .cache(httpCache)
                .build();
    }

    @Provides @Singleton
    public Interceptor provideUrlAndHeaderInterceptor(final SharedPreferences pref)
    {
        return new Interceptor()
        {
            @Override public Response intercept(Chain chain) throws IOException
            {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder();
                builder.addHeader(USER_AGENT, RELOADMANAGER_ANDROID_APP);
                builder.addHeader(AUTHORIZATION, "Bearer " + pref.getString(API_TOKEN, ""));

                return chain.proceed(builder.build());
            }
        };
    }

    @Provides @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor()
    {
        HttpLoggingInterceptor httpLog = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger()
        {
            @Override public void log(String message)
            {
                Timber.d(message);
            }
        });

        httpLog.setLevel(BuildConfig.DEBUG ? BODY : NONE);

        return httpLog;
    }

    @Provides @Singleton
    public Cache provideCache(Context context)
    {
        Cache cache = null;

        try {
            cache = new Cache(new File(context.getCacheDir(), "http-cache"), 10 * 1024 * 1024);
        } catch (Exception e) {
            Timber.e(e, "Could not create cache!");
        }

        return cache;
    }

    @Provides @Singleton
    public ReloadManagerApi provideReloadManagerApi(Retrofit retrofit)
    {
        return retrofit.create(ReloadManagerApi.class);
    }

    @Provides
    public List<DownloadContract> provideListDataSync()
    {
        List<DownloadContract> services = new ArrayList<>();
        services.add(new DownloadCustomer());

        return services;
    }

    @Provides @Singleton
    public CustomerService provideCustomerService(ReloadManagerApi reloadManagerApi, EventBus bus)
    {
        return new CustomerService(reloadManagerApi, bus);
    }
}
