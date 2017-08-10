package com.neosolusi.reloadmanager.features.pos.grosir;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.models.Product;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MkiosAdapter extends RecyclerView.Adapter<MkiosAdapter.ViewHolder>
{
    private List<Product> mDataSet = Collections.emptyList();
    private DecimalFormat mDecimalFormat = new DecimalFormat("#,###");
    private OnItemClickListener mListener;

    public MkiosAdapter(OnItemClickListener listener)
    {
        setHasStableIds(true);

        mListener = listener;
    }

    public void update(List<Product> products)
    {
        this.mDataSet = products;

        notifyDataSetChanged();
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grosir_mkios_item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.bindTo(mDataSet.get(position), mListener);
    }

    @Override public int getItemCount()
    {
        return mDataSet.size();
    }

    @Override public long getItemId(int position)
    {
        return mDataSet.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.textMkiosProdname) TextView mTextProdname;
        @BindView(R.id.textMkiosPrice) TextView mTextPrice;
        @BindView(R.id.editMkiosQty) EditText mEditTextQty;
//        @BindView(R.id.buttonMkiosEdit) ImageButton mBtnEdit;
//        @BindView(R.id.buttonMkiosSave) ImageButton mBtnSave;
//        @BindView(R.id.buttonMkiosClear) ImageButton mBtnClear;

        public ViewHolder(View itemView)
        {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bindTo(final Product product, final OnItemClickListener listener)
        {
            mTextProdname.setText(product.getName());
            mTextPrice.setText(mDecimalFormat.format(product.getPrice()));
            mEditTextQty.setText("0");

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v)
                {
                    listener.onItemClick(v, product);
                }
            });
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, Product product);
    }


}
