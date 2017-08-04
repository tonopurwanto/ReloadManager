package com.neosolusi.reloadmanager.features.shared;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ActivityUtils
{
    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId)
    {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.add(frameId, fragment);
        transaction.commit();
    }
}
