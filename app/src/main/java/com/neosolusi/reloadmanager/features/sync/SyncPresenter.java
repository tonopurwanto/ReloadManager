package com.neosolusi.reloadmanager.features.sync;

import android.support.annotation.NonNull;

import com.neosolusi.reloadmanager.features.shared.download.DownloadContract;
import com.neosolusi.reloadmanager.features.shared.events.download.DownloadCompleteEvent;
import com.neosolusi.reloadmanager.features.shared.events.download.DownloadFailedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SyncPresenter implements SyncContract.Presenter
{
    private SyncContract.View mSyncView;
    private EventBus mEventBus;
    private Iterator<DownloadContract> mDataSyncIterator;

    public SyncPresenter(@NonNull List<DownloadContract> dataSync, @NonNull EventBus bus)
    {
        this.mDataSyncIterator = checkNotNull(dataSync.iterator());
        this.mEventBus = checkNotNull(bus);
    }

    @Override public void download()
    {
        if (mDataSyncIterator.hasNext()) {
            DownloadContract service = mDataSyncIterator.next();

            mSyncView.resetProgress();
            mSyncView.showMessage(service.tag());

            service.process();
        } else {
            mSyncView.showSyncFinish();
        }
    }

    @Override public void setView(@NonNull SyncContract.View syncView)
    {
        this.mSyncView = checkNotNull(syncView);

        this.mEventBus.register(this);

        download();
    }

    @Override public void unsetView()
    {
        mSyncView = null;

        mEventBus.unregister(this);
    }

    @Subscribe public void onEvent(DownloadCompleteEvent event)
    {
        mSyncView.changeProgress(event.getRecordCount());

        download();
    }

    @Subscribe public void onEvent(DownloadFailedEvent event)
    {
        mSyncView.showSyncFailed(event.getMessage());
    }
}
