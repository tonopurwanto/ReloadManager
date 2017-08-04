package com.neosolusi.reloadmanager.features.sync;

import com.neosolusi.reloadmanager.features.shared.download.DownloadContract;
import com.neosolusi.reloadmanager.features.shared.events.download.DownloadCompleteEvent;
import com.neosolusi.reloadmanager.features.shared.events.download.DownloadFailedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Iterator;
import java.util.List;

public class SyncPresenter implements SyncContract.Presenter
{
    private SyncContract.View mSyncView;
    private EventBus mEventBus;
    private Iterator<DownloadContract> mDataSyncIterator;

    public SyncPresenter(List<DownloadContract> dataSync, EventBus bus)
    {
        this.mDataSyncIterator = dataSync.iterator();
        this.mEventBus = bus;
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

    @Override public void attachView(SyncContract.View syncView)
    {
        mSyncView = syncView;

        mEventBus.register(this);
    }

    @Override public void detachView()
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
