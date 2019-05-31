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
//        new NetworkBoundResounce<UserDao, UserDao>() {
//            @Override
//            protected void saveCallResult(UserDao item) {
//
//            }
//
//            @Override
//            protected boolean shouldFetch(UserDao data) {
//                return false;
//            }
//
//            @Override
//            protected LiveData<UserDao> loadFromDb() {
//                return null;
//            }
//
//            @Override
//            protected LiveData<ApiResponse<UserDao>> createCall() {
//                return null;
//            }
//        }
    }
}
