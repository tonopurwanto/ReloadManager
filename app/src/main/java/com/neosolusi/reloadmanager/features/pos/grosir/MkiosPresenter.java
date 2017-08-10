package com.neosolusi.reloadmanager.features.pos.grosir;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.neosolusi.reloadmanager.data.customer.CustomerDataSource;
import com.neosolusi.reloadmanager.models.Customer;
import com.neosolusi.reloadmanager.models.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;

import static com.google.common.base.Preconditions.checkNotNull;

public class MkiosPresenter implements MkiosContract.Presenter
{
    private MkiosContract.View mView;
    private CustomerDataSource mCustomerRepository;
    private Realm mRealm;
    private List<Order> mDataOrder = new ArrayList<>();
    private double mGrandTotal;

    public MkiosPresenter(CustomerDataSource customerRepository)
    {
        this.mCustomerRepository = customerRepository;
    }

    private void start(@Nullable Long id)
    {
        mRealm = Realm.getDefaultInstance();

        if (id != null && id != 0) {
            Customer customer = mRealm.where(Customer.class).equalTo("id", id).findFirst();
            mView.displayCustomerHp(customer.getCustid());
            mView.displayCustomerName(customer.getCustname());
        }

        loadCustomers();
        loadProducts();

        List<Product> products = new ArrayList<>();
        products.add(new Product(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "S5", "SIMPATI 5000", 5200));
        products.add(new Product(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "S10", "SIMPATI 10000", 10100));
        products.add(new Product(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "S20", "SIMPATI 20000", 20000));
        products.add(new Product(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "S25", "SIMPATI 25000", 25000));
        products.add(new Product(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "S50", "SIMPATI 50000", 49000));
        products.add(new Product(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "S100", "SIMPATI 100000", 97500));

        mView.showOrder(products);
    }

    @Override public void addProduct(@NonNull Product product, double qty)
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
                mView.showErrorMessage(message);
            }
        });
    }

    @Override public void loadProducts()
    {

    }

    @Override public void calculate(@NonNull Product product, @NonNull Double quantity)
    {
        Order order = new Order(product, quantity);

        int findOrder = Collections.binarySearch(mDataOrder, order, null);
        if (findOrder >= 0) {
            mDataOrder.get(mDataOrder.indexOf(order)).qty = quantity;
        } else {
            mDataOrder.add(order);
        }

        mGrandTotal = 0;
        for (Order loopOrder : mDataOrder) {
            mGrandTotal += loopOrder.qty * loopOrder.product.getPrice();
        }

        mView.displayTotal(mGrandTotal);
    }

    @Override public void saveOrder()
    {

    }

    @Override public void setView(@NonNull MkiosContract.View mkiosView, @Nullable Long id)
    {
        this.mView = checkNotNull(mkiosView);

        start(id);
    }

    private class Order implements Comparable<Order>
    {
        public Product product;
        public double qty;

        public Order(Product product, double qty)
        {
            this.product = product;
            this.qty = qty;
        }

        @Override public boolean equals(Object object)
        {
            return object instanceof Order && this.product.equals(((Order) object).product);
        }

        @Override public int compareTo(@NonNull Order order)
        {
            return this.product.getProduct_id().compareToIgnoreCase(order.product.getProduct_id());
        }
    }
}
