package com.heybo.heybo.type;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Created by aiden on 10/22/15.
 */
public class User {
    private Oauth2AccessToken mAccessToken;

    public User(Oauth2AccessToken token) {
        mAccessToken = token;
    }

    public Oauth2AccessToken getAccessToken() {
        return mAccessToken;
    }

    public String getUid() {
        return mAccessToken.getUid();
    }
}
