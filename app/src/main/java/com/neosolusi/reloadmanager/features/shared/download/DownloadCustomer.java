package com.neosolusi.reloadmanager.features.shared.download;

import android.content.Context;

import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.data.CustomerService;
import com.neosolusi.reloadmanager.features.shared.events.customer.CustomersLoadedSuccessfulEvent;
import com.neosolusi.reloadmanager.features.shared.events.customer.CustomersLoadingErrorEvent;
import com.neosolusi.reloadmanager.features.shared.events.download.DownloadCompleteEvent;
import com.neosolusi.reloadmanager.features.shared.events.download.DownloadFailedEvent;
import com.neosolusi.reloadmanager.models.Customer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.realm.Realm;

public class DownloadCustomer implements DownloadContract
{
    @Inject Context mContext;
    @Inject CustomerService mCustomerService;
    @Inject EventBus mEventBus;
    private Realm mRealm;
    private int mRecordCount;

    public DownloadCustomer()
    {
        ReloadManager.getInstance().getComponent().inject(this);

        Realm.init(mContext);
        this.mRealm = Realm.getDefaultInstance();
        this.mRecordCount = 0;
    }

    @Override public String tag()
    {
        return "Downloading Customers";
    }

    @Override public void process()
    {
        mEventBus.register(this);
        mCustomerService.loadCustomers(1);
    }

    @Subscribe public void onEvent(CustomersLoadedSuccessfulEvent event)
    {
        for (Customer data : event.getCustomers()) {
            mRealm.beginTransaction();
            long id = UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE;
            Customer customer = mRealm.createObject(Customer.class, id);
            customer.setCustid(data.getCustid());
            customer.setCustname(data.getCustname());
            customer.setHpupline(data.getHpupline());
            customer.setPrice(data.getPrice());
            customer.setAlamat(data.getAlamat());
            customer.setKecamatan(data.getKecamatan());
            customer.setKelurahan(data.getKelurahan());
            customer.setKabupaten(data.getKabupaten());
            mRealm.commitTransaction();
        }

//        mRealm.beginTransaction();
//        mRealm.copyToRealm(event.getCustomers());
//        mRealm.commitTransaction();

        mRecordCount += event.getCustomers().size();

        if (event.getNextUrl() != null) {
            mCustomerService.loadCustomers(event.getNextPage());
        } else {
            mEventBus.post(new DownloadCompleteEvent(mRecordCount));
            mEventBus.unregister(this);
        }
    }

    @Subscribe public void onEvent(CustomersLoadingErrorEvent event)
    {
        mEventBus.post(new DownloadFailedEvent(event.getMessage()));
        mEventBus.unregister(this);
    }
}