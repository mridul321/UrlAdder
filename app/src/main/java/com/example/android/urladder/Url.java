package com.example.android.urladder;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Url {

    @SerializedName("folder_name")
    private String mUrlFolder;

    @SerializedName("urls")
    private ArrayList<UrlList> urlLists;


// getters --------------->

    public String getFolderName(){ return mUrlFolder; }
    public ArrayList<UrlList> getUrl(){ return urlLists; }

//--------------------------->
    @Override
    public String toString() {
        return "Url{" +
                "mUrlFolder='" + mUrlFolder + '\'' +
                ", urlLists=" + urlLists +
                '}';
    }
}
