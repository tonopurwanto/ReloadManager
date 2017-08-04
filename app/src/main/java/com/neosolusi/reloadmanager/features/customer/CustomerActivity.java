package com.neosolusi.reloadmanager.features.customer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.features.shared.ActivityUtils;

import javax.inject.Inject;

public class CustomerActivity extends AppCompatActivity
{
    @Inject CustomerContract.Presenter mPresenter;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        CustomerFagment fragment = CustomerFagment.getInstance();

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.content);
    }

    @Override protected void onResume()
    {
        super.onResume();
    }

    @Override protected void onPause()
    {
        super.onPause();
    }

//    @Override public boolean onCreateOptionsMenu(Menu menu)
//    {
//        getMenuInflater().inflate(R.menu.customer, menu);
//
//        return true;
//    }
//
//    @Override public boolean onOptionsItemSelected(MenuItem item)
//    {
//        return super.onOptionsItemSelected(item);
//    }
}
