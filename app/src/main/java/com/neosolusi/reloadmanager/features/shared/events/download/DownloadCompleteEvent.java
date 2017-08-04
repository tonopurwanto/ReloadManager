package com.neosolusi.reloadmanager.features.shared.events.download;

public class DownloadCompleteEvent
{
    private int recordCount;

    public DownloadCompleteEvent(int count)
    {
        this.recordCount = count;
    }

    public int getRecordCount()
    {
        return recordCount;
    }
}
