package com.nsxz.smart.bean;

import java.util.List;

/**
 * Created by gaoshun on 2019/3/20.
 */

public class Aa extends BaseBean{

    /**
     * error : false
     * results : [{"_id":"5c6a4ae99d212226776d3256","createdAt":"2019-02-18T06:04:25.571Z","desc":"2019-02-18","publishedAt":"2019-02-18T06:05:41.975Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg","used":true,"who":"lijinshanmx"},{"_id":"5c6385b39d21225dd7a417ce","createdAt":"2019-02-13T02:49:23.946Z","desc":"2019-02-13","publishedAt":"2019-02-13T02:49:33.16Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1g04lsmmadlj31221vowz7.jpg","used":true,"who":"lijinshanmx"},{"_id":"5c4578db9d212264d4501d40","createdAt":"2019-01-21T07:46:35.304Z","desc":"2019-01-21","publishedAt":"2019-01-21T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqgy1fze94uew3jj30qo10cdka.jpg","used":true,"who":"lijinshanmx"}]
     */

    public boolean error;
    public List<ResultsBean> results;

    public static class ResultsBean extends BaseBean{
        /**
         * _id : 5c6a4ae99d212226776d3256
         * createdAt : 2019-02-18T06:04:25.571Z
         * desc : 2019-02-18
         * publishedAt : 2019-02-18T06:05:41.975Z
         * source : web
         * type : 福利
         * url : https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg
         * used : true
         * who : lijinshanmx
         */

        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
    }



}
