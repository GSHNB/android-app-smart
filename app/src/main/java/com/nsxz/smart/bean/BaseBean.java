package com.nsxz.smart.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by gaoshun on 2018/12/21.
 */

public class BaseBean implements Serializable {

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
