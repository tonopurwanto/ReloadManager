package com.neosolusi.reloadmanager.features.shared.events.customer;

import com.neosolusi.reloadmanager.models.Customer;

import java.util.List;

public class CustomersLoadedSuccessfulEvent
{
    private List<Customer> customers;
    private String nextUrl;
    private int nextPage;

    public CustomersLoadedSuccessfulEvent(List<Customer> customers, String nextUrl, int currentPage)
    {
        this.customers = customers;
        this.nextUrl = nextUrl;
        this.nextPage = currentPage + 1;
    }

    public List<Customer> getCustomers()
    {
        return customers;
    }

    public String getNextUrl()
    {
        return nextUrl;
    }

    public int getNextPage()
    {
        return nextPage;
    }
}
