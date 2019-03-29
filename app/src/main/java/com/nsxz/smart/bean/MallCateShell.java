package com.nsxz.smart.bean;

import java.util.List;

/**
 * Created by gaoshun on 2019/3/27.
 */

public class MallCateShell extends BaseBean{
    public List<MallCateEntity> cates;

    public static class MallCateEntity extends BaseBean {
        /**
         * score : 6
         * name : 特色农产品
         * code : NONGCHANPIN
         * "title": "推荐商品",
         * "subtitle": "推荐商品 超级实惠",
         * "image": "http://ayipic.ayibang.com/07973cb77e0f2cb3dc0542539608dcd3.png"
         */
        public String score;
        public String name;
        public String code;
        public String title;
        public String subtitle;
        public String image;
        public boolean isSelected;
    }
}
