package com.nsxz.smart.repository;

import com.nsxz.smart.app.AppExecutors;
import com.nsxz.smart.db.dao.UserDao;
import com.nsxz.smart.network.request.Request;

import javax.inject.Singleton;


@Singleton
public class UserRepository {
    Request request;
    UserDao userDao;
    AppExecutors executors;

    public UserRepository(Request request, UserDao userDao, AppExecutors executor) {
        this.request = request;
        this.userDao = userDao;
        this.executors = executor;
    }

//    public LiveData<User> getUser(int userID){
//        refreshUser(userID);
//
//        return userDao.load(userID);
//    }

    private void refreshUser(final int userID){

    }
}
