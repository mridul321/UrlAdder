package com.example.android.urladder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private String content;
    private TextView textView;
    private Button addBookmark;
    private Button getBookmark;
    private static final String BOOKMARK_REQUEST_URL = "http://10.0.2.2:3000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        textView = findViewById(R.id.retrofitView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        addBookmark = findViewById(R.id.add_bookmark);
        getBookmark = findViewById(R.id.button);



        addBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewBookmark.class);
                startActivity(intent);
            }
        });

        getBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPosts();
            }
        });
    }


   //this Method sends get request to fetch all bookmarks ------------------------>

    public void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BOOKMARK_REQUEST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getJson json = retrofit.create(getJson.class);
        Call<List<Url>> call = json.getPosts();
        call.enqueue(new Callback<List<Url>>() {
            @Override
            public void onResponse(Call<List<Url>> call, Response<List<Url>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:"+response.code());
                    return;
                }

                textView.setText("");
                List<Url> urls = response.body();
                for(Url urlList: urls){
                      content="";
                    content+= "Folder: "+urlList.getFolderName() + "\n";
                    for(int i=0;i<urlList.getUrl().size();i++){
                        content+="Url" +(i+1)+": "+urlList.getUrl().get(i).getmUrl() + "\n";
                    }
                                    //place a loop that takes all the elements in array
                    content+="\n";
                    textView.append(content);

                }
                Toast.makeText(MainActivity.this, "Bookmarks Fetched with response "+ response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Url>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });


    }
//------------------------------------------------------------------------------------------>



}
