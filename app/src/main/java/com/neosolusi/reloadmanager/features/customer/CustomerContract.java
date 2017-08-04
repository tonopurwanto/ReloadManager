package com.neosolusi.reloadmanager.features.customer;

import com.neosolusi.reloadmanager.models.Customer;

import java.util.List;

/**
 * Created by tonopurwanto on 30/06/17.
 */

public interface CustomerContract
{
    interface View
    {
        void showCustomers(List<Customer> customers);
        void showErrorMessage(String message);
        void refresh();
        void addCustomer();

    }

    interface Presenter
    {
        void attachView(CustomerContract.View customerView);
        void detachView();
        void loadCustomers();
    }
}
