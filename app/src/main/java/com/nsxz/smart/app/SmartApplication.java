package com.nsxz.smart.app;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by gaoshun on 2019/2/21.
 */

public class SmartApplication extends TinkerApplication {
    public SmartApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.nsxz.smart.app.SmartApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
