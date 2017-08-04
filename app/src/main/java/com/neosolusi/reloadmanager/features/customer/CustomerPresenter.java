package com.neosolusi.reloadmanager.features.customer;

import com.neosolusi.reloadmanager.data.CustomerRepo;
import com.neosolusi.reloadmanager.features.shared.events.customer.CustomersLoadedSuccessfulEvent;
import com.neosolusi.reloadmanager.features.shared.events.customer.CustomersLoadingErrorEvent;
import com.neosolusi.reloadmanager.features.shared.events.download.DownloadCompleteEvent;
import com.neosolusi.reloadmanager.features.shared.events.download.DownloadFailedEvent;
import com.neosolusi.reloadmanager.models.Customer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class CustomerPresenter implements CustomerContract.Presenter
{
    private CustomerContract.View mCustomerView;
    private CustomerRepo mCustomerRepo;
    private EventBus mEventBus;

    public CustomerPresenter(CustomerRepo repo, EventBus bus)
    {
        this.mCustomerRepo = repo;
        this.mEventBus = bus;
    }

    @Override public void attachView(CustomerContract.View customerView)
    {
        this.mCustomerView = customerView;

        mEventBus.register(this);
    }

    @Override public void detachView()
    {
        this.mCustomerView = null;

        mEventBus.unregister(this);
    }

    @Override public void loadCustomers()
    {
        mCustomerView.showCustomers(mCustomerRepo.loadCustomers());
    }

    @Subscribe public void onEvent(CustomersLoadedSuccessfulEvent event)
    {
        // Empty
    }

    @Subscribe public void onEvent(CustomersLoadingErrorEvent event)
    {
        // Empty
    }
}