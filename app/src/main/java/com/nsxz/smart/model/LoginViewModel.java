package com.nsxz.smart.model;

import android.app.Application;

import com.nsxz.smart.bean.User;
import com.nsxz.smart.repository.UserRepository;

//import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class LoginViewModel extends AndroidViewModel {

    private  LiveData<User> userData;
    private UserRepository userRepository;

//    @Inject
    public LoginViewModel(@NonNull Application application, UserRepository userRepository) {
        super(application);
//        userData = new MutableLiveData<>();
        this.userRepository = userRepository;
    }

    public void init(int userID){
        if (userData!=null) {
            return;
        }
//        userData=userRepository.getUser();
    }

    public LiveData<User> getUser() {
        return userData;
    }

}
