package com.neosolusi.reloadmanager.features.shared.events.customer;

public class CustomersLoadingErrorEvent
{
    private String message;

    public CustomersLoadingErrorEvent(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
