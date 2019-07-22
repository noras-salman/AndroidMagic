package com.nasable.magiclibs.MagicLibs.HTTPClient;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONObject;

public abstract  class ModernRestClient extends ModernHTTPClient{


    /**
     * @param context
     */
    ModernRestClient(Context context) {
        super(context);
    }

    /**
     *
     * @param endPoint
     * @param params
     * @param onJSONObjectSuccessListener
     */
    public void post(String endPoint, JSONObject params, RequestListener.OnJSONObjectSuccessListener onJSONObjectSuccessListener){
       restObjectRequest(Request.Method.POST,endPoint,params,onJSONObjectSuccessListener);
    }

    /**
     *
     * @param endPoint
     * @param params
     * @param onJSONObjectSuccessListener
     */
    public void put(String endPoint, JSONObject params, RequestListener.OnJSONObjectSuccessListener onJSONObjectSuccessListener){
        restObjectRequest(Request.Method.PUT,endPoint,params,onJSONObjectSuccessListener);
    }

    /**
     *
     * @param endPoint
     * @param params
     * @param onJSONObjectSuccessListener
     */
    public void delete(String endPoint, JSONObject params, RequestListener.OnJSONObjectSuccessListener onJSONObjectSuccessListener){
        restObjectRequest(Request.Method.DELETE,endPoint,params,onJSONObjectSuccessListener);
    }

    /**
     *
     * @param endPoint
     * @param onJSONObjectSuccessListener
     */
    public void get(String endPoint,  RequestListener.OnJSONObjectSuccessListener onJSONObjectSuccessListener){
        restObjectRequest(Request.Method.GET,endPoint ,null,onJSONObjectSuccessListener);
    }

}
