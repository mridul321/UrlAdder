package com.example.android.urladder;

import com.google.gson.annotations.SerializedName;

public class UrlList {

    @SerializedName("url")
    private String mUrl;



    public String getmUrl() { return mUrl; }



    @Override
    public String toString() {
        return "UrlList{" +
                "mUrl='" + mUrl + '\'' +
                '}';
    }
}
