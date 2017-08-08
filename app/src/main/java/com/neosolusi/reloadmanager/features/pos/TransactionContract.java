package com.neosolusi.reloadmanager.features.pos;

import android.support.annotation.NonNull;

import java.util.List;

public interface TransactionContract
{
    interface View
    {
        void showMenus(@NonNull List<TransactionItem> menus);
        void showErrorMessage(@NonNull String message);
    }

    interface Presenter
    {
        void loadMenus();
        void setView(@NonNull TransactionContract.View transactionView);
    }
}
