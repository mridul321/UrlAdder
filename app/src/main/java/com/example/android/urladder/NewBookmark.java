package com.example.android.urladder;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewBookmark extends AppCompatActivity {

    private EditText editText,editText1;
    private Button addBookmark;
    private static final String BOOKMARK_REQUEST_URL = "http://10.0.2.2:3000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bookmark);


        addBookmark = findViewById(R.id.add);
        editText = findViewById(R.id.add_folder_name);
        editText1=findViewById(R.id.add_url_name);


        addBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String folderName = editText.getText().toString();
                String urlName = editText1.getText().toString();
                createPost(folderName,urlName);

            }
        });
    }

    //this methods bookmarks a new url by sending post request --------------------------------->

    private void createPost(String folderName,String urlName) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BOOKMARK_REQUEST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJson json = retrofit.create(getJson.class);

          if(!folderName.equals("")){
              Call<ResponseBody> urlCall = json.createPost(folderName,urlName);

              urlCall.enqueue(new Callback<ResponseBody>() {
                  @Override
                  public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                      if(!response.isSuccessful()){
                          Log.i("Code", String.valueOf(response.code()));
                          return;
                      }
                      Toast.makeText(NewBookmark.this, "BookMark Added with response "+ response.code(), Toast.LENGTH_SHORT).show();
                  }

                  @Override
                  public void onFailure(Call<ResponseBody> call, Throwable t) {
                      Log.i("Error",t.getMessage());
                  }
              });
          }


        if(folderName.equals("")){
            Call<ResponseBody> urlDefaultCall = json.createPost("Default folder",urlName);
            urlDefaultCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(!response.isSuccessful()){
                        Log.i("Code", String.valueOf(response.code()));
                        return;
                    }
                    Toast.makeText(NewBookmark.this, "BookMark in Default Folder Added", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.i("Error Message:",t.getMessage());
                }
            });
        }


    }
 //--------------------------------------------------------------------------------------------->

}
