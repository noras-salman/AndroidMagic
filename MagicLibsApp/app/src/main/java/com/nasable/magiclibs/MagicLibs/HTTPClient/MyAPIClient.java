package com.nasable.magiclibs.MagicLibs.HTTPClient;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by noras on 2019-02-13.
 */

public class MyAPIClient extends ModernRESTClient {


    MyAPIClient(Context context) {
        super(context);
    }

    @Override
    public String absoluteURL(String endPoint) {
        return "https://www.mysite.com/";
    }

    public static class User {

        public static  abstract class LoginListener extends ModernRESTClient.RequestListener {

            public abstract void onSuccess(int userId, boolean isAdmin, boolean isCompany, boolean isAgreeWithPersonalTerms, boolean isAgreeWithGeneralTerms, int warehouseId);


            @Override
            public void onSuccess(JSONObject jsonObject) {
                    // TODO : *************************** *************************** ***************************
                    // TODO : *************************** *************************** ***************************
                    //TODO: fix parsing
                    onSuccess(123,false,false,false,false,5);
            }


        }


        public static void login(Context context, String email, String password, LoginListener loginListener){
            ModernRESTClient modernRESTClient=new MyAPIClient(context);
            JSONObject params=new JSONObject();
            try{
                params.put("email",email);
                params.put("password",password);
            }catch (Exception e){}
            modernRESTClient.jsonObjectPostRequest("api/user/login",params,loginListener);
        }


    }




}
