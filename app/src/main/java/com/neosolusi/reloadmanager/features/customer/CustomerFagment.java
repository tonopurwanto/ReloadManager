package com.neosolusi.reloadmanager.features.customer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.models.Customer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerFagment extends Fragment implements CustomerContract.View
{
    private List<Customer> mListCustomers;
    private CustomerAdapter mCustomerAdapter;
    private AppCompatActivity mActivity;

    @BindView(R.id.toolbarCustomer) Toolbar mToolbar;
    @BindView(R.id.listCustomer) RecyclerView mRecyclerView;

    @Inject CustomerContract.Presenter mPresenter;

    public CustomerFagment()
    {
        // Required empty public constructor
    }

    public static CustomerFagment getInstance()
    {
        return new CustomerFagment();
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((ReloadManager) getActivity().getApplication()).getComponent().inject(this);

        mCustomerAdapter = new CustomerAdapter();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_customer_fagment, container, false);

        ButterKnife.bind(this, view);

        configureLayout();

        mPresenter.attachView(this);
        mPresenter.loadCustomers();

        return view;
    }

    @Override public void onAttach(Context context)
    {
        super.onAttach(context);

        mActivity = (AppCompatActivity) getActivity();
    }

    @Override public void onDetach()
    {
        mPresenter.detachView();

        super.onDetach();
    }

    @Override public void showCustomers(List<Customer> customers)
    {
        mListCustomers = customers;
        mCustomerAdapter.updateCustomers(mListCustomers);
    }

    @Override public void showErrorMessage(String message)
    {

    }

    @Override public void refresh()
    {
        mPresenter.loadCustomers();
    }

    @Override public void addCustomer()
    {

    }

    private void configureLayout()
    {
        mActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        setHasOptionsMenu(true);

        mToolbar.setTitle("Select customer");
        mToolbar.setSubtitle("24000 peoples");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(),
//                DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mCustomerAdapter);
        mRecyclerView.setHasFixedSize(true);

    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.customer, menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }
}
