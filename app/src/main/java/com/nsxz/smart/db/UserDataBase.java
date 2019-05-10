package com.nsxz.smart.db;

import com.nsxz.smart.db.dao.UserDao;

//import androidx.room.RoomDatabase;

//@Database(entities = {User.class}, version = 1)
public abstract class UserDataBase  {
    public abstract UserDao userDao();
}
