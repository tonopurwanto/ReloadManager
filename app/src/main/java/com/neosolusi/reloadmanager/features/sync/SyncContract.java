package com.neosolusi.reloadmanager.features.sync;

import android.support.annotation.NonNull;

public interface SyncContract
{
    interface View
    {
        void showMessage(@NonNull String message);
        void showSyncFailed(@NonNull String message);
        void showSyncFinish();
        void changeProgress(int recordCount);
        void resetProgress();
    }

    interface Presenter
    {
        void download();
        void setView(@NonNull SyncContract.View syncView);
        void unsetView();
    }
}