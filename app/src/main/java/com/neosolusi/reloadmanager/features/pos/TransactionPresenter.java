package com.neosolusi.reloadmanager.features.pos;

import android.support.annotation.NonNull;

import com.neosolusi.reloadmanager.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public class TransactionPresenter implements TransactionContract.Presenter
{
    private TransactionContract.View mView;

    @Override public void loadMenus()
    {
        List<TransactionItem> menus = new ArrayList<>();
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_add_shopping_cart_black_24dp, "Order Stok MKIOS", R.drawable.ic_chevron_right_black_24dp));
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_monetization_on_black_24dp, "Transfer Deposit", R.drawable.ic_chevron_right_black_24dp));
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_payment_black_24dp, "Penjualan Pulsa MKIOS Reguler", R.drawable.ic_chevron_right_black_24dp));
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_swap_vertical_circle_black_24dp, "Penjualan Pulsa MKIOS Data", R.drawable.ic_chevron_right_black_24dp));
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_device_hub_black_24dp, "Penjualan Multi Operator", R.drawable.ic_chevron_right_black_24dp));
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_info_black_24dp, "Informasi MKIOS", R.drawable.ic_chevron_right_black_24dp));
        menus.add(new TransactionItem(UUID.randomUUID().getMostSignificantBits() + Long.MAX_VALUE, R.drawable.ic_airplanemode_active_black_24dp, "Pembelian Tiket", R.drawable.ic_chevron_right_black_24dp));

        if (menus.isEmpty()) {
            mView.showErrorMessage("Menu not found");
        } else {
            mView.showMenus(menus);
        }
    }

    @Override public void setView(@NonNull TransactionContract.View transactionView)
    {
        this.mView = checkNotNull(transactionView);

        loadMenus();
    }
}