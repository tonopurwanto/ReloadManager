package com.neosolusi.reloadmanager.data.customer;

import android.support.annotation.NonNull;

import com.neosolusi.reloadmanager.models.Customer;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;

public class CustomerLocalDataSource implements CustomerDataSource
{
    private Realm mRealm;

    public CustomerLocalDataSource()
    {
        mRealm = Realm.getDefaultInstance();
    }

    @Override public void getCustomers(int page, @NonNull LoadCustomersCallback callback)
    {
        List<Customer> customers = mRealm.where(Customer.class).findAll();

        if (! customers.isEmpty()) {
            callback.onCustomersLoaded(customers, "", 1);
        } else {
            callback.onLoadedFailed("No data");
        }
    }

    @Override public void getCustomer(@NonNull String custid, @NonNull LoadCustomerCallback callback)
    {
        Customer customer = mRealm.where(Customer.class).equalTo("custid", custid).findFirst();

        if (customer != null) {
            callback.onCustomerLoaded(customer);
        } else {
            callback.onLoadedFailed();
        }
    }

    @Override public void saveCustomer(@NonNull Customer customer)
    {
        long id = UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE;

        mRealm.beginTransaction();
        mRealm.createObject(Customer.class, id);
        mRealm.commitTransaction();
    }

    @Override public void refreshCustomers()
    {
        // Not required because the {@link TasksRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }
}
