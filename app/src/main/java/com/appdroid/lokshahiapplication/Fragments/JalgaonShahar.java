package com.appdroid.lokshahiapplication.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JalgaonShahar extends Fragment {

    private static final String TAG = "appdroidTech";
    RecyclerView recyclerView;
    LinearLayoutManager manager_jalgaon_shahar;
    List<NewHolder> newsHolderList;
    public static NewsAdapter adapter;
    public static Boolean isScrolling = true;
    public static int previoustotal;
    int page =1;
    int currentItems, totalItems,scrollOutItems;

    RelativeLayout progress_layout;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_jalgaon_shahar, container, false);

        isScrolling = true;
        previoustotal = 0;

        recyclerView=view.findViewById(R.id.recyclerView_corona);
        recyclerView.hasFixedSize();
        manager_jalgaon_shahar=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager_jalgaon_shahar);

        newsHolderList = new ArrayList<>();

        extractPost("https://lokshahilive.com/wp-json/wp/v2/posts?categories=2");
        adapter=new NewsAdapter(newsHolderList,getContext());
        recyclerView.setAdapter(adapter);

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

                currentItems = manager_jalgaon_shahar.getChildCount();
                totalItems = manager_jalgaon_shahar.getItemCount();
                scrollOutItems = manager_jalgaon_shahar.findFirstVisibleItemPosition();

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

        return view;
    }

    private void extractPost(String URL) {


        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("TAG", "onResponse:" + response.length());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        NewHolder p = new NewHolder();
                        {
                            JSONObject jsonObjectdata = response.getJSONObject(i);

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
                            p.setFeature_image(jsonObjectdata.getString("jetpack_featured_media_url"));

                            Log.d("Hello", "onResponse: "+p.getId());
                            adapter.notifyDataSetChanged();
                        }
                        newsHolderList.add(p);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);
    }
    private void fetchData(int totalItems) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                extractPost("https://lokshahilive.com/wp-json/wp/v2/posts?categories=2"+"offset="+totalItems);
            }
        },1000);
    }
}