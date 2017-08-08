package com.neosolusi.reloadmanager.features.customer;

import android.support.annotation.NonNull;

import com.neosolusi.reloadmanager.data.CustomerRepo;
import com.neosolusi.reloadmanager.features.shared.events.customer.CustomersLoadedSuccessfulEvent;
import com.neosolusi.reloadmanager.features.shared.events.customer.CustomersLoadingErrorEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.google.common.base.Preconditions.checkNotNull;

public class CustomerPresenter implements CustomerContract.Presenter
{
    private CustomerContract.View mCustomerView;
    private CustomerRepo mCustomerRepo;
    private EventBus mEventBus;

    public CustomerPresenter(@NonNull CustomerRepo repo, @NonNull EventBus bus)
    {
        this.mCustomerRepo = checkNotNull(repo);
        this.mEventBus = checkNotNull(bus);
    }

    @Override public void setView(@NonNull CustomerContract.View customerView)
    {
        this.mCustomerView = checkNotNull(customerView);

        mEventBus.register(this);

        loadCustomers();
    }

    @Override public void unsetView()
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