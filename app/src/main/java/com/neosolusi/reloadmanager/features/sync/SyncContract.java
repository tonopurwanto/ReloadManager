package com.neosolusi.reloadmanager.features.sync;

public interface SyncContract
{
    interface View
    {
        void showMessage(String message);
        void showSyncFailed(String message);
        void showSyncFinish();
        void changeProgress(int recordCount);
        void resetProgress();
    }

    interface Presenter
    {
        void download();
        void attachView(SyncContract.View syncView);
        void detachView();
    }
}