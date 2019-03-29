package com.nsxz.smart.network;

import com.nsxz.smart.BuildConfig;

/**
 * Created by gaoshun on 2019/3/19.
 */

public class HostManager {
    private static final String host_demo_url = "http://gank.io/api/";
    private static final String host_demo_url1 = "https://api-gateway-cust.ayibang.com";

    private static final String host_online_url = "https://api-gateway-cust.ayibang.com";

    public static String getHost() {
        String url = null;
        switch (BuildConfig.FLAVOR) {
            case "demo":
                url = host_demo_url1;
                break;
            case "online":
                url = host_online_url;
                break;
        }

        return url;
    }
}
