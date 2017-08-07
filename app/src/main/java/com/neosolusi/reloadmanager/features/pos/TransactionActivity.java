package com.neosolusi.reloadmanager.features.pos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.features.shared.ActivityUtils;

public class TransactionActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        TransactionFragment fragment = TransactionFragment.getInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.content);

        TransactionContract.Presenter presenter = new TransactionPresenter();

        fragment.setPresenter(presenter);
    }
}
