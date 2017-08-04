package com.neosolusi.reloadmanager.features.customer;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.models.Customer;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>
{
    private List<Customer> mCustomers = Collections.EMPTY_LIST;

    public CustomerAdapter()
    {
        setHasStableIds(true);
    }

    public void updateCustomers(List<Customer> customers)
    {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallback(mCustomers, customers), false);
        this.mCustomers = customers;
        result.dispatchUpdatesTo(this);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.customer_item_id) TextView textId;
        @BindView(R.id.customer_item_name) TextView textName;

        public ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTo(Customer customer)
        {
            textId.setText(customer.getCustid());
            textName.setText(customer.getCustname());
        }
    }

    public class DiffCallback extends DiffUtil.Callback
    {
        protected List<Customer> oldList, newList;

        public DiffCallback(List<Customer> oldList, List<Customer> newList)
        {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override public int getOldListSize()
        {
            return oldList == null ? 0 : oldList.size();
        }

        @Override public int getNewListSize()
        {
            return newList == null ? 0 : newList.size();
        }

        @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition)
        {
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }

        @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition)
        {
            return true;
        }
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.bindTo(mCustomers.get(position));
    }

    @Override public int getItemCount()
    {
        return mCustomers.size();
    }

    @Override public long getItemId(int position)
    {
        return this.mCustomers.get(position).getId();
    }
}
