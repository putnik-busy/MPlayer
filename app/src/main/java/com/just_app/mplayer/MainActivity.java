package com.just_app.mplayer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private static ArrayList<Model_Melodies.Melodies> melodiesList;
    private Model_Melodies melodies;
    private ListView mListView;
    private AdapterListMelodies adapter;
    public final int LIMIT = 20;
    public final String BASE_URL = "https://api-content-beeline.intech-global.com";
    public int countItems = 0;
    public String JSON_URL = BASE_URL +
            "/public/marketplaces/1/tags/4/melodies/?limit=%d&from=%d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        melodies = new Model_Melodies();
        melodies.setMelodies(melodiesList);
        melodiesList = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.listView1);
        adapter = new AdapterListMelodies(MainActivity.this, melodiesList);
        mListView.setAdapter(adapter);
        customLoadMoreDataFromApi(countItems);
        mListView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(page);
                return true;
            }
        });
    }

    private void customLoadMoreDataFromApi(int page) {
        countItems = LIMIT*page;
        RequestQueue queue = Volley.newRequestQueue(this);
       JsonObjectRequest jsObjRequest = new JsonObjectRequest(
               Request.Method.GET,
                String.format(JSON_URL,LIMIT,countItems),
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    toList(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(jsObjRequest);
    }

    public ArrayList toList(JSONObject response) throws JSONException {
        JSONArray values = response.getJSONArray("melodies");
        for (int i = 0; i < values.length(); i++) {
            JSONObject o = (JSONObject) values.get(i);
            melodiesList.add(new Model_Melodies.Melodies(
                    o.getString("picUrl"), o.getString("title"), o.getString("artist"), o.getString("demoUrl")));
        }
        return melodiesList;
    }

}
