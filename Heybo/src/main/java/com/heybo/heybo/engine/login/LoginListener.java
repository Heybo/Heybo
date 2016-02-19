package com.heybo.heybo.engine.login;

import com.heybo.heybo.type.User;

/**
 * Created by aiden on 11/6/15.
 */
public interface LoginListener {
    public void onLoginResult(int code, User user, String errorMsg);
}
