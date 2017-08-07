package com.neosolusi.reloadmanager.features.pos;

import android.content.Context;
import android.os.Bundle;
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
    private AppCompatActivity mActivity;
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

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((ReloadManager) getActivity().getApplication()).getComponent().inject(this);

        mTransactionAdapter = new TransactionAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        ButterKnife.bind(this, view);

        configureLayout();

        mPresenter.attachView(this);
        mPresenter.loadMenus();

        return view;
    }

    @Override public void onAttach(Context context)
    {
        super.onAttach(context);

        mActivity = (AppCompatActivity) getActivity();
    }

    @Override public void onDetach()
    {
        mPresenter.detachView();

        super.onDetach();
    }

    @Override public void showMenus(List<TransactionItem> menus)
    {
        mTransactionAdapter.update(menus);
    }

    @Override public void showErrorMessage(String message)
    {
        // Empty
    }

    private void configureLayout()
    {
        mActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        setHasOptionsMenu(true);

        mToolbar.setTitle("Pilih Transaksi");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mTransactionAdapter);
        mRecyclerView.setHasFixedSize(true);
    }
}
