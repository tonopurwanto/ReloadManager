package com.neosolusi.reloadmanager.features.pos;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neosolusi.reloadmanager.R;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>
{
    private List<TransactionItem> mDataSet = Collections.emptyList();
    private Context mContext;

    public TransactionAdapter(Context context)
    {
        setHasStableIds(true);

        this.mContext = context;
    }

    public void update(List<TransactionItem> menus)
    {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallback(this.mDataSet, menus), false);

        this.mDataSet = menus;

        result.dispatchUpdatesTo(this);
    }

    @Override public TransactionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(TransactionAdapter.ViewHolder holder, int position)
    {
        holder.bindTo(mDataSet.get(position));
    }

    @Override public int getItemCount()
    {
        return mDataSet.size();
    }

    @Override public long getItemId(int position)
    {
        return this.mDataSet.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.icon) ImageView mIcon;
        @BindView(R.id.title) TextView mTitle;
        @BindView(R.id.arrow) ImageView mArrow;

        public ViewHolder(View itemView)
        {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bindTo(TransactionItem menu)
        {
            mIcon.setImageDrawable(ActivityCompat.getDrawable(mContext, menu.getIcon()));
            mTitle.setText(menu.getTitle());
            mArrow.setImageDrawable(ActivityCompat.getDrawable(mContext, menu.getArrow()));
        }
    }

    public class DiffCallback extends DiffUtil.Callback
    {
        private List<TransactionItem> oldItems, newItems;

        public DiffCallback(List<TransactionItem> oldItems, List<TransactionItem> newItems)
        {
            this.oldItems = oldItems;
            this.newItems = newItems;
        }

        @Override public int getOldListSize()
        {
            return oldItems.size();
        }

        @Override public int getNewListSize()
        {
            return newItems.size();
        }

        @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition)
        {
            return oldItems.get(newItemPosition).equals(newItems.get(newItemPosition));
        }

        @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition)
        {
            return true;
        }
    }
}
