package com.nasable.magiclibs.MagicLibs.HTTPClient;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by noras on 2019-02-13.
 */

public abstract class ModernRESTClient {

    private Cache cache;
    private Network network;
    private RequestQueue queue;
    private Context context;
    private List<AdditionalHeader> additionalHeaders;

    ModernRESTClient(Context context) {
        this.context = context;
        cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
        network = new BasicNetwork(new HurlStack());
        queue = new RequestQueue(cache, network);
        queue.start();
        additionalHeaders=new ArrayList<>();
    }

    public class AdditionalHeader{
        private String key;
        private String value;

        public AdditionalHeader(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    public void addHeaders(String key,String value){
        additionalHeaders.add(new AdditionalHeader(key,value));
    }

    public void addBasicAuthorization(String token){
        additionalHeaders.add(new AdditionalHeader("Authorization","Basic "+token));
    }

    public static abstract class RequestListener {
        public void onSuccess(JSONArray jsonArray) {
        }

        ;

        public void onSuccess(JSONObject jsonObject) {
        }

        ;

        public void onSuccess(String responseString) {
        }

        ;

        public abstract void onError();
    }


    private Map<String, String> checkForAdditionalHeaders(){
        Map<String, String> params = new HashMap<String, String>();

        if(additionalHeaders.size()!=0){
            for(AdditionalHeader additionalHeader:additionalHeaders)
                params.put(additionalHeader.key,additionalHeader.value);
        }
        return params;
    }
    public abstract String absoluteURL(String endPoint);

    public void jsonObjectPostRequest(String endPoint, JSONObject params, final RequestListener requestListener) {



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, absoluteURL(endPoint), params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        requestListener.onSuccess(response);
                        Log.d("RestResponse ", " [*] JSONObject: " + response.toString());

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestListener.onError();
            }

        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return checkForAdditionalHeaders();
            }
        };

        queue.add(jsonObjectRequest);
    }


    public void jsonArrayPostRequest(String endPoint, JSONArray params, final RequestListener requestListener) {


        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.POST, absoluteURL(endPoint), params,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        requestListener.onSuccess(response);
                        Log.d("RestResponse ", " [*] JSONArray: " + response.toString());

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestListener.onError();
            }

        }
        );
        queue.add(jsonObjectRequest);
    }


    public void formPostRequest(String endPoint, final Map<String, String> params, final RequestListener requestListener) {

        StringRequest stringRequest = new StringRequest(

                Request.Method.POST,
                absoluteURL(endPoint),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RestResponse ", " [*] String: " + response);
                        requestListener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestListener.onError();
                    }
                }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return checkForAdditionalHeaders();
            }

        };
        queue.add(stringRequest);
    }


    public void formGetRequest(String endPoint, final Map<String, String> params, final RequestListener requestListener) {
        StringRequest stringRequest = new StringRequest(

                Request.Method.GET,
                absoluteURL(endPoint),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RestResponse ", " [*] String: " + response);
                        requestListener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestListener.onError();
                    }
                }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return checkForAdditionalHeaders();
            }

        };
        queue.add(stringRequest);
    }
}
