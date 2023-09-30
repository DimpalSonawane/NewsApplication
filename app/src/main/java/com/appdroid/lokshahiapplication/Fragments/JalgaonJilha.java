package com.appdroid.lokshahiapplication.Fragments;

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

public class JalgaonJilha extends Fragment {

    private static final String TAG = "appdroidTech";
    RecyclerView recyclerView;
    LinearLayoutManager manager_jalgaon_shahar;
    List<NewHolder> newsList;
    public static NewsAdapter adapter;
    public static Boolean isScrolling = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jalgaon_jilha, container, false);

        isScrolling = true;
        recyclerView = view.findViewById(R.id.recyclerView_jalgoan_jilha);
        recyclerView.hasFixedSize();
        manager_jalgaon_shahar = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager_jalgaon_shahar);

        newsList = new ArrayList<>();

        extractPost("https://lokshahilive.com/wp-json/wp/v2/posts?categories=10");
        adapter = new NewsAdapter(newsList,getContext());
        recyclerView.setAdapter(adapter);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        return view;
    }

    private void extractPost(String URL) {

        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
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

                            p.setFeature_image(jsonObjectdata.getString("jetpack_featured_media_url"));
                            // p.setVideo(jsonObjectdata.getString("video"));


                            Log.d("SSSSSSSSAAAAA", "onResponse: " + p.getId());
                            adapter.notifyDataSetChanged();
                        }

                        newsList.add(p);
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

    private  void fetchData(int totalItems){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                extractPost("https://lokshahilive.com/wp-json/wp/v2/posts?categories=10"+"&offset="+totalItems);
            }
        },1000);
    }
}