package com.neosolusi.reloadmanager.features.pos.grosir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.neosolusi.reloadmanager.R;

public class MkiosActivity extends AppCompatActivity
{
    public static final String EXTRA_CUSTOMER_ID = "EXTRA_CUSTOMER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mkios);
    }
}
