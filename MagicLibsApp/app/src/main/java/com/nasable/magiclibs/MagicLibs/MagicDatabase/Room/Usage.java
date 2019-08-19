package com.nasable.magiclibs.MagicLibs.MagicDatabase.Room;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Usage {

    public AppDatabase getInstance(Context context){
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "database-name").build();

        /*Normal*/
        //db.userDao().getAll();

        /*RxJava*/
        /*
        db.userDao().getAllRx()
                .subscribeOn(Schedulers.io())         // Execute API calls on IO thread
                .observeOn(AndroidSchedulers.mainThread()) // Receive results on MainThread
                .subscribe(new SingleObserver<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onSuccess(List<User> listResponse) {
                        listResponse.size(); //here are your data
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                });

                */
        return db;
    }
}
