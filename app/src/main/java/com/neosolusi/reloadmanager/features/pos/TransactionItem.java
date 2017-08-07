package com.neosolusi.reloadmanager.features.pos;

public class TransactionItem
{
    private long id;
    private int icon;
    private String title;
    private int arrow;

    public TransactionItem(long id, int icon, String title, int arrow)
    {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.arrow = arrow;
    }

    public long getId()
    {
        return id;
    }

    public int getIcon()
    {
        return icon;
    }

    public String getTitle()
    {
        return title;
    }

    public int getArrow()
    {
        return arrow;
    }
}
