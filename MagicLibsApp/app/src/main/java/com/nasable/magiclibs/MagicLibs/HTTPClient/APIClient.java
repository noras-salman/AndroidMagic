package com.nasable.magiclibs.MagicLibs.HTTPClient;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Example
 */

public class APIClient extends ModernRESTClient {


    APIClient(Context context) {
        super(context);
    }



    @Override
    public String getHostAddress() {
        return null;
    }




}
