package com.heybo.heybo.engine.account;

import com.heybo.heybo.type.User;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aiden on 11/6/15.
 */
public class UserManager {
    private static UserManager sInstance;

    private Map<String, User> mUserMap;
    private User mCurrentUser;

    private static UserManager getInstance() {
        if (sInstance == null) {
            synchronized (UserManager.class) {
                if (sInstance == null) {
                    sInstance = new UserManager();
                }
            }
        }

        return sInstance;
    }

    private UserManager() {
        mUserMap = new HashMap();
    }

    public static void addUser(String uid, User user) {
        Map map = getInstance().mUserMap;
        if (map.containsKey(uid)) return;

        getInstance().mUserMap.put(uid, user);

        // 保存 Token 到 SharedPreferences
        //AccessTokenKeeper.writeAccessToken(WBAuthActivity.this, accessToken);
        //.........
    }

    public static Oauth2AccessToken getToken(String uid) {
        Map map = getInstance().mUserMap;
        User user = (User) map.get(uid);
        return user == null ? null : user.getAccessToken();
    }

    public static boolean setCurrentUser(String uid) {
        Map map = getInstance().mUserMap;
        if (!map.containsKey(uid)) {
            return false;
        }

        User user = (User) map.get(uid);
        if (user == null) {
            return false;
        }

        getInstance().mCurrentUser = user;
        return true;
    }

    public static User getCurrentUser() {
        return getInstance().mCurrentUser;
    }

}
