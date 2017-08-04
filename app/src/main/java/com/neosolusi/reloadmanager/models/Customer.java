package com.neosolusi.reloadmanager.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Customer extends RealmObject implements Parcelable
{
    @PrimaryKey
    private long id;

    private String custid;
    private String custname;
    private String hpupline;
    private String price;
    private String alamat;
    private String kelurahan;
    private String kecamatan;
    private String kabupaten;
    private Date created_at = new Date();
    private Date updated_at = new Date();

    public Customer()
    {
        // Required empty constructor.
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getCustid()
    {
        return custid;
    }

    public void setCustid(String custid)
    {
        this.custid = custid;
    }

    public String getCustname()
    {
        return custname;
    }

    public void setCustname(String custname)
    {
        this.custname = custname;
    }

    public String getHpupline()
    {
        return hpupline;
    }

    public void setHpupline(String hpupline)
    {
        this.hpupline = hpupline;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getAlamat()
    {
        return alamat;
    }

    public void setAlamat(String alamat)
    {
        this.alamat = alamat;
    }

    public String getKelurahan()
    {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan)
    {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan()
    {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan)
    {
        this.kecamatan = kecamatan;
    }

    public String getKabupaten()
    {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten)
    {
        this.kabupaten = kabupaten;
    }

    public Date getCreated_at()
    {
        return created_at;
    }

    public void setCreated_at(Date created_at)
    {
        this.created_at = created_at;
    }

    public Date getUpdated_at()
    {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at)
    {
        this.updated_at = updated_at;
    }

    @Override public String toString()
    {
        return "Customer{" +
                "custid='" + this.custid + '\'' +
                "custname='" + this.custname + '\'' +
                '}';
    }

    @Override public int describeContents()
    {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeLong(this.id);
        dest.writeString(this.custid);
        dest.writeString(this.custname);
        dest.writeString(this.hpupline);
        dest.writeString(this.price);
        dest.writeString(this.alamat);
        dest.writeString(this.kelurahan);
        dest.writeString(this.kecamatan);
        dest.writeString(this.kabupaten);
    }

    protected Customer(Parcel in)
    {
        this.id = in.readLong();
        this.custid = in.readString();
        this.custname = in.readString();
        this.hpupline = in.readString();
        this.price = in.readString();
        this.alamat = in.readString();
        this.kelurahan = in.readString();
        this.kecamatan = in.readString();
        this.kabupaten = in.readString();
    }

    public static final Parcelable.Creator<Customer> CREATOR = new Parcelable.Creator<Customer>()
    {

        @Override public Customer createFromParcel(Parcel source)
        {
            return new Customer(source);
        }

        @Override public Customer[] newArray(int size)
        {
            return new Customer[size];
        }
    };
}
