package com.example.android.urladder;



import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface getJson {

    @GET("bookmarks")
    Call<List<Url>> getPosts();

    @FormUrlEncoded
    @POST("bookmarks")
    Call<ResponseBody> createPost(@Field("folder_name") String folderName, @Field("url") String url);
}
