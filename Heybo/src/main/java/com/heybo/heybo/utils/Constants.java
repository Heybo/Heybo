package com.heybo.heybo.utils;

/**
 * Created by aiden on 11/6/15.
 */
public class Constants {
    /** 当前应用的 APP_KEY */
    public static final String APP_KEY = "2981134127";

    /**
     * 应用秘钥
     */
    public static final String APP_SC = "d049350ac48171f3997aab4f97499c28";

    /**
     * 应用签名
     */
    public static final String APP_SIGN = "0ec776cb4da03771fc826683a4372bc8";

    /**
     * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
     * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
     */
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

    /**
     * WeiboSDKDemo 应用对应的权限，第三方开发者一般不需要这么多，可直接设置成空即可。
     * 详情请查看 Demo 中对应的注释。
     */
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";
}
