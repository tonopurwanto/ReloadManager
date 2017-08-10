package com.neosolusi.reloadmanager.models;

import android.support.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Product extends RealmObject
{
    @PrimaryKey
    private long id;
    private String product_id;
    private String name;
    private double price;

    public Product()
    {
        // Required empty constructor
    }

    public Product(long id, String product_id, String name, double price)
    {
        this.id = id;
        this.product_id = product_id;
        this.name = name;
        this.price = price;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getProduct_id()
    {
        return product_id;
    }

    public void setProduct_id(String product_id)
    {
        this.product_id = product_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override public boolean equals(Object product)
    {
        return product instanceof Product && this.product_id.equalsIgnoreCase(((Product) product).getProduct_id());
    }
}
