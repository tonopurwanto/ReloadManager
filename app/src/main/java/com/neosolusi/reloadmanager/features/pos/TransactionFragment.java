package com.neosolusi.reloadmanager.features.pos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.ReloadManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionFragment extends Fragment implements TransactionContract.View
{
    private TransactionAdapter mTransactionAdapter;

    @Inject TransactionContract.Presenter mPresenter;

    @BindView(R.id.toolbarTransaction) Toolbar mToolbar;
    @BindView(R.id.listMenus) RecyclerView mRecyclerView;

    public TransactionFragment()
    {
        // Required empty public constructor
    }

    public static TransactionFragment getInstance()
    {
        return new TransactionFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((ReloadManager) getActivity().getApplication()).getComponent().inject(this);

        mTransactionAdapter = new TransactionAdapter(getContext());
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        ButterKnife.bind(this, view);

        configureLayout();

        return view;
    }

    @Override public void onResume()
    {
        super.onResume();

        mPresenter.setView(this);
    }

    @Override public void showMenus(@NonNull List<TransactionItem> menus)
    {
        mTransactionAdapter.update(menus);
    }

    @Override public void showErrorMessage(@NonNull String message)
    {
        // Empty
    }

    private void configureLayout()
    {
        AppCompatActivity mActivity = (AppCompatActivity) getActivity();
        mActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Pilih Transaksi");
        }
        setHasOptionsMenu(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mTransactionAdapter);
        mRecyclerView.setHasFixedSize(true);
    }
}
