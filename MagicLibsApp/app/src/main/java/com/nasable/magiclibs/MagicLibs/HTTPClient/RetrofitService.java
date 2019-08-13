package com.nasable.magiclibs.MagicLibs.HTTPClient;

import com.nasable.magiclibs.MagicLibs.MagicDatabase._ExampleUser;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;



public class RetrofitService {

    public void getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);


        service.groupList(1)
                /* Execute API calls on IO thread */
                .subscribeOn(Schedulers.io())
                /* Receive results on MainThread */
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<List<_ExampleUser.User>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onSuccess(Response<List<_ExampleUser.User>> listResponse) {
                        listResponse.body(); //here are your data
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                });

    }

    public interface GitHubService{
        @GET("group/{id}/users")
        Single<Response<List<_ExampleUser.User>>> groupList(@Path("id") int groupId);

       // @POST("users/new")
       // Call<_ExampleUser.User> createUser(@Body _ExampleUser.User user);
    }
}
