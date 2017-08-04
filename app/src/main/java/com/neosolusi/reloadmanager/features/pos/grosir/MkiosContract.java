package com.neosolusi.reloadmanager.features.pos.grosir;

import com.neosolusi.reloadmanager.features.shared.BasePresenterContract;
import com.neosolusi.reloadmanager.features.shared.BaseViewContract;
import com.neosolusi.reloadmanager.models.Customer;
import com.neosolusi.reloadmanager.models.Product;

import java.util.List;

public interface MkiosContract
{
    interface View extends BaseViewContract<Presenter>
    {
        void setCustomers(List<Customer> customers);
        void setProducts(List<Product> products);
        void showError(String message);
    }

    interface Presenter extends BasePresenterContract<View>
    {
        void addProduct(Product product, double qty);
        void loadCustomers();
        void loadProducts();
        void saveOrder();
    }
}
