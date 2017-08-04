package com.neosolusi.reloadmanager.features.pos.grosir;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.models.Customer;
import com.neosolusi.reloadmanager.models.Product;

import java.util.List;

public class MkiosFragment extends Fragment implements MkiosContract.View
{
    private MkiosContract.Presenter mPresenter;

    public MkiosFragment()
    {
        // Required empty public constructor
    }

    public static MkiosFragment newInstance()
    {
        return new MkiosFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_mkios, container, false);
    }

    @Override public void onAttach(Context context)
    {
        super.onAttach(context);

        mPresenter.attach(this);
    }

    @Override public void onDetach()
    {
        mPresenter.detach();

        super.onDetach();
    }

    @Override public void onResume()
    {
        super.onResume();

        mPresenter.start();
    }

    @Override public void setPresenter(MkiosContract.Presenter presenter)
    {
        mPresenter = presenter;
    }

    @Override public void setCustomers(List<Customer> customers)
    {

    }

    @Override public void setProducts(List<Product> products)
    {

    }

    @Override public void showError(String message)
    {
        Snackbar.make(this.getView(), message, Snackbar.LENGTH_LONG).show();
    }
}
