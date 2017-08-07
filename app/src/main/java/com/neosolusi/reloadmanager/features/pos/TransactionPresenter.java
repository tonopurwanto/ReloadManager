package com.neosolusi.reloadmanager.features.pos;

import com.neosolusi.reloadmanager.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionPresenter implements TransactionContract.Presenter
{
    private TransactionContract.View mView;

    @Override public void attachView(TransactionContract.View view)
    {
        this.mView = view;
    }

    @Override public void detachView()
    {
        this.mView = null;
    }

    @Override public void loadMenus()
    {
        List<TransactionItem> menus = new ArrayList<>();
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_info_black_24dp, "Order Stok MKIOS", R.drawable.ic_chevron_right_black_24dp));
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_info_black_24dp, "Transfer Deposit", R.drawable.ic_chevron_right_black_24dp));
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_info_black_24dp, "Penjualan Pulsa MKIOS Reguler", R.drawable.ic_chevron_right_black_24dp));
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_info_black_24dp, "Penjualan Pulsa MKIOS Data", R.drawable.ic_chevron_right_black_24dp));
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_info_black_24dp, "Penjualan Multi Operator", R.drawable.ic_chevron_right_black_24dp));
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_info_black_24dp, "Informasi MKIOS", R.drawable.ic_chevron_right_black_24dp));
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_info_black_24dp, "Pembelian Tiket", R.drawable.ic_chevron_right_black_24dp));

        mView.showMenus(menus);
    }
}
