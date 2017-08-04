package com.neosolusi.reloadmanager.features.pos.grosir;

import com.neosolusi.reloadmanager.data.customer.CustomerDataSource;
import com.neosolusi.reloadmanager.models.Customer;
import com.neosolusi.reloadmanager.models.Product;

import java.util.List;

public class MkiosPresenter implements MkiosContract.Presenter
{
    private MkiosContract.View mView;
    private CustomerDataSource mCustomerRepository;

    public MkiosPresenter(CustomerDataSource customerRepository)
    {
        this.mCustomerRepository = customerRepository;
    }

    @Override public void attach(MkiosContract.View view)
    {
        mView = view;
    }

    @Override public void detach()
    {
        mView = null;
    }

    @Override public void start()
    {
        loadCustomers();
        loadProducts();
    }

    @Override public void addProduct(Product product, double qty)
    {

    }

    @Override public void loadCustomers()
    {
        mCustomerRepository.getCustomers(1, new CustomerDataSource.LoadCustomersCallback()
        {
            @Override public void onCustomersLoaded(List<Customer> customers, String nextPage, int currentPage)
            {
                mView.setCustomers(customers);
            }

            @Override public void onLoadedFailed(String message)
            {
                mView.showError(message);
            }
        });
    }

    @Override public void loadProducts()
    {

    }

    @Override public void saveOrder()
    {

    }
}
