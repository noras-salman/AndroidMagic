package com.nasable.magiclibs.MagicLibs.MagicDatabase.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    //RxJavaExample
    @Query("SELECT * FROM user")
    Single<List<User>> getAllRx();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
