package com.neosolusi.reloadmanager.features.pos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neosolusi.reloadmanager.R;

import java.util.List;

public class TransactionFragment extends Fragment implements TransactionContract.View
{

    public TransactionFragment()
    {
        // Required empty public constructor
    }

    public static TransactionFragment getInstance()
    {
        return new TransactionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }

    @Override public void showMenus(List<TransactionItem> menus)
    {

    }

    @Override public void showErrorMessage(String message)
    {

    }
}
