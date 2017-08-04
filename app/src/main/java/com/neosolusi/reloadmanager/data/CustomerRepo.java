package com.neosolusi.reloadmanager.data;

import android.content.Context;

import com.neosolusi.reloadmanager.features.customer.CustomerContract;
import com.neosolusi.reloadmanager.models.Customer;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class CustomerRepo
{
    private Realm mRealm;

    public CustomerRepo(Context context)
    {
//        Realm.init(context);
        mRealm = Realm.getDefaultInstance();
    }

    public List<Customer> loadCustomers()
    {
        return mRealm.where(Customer.class).findAll();
    }
}
