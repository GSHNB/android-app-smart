package com.nsxz.smart.db.dao;

import com.nsxz.smart.bean.User;

import androidx.lifecycle.LiveData;

//@Dao
public interface UserDao {
//    @Insert()
    void save(User user);

//    @Query("select * from user where id=:userId")
    LiveData<User> load(int userId);
}
