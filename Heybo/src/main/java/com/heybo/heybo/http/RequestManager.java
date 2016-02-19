package com.heybo.heybo.http;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.heybo.heybo.R;
import com.heybo.heybo.engine.account.UserManager;
import com.heybo.heybo.utils.Constants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by aiden on 10/21/15.
 */
public class RequestManager {
    public static void startGitHubRequest() {
        String username = "octocat";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<ResponseBody> result = service.listRepos(username);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                try {
                    Log.i("Retrofit", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    // FIXME: 接口需要整理
    public static void sendPhotoWeibo(Activity activity, String uid) {

        // 1. 获取要发送的图片
        Drawable drawable = activity.getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        // 2. 调用接口发送微博
        WeiboParameters params = new WeiboParameters(Constants.APP_KEY);
        params.put("access_token", UserManager.getToken(uid).getToken());
        params.put("status", "通过API发送微博-upload");
        params.put("visible", "0");
        params.put("list_id", "");
        params.put("pic", bitmap);
        params.put("lat", "14.5");
        params.put("long", "23.0");
        params.put("annotations", "");

        AsyncWeiboRunner runner = new AsyncWeiboRunner(activity);
        runner.requestAsync(
                "https://api.weibo.com/2/statuses/upload.json",
                params,
                "POST",
                new RequestListener() {
                    @Override
                    public void onComplete(String s) {

                    }

                    @Override
                    public void onWeiboException(WeiboException e) {

                    }
                });
    }
}
