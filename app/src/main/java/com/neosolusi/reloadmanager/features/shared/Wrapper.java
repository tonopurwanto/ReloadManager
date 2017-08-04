package com.neosolusi.reloadmanager.features.shared;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Wrapper<T>
{
    @SerializedName("message")
    public String loginMessage;

    @SerializedName("current_page")
    public int currentPage;
    @SerializedName("data")
    public List<T> data;
    @SerializedName("from")
    public int fromPage;
    @SerializedName("last_page")
    public int lastPage;
    @SerializedName("next_page_url")
    public String nextPageUrl;
    @SerializedName("per_page")
    public int perPage;
    @SerializedName("prev_page_url")
    public String prevPageUrl;
    @SerializedName("to")
    public int toPage;
    @SerializedName("total")
    public int totalPage;
}
