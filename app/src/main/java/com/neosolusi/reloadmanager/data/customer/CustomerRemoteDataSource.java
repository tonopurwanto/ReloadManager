package com.neosolusi.reloadmanager.data.customer;

import android.support.annotation.NonNull;

import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.data.ReloadManagerApi;
import com.neosolusi.reloadmanager.features.shared.Wrapper;
import com.neosolusi.reloadmanager.features.shared.events.customer.CustomersLoadedSuccessfulEvent;
import com.neosolusi.reloadmanager.features.shared.events.customer.CustomersLoadingErrorEvent;
import com.neosolusi.reloadmanager.models.Customer;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class CustomerRemoteDataSource implements CustomerDataSource
{
    @Inject ReloadManagerApi mReloadManagerApi;

    public CustomerRemoteDataSource()
    {
        ReloadManager.getInstance().getComponent().inject(this);
    }

    @Override public void getCustomers(int page, @NonNull final LoadCustomersCallback callback)
    {
        mReloadManagerApi.getCustomer(page).enqueue(new Callback<Wrapper<Customer>>()
        {
            @Override public void onResponse(Call<Wrapper<Customer>> call, Response<Wrapper<Customer>> response)
            {
                if (response.isSuccessful()) {
                    List<Customer> customers = response.body().data;
                    String nextPageUrl = response.body().nextPageUrl;
                    int currentPage = response.body().currentPage;

                    callback.onCustomersLoaded(customers, nextPageUrl, currentPage);
                } else {
                    callback.onLoadedFailed(response.message());
                }
            }

            @Override public void onFailure(Call<Wrapper<Customer>> call, Throwable t)
            {
                callback.onLoadedFailed("Unable to download customers");
            }
        });
    }

    @Override public void getCustomer(@NonNull String custid, @NonNull LoadCustomerCallback callback)
    {

    }

    @Override public void saveCustomer(@NonNull Customer customer)
    {

    }

    @Override public void refreshCustomers()
    {

    }
}
