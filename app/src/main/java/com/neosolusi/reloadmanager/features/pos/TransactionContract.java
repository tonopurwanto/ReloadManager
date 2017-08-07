package com.neosolusi.reloadmanager.features.pos;

import com.neosolusi.reloadmanager.features.shared.BasePresenterContract;
import com.neosolusi.reloadmanager.features.shared.BaseViewContract;

import java.util.List;

public interface TransactionContract
{
    interface View extends BaseViewContract<TransactionContract.Presenter>
    {
        void showMenus(List<TransactionItem> menus);
        void showErrorMessage(String message);
    }

    interface Presenter extends BasePresenterContract<TransactionContract.View>
    {
        void loadMenus();
    }
}
