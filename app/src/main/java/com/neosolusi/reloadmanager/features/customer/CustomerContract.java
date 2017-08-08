package com.neosolusi.reloadmanager.features.customer;

import android.support.annotation.NonNull;

import com.neosolusi.reloadmanager.models.Customer;

import java.util.List;

public interface CustomerContract
{
    interface View
    {
        void showCustomers(@NonNull List<Customer> customers);
        void showErrorMessage(@NonNull String message);
        void refresh();
        void addCustomer();

    }

    interface Presenter
    {
        void setView(@NonNull CustomerContract.View customerView);
        void unsetView();
        void loadCustomers();
    }
}
