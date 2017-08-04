package com.neosolusi.reloadmanager.data.customer;

import android.support.annotation.NonNull;

import com.neosolusi.reloadmanager.models.Customer;

import java.util.List;

public class CustomerRepository implements CustomerDataSource
{
    private static CustomerRepository INSTANCE = null;

    private CustomerDataSource mLocalDataSource;
    private CustomerDataSource mRemoteDataSource;

    private CustomerRepository(@NonNull CustomerDataSource localSource, @NonNull CustomerDataSource remoteSource)
    {
        this.mLocalDataSource = localSource;
        this.mRemoteDataSource = remoteSource;
    }

    public static CustomerRepository getInstance(@NonNull CustomerDataSource localSource, @NonNull CustomerDataSource remoteSource)
    {
        if (INSTANCE == null) {
            INSTANCE = new CustomerRepository(localSource, remoteSource);
        }

        return INSTANCE;
    }

    public void destroyInstance()
    {
        INSTANCE = null;
    }

    @Override public void getCustomers(final int page, @NonNull final LoadCustomersCallback callback)
    {
        mLocalDataSource.getCustomers(page, new LoadCustomersCallback()
        {
            @Override public void onCustomersLoaded(List<Customer> customers, String nextPage, int currentPage)
            {
                if (customers.isEmpty()) {
//                    getCustomersFromRemoteDataSource(page, callback);
                    callback.onLoadedFailed("No data");
                } else {
                    callback.onCustomersLoaded(customers, nextPage, currentPage);
                }
            }

            @Override public void onLoadedFailed(String message)
            {
                callback.onLoadedFailed(message);
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

    private void getCustomersFromRemoteDataSource(int page, @NonNull final LoadCustomersCallback callback)
    {
        mRemoteDataSource.getCustomers(page, new LoadCustomersCallback()
        {
            @Override public void onCustomersLoaded(List<Customer> customers, String nextPage, int currentPage)
            {
                if (! customers.isEmpty()) {
                    callback.onCustomersLoaded(customers, nextPage, currentPage);
                } else {
                    callback.onLoadedFailed("No Data");
                }
            }

            @Override public void onLoadedFailed(String message)
            {
                callback.onLoadedFailed(message);
            }
        });
    }
}
