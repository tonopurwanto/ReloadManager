package com.neosolusi.reloadmanager.features.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
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
import com.neosolusi.reloadmanager.features.pos.grosir.MkiosActivity;
import com.neosolusi.reloadmanager.models.Customer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerFragment extends Fragment implements CustomerContract.View
{
    private List<Customer> mListCustomers;
    private CustomerAdapter mCustomerAdapter;

    @BindView(R.id.toolbarCustomer) Toolbar mToolbar;
    @BindView(R.id.listCustomer) RecyclerView mRecyclerView;

    @Inject CustomerContract.Presenter mPresenter;

    public CustomerFragment()
    {
        // Required empty public constructor
    }

    public static CustomerFragment getInstance()
    {
        return new CustomerFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((ReloadManager) getActivity().getApplication()).getComponent().inject(this);

        mCustomerAdapter = new CustomerAdapter(new CustomerAdapter.OnItemClickListener()
        {
            @Override public void onItemClick(View view, Customer customer)
            {
                Intent intent = new Intent(getActivity(), MkiosActivity.class);
                intent.putExtra(MkiosActivity.EXTRA_CUSTOMER_ID, customer.getId());
                startActivity(intent);
            }
        });
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_customer_fagment, container, false);

        ButterKnife.bind(this, view);

        configureLayout();

        return view;
    }

    @Override public void onResume()
    {
        super.onResume();

        mPresenter.setView(this);
    }

    @Override public void onPause()
    {
        mPresenter.unsetView();

        super.onPause();
    }

    @Override public void showCustomers(@NonNull List<Customer> customers)
    {
        mListCustomers = customers;
        mCustomerAdapter.updateCustomers(mListCustomers);
    }

    @Override public void showErrorMessage(@NonNull String message)
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
        AppCompatActivity mActivity = (AppCompatActivity) getActivity();
        mActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Select customer");
            actionBar.setSubtitle("24000 peoples");
        }
        setHasOptionsMenu(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
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
