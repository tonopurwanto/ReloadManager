package com.neosolusi.reloadmanager.features.pos.grosir;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.models.Customer;
import com.neosolusi.reloadmanager.models.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class MkiosFragment extends Fragment implements MkiosContract.View
{
    private MkiosAdapter mOrderAdapter;
    private double mGrandTotal;
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");

    @Inject MkiosContract.Presenter mPresenter;

    @BindView(R.id.toolbarMkiosGrosir) Toolbar mToolbar;
    @BindView(R.id.editTextCustid) EditText mTextCustid;
    @BindView(R.id.editTextCustname) EditText mTextCustname;
    @BindView(R.id.recyclerOrder) RecyclerView mRecyclerOrder;
    @BindView(R.id.textMkiosTotal) TextView mTextTotal;

    public MkiosFragment()
    {
        // Required empty public constructor
    }

    public static MkiosFragment getInstance()
    {
        return new MkiosFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((ReloadManager) getActivity().getApplication()).getComponent().inject(this);

        mOrderAdapter = new MkiosAdapter(new MkiosAdapter.OnItemClickListener()
        {
            @Override public void onItemClick(View view, final Product product)
            {
                final EditText qty = (EditText) view.findViewById(R.id.editMkiosQty);
                qty.setFocusable(true);
                qty.setFocusableInTouchMode(true);
                qty.requestFocus();

                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(MkiosActivity.INPUT_METHOD_SERVICE);
                imm.showSoftInput(qty, 0);

                qty.addTextChangedListener(new TextWatcher()
                {
                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after)
                    {

                    }

                    @Override public void onTextChanged(CharSequence s, int start, int before, int count)
                    {

                    }

                    @Override public void afterTextChanged(Editable s)
                    {
                        if (TextUtils.isEmpty(s)) {
                            qty.setText("0");
                        } else if (! TextUtils.isDigitsOnly(s)) {
                            qty.setText("0");
                        }

                        mPresenter.calculate(product, Double.valueOf(qty.getText().toString()));
//
//                        double subTotal = product.getPrice() * Double.valueOf(qty.getText().toString());
//
//                        mGrandTotal += subTotal;
//
//                        mTextTotal.setText("Total " + decimalFormat.format(mGrandTotal));
                    }
                });
            }
        });
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_mkios, container, false);

        ButterKnife.bind(this, view);

        configureLayout();

        return view;
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.grosir_mkios, menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.action_done:
                Snackbar.make(getActivity().findViewById(R.id.contentGrosirMkios), "Done", Snackbar.LENGTH_LONG).show();
        }

        return true;
    }

    private void configureLayout()
    {
        AppCompatActivity mActivity = (AppCompatActivity) getActivity();
        mActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Order Stok MKIOS Grosir");
        }
        setHasOptionsMenu(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        mRecyclerOrder.setLayoutManager(layoutManager);
        mRecyclerOrder.addItemDecoration(new DividerItemDecoration(mRecyclerOrder.getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerOrder.setHasFixedSize(true);
        mRecyclerOrder.setAdapter(mOrderAdapter);

        mRecyclerOrder.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                return false;
            }
        });
    }

    @Override public void onResume()
    {
        super.onResume();

        long id = getActivity().getIntent().getLongExtra(MkiosActivity.EXTRA_CUSTOMER_ID, 0);

        mPresenter.setView(this, id);
    }

    @Override public void setCustomers(@NonNull List<Customer> customers)
    {

    }

    @Override public void setProducts(@NonNull List<Product> products)
    {

    }

    @Override public void showOrder(@NonNull List<Product> products)
    {
        mOrderAdapter.update(products);
    }

    @Override public void showErrorMessage(@NonNull String message)
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Failed")
                .setMessage(message)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_error_outline_red_700_24dp))
                .show();
    }

    @Override public void displayCustomerHp(@NonNull String phone)
    {
        mTextCustid.setText(checkNotNull(phone));

    }

    @Override public void displayCustomerName(@NonNull String name)
    {
        mTextCustname.setText(checkNotNull(name));
    }

    @Override public void displayTotal(@NonNull Double total)
    {
        mTextTotal.setText("Total " + decimalFormat.format(total));
    }
}
