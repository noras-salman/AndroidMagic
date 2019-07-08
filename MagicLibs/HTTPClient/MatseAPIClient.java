package se.mat.matse.HTTPClient;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by noras on 2019-02-13.
 */

public class MatseAPIClient {


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
            ModernRESTClient modernRESTClient=new ModernRESTClient(context);
            JSONObject params=new JSONObject();
            try{
                params.put("email",email);
                params.put("password",password);
            }catch (Exception e){}
            modernRESTClient.jsonObjectPostRequest("api/user/login",params,loginListener);
        }


    }

//    public static class ProductCalls {
//
//        public  static abstract  class FavoriteListener extends ModernRESTClient.RequestListener{
//            public abstract void onSuccess(boolean isAgreeWithPersonalTerms, List<Product> favoriteProducts);
//
//
//            @Override
//            public void onSuccess(JSONObject jsonObject) {
//                try {
//                    ObjectMapper mapper = new ObjectMapper();
//                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//                    List<Product> favourites = mapper.readValue(jsonObject.getJSONArray("favourites").toString(), new TypeReference<List<Product>>() { });
//                    onSuccess(jsonObject.getBoolean("isAgreeWithPersonalTerms"),favourites);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    onError();
//                }
//            }
//        }
//
//        public static void getFavoriteProducts(Context context, FavoriteListener favoriteListener){
//            ModernRESTClient modernRESTClient=new ModernRESTClient(context);
//            modernRESTClient.jsonObjectPostRequest("api/favourite/getFavouriteProductsForUser",null,favoriteListener);
//        }
//
//
//        public  static abstract  class ProductListListener extends ModernRESTClient.RequestListener{
//            public abstract void onSuccess(List<Product> products);
//
//
//            @Override
//            public void onSuccess(JSONObject jsonObject) {
//                try {
//                    ObjectMapper mapper = new ObjectMapper();
//                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//                    List<Product> products = mapper.readValue(jsonObject.getJSONArray("products").toString(), new TypeReference<List<Product>>() { });
//                    onSuccess(products);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    onError();
//                }
//            }
//
//        }
//
//        public static void getCampaignProducts(Context context, ProductListListener productListListener){
//            ModernRESTClient modernRESTClient=new ModernRESTClient(context);
//            modernRESTClient.jsonObjectPostRequest("api/product/search?onlyCampaign=1&max=500",null,productListListener);
//        }
//
//        public static void getFeaturedProducts(Context context, ProductListListener productListListener){
//            ModernRESTClient modernRESTClient=new ModernRESTClient(context);
//            modernRESTClient.jsonObjectPostRequest("api/product/search?onlyFeatured=1&max=500",null,productListListener);
//        }
//
//        public static void getCategoryProducts(Context context, int categoryId, ProductListListener productListListener){
//            ModernRESTClient modernRESTClient=new ModernRESTClient(context);
//            modernRESTClient.jsonArrayPostRequest("api/product/listByCategory?categoryId="+categoryId,null,productListListener);
//        }
//
//        /** AUTO PILOT **/
//        public  static abstract  class AutoPilotProductListener extends ModernRESTClient.RequestListener{
//            public abstract void onSuccess(boolean isAgreeWithPersonalTerms, List<Product> favoriteProducts);
//
//            @Override
//            public void onSuccess(JSONObject jsonObject) {
//                try {
//                    ObjectMapper mapper = new ObjectMapper();
//                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//                    // TODO : *************************** *************************** ***************************
//                    // TODO : *************************** *************************** ***************************
//                    // TODO : *************************** *************************** ***************************
//                    // TODO :               {"probability":0.4,"product_id":84154,"quantity":1}
//                    // TODO : *************************** *************************** ***************************
//                    // TODO : *************************** *************************** ***************************
//                    // TODO : *************************** *************************** ***************************
//
//                    List<Product> products = mapper.readValue(jsonObject.getJSONArray("predictedData").toString(), new TypeReference<List<Product>>() { });
//                    onSuccess(jsonObject.getBoolean("isAgreeWithPersonalTerms"),products);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    onError();
//                }
//            }
//        }
//
//        public static void getAutoPilotOrderSuggestions(Context context, AutoPilotProductListener autoPilotProductListener){
//            ModernRESTClient modernRESTClient=new ModernRESTClient(context);
//            modernRESTClient.jsonObjectPostRequest("api/v1.2/recommendation/fetchAutoPilotRecommendations?source=order_suggestions",null,autoPilotProductListener);
//        }
//
//        public static void getAutoPilotCheckoutReminder(Context context, AutoPilotProductListener autoPilotProductListener){
//            ModernRESTClient modernRESTClient=new ModernRESTClient(context);
//            modernRESTClient.jsonObjectPostRequest("api/v1.2/recommendation/fetchAutoPilotRecommendations?source=checkout_reminder",null,autoPilotProductListener);
//        }
//
//
//
//        public  static abstract  class PersonalProductListListener extends ModernRESTClient.RequestListener{
//            public abstract void onSuccess(boolean isAgreeWithPersonalTerms, List<Product> favoriteProducts);
//            @Override
//            public void onSuccess(JSONObject jsonObject) {
//                try {
//                    ObjectMapper mapper = new ObjectMapper();
//                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//                    List<Product> favourites = mapper.readValue(jsonObject.getJSONArray("products").toString(), new TypeReference<List<Product>>() { });
//                    onSuccess(jsonObject.getBoolean("isAgreeWithPersonalTerms"),favourites);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    onError();
//                }
//            }
//        }
//        public static void getProductRecommendations(Context context, int productId, PersonalProductListListener personalProductListListener){
//            ModernRESTClient modernRESTClient=new ModernRESTClient(context);
//            modernRESTClient.jsonObjectPostRequest("api/v1.2/recommendation/fetchProductRecommendations?productId="+productId,null,personalProductListListener);
//        }
//
//        public static abstract class ProductFavoriteSetListener extends ModernRESTClient.RequestListener{
//
//            public abstract void onSuccess(boolean isAgreeWithPersonalTerms,boolean success);
//            @Override
//            public void onSuccess(JSONObject jsonObject) {
//
//                try {
//                    if (!jsonObject.getBoolean("isAgreeWithPersonalTerms")) {
//                         onSuccess(false,false);
//                        return;
//                    }
//                    //ignore exception ..
//                } catch (JSONException e) {}
//
//
//                try {
//                    jsonObject.getInt("totalFavouriteUsers");
//                    onSuccess(true,true);
//                }catch (Exception e){
//                    onError();
//                }
//            }
//        }
//
//        public static void setProductAsFavorite(Context context, int productId, boolean status, ProductFavoriteSetListener productFavoriteSetListener){
//            JSONObject params=new JSONObject();
//            try {
//                params.put("id",productId);
//                params.put("bool",status);
//                }catch (Exception e){}
//
//            ModernRESTClient modernRESTClient=new ModernRESTClient(context);
//            modernRESTClient.jsonObjectPostRequest("api/favourite/setFavourite" ,params,productFavoriteSetListener);
//        }
//
//        public static abstract class ProductBuyListener extends ModernRESTClient.RequestListener{
//            public abstract void onSuccess(int quantityInCart,int totalProducts,double totalAmount);
//            @Override
//            public void onSuccess(JSONObject jsonObject) {
//                try {
//
//                    onSuccess(jsonObject.getInt("quantityInCart"),jsonObject.getInt("totalProducts"),jsonObject.getDouble("totalAmount"));
//                }catch (Exception e){
//                    onError();
//                }
//            }
//        }
//        // ADD OR REMOVE ITEMS FROM CART
//        public static void updateProductQuantity(Context context, int productId, int quantityChange, boolean decrease, ProductBuyListener productBuyListener){
//            JSONObject params=new JSONObject();
//            try {
//                if(decrease)
//                    quantityChange*=-1;
//
//                params.put("productId",productId);
//                params.put("quantity",quantityChange);
//            }catch (Exception e){}
//
//            ModernRESTClient modernRESTClient=new ModernRESTClient(context);
//            modernRESTClient.jsonObjectPostRequest("g/shoppingCart/updateQuantity" ,params,productBuyListener);
//
//        }
//
//    }


}
