package com.neosolusi.reloadmanager.features.pos;

import java.util.List;

public interface TransactionContract
{
    interface View
    {
        void showMenus(List<TransactionItem> menus);
        void showErrorMessage(String message);
    }

    interface Presenter
    {
        void attachView(TransactionContract.View view);
        void detachView();
        void loadMenus();
    }
}
