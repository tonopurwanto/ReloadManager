package com.neosolusi.reloadmanager.features.shared.events.download;

public class DownloadFailedEvent
{
    private String message;

    public DownloadFailedEvent(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
