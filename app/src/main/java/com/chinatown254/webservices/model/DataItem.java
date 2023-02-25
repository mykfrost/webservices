package com.chinatown254.webservices.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DataItem implements Parcelable {
    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String ItemName;
    private String category;
    private String description;
    private int sort;
    private double price;
    private String image;
    //private Map<String , Object> additionalProperties = new HashMap<String , Object>();

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ItemName);
        dest.writeString(this.category);
        dest.writeString(this.description);
        dest.writeInt(this.sort);
        dest.writeDouble(this.price);
        dest.writeString(this.image);
    }

    public void readFromParcel(Parcel source) {
        this.ItemName = source.readString();
        this.category = source.readString();
        this.description = source.readString();
        this.sort = source.readInt();
        this.price = source.readDouble();
        this.image = source.readString();
    }

    public DataItem() {
    }

    protected DataItem(Parcel in) {
        this.ItemName = in.readString();
        this.category = in.readString();
        this.description = in.readString();
        this.sort = in.readInt();
        this.price = in.readDouble();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel source) {
            return new DataItem(source);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };
}
