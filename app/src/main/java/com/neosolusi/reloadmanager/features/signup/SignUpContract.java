package com.neosolusi.reloadmanager.features.signup;

import com.neosolusi.reloadmanager.features.shared.BasePresenterContract;
import com.neosolusi.reloadmanager.features.shared.BaseViewContract;

public interface SignUpContract
{
    interface View extends BaseViewContract<SignUpContract.Presenter>
    {

    }

    interface Presenter extends BasePresenterContract<SignUpContract.View>
    {

    }
}
