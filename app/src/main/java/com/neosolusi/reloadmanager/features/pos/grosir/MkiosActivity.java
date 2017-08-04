package com.neosolusi.reloadmanager.features.pos.grosir;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.data.customer.CustomerDataSource;
import com.neosolusi.reloadmanager.data.customer.CustomerLocalDataSource;
import com.neosolusi.reloadmanager.data.customer.CustomerRemoteDataSource;
import com.neosolusi.reloadmanager.data.customer.CustomerRepository;

public class MkiosActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mkios);

        MkiosFragment fragment = MkiosFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, fragment);
        transaction.commit();

        CustomerDataSource localDataSource = new CustomerLocalDataSource();
        CustomerDataSource remoteDataSource = new CustomerRemoteDataSource();

        MkiosPresenter presenter = new MkiosPresenter(CustomerRepository.getInstance(localDataSource, remoteDataSource));

        fragment.setPresenter(presenter);
    }
}
