package com.neosolusi.reloadmanager.features.pos;

import java.util.List;

public interface TransactionContract
{
    interface View
    {
        void showMenus(List<TransactionItem> menus);
        void showErrorMessage(String message);
    }

    interface Persenter
    {
        void attachView(TransactionContract.View customerView);
        void detachView();
        void loadMenus();
    }
}
