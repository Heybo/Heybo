package com.heybo.heybo.engine.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.heybo.heybo.data.WeiboAuthInfo;
import com.heybo.heybo.engine.account.UserManager;
import com.heybo.heybo.type.User;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import java.lang.ref.WeakReference;

/**
 * Created by aiden on 10/22/15.
 */
public class LoginHelper implements WeiboAuthListener {

    private static LoginHelper sInstance;

    private SsoHandler mSsoHandler;
    private WeakReference<LoginListener> mListener;

    public static void login(Activity activity, LoginListener listener) {
        getInstance().mListener = new WeakReference(listener);

        // check token


        // token 失效重新登陆
        //loginByWeb(activity);
        //loginBySso(activity);
        loginAllInOne(activity);
    }

    private static LoginHelper getInstance() {
        if (sInstance == null) {
            synchronized (LoginHelper.class) {
                if (sInstance == null) {
                    sInstance = new LoginHelper();
                }
            }
        }

        return sInstance;
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (getInstance().mSsoHandler != null) {
            getInstance().mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private static void loginByWeb(Activity activity) {
        // 方式一
        getInstance().mSsoHandler = new SsoHandler(activity, WeiboAuthInfo.getAuthInfo(activity));
        getInstance().mSsoHandler.authorizeWeb(getInstance());
    }

    private static void loginBySso(Activity activity) {
        // 方式二
        getInstance().mSsoHandler = new SsoHandler(activity, WeiboAuthInfo.getAuthInfo(activity));
        getInstance().mSsoHandler.authorizeClientSso(getInstance());
    }

    private static void loginAllInOne(Activity activity) {
        // 方式三
        getInstance().mSsoHandler = new SsoHandler(activity, WeiboAuthInfo.getAuthInfo(activity));
        getInstance().mSsoHandler.authorizeClientSso(getInstance());
    }

    @Override
    public void onComplete(Bundle values) {
        // 从 Bundle 中解析 Token
        Oauth2AccessToken accessToken = Oauth2AccessToken.parseAccessToken(values);
        if (accessToken.isSessionValid()) {
            User newUser = new User(accessToken);
            String uid = accessToken.getUid();
            UserManager.addUser(uid, newUser);
            UserManager.setCurrentUser(uid);

            notifyLoginResult(LoginResultCode.LOGIN_SUCCESS, newUser, "");
        } else {
            // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
            String code = values.getString("code", "");
            //.........

            // TODO 确认是否有message返回
            notifyLoginResult(LoginResultCode.LOGIN_ERROR_FIRSTCODE, null, code);
        }
    }

    @Override
    public void onCancel() {
        notifyLoginResult(LoginResultCode.LOGIN_ERROR_CANCEL, null, "用户取消登陆");
    }

    @Override
    public void onWeiboException(WeiboException e) {
    }

    private static void notifyLoginResult(int code, User user, String errorMsg) {
        LoginListener listener = getInstance().mListener.get();
        if (listener == null)
            return;

        listener.onLoginResult(code, user, errorMsg);
    }
}
