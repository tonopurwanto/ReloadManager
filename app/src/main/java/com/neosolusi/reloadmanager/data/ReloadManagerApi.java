package com.neosolusi.reloadmanager.data;

import com.neosolusi.reloadmanager.features.shared.Wrapper;
import com.neosolusi.reloadmanager.models.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReloadManagerApi
{
    @FormUrlEncoded
    @POST("login")
    Call<Wrapper> login(@Field("email") String email, @Field("password") String password);

    @GET("customers")
    Call<Wrapper<Customer>> getCustomer(@Query("page") int page);
}