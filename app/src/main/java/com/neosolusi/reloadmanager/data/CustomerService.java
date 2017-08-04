package com.neosolusi.reloadmanager.data;

import com.neosolusi.reloadmanager.features.shared.Wrapper;
import com.neosolusi.reloadmanager.features.shared.events.customer.CustomersLoadedSuccessfulEvent;
import com.neosolusi.reloadmanager.features.shared.events.customer.CustomersLoadingErrorEvent;
import com.neosolusi.reloadmanager.models.Customer;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class CustomerService
{
    private ReloadManagerApi mReloadManagerApi;
    private EventBus mEventBus;

    public CustomerService(ReloadManagerApi api, EventBus bus)
    {
        this.mReloadManagerApi = api;
        this.mEventBus = bus;
    }

    public void loadCustomers(final int page)
    {
        mReloadManagerApi.getCustomer(page).enqueue(new Callback<Wrapper<Customer>>()
        {
            @Override public void onResponse(Call<Wrapper<Customer>> call, Response<Wrapper<Customer>> response)
            {
                if (response.isSuccessful()) {
                    List<Customer> customers = response.body().data;
                    String nextPageUrl = response.body().nextPageUrl;
                    int currentPage = response.body().currentPage;

                    mEventBus.post(new CustomersLoadedSuccessfulEvent(customers, nextPageUrl, currentPage));
                    Timber.i("Customers data was loaded from API");
                } else {
                    mEventBus.post(new CustomersLoadingErrorEvent(response.message()));
                }
            }

            @Override public void onFailure(Call<Wrapper<Customer>> call, Throwable t)
            {
                mEventBus.post(new CustomersLoadingErrorEvent("Unable to download customers"));
                Timber.e(t, "Unable to load the customers data from API");
            }
        });
    }
}
