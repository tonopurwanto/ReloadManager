package com.neosolusi.reloadmanager.features.pos.grosir;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class StaticLinearLayoutManager extends LinearLayoutManager
{
    public StaticLinearLayoutManager(Context context)
    {
        super(context);
    }

    @Override public boolean canScrollVertically()
    {
        return false;
    }
}
