package com.neosolusi.reloadmanager.features.shared;

public interface BasePresenterContract<T>
{
    void attach(T view);
    void detach();
    void start();
}
