package com.neosolusi.reloadmanager.features.pos.grosir;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.neosolusi.reloadmanager.models.Customer;
import com.neosolusi.reloadmanager.models.Product;

import java.util.List;

public interface MkiosContract
{
    interface View
    {
        void setCustomers(@NonNull List<Customer> customers);
        void setProducts(@NonNull List<Product> products);
        void showOrder(@NonNull List<Product> products);
        void showErrorMessage(@NonNull String message);
        void displayCustomerHp(@NonNull String phone);
        void displayCustomerName(@NonNull String name);
        void displayTotal(@NonNull Double total);
    }

    interface Presenter
    {
        void addProduct(@NonNull Product product, double qty);
        void loadCustomers();
        void loadProducts();
        void calculate(@NonNull Product product, @NonNull Double quantity);
        void saveOrder();
        void setView(@NonNull MkiosContract.View mkiosView, @Nullable Long id);
    }
}
