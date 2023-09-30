package com.appdroid.lokshahiapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.appdroid.lokshahiapplication.Adapter.NewsAdapter;
import com.appdroid.lokshahiapplication.Holder.NewHolder;
import com.appdroid.lokshahiapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowingVillagesData extends AppCompatActivity {
    private static final String TAG = "appdroidTechssss";
    RecyclerView recyclerView;
    LinearLayoutManager manager_corona;
    ImageView back;

    List<NewHolder> newsList;
    public static NewsAdapter adepter;

    public static Boolean isScrolling = true;
    public static int previoustotal;
    int page =1;
    int currentItems, totalItems,scrollOutItems;

    RelativeLayout progress_layout;
    String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_villages_data);

        back = findViewById(R.id.back);
        link = getIntent().getStringExtra("link");

        previoustotal = 0;
        if (isScrolling){
            isScrolling = false;
        }

        Log.d(TAG, "onCreate: "+link);
        recyclerView = findViewById(R.id.recyclerView_corona);
        recyclerView.hasFixedSize();
        manager_corona = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager_corona);

        newsList = new ArrayList<>();
        progress_layout = findViewById(R.id.progress_layout);

        extractPost("https://lokshahilive.com/wp-json/wp/v2/posts?categories=10");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        adepter = new NewsAdapter(newsList,this);
        recyclerView.setAdapter(adepter);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    //   isScrolling = true;
                }
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager_corona.getChildCount();
                totalItems = manager_corona.getItemCount();
                scrollOutItems = manager_corona.findFirstVisibleItemPosition();
                Log.d("appdroidssssssssss", "current itek "+currentItems+" total Item "+totalItems+" scrollout "+scrollOutItems +" is scro "+isScrolling +" previas totoal "+previoustotal);
                if (isScrolling) {
                    if (totalItems > previoustotal) {
                        previoustotal = totalItems;
                        page++;
                        isScrolling = false;
                    }
                }
                if (!isScrolling && (currentItems+scrollOutItems) == totalItems){

                    Log.d("currentItem", "onScrolled: "+currentItems);

                    Log.d("scrollOutItems", "onScrolled: "+scrollOutItems);
                    Log.d("totalItems", "onScrolled: "+totalItems);

                    isScrolling = true;
                    fetchData(totalItems);

                }

            }
        });

    }
    public void extractPost(String URL){

        progress_layout.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("TAG", "onResponse: "+response.length());
                if (response.length() == 0){
                    progress_layout.setVisibility(View.GONE);
                }
                for (int i=0; i < response.length(); i++){
                    try {
                        NewHolder p = new NewHolder();
                        {

                            JSONObject jsonObjectdata = response.getJSONObject(i);
                            //Log.d("date", "onResponse: " + jsonObjectdata.getString("date"));

                            p.setDate(jsonObjectdata.getString("date"));

                            p.setId(jsonObjectdata.getLong("id"));

                            JSONObject titleObject = jsonObjectdata.getJSONObject("title");
                            p.setTitle(titleObject.getString("rendered"));

                            JSONObject shareLinkObj = jsonObjectdata.getJSONObject("guid");
                            p.setLink(shareLinkObj.getString("rendered"));

                            JSONObject contentObject = jsonObjectdata.getJSONObject("content");
                            p.setContent(contentObject.getString("rendered"));

                            JSONObject excerptObject = jsonObjectdata.getJSONObject("excerpt");
                            p.setExcerpt(excerptObject.getString("rendered"));

                            //p.setVideo(jsonObjectdata.getString("video"));

                            p.setFeature_image(jsonObjectdata.getString("jetpack_featured_media_url"));

                            Log.d("SSSSSSSSAAAAA", "onResponse: "+p.getId());
                            adepter.notifyDataSetChanged();
                        }

                        newsList.add(p);
                        progress_layout.setVisibility(View.GONE);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                //loadAdsForAdg();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }

    private void fetchData(int totalItems) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                extractPost("https://lokshahilive.com/wp-json/wp/v2/posts?categories=10"+"&offset="+totalItems);
            }
        },1000);
    }

}