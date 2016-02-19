package com.heybo.heybo.data;

import android.content.Context;

import com.heybo.heybo.utils.Constants;
import com.sina.weibo.sdk.auth.AuthInfo;

/**
 * Created by aiden on 11/6/15.
 */
public class WeiboAuthInfo {

    public static AuthInfo getAuthInfo(Context context) {
        AuthInfo info = new AuthInfo(context.getApplicationContext(),
                Constants.APP_KEY,
                Constants.REDIRECT_URL, Constants.SCOPE);

        return info;
    }
}
