package com.neosolusi.reloadmanager.data.customer;

import android.support.annotation.NonNull;

import com.neosolusi.reloadmanager.models.Customer;

import java.util.List;

public interface CustomerDataSource
{
    interface LoadCustomersCallback
    {
        void onCustomersLoaded(List<Customer> customers, String nextPage, int currentPage);
        void onLoadedFailed(String message);
    }

    interface LoadCustomerCallback
    {
        void onCustomerLoaded(Customer customers);
        void onLoadedFailed();
    }

    void getCustomers(int page, @NonNull LoadCustomersCallback callback);
    void getCustomer(@NonNull String custid, @NonNull LoadCustomerCallback callback);
    void saveCustomer(@NonNull Customer customer);
    void refreshCustomers();
}
