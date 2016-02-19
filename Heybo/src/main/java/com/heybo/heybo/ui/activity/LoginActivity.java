package com.heybo.heybo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.heybo.heybo.R;
import com.heybo.heybo.engine.login.LoginHelper;
import com.heybo.heybo.engine.login.LoginListener;
import com.heybo.heybo.engine.login.LoginResultCode;
import com.heybo.heybo.type.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                LoginHelper.login(LoginActivity.this, new LoginListener() {
                    @Override
                    public void onLoginResult(int code, User user, String errorMsg) {
                        switch (code) {
                            case LoginResultCode.LOGIN_SUCCESS:
                                Heybo.startHeyboMainActivity(LoginActivity.this);
                                LoginActivity.this.finish();
                                break;
                            case LoginResultCode.LOGIN_ERROR_CANCEL:
                                break;
                            case LoginResultCode.LOGIN_ERROR_FIRSTCODE:
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LoginHelper.onActivityResult(requestCode, resultCode, data);
    }

}
